package com.payroll.mapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payroll.constant.PtgGatewayConstants;
import com.payroll.entity.EarnCode;
import com.payroll.entity.PayrollEarningRecord;
import com.payroll.entity.PayrollEmployeeRecord;
import com.payroll.entity.PayrollEmployeeRecordVW;
import com.payroll.entity.PayrollError;
import com.payroll.entity.pk.PayrollErrorKey;
import com.payroll.utils.PrimitiveCompare;
import com.ptg.payroll.domain.Department;
import com.ptg.payroll.domain.Employee;
import com.ptg.payroll.domain.EmployeeLeaveBalance;
import com.ptg.payroll.domain.payroll.Amount;
import com.ptg.payroll.domain.payroll.CheckDetail;
import com.ptg.payroll.domain.payroll.DepartmentEarning;
import com.ptg.payroll.domain.payroll.EarnUnit;
import com.ptg.payroll.domain.payroll.EmployeePayLineItem;
import com.ptg.payroll.domain.payroll.Hours;
import com.ptg.payroll.domain.payroll.LeaveBalance;
import com.ptg.payroll.domain.payroll.OverridePayRate;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.domain.payroll.PayrollEmployeeError;
import com.ptg.payroll.domain.payroll.RecurringEarningDistribution;
import com.ptg.payroll.domain.payroll.UnitPay;



public class PayrollDataEntryMapper {
    private static String SOURCE_GRID = "Grid";
    private static String SOURCE_ERFT = "ERFT";
    private static String SOURCE_RECR = "RECR";
    private static final String ERROR_DEPT = "department_error";
    private static final String ERROR_EMPL = "employee_error";
    private static final String ERROR_ERNCD = "earningcode_error";
    private static final String RECURRING_EARNING_WITH_JED = "J";
    private static final Logger log = LoggerFactory.getLogger(PayrollDataEntryMapper.class);

    /*****************************************************************************/
    /************************    DomainToAdditionalPayRequests    ****************/
    /*****************************************************************************/
    public static class DomainToAdditionalPayRequests {
        private PayGroup payGroup;

        public DomainToAdditionalPayRequests(PayGroup payGroup) {
            this.payGroup = payGroup;
        }
    }
    
    
    /*****************************************************************************/
    /************************    EntityToDomainMapper    *************************/
    /*****************************************************************************/
   
    public static class EntityToDomainMapper{
        private PayGroup payGroup;
        private String defaultRegEarnCode;
        // Key of EmployeeID, EmployeePayLineItem
        private Map<String, EarnCode> earnCodeMap = new HashMap<String, EarnCode>();
        private Map<String, String> employeesPaymentMethod = new HashMap<String, String>();
        private Map<String, List<LeaveBalance>> employeesLeaveBalances = new HashMap<String, List<LeaveBalance>>();
        private Map<String, List<CheckDetail>> employeesCheckDetails = new HashMap<String, List<CheckDetail>>();
        private Map<String, List<OverridePayRate>> employeeOverridePayRateMap = new HashMap<String, List<OverridePayRate>>();
        

        public EntityToDomainMapper(List<EarnCode> earningCodeList, Map<String, String> paymentMethods, List<EmployeeLeaveBalance> balances, PayGroup payGroup,
                String defaultRegularEarnCode) {
            this.payGroup = payGroup;
            this.defaultRegEarnCode = defaultRegularEarnCode;
            for (EarnCode earnCode : earningCodeList) {
                earnCodeMap.put(earnCode.getCode(), earnCode);
            }
            employeesPaymentMethod.putAll(paymentMethods);

            for (EmployeeLeaveBalance balance : balances) {
                List<LeaveBalance> leaveBalanceList = employeesLeaveBalances.get(balance.getEmployeeId());
                if (leaveBalanceList == null) {
                    leaveBalanceList = new ArrayList<LeaveBalance>();
                    employeesLeaveBalances.put(balance.getEmployeeId(), leaveBalanceList);
                }
                LeaveBalance leaveBalance = new LeaveBalance();
                leaveBalance.setEarnCode(balance.getEarnCode());
                leaveBalance.setBalanceHours(balance.getBalanceHours());
                leaveBalanceList.add(leaveBalance);
            }
        }

        public List<EmployeePayLineItem> translate(List<PayrollEmployeeRecordVW> employeeRecords, List<PayrollEarningRecord> earningRecords, boolean jobCostingEnabled) {
            LinkedHashMap<String, List<EmployeePayLineItem>> employeePayLineRootMap = new LinkedHashMap<String, List<EmployeePayLineItem>>();

            // Constructs EmployeePayLineItems from the EmployeeRecords.
            for (PayrollEmployeeRecordVW employeeRecord : employeeRecords) {
                constructAndUpdatePayLineItem(employeePayLineRootMap, employeeRecord);
            }
            // Update the position for each of the EmployeePayLineItems
            if(isOnCycle(payGroup)){
            	for (List<EmployeePayLineItem> employees : employeePayLineRootMap.values()) {
                    for (int i = 0; i < employees.size(); i++) {
                        EmployeePayLineItem employee = employees.get(i);
                        if(employee.getStartDate().before(this.payGroup.getPayBeginDate())) {
                        	employee.setPosition(i + 1);
                        } else if(employee.getStartDate().getTime() == this.payGroup.getPayBeginDate().getTime()) {
                        	employee.setPosition(0);
                        } else {
                        	employee.setPosition(i);
                        }
                    }
                }
            } 

            for (PayrollEarningRecord earningRecord : earningRecords) {
                constructAndUpdateEarnUnits(employeePayLineRootMap, earningRecord,jobCostingEnabled);
                if(isOnCycle(payGroup)){
                	parseAndFillCheckDetails(employeePayLineRootMap, earningRecord);
                } else {
                	updateCheckDetails(employeePayLineRootMap, earningRecord);
                }
            }

            List<EmployeePayLineItem> payLineItems = new ArrayList<EmployeePayLineItem>();
            for (List<EmployeePayLineItem> items : employeePayLineRootMap.values()) {
               removeRecurringDepartmentEarnings(items);
            	payLineItems.addAll(items);
            }
            updateEmployeePayLineItemWithAdditionalData(payLineItems);
            return payLineItems;
        }
        
        
        /**
         * Remove department earnings for all the department with source RECR from departmentEarnings
         * */
        private void removeRecurringDepartmentEarnings(
        		List<EmployeePayLineItem> items) {
        	for (EmployeePayLineItem employeePayLineItem : items) {
        		Iterator<DepartmentEarning> departmentEarnings = employeePayLineItem.getDepartmentEarnings().iterator();
        		while (departmentEarnings.hasNext()) {
        			DepartmentEarning departmentEarning = departmentEarnings.next();   
        			if(SOURCE_RECR.equals(departmentEarning.getDepartment().getSource())) {
        				departmentEarnings.remove();;
        			}else {
        				//iterate through all other earning and delete anything with jedRecurring=true/J and source=RECR
        				List<Amount> otherEarnings = departmentEarning.getOtherEarnings();
        				Iterator<Amount> otherEarningIterator = otherEarnings.iterator();
        				while (otherEarningIterator.hasNext()) {
        					Amount amount = otherEarningIterator.next();
        					if(RECURRING_EARNING_WITH_JED.equals(amount.getJedRecurring())){
        						otherEarningIterator.remove();
        					}
        				}

        			}
        		}
        	}
        }

		/**
         * <pre>
         * Biz Rules 
         *  1. Earn Code = ( "01" or "REG" ) - Hours -> Hours 
         *  2. PaymentType is 'A' or 'F' - Amount -> otherEarnings 
         *  3. PaymentType is 'H' - Hours -> otherHours 
         *  4. PaymentType is 'U' - UnitPay -> UnitsPay
         *  5. PaymentType is 'B' or 'E' - Hours & Amount
         * 
         * </pre>
         * 
         * @param employee
         * @param department
         * @param dbEmployeeEntry
         */

        private void constructAndUpdateEarnUnits(LinkedHashMap<String, List<EmployeePayLineItem>> employeePayLineRootMap, PayrollEarningRecord earningRecord, boolean jobCostingEnabled) {
            String earnCodeString = earningRecord.getEarnCode();
            EarnCode earnCode = earnCodeMap.get(earnCodeString);
            if (earnCode == null) {
            	logError("Earning code is not exists :: " + earnCodeString);
            	List<EmployeePayLineItem> payLineItems = employeePayLineRootMap.get(earningRecord.getEmplId());
            	this.addErrorRecord(payLineItems.get(0), earningRecord.getEmplId(), earningRecord.getDeptId(), earnCodeString, ERROR_ERNCD);;
            }
            EmployeePayLineItem payLineItem = lookupEmployeePayLineItem(employeePayLineRootMap, earningRecord);
            // HACK: If earning code is deleted, It will not present in employee earnings.
            String paymentType = earnCode != null ? earnCode.getEnrCdPaymentType() : "";
            DepartmentEarning deptEarning = lookupDepartmentEarning(employeePayLineRootMap, earningRecord,jobCostingEnabled);
            if (defaultRegEarnCode.equals(earnCodeString)) {
            	Hours hours = constructHours(earningRecord, earnCode);
        		deptEarning.getHours().add(hours);
            	if(jobCostingEnabled){
                    if(earningRecord.getOverrideHourlyRate().compareTo(BigDecimal.ZERO) > 0) {
                    	OverridePayRate overridePayRate= constructOverridePayRate(earningRecord, earnCode);
                    	deptEarning.getOverridePayRates().add(overridePayRate);
                    }
            	}
            } else if ("A".equals(paymentType) || "F".equals(paymentType)) {
                Amount amount = constructAmount(earningRecord, earnCode);
                deptEarning.getOtherEarnings().add(amount);
                updateRecurringJedDepartmentList(payLineItem, deptEarning, amount);
            } else if ("H".equals(paymentType)) {
                Hours hours = constructHours(earningRecord, earnCode);
                if (hours.getHours() != 0) deptEarning.getOtherHours().add(hours);
            	if(jobCostingEnabled) {
                    if(earningRecord.getOverrideHourlyRate().compareTo(BigDecimal.ZERO) > 0) {
                    	OverridePayRate overridePayRate= constructOverridePayRate(earningRecord, earnCode);
                    	deptEarning.getOverridePayRates().add(overridePayRate);
                    }
            	}                
                
            } else if ("U".equals(paymentType)) {
                UnitPay unitPay = constructUnitPay(earningRecord, earnCode);
                if (unitPay.getUnit() != 0) deptEarning.getUnitsPay().add(unitPay);
            } else if ("B".equals(paymentType) || "E".equals(paymentType)) {
                Hours hours = constructHours(earningRecord, earnCode);
                if (hours.getHours() != 0) deptEarning.getOtherHours().add(hours);
                
            	if(jobCostingEnabled) {
                    if(earningRecord.getOverrideHourlyRate().compareTo(BigDecimal.ZERO) > 0) {
                    	OverridePayRate overridePayRate= constructOverridePayRate(earningRecord, earnCode);
                    	deptEarning.getOverridePayRates().add(overridePayRate);
                    }
            	}                

                Amount amount = constructAmount(earningRecord, earnCode);
                if (amount.getAmount() != 0) deptEarning.getOtherEarnings().add(amount);
            }
        }
        


        private void updateRecurringJedDepartmentList(EmployeePayLineItem payLineItem, DepartmentEarning deptEarning, Amount amount) {
        	if(RECURRING_EARNING_WITH_JED.equals(amount.getJedRecurring())){
        		List<RecurringEarningDistribution>recurringDetails = payLineItem.getEarningDistributionDetails();
        		boolean updated = false;
        		if(recurringDetails.size() > 0){
        			for (RecurringEarningDistribution recurringEarningDistribution : recurringDetails) {
        				if(amount.getEarnCode().equals(recurringEarningDistribution.getEarnCode())){
        					recurringEarningDistribution.updateRecurringEarningDepartment(deptEarning.getDepartment(), amount);	
        					updated = true;
        				}
        			}
        			if(!updated){
        				RecurringEarningDistribution recurringEarningDistributionToAdd = new RecurringEarningDistribution(deptEarning.getDepartment(), amount);
        				recurringDetails.add(recurringEarningDistributionToAdd);	
        			}

        		}else{
        			RecurringEarningDistribution recurringEarningDistribution = new RecurringEarningDistribution(deptEarning.getDepartment(), amount);
        			recurringDetails.add(recurringEarningDistribution);
        			payLineItem.setEarningDistributionDetails(recurringDetails);
        		}
        	}
        }

		private Hours constructHours(PayrollEarningRecord earningRecord, EarnCode earnCode) {
            Hours hours = new Hours();
            parseAndUpdateEarnUnit(hours, earnCode, earningRecord);
            hours.setHours(earningRecord.getHours().doubleValue());
            hours.setOverridePayRate(earningRecord.getOverrideHourlyRate());        
            if (PtgGatewayConstants.PE_PTO_SOURCE.equals(earningRecord.getSource())) {
                hours.setLeave(true);
            }
            hours.setSource(earningRecord.getSource());
            return hours;
        }
		
		private OverridePayRate constructOverridePayRate(PayrollEarningRecord earningRecord, EarnCode earnCode) {
			OverridePayRate overridePayRate = new OverridePayRate();
            parseAndUpdateEarnUnit(overridePayRate, earnCode, earningRecord);
            overridePayRate.setOverridePayRate(earningRecord.getOverrideHourlyRate());
            overridePayRate.setSource(earningRecord.getSource());
            return overridePayRate;
        }
		
		

        private UnitPay constructUnitPay(PayrollEarningRecord earningRecord, EarnCode earnCode) {
            UnitPay unitPay = new UnitPay();
            parseAndUpdateEarnUnit(unitPay, earnCode, earningRecord);
            unitPay.setUnit(earningRecord.getUnits().doubleValue());
            unitPay.setAmount(earningRecord.getOverrideUnitRate().doubleValue());
            return unitPay;
        }

        private Amount constructAmount(PayrollEarningRecord earningRecord, EarnCode earnCode) {
            Amount amount = new Amount();
            parseAndUpdateEarnUnit(amount, earnCode, earningRecord);
            amount.setAmount(earningRecord.getAmount().doubleValue());
            amount.setEarnBeginDate(earningRecord.getEarnBeginDate());
            amount.setEarnEndDate(earningRecord.getEarnEndDate());
            amount.setExcludeFrom401K(earningRecord.getExcludeFrom401K().equals("Y") ? true : false);
            amount.setForceSepChk(earningRecord.getForceSepChk().equals("Y") ? true : false);
            if (earningRecord.getSource().equals("RECR")) amount.setRecurring(true);
            amount.setJedRecurring(earningRecord.getRecrJed());
            return amount;
        }

        private void parseAndUpdateEarnUnit(EarnUnit earnUnit, EarnCode companyEarnCode, PayrollEarningRecord earningRecord) {
            earnUnit.setEarnCode(companyEarnCode.getCode());
            earnUnit.setPaymentType(companyEarnCode.getEnrCdPaymentType());
            earnUnit.setReportedDate(earningRecord.getReportedDate());
            earnUnit.setMultiFactor(companyEarnCode.getFactorMult());
            earnUnit.setSeqNumber(companyEarnCode.getSeqNo());
            earnUnit.setAdditionalNumber(earningRecord.getAddLineNum());
            earnUnit.setSource(earningRecord.getSource());
            earnUnit.setEarnBeginDate(earningRecord.getEarnBeginDate());
            earnUnit.setEarnEndDate(earningRecord.getEarnEndDate());
            earnUnit.setSepCheck(earningRecord.getSepChk());
            earnUnit.setDisplayOnly(companyEarnCode.getDisplayOnly());
            earnUnit.setAddlSeq(earningRecord.getAddSeq());
        }

        /*
         * Constructs EmployeePayLineItems
         */
        private void constructAndUpdatePayLineItem(LinkedHashMap<String, List<EmployeePayLineItem>> itemRootMap, PayrollEmployeeRecordVW employeeRecord) {
            List<EmployeePayLineItem> employeeList = itemRootMap.get(employeeRecord.getEmplId());
            // No EmployeeItemList hence construct one.
            if (employeeList == null) {
                employeeList = new ArrayList<EmployeePayLineItem>();
                itemRootMap.put(employeeRecord.getEmplId(), employeeList);
            }

            // Look for already existing pay-line that's created else construct a new one.
            EmployeePayLineItem employeePayLineItem = null;
            for (EmployeePayLineItem payLineItem : employeeList) {
            	if(isOnCycle(payGroup)){
	                if (payLineItem.getEndDate().compareTo(employeeRecord.getEndDate()) == 0) {
	                    employeePayLineItem = payLineItem;
	                    break;
	                }
            	} else {
            		if (payLineItem.getEndDate().compareTo(employeeRecord.getEndDate()) == 0
            				&& payLineItem.getLineNumber() == employeeRecord.getLineNumber()) {
	                    employeePayLineItem = payLineItem;
	                    break;
	                }
            	}
            }
            
            if (employeePayLineItem == null) {
                employeePayLineItem = new EmployeePayLineItem();
                employeePayLineItem.setEndDate(employeeRecord.getEndDate());
                employeePayLineItem.setStartDate(employeeRecord.getStartDate());
                employeePayLineItem.setLineNumber(employeeRecord.getLineNumber());
                Employee employee = constructEmployee(employeeRecord);
                employeePayLineItem.setEmployee(employee);
                employeeList.add(employeePayLineItem);
            }

            // Look for already existing department that's created else construct a new one.
            DepartmentEarning deptEarning = null;
            for (DepartmentEarning departmentEarning : employeePayLineItem.getDepartmentEarnings()) {
                if (departmentEarning.getDepartment().equals(employeeRecord.getDeptId())) deptEarning = departmentEarning;
            }
            if (deptEarning == null) {
                deptEarning = new DepartmentEarning();
                deptEarning.setSplitPercent(employeeRecord.getSplitPercent());
                Department dept = constructDepartment(employeeRecord);
                deptEarning.setDepartment(dept);
                employeePayLineItem.getDepartmentEarnings().add(deptEarning);
            }
            if(!isOnCycle(payGroup)){
            	employeePayLineItem.setPosition(employeePayLineItem.getLineNumber());
            	parseAndFillCheckDetails(employeePayLineItem, employeeRecord);
            }
        }

        // Look for existing matching pay-line Item,
        private EmployeePayLineItem lookupEmployeePayLineItem(LinkedHashMap<String, List<EmployeePayLineItem>> itemRootMap, PayrollEarningRecord earningRecord) {
            List<EmployeePayLineItem> employeeList = itemRootMap.get(earningRecord.getEmplId());
            if(employeeList == null) {
            	logError("Employee data missing for employee Id :: " + earningRecord.getEmplId());
            	EmployeePayLineItem payLineItem = new EmployeePayLineItem(earningRecord);
            	employeeList = new ArrayList<EmployeePayLineItem>();
            	employeeList.add(payLineItem);
            	itemRootMap.put(earningRecord.getEmplId(), employeeList);
            	this.addErrorRecord(payLineItem, earningRecord.getEmplId(), earningRecord.getDeptId(), null, ERROR_EMPL);
            }
            for (EmployeePayLineItem payLineItem : employeeList) {
                // Crucial biz rule. The date under reported should be between the payLineItem start & end date.
            	if(isOnCycle(payGroup)){
	                if (earningRecord.getReportedDate().compareTo(payLineItem.getStartDate()) >= 0
	                        && earningRecord.getReportedDate().compareTo(payLineItem.getEndDate()) <= 0) {
	                    return payLineItem;
	                }
            	} else {
            		if (earningRecord.getReportedDate().compareTo(payLineItem.getStartDate()) >= 0
	                        && earningRecord.getReportedDate().compareTo(payLineItem.getEndDate()) <= 0
	                        && earningRecord.getSepChk() == payLineItem.getLineNumber()) {
	                    return payLineItem;
	                }
            	}
            }
            return null;
        }

        // Look for Existing Department Earnings
        private DepartmentEarning lookupDepartmentEarning(LinkedHashMap<String, List<EmployeePayLineItem>> itemRootMap, PayrollEarningRecord earningRecord, boolean jobCostingEnabled) {
        	DepartmentEarning departmentEarning = null;
            EmployeePayLineItem payLineItem = lookupEmployeePayLineItem(itemRootMap, earningRecord);
            for (DepartmentEarning deptEarning : payLineItem.getDepartmentEarnings()) {
            	if(jobCostingEnabled) {
                    if (deptEarning.getDepartment().getDeptId().equals(earningRecord.getDeptId())
                    		&&
                    		deptEarning.getDepartment().getJobCode().equals(earningRecord.getJobCode())                		
                    		) {
                    	departmentEarning = deptEarning;
                    	break;
                    }
            	}else{
                        if (deptEarning.getDepartment().getDeptId().equals(earningRecord.getDeptId())) {
                        	departmentEarning = deptEarning;
                        	break;
                    }
            	}
            	
            }
            if (departmentEarning == null) {
            	logError("Department did not find for employee id :: " + earningRecord.getEmplId() 
            			+ ", Department Id: "+earningRecord.getDeptId());
            	departmentEarning = new DepartmentEarning(earningRecord);
            	payLineItem.getDepartmentEarnings().add(departmentEarning);
            	this.addErrorRecord(payLineItem, earningRecord.getEmplId(), earningRecord.getDeptId(), null, ERROR_DEPT);
            }
            return departmentEarning;
        }

        private Employee constructEmployee(PayrollEmployeeRecordVW employeeRecord) {
            Employee employee = new Employee();
            employee.setEmployeeId(employeeRecord.getEmplId());
            employee.setEmployeeRecord(employeeRecord.getEmplRecord());
            employee.setFirstName(employeeRecord.getFirstName());
            employee.setMiddleName(employeeRecord.getMiddleName());
            employee.setLastName(employeeRecord.getLastName());
            employee.setStatus(employeeRecord.getEmplStatus());
            employee.setStatusDesc(employeeRecord.getEmplStatusDescr());
            employee.setEmployeeType(employeeRecord.getEmplType());
            employee.setRegOrTemp(employeeRecord.getRegOrTemp());
            employee.setFullOrPartTime(employeeRecord.getFullOrPartTime());
            employee.setTerminationDate(employeeRecord.getTerminationDate());
            employee.setHireDate(employeeRecord.getHireDt());
            employee.setPayRate(employeeRecord.getCompRate().doubleValue());
            if("S".equals(employeeRecord.getEmplType())){
            	employee.setHourlyRate(employeeRecord.getCompRate().doubleValue()/2080);
            } else {
            	employee.setHourlyRate(employeeRecord.getCompRate().doubleValue());
            }
            employee.setOverrideCompRate(employeeRecord.getOverrideCompRate());
            employee.setUsWorkEligibility(employeeRecord.getUsWorkEligibilty());
            employee.setJed(employeeRecord.getJed());
            employee.setStdHours(new BigDecimal(employeeRecord.getStdHours()));
            employee.setDisableDirectDeposit(employeeRecord.getDisableDirectDeposit());
            employee.setPaymentMethod(employeesPaymentMethod.get(employeeRecord.getEmplId()));
            employee.setChangedByProcessor(employeeRecord.getChangedByProc());
            employee.setMidPayAction(employeeRecord.getMidPayAction());
            employee.setMidPayActionDesc(employeeRecord.getMidPayActionDesc());
            employee.setActionEffDate(employeeRecord.getActionEffDate());
            employee.setEmplClass(employeeRecord.getEmplClass());
            return employee;
        }

        private Department constructDepartment(PayrollEmployeeRecordVW employeeRecord) {
            Department dept = new Department();
            dept.setDeptId(employeeRecord.getDeptId());
            dept.setDeptDesc(employeeRecord.getDepartmentDesc());
            dept.setJobCode(employeeRecord.getJobCode());
            dept.setLocation(employeeRecord.getLocation());
            dept.setLocationDesc(employeeRecord.getLocationDesc());
            dept.setSource(employeeRecord.getSource());
            dept.setPayPeriodHours(employeeRecord.getPayPeriodHours());
            boolean isHome = (employeeRecord.getHomeDept() != null && employeeRecord.getHomeDept().equals("Y")) ? true : false;
            dept.setHomeDepartment(isHome);
            dept.setWageOverride(employeeRecord.getOverrideCompRate().doubleValue());
            return dept;
        }

        /**
         * We perform quite some biz logic in here. First, we would look for the primary employee within a list of
         * employees. Because due to Mid Pay and Special Pay, there can be more than one employee item / employee. In
         * this case, we would attach the metadata only for the primary ( first ) employee. Second, Check metadata
         * information will be attache to each employee.
         * 
         * 
         * @param payLineItems
         */
        private void updateEmployeePayLineItemWithAdditionalData(List<EmployeePayLineItem> payLineItems) {
            for (EmployeePayLineItem employeeItem : payLineItems) {
                List<CheckDetail> checkDetails = employeesCheckDetails.get(employeeItem.getEmployee().getEmployeeId() + "-" + employeeItem.getPosition());
                if (checkDetails == null) {
                    checkDetails = new ArrayList<CheckDetail>();
                    checkDetails.add(new CheckDetail());
                }
                employeeItem.setCheckDetails(checkDetails);

                List<LeaveBalance> leaveBalances = employeesLeaveBalances.get(employeeItem.getEmployee().getEmployeeId());
                if (employeeItem.getStartDate().compareTo(payGroup.getPayBeginDate()) == 0) {
                    employeeItem.setPrimary(true);
                    if (leaveBalances != null) employeeItem.setLeaveBalances(leaveBalances);
                    else employeeItem.setLeaveBalances(new ArrayList<LeaveBalance>());
                }

                updateRegularHours(employeeItem);
            }
        }

        private void updateRegularHours(EmployeePayLineItem employeeItem) {
            if ("S".equals(employeeItem.getEmployee().getEmployeeType())) {
                for (DepartmentEarning departmentEarning : employeeItem.getDepartmentEarnings()) {
                    for (Hours hours : departmentEarning.getHours()) {
                        Double totalReduceHours = this.getReduceOtherHours(departmentEarning.getOtherHours(), hours.getReportedDate());
                        Double regularHours = hours.getHours() - totalReduceHours;
                        hours.setHours(regularHours);
                    }
                }
            }
        }

        private Double getReduceOtherHours(List<Hours> otherHours, Date regularHoursReportDate) {
            Double totalReduceHours = 0d;
            for (Hours hours : otherHours) {
                if (regularHoursReportDate.equals(hours.getReportedDate())) {
                    EarnCode earnCode = earnCodeMap.get(hours.getEarnCode());
                    if ("Y".equals(earnCode.getReduceRegHours())) {
                        totalReduceHours += hours.getHours();
                    }
                }
            }
            return totalReduceHours;
        }

        private CheckDetail lookupCheckDetail(List<CheckDetail> checkDetails, int checkNumber) {
            for (CheckDetail checkDetail : checkDetails) {
                if (checkDetail.getCheckNumber() == checkNumber) return checkDetail;
            }
            return null;
        }

        /**
         * Look for check information in Employee record and if there, either create a new one if not has been created.
         * 
         * @param employee
         * @param department
         * @param dbEmployeeEntry
         */
        private void parseAndFillCheckDetails(LinkedHashMap<String, List<EmployeePayLineItem>> itemRootMap, PayrollEarningRecord earningRecord) {
            EmployeePayLineItem employeePayLineItem = lookupEmployeePayLineItem(itemRootMap, earningRecord);
            // Since there can be more than one employee with same employee Id, we want to associate the check with the
            // correct employee.
            String checkKey = earningRecord.getEmplId() + "-" + employeePayLineItem.getPosition();
            List<CheckDetail> checkDetails = employeesCheckDetails.get(checkKey);
            if (checkDetails == null) {
                checkDetails = new ArrayList<CheckDetail>();
                employeesCheckDetails.put(checkKey, checkDetails);
            }
            CheckDetail checkDetail = lookupCheckDetail(checkDetails, earningRecord.getSepChk());
            if (checkDetail == null) { // Found a new Check, but don't have a check detail already
                checkDetail = new CheckDetail();
                checkDetail.setCheckNumber(earningRecord.getSepChk());
                checkDetail.setGrossUp(earningRecord.getGrossup());
                checkDetail.setExcludeFrom401K(earningRecord.getExcludeFrom401K());
                if ("Y".equals(earningRecord.getDisableDirectDeposit())) {
                    checkDetail.setPaymentOption(CheckDetail.PAYMENT_OPTION_CHECK);
                } else {
                    checkDetail.setPaymentOption(CheckDetail.PAYMENT_OPTION_DIRECT);
                }

                employeesCheckDetails.get(checkKey).add(checkDetail);
            }
        }
        
        private void updateCheckDetails(LinkedHashMap<String, List<EmployeePayLineItem>> itemRootMap, PayrollEarningRecord earningRecord) {
        	//Set exclude 401k flag if value is true
        	if ("Y".equals(earningRecord.getExcludeFrom401K())) {
	            EmployeePayLineItem employeePayLineItem = lookupEmployeePayLineItem(itemRootMap, earningRecord);
	            String checkKey = earningRecord.getEmplId() + "-" + employeePayLineItem.getLineNumber();
	            List<CheckDetail> checkDetails = employeesCheckDetails.get(checkKey);
	            CheckDetail checkDetail = lookupCheckDetail(checkDetails, earningRecord.getSepChk());
	            if (checkDetail != null) {
	                checkDetail.setExcludeFrom401K(earningRecord.getExcludeFrom401K());
	            }
        	}
        }
        
        private void parseAndFillCheckDetails(EmployeePayLineItem employeePayLineItem, PayrollEmployeeRecordVW employee) {
            
            String checkKey = employee.getEmplId() + "-" + employeePayLineItem.getLineNumber();
            List<CheckDetail> checkDetails = employeesCheckDetails.get(checkKey);
            if (checkDetails == null) {
                checkDetails = new ArrayList<CheckDetail>();
                employeesCheckDetails.put(checkKey, checkDetails);
            }
            CheckDetail checkDetail = lookupCheckDetail(checkDetails, employee.getLineNumber());
            if (checkDetail == null) { // Found a new Check, but don't have a check detail already
                checkDetail = new CheckDetail();
                checkDetail.setCheckNumber(employee.getLineNumber());
                checkDetail.setGrossUp(employee.getGrossup());
                if ("Y".equals(employee.getDisableDirectDeposit())) {
                    checkDetail.setPaymentOption(CheckDetail.PAYMENT_OPTION_CHECK);
                } else {
                    checkDetail.setPaymentOption(CheckDetail.PAYMENT_OPTION_DIRECT);
                }

                employeesCheckDetails.get(checkKey).add(checkDetail);
            }
        }
        
        private boolean isOnCycle(PayGroup payGroup){
        	return "N".equals(payGroup.getOffCycle());
        }
        
        private void logError(String errorStr){
        	log.error("PAYROLL-ENTRY-ERROR: " + errorStr
        			+ ", Company : "+ payGroup.getCompany()
    				+ ", PayGroup : "+ payGroup.getPayGroup()
    				+ ", PayEndDate : " + payGroup.getPayEndDate()
    				+ ", HRPPayrollNumber : " + payGroup.getPayrollNumber());
        }
        
        private void addErrorRecord(EmployeePayLineItem payLineItem, String employeeId, String departmentId, String earningCode, String type) {
        	PayrollEmployeeError error = null;
        	if(type.equals(ERROR_EMPL)) {
        		error = new PayrollEmployeeError(employeeId, departmentId, earningCode, "EMPL", "Employee is not exists in PS_T2_PE_PYRL_EMP table");
        	} else if(type.equals(ERROR_DEPT)) {
        		error = new PayrollEmployeeError(employeeId, departmentId, earningCode, "DEPT", "Department is not exists for employee in PS_T2_PE_PYRL_EMP table");
        	} else if(type.equals(ERROR_ERNCD)) {
        		error = new PayrollEmployeeError(employeeId, departmentId, earningCode, "ERCD", "Earning code is not exists in payroll earnings table");
        	}
        	payLineItem.setHasErrors(true);
        	payLineItem.getErrors().add(error);
        }
        
        public List<PayrollError> translatePayrollErrors(List<EmployeePayLineItem> employeePayLineItems, PayGroup payGroup, String operatorId) {
            List<PayrollError> errors = new ArrayList<PayrollError>();
            
            for (EmployeePayLineItem payLineItem : employeePayLineItems) {
            	if (payLineItem.isHasErrors()) {
            		for(PayrollEmployeeError empError : payLineItem.getErrors()) {
            			PayrollError error = new PayrollError();
            			error.setMessage(empError.getMessage());
            			error.setOperatorId(operatorId);
            			error.setLastUpdatedDate(new Date());
            			error.setReviewed("N");
                		
                		PayrollErrorKey key = new PayrollErrorKey();
                		key.setCompany(payGroup.getCompany());
                		key.setPayrollGroup(payGroup.getPayGroup());
                		key.setOffCycle(payGroup.getOffCycle());
                		key.setPayrollNumber(payGroup.getPayrollNumber());
                		key.setPayEndDate(payGroup.getPayEndDate());
                		key.setEmplId(StringUtils.isNotBlank(empError.getEmployeeId()) ? empError.getEmployeeId() : " ");
                		key.setDeptId(StringUtils.isNotBlank(empError.getDepartmentId()) ? empError.getDepartmentId() : " ");
                		key.setEarningCode(StringUtils.isNotBlank(empError.getEarningCode()) ? empError.getEarningCode() : " ");
                		key.setErrorCode(StringUtils.isNotBlank(empError.getType()) ? empError.getType() : " ");
                		
                		error.setKey(key);
                		
                		errors.add(error);
            		}
            	}
            }
            return errors;
        }
    }
    
    /*****************************************************************************/
    /************************    DomainToEntityMapper    *************************/
    /*****************************************************************************/


    public static class DomainToEntityMapper {

        private String defaultEarnCode;
        private PayGroup payGroup;
        List<PayrollEmployeeRecord> employeeRecords = new ArrayList<PayrollEmployeeRecord>();
        List<PayrollEarningRecord> earningRecords = new ArrayList<PayrollEarningRecord>();
        private Map<String, EarnCode> earnCodeMap = new HashMap<String, EarnCode>();
        Map<String, Integer> employeeSeqNumMap = new HashMap<String, Integer>();
        
        
        
        public DomainToEntityMapper(PayGroup payGroup, String defaultEarnCode, List<EarnCode> earningCodeList) {
            this.payGroup = payGroup;
            this.defaultEarnCode = defaultEarnCode;
            for (EarnCode earnCode : earningCodeList) {
                earnCodeMap.put(earnCode.getCode(), earnCode);
            }
        }

        /* All the Business logic to convert Domain to Entity below */
        public void translate(List<EmployeePayLineItem> employeePayLineItems, boolean jobCostingEnabled) {
            for (EmployeePayLineItem payLineItem : employeePayLineItems) {
                updateRegularHours(payLineItem);
                for (DepartmentEarning deptEarning : payLineItem.getDepartmentEarnings()) {
                    PayrollEmployeeRecord employeeRecord = constructPayrollEmployeeRecord(payLineItem, deptEarning);
                    employeeRecords.add(employeeRecord);
                    List<PayrollEarningRecord> employeeEarningRecords = constructPayrollEarningRecords(payLineItem, deptEarning,jobCostingEnabled);
                    earningRecords.addAll(employeeEarningRecords);
                }
            }
        }

     

		/* Construct the records for the payroll employee table */
        public PayrollEmployeeRecord constructPayrollEmployeeRecord(EmployeePayLineItem payLineItem, DepartmentEarning deptEarning) {
            PayrollEmployeeRecord employeeRecord = new PayrollEmployeeRecord();

            // PayGroup Details
            employeeRecord.setCompany(payGroup.getCompany());
            employeeRecord.setPayrollGroup(payGroup.getPayGroup());
            employeeRecord.setPayEndDate(payGroup.getPayEndDate());
            employeeRecord.setOffCycle(payGroup.getOffCycle());
            employeeRecord.setPayrollNum(payGroup.getPayrollNumber());

            // Employee Details
            Employee employee = payLineItem.getEmployee();
            employeeRecord.setEmplId(employee.getEmployeeId());
            employeeRecord.setEmplRecd(employee.getEmployeeRecord());
            employeeRecord.setStartDate(payLineItem.getStartDate());
            employeeRecord.setEndDate(payLineItem.getEndDate());
            employeeRecord.setLineNumber(payLineItem.getLineNumber());
            employeeRecord.setEmplType(employee.getEmployeeType());
            employeeRecord.setStdHours(employee.getStdHours());
            employeeRecord.setCompRate(new BigDecimal(employee.getPayRate()));
            employeeRecord.setOverrideCompRate(employee.getOverrideCompRate());
            employeeRecord.setDisableDirectDeposit(employee.getDisableDirectDeposit());
            employeeRecord.setJed(employee.getJed());
            // Department Details
            Department dept = deptEarning.getDepartment();
            employeeRecord.setDeptId(dept.getDeptId());
           
            if ( StringUtils.isEmpty(dept.getSource())) employeeRecord.setSource(" ");
            else employeeRecord.setSource(dept.getSource());

            employeeRecord.setSplitPercent(deptEarning.getSplitPercent());
            if (StringUtils.isEmpty(dept.getJobCode())) { // Department split happened in UI
                Department homeDept = lookupHomeDepartment(payLineItem);
                copyCoreAttributesFromHomeDepartment(dept, homeDept);
                employeeRecord.setSource(SOURCE_GRID);
            }
            employeeRecord.setJobCode(dept.getJobCode());
            employeeRecord.setLocation(dept.getLocation());
            employeeRecord.setOverrideCompRate(new BigDecimal(dept.getWageOverride() != null ? dept.getWageOverride() : 0));
            employeeRecord.setPayPeriodHours(dept.getPayPeriodHours());
            if (dept.isHomeDepartment()) employeeRecord.setHomeDept("Y");
            else employeeRecord.setHomeDept("N");
            
            if ("Y".equals(payGroup.getOffCycle())) {
	            if(payLineItem.getCheckDetails() != null && payLineItem.getCheckDetails().size() > 0){
	            	String grossUp = payLineItem.getCheckDetails().get(0).getGrossUp();
	            	grossUp = StringUtils.isEmpty(grossUp) ? "N" : grossUp;
	            	employeeRecord.setGrossup(grossUp);
	            } else {
	            	employeeRecord.setGrossup("N");
	            }
            } else {
            	employeeRecord.setGrossup("N");
            }

            return employeeRecord;
        }

        private void copyCoreAttributesFromHomeDepartment(Department department, Department homeDepartment) {
            department.setJobCode(homeDepartment.getJobCode());
            department.setLocation(homeDepartment.getLocation());
        }

        private Department lookupHomeDepartment(EmployeePayLineItem payLineItem) {
            List<DepartmentEarning> deptEarnings = payLineItem.getDepartmentEarnings();
            for (DepartmentEarning deptEarning : deptEarnings) {
                if (deptEarning.getDepartment().isHomeDepartment()) return deptEarning.getDepartment();
            }
            return null;
        }

        /* Constructs the records for the earning records */
        public List<PayrollEarningRecord> constructPayrollEarningRecords(EmployeePayLineItem payLineItem, DepartmentEarning deptEarning, boolean jobCostingEnabled) {
            Department dept = deptEarning.getDepartment();
            List<PayrollEarningRecord> employeeEarningRecords = new ArrayList<PayrollEarningRecord>();
            Map<BigDecimal, Integer> employeeJobCostingSeqNumMap= new HashMap<BigDecimal,Integer>(); 
            
            for (Hours hours : deptEarning.getHours()) {
                PayrollEarningRecord earningRecord = constructBasePayrollEarningRecord(payLineItem, dept, hours);
                earningRecord.setHours(new BigDecimal(hours.getHours()));
                employeeEarningRecords.add(earningRecord);
              if(jobCostingEnabled) {
	                if(!(hours.getOverridePayRate().compareTo( BigDecimal.ZERO) == 0 )) {
	                	earningRecord.setOverrideHourlyRate(hours.getOverridePayRate());
	                }
	                
	            	if(isUpdateAdditionalNumber(employeeEarningRecords,earningRecord,hours.getOverridePayRate())){
	            		updateAdditionalNumber(employeeEarningRecords, earningRecord,jobCostingEnabled);
	            		hours.setAdditionalNumber(earningRecord.getAddLineNum());
	            		updateAdditionalNumberForOverridePayRate( deptEarning, hours);
	            	}
            		employeeJobCostingSeqNumMap.put(hours.getOverridePayRate(), earningRecord.getAddLineNum());
	              }
            }
            for (Hours hours : deptEarning.getOtherHours()) {
                PayrollEarningRecord earningRecord = constructBasePayrollEarningRecord(payLineItem, dept, hours);
                earningRecord.setHours(new BigDecimal(hours.getHours()));
                
                if(jobCostingEnabled ){
                	
                	if(employeeJobCostingSeqNumMap.get(hours.getOverridePayRate())==null && isUpdateAdditionalNumberMinusEarnCode(employeeEarningRecords,earningRecord,hours.getOverridePayRate())){
                		updateAdditionalNumberMinusEarnCode(employeeEarningRecords, earningRecord);
	            		hours.setAdditionalNumber(earningRecord.getAddLineNum());
	            		updateAdditionalNumberForOverridePayRate( deptEarning, hours);
	            		employeeJobCostingSeqNumMap.put(hours.getOverridePayRate(), earningRecord.getAddLineNum());
                	}
                	
                	if (!(hours.getOverridePayRate().compareTo( BigDecimal.ZERO) == 0 )) {
                    	earningRecord.setOverrideHourlyRate(hours.getOverridePayRate());
                	}
                    	
                	if(employeeJobCostingSeqNumMap.get(hours.getOverridePayRate())!=null) {
                		Integer additionalNumber=employeeJobCostingSeqNumMap.get(hours.getOverridePayRate());
	            		hours.setAdditionalNumber(additionalNumber);
	            		updateAdditionalNumberForOverridePayRate( deptEarning, hours);
                		earningRecord.setAddLineNum(additionalNumber);
                	}
                }
                employeeEarningRecords.add(earningRecord);
            }
            for (Amount amount : deptEarning.getOtherEarnings()) {
                PayrollEarningRecord earningRecord = constructBasePayrollEarningRecord(payLineItem, dept, amount);
                PayrollEarningRecord lookupRecord = lookupEarningRecord(employeeEarningRecords, earningRecord);
                //PAYR-7065 start - changed to remove the update of the seqnum 2 times.
               // Now look up and set the earncode to the existing one
                if (lookupRecord != null) earningRecord = lookupRecord;
                //update the date
                earningRecord.setAmount(new BigDecimal(amount.getAmount()));
                earningRecord.setExcludeFrom401K(amount.isExcludeFrom401K() ? "Y" : "N");
                earningRecord.setForceSepChk(amount.isForceSepChk() ? "Y" : "N");
                earningRecord.setRecrJed(amount.getJedRecurring());
                updateAdditionalNumber(employeeEarningRecords, earningRecord, jobCostingEnabled);

                if(jobCostingEnabled){
                	amount.setAdditionalNumber(earningRecord.getAddLineNum());
                }
                //Add the earncode to the set to update to the backend.
                employeeEarningRecords.add(earningRecord);
                //PAYR-7065 end changes
            }
            Map<String, Integer> unitPayEarnCodeMap = new HashMap<String, Integer>();
            for (UnitPay unitPay : deptEarning.getUnitsPay()) {
                // Update additional number
                updateUnitPayAdditionalNumber(unitPay, unitPayEarnCodeMap);
                PayrollEarningRecord earningRecord = constructBasePayrollEarningRecord(payLineItem, dept, unitPay);
                earningRecord.setUnits(new BigDecimal(unitPay.getUnit()));
                earningRecord.setAddLineNum(unitPay.getAdditionalNumber());
                earningRecord.setOverrideUnitRate(new BigDecimal(unitPay.getAmount()));
                employeeEarningRecords.add(earningRecord);
            }
            updateNullEntitiesWithDefaultValues(employeeEarningRecords, payLineItem);
            return employeeEarningRecords;
        }
        
        private void updateAdditionalNumberForOverridePayRate(DepartmentEarning deptEarning, Hours hours){
        	for (OverridePayRate overridePayRate: deptEarning.getOverridePayRates()){
        		if(hours.getOverridePayRate().equals(overridePayRate.getOverridePayRate())){
        			overridePayRate.setAdditionalNumber(hours.getAdditionalNumber());
        			break;
        		}
        	}
        	
        }

        private void updateAdditionalNumber(List<PayrollEarningRecord> alreadyCreatedEarningRecords,
        		PayrollEarningRecord earningRecord) {
        	String earningRecordUniqueDbKey = earningRecord.computeUniquenessForSeqNum();
        	Integer maxSeqNum = this.employeeSeqNumMap.get(earningRecordUniqueDbKey);
        	for (PayrollEarningRecord alreadyCreatedEarningRecord : alreadyCreatedEarningRecords) {
                if(alreadyCreatedEarningRecord.computeUniquenessForSeqNum().equals(earningRecordUniqueDbKey) && !"RECR".equals(alreadyCreatedEarningRecord.getSource())){
        			if(null == maxSeqNum){
        				maxSeqNum = new Integer(1);
        			}else{
        				maxSeqNum++;
        			}
        			earningRecord.setAddLineNum(maxSeqNum);
    				this.employeeSeqNumMap.put(earningRecordUniqueDbKey, maxSeqNum);
        			break;
        		}
        	}  
        }
        
        private void updateAdditionalNumber(List<PayrollEarningRecord> alreadyCreatedEarningRecords,
        		PayrollEarningRecord earningRecord, boolean jobCostingEnabled) {
        	
    		updateAdditionalNumber(alreadyCreatedEarningRecords,earningRecord);

        	if(jobCostingEnabled){
            	String earningRecordUniqueDbKey = earningRecord.computeUniquenessForSeqNum();
            	Integer maxSeqNum = this.employeeSeqNumMap.get(earningRecordUniqueDbKey);
            	
            	if(null == maxSeqNum && earningRecord.getAddLineNum()>=1 && "RECR".equals(earningRecord.getSource())){
    				this.employeeSeqNumMap.put(earningRecordUniqueDbKey, earningRecord.getAddLineNum());
            	}
            	
            	String earningRecordUniqueDbKeyMinusEarnCode = earningRecord.computeUniquenessForSeqNumMinusEarnCode();
				this.employeeSeqNumMap.put(earningRecordUniqueDbKeyMinusEarnCode, maxSeqNum);
        	}
        }

        
        private void updateAdditionalNumberMinusEarnCode(List<PayrollEarningRecord> alreadyCreatedEarningRecords,
        		PayrollEarningRecord earningRecord) {
        	String earningRecordUniqueDbKey = earningRecord.computeUniquenessForSeqNumMinusEarnCode();
        	Integer maxSeqNum = this.employeeSeqNumMap.get(earningRecordUniqueDbKey);
        	for (PayrollEarningRecord alreadyCreatedEarningRecord : alreadyCreatedEarningRecords) {
        		if(alreadyCreatedEarningRecord.computeUniquenessForSeqNumMinusEarnCode().equals(earningRecordUniqueDbKey)){
        			if(null == maxSeqNum){
        				maxSeqNum = new Integer(1);
        			}else{
        				maxSeqNum++;
        			}
        			earningRecord.setAddLineNum(maxSeqNum);
    				this.employeeSeqNumMap.put(earningRecordUniqueDbKey, maxSeqNum);
        			break;
        		}
        	}  
        }
        
        
        private boolean isUpdateAdditionalNumber(List<PayrollEarningRecord> alreadyCreatedEarningRecords,
        		PayrollEarningRecord earningRecord, BigDecimal overridePayRate) {
        			boolean updateAdditionalNumber=false;
        			
                	String earningRecordUniqueDbKey = earningRecord.computeUniquenessForSeqNum();
                	for (PayrollEarningRecord alreadyCreatedEarningRecord : alreadyCreatedEarningRecords) {
                		if(alreadyCreatedEarningRecord.computeUniquenessForSeqNum().equals(earningRecordUniqueDbKey) &&
                				!alreadyCreatedEarningRecord.getOverrideHourlyRate().equals(overridePayRate)){
                			updateAdditionalNumber=true;
                		}
                	}
        			
        			
        			return updateAdditionalNumber;
        }
        
        private boolean isUpdateAdditionalNumberMinusEarnCode(List<PayrollEarningRecord> alreadyCreatedEarningRecords,
        		PayrollEarningRecord earningRecord, BigDecimal overridePayRate) {
        			boolean updateAdditionalNumber=false;
        			
                	String earningRecordUniqueDbKey = earningRecord.computeUniquenessForSeqNumMinusEarnCode();
                	for (PayrollEarningRecord alreadyCreatedEarningRecord : alreadyCreatedEarningRecords) {
                		if(alreadyCreatedEarningRecord.computeUniquenessForSeqNumMinusEarnCode().equals(earningRecordUniqueDbKey) &&
                				!alreadyCreatedEarningRecord.getOverrideHourlyRate().equals(overridePayRate)){
                			updateAdditionalNumber=true;
                		}
                	}
        			
        			
        			return updateAdditionalNumber;
        }       
        
        
        
        
        
        
        
		private void updateUnitPayAdditionalNumber(UnitPay unitPay, Map<String, Integer> unitPayEarnCodeMap) {
            Integer lastAdditionalNumber = unitPayEarnCodeMap.get(unitPay.getEarnCode());
            if (PrimitiveCompare.isIntegerGreaterThanOrEqualToZero(lastAdditionalNumber)) {
                lastAdditionalNumber++;
            } else {
                lastAdditionalNumber = 0;
            }
            unitPay.setAdditionalNumber(lastAdditionalNumber);
            unitPayEarnCodeMap.put(unitPay.getEarnCode(), lastAdditionalNumber);
        }

        /**
         * Looks for a matching earn unit in the hours, for the same earn code and reported date.
         */
		public PayrollEarningRecord lookupEarningRecord(List<PayrollEarningRecord> records, PayrollEarningRecord matchRecord) {
			for (PayrollEarningRecord record : records) {
				if (record.getEarnCode().equals(matchRecord.getEarnCode()) && record.getSepChk() == matchRecord.getSepChk()
						&& !( ("RECR".equals(record.getSource()) && !"RECR".equals(matchRecord.getSource()))  || ("RECR".equals(matchRecord.getSource()) && !"RECR".equals(record.getSource())))) {
					if (record.getReportedDate() == null && matchRecord.getReportedDate() == null) return record;
					else if (record.getReportedDate().equals(matchRecord.getReportedDate())) return record;
				}	

			}
			return null;
		}

        public PayrollEarningRecord constructBasePayrollEarningRecord(EmployeePayLineItem payLineItem, Department dept, EarnUnit earnUnit) {
            Employee employee = payLineItem.getEmployee();
            // PayGroup Details
            PayrollEarningRecord earningRecord = new PayrollEarningRecord();
            earningRecord.setCompany(payGroup.getCompany());
            earningRecord.setPayrollGroup(payGroup.getPayGroup());
            earningRecord.setPayEndDate(payGroup.getPayEndDate());
            earningRecord.setOffCycle(payGroup.getOffCycle());
            earningRecord.setPayrollNum(payGroup.getPayrollNumber());

            // Employee Details
            earningRecord.setEmplId(employee.getEmployeeId());
            earningRecord.setEmplRcd(employee.getEmployeeRecord());

            // Department Details
            earningRecord.setDeptId(dept.getDeptId());

            if (StringUtils.isEmpty(dept.getJobCode())) { // Department split happened in UI
                Department homeDept = lookupHomeDepartment(payLineItem);
                copyCoreAttributesFromHomeDepartment(dept, homeDept);
            }
            earningRecord.setLocation(dept.getLocation());
            earningRecord.setJobCode(dept.getJobCode());

            // EarnUnit details
            earningRecord.setEarnCode(earnUnit.getEarnCode());
            earningRecord.setReportedDate(payLineItem.getEndDate());
            earningRecord.setAddLineNum(earnUnit.getAdditionalNumber());
            earningRecord.setAddSeq(earnUnit.getAddlSeq());
            String source = earnUnit.getSource();
            if (StringUtils.isBlank(source)) source = SOURCE_GRID;
            earningRecord.setSource(source);

            Date earnBeginDate = earnUnit.getEarnBeginDate();
            if (earnBeginDate == null || !source.equals(SOURCE_ERFT)) earnBeginDate = payLineItem.getStartDate();
            earningRecord.setEarnBeginDate(earnBeginDate);

            Date earnEndDate = earnUnit.getEarnEndDate();
            if (earnEndDate == null || !source.equals(SOURCE_ERFT)) earnEndDate = payLineItem.getEndDate();
            earningRecord.setEarnEndDate(earnEndDate);
            
            Integer checkNumber = new Integer(0);
            CheckDetail check = null;
            //Special payroll check number read from payline and set to earning records.
            if ("Y".equals(payGroup.getOffCycle())) {
            	check = this.getCheck(payLineItem, payLineItem.getLineNumber());
            	earningRecord.setSepChk(check.getCheckNumber());
            } else {
            	checkNumber = earnUnit.getSepCheck() != null ? earnUnit.getSepCheck() : new Integer(0);
            	check = this.getCheck(payLineItem, checkNumber);
            	earningRecord.setSepChk(earnUnit.getSepCheck());
            }
            earningRecord.setGrossup(check != null && !StringUtils.isBlank(check.getGrossUp()) ? check.getGrossUp() : "N");
            
            earningRecord.setExcludeFrom401K(check != null && !StringUtils.isBlank(check.getExcludeFrom401K()) ?  check.getExcludeFrom401K() : "N");

            // Disable Direct deposit @ employee level only for default check sepChk == 0
            if (earningRecord.getSepChk() == 0) {
                earningRecord.setDisableDirectDeposit(payLineItem.getEmployee().getDisableDirectDeposit());
            } else {
            	earningRecord.setDisableDirectDeposit(check != null && "D".equals(check.getPaymentOption()) ? "N" : "Y");
            }

            return earningRecord;

        }
        
        private CheckDetail getCheck(EmployeePayLineItem payLineItem, Integer checkNumber) {
        	CheckDetail check = null;
        	for(CheckDetail checkDetail : payLineItem.getCheckDetails()) {
        		if (checkDetail.getCheckNumber() == checkNumber.intValue()) {
        			check = checkDetail;
        			break;
        		}
        	}
        	return check;
        }

        private void updateNullEntitiesWithDefaultValues(List<PayrollEarningRecord> earningRecords, EmployeePayLineItem payLineItem) {
            for (PayrollEarningRecord earningRecord : earningRecords) {
                // Fix for Reported Date

                if (earningRecord.getReportedDate() == null) earningRecord.setReportedDate(payLineItem.getEndDate());
                if (earningRecord.getSepChk() == null) earningRecord.setSepChk(0);
                if (earningRecord.getAddLineNum() == null) earningRecord.setAddLineNum(0);

                if (earningRecord.getHours() == null) earningRecord.setHours(new BigDecimal(0));
                if (earningRecord.getAmount() == null) earningRecord.setAmount(new BigDecimal(0));
                if (StringUtils.isEmpty(earningRecord.getSource())) earningRecord.setSource(" ");
                if (StringUtils.isEmpty(earningRecord.getDedCode())) earningRecord.setDedCode(" ");
                if (StringUtils.isEmpty(earningRecord.getDedClass())) earningRecord.setDedClass(" ");

                if (StringUtils.isEmpty(earningRecord.getDisableDirectDeposit())) {
                    earningRecord.setDisableDirectDeposit("N");
                }
                if("Y".equals(payGroup.getOffCycle())) {
                	earningRecord.setSepChk(payLineItem.getLineNumber());
                }
            }
        }

        public List<PayrollEmployeeRecord> getEmployeeRecords() {
            return employeeRecords;
        }

        public List<PayrollEarningRecord> getEarningRecords() {
            return earningRecords;
        }

        private void updateRegularHours(EmployeePayLineItem employeeItem) {
            if ("S".equals(employeeItem.getEmployee().getEmployeeType())) {
                for (DepartmentEarning departmentEarning : employeeItem.getDepartmentEarnings()) {
                    for (Hours hours : departmentEarning.getHours()) {
                        Double totalReduceHours = this.getReduceOtherHours(departmentEarning.getOtherHours(), hours.getReportedDate());
                        Double regularHours = hours.getHours() + totalReduceHours;
                        hours.setHours(regularHours);
                    }
                }
            }
        }

        private Double getReduceOtherHours(List<Hours> otherHours, Date regularHoursReportDate) {
        	Double totalReduceHours = 0d;
        	for (Hours hours : otherHours) {
        		if (regularHoursReportDate != null && regularHoursReportDate.equals(hours.getReportedDate())) {
        			EarnCode earnCode = earnCodeMap.get(hours.getEarnCode());
        			if (earnCode != null && "Y".equals(earnCode.getReduceRegHours())) {
        				totalReduceHours += hours.getHours();
        			}
        		}
        	}
        	return totalReduceHours;
        }

        public void restoreRegularHours(List<EmployeePayLineItem> employeePayLineItems) {
        	for (EmployeePayLineItem employeeItem : employeePayLineItems) {
        		if ("S".equals(employeeItem.getEmployee().getEmployeeType())) {
        			for (DepartmentEarning departmentEarning : employeeItem.getDepartmentEarnings()) {
        				for (Hours hours : departmentEarning.getHours()) {
        					Double totalReduceHours = this.getReduceOtherHours(departmentEarning.getOtherHours(), hours.getReportedDate());
        					Double regularHours = hours.getHours() - totalReduceHours;
        					hours.setHours(regularHours);
        				}
        			}
        		}
        	} 
        }
       
    }

}
