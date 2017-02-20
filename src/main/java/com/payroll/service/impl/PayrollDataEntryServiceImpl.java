package com.payroll.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.transaction.annotation.Transactional;

import com.payroll.constant.PtgGatewayConstants;
import com.payroll.dao.PayrollDataAccessDao;
import com.payroll.dao.PayrollDataEntryDao;
import com.payroll.dao.PayrollProcessDao;
import com.payroll.entity.EarnCode;
import com.payroll.entity.JobCostingCode;
import com.payroll.entity.PayrollEarningRecord;
import com.payroll.entity.PayrollEmployeeJobCodeRecord;
import com.payroll.entity.PayrollEmployeeRecord;
import com.payroll.entity.PayrollEmployeeRecordBackend;
import com.payroll.entity.PayrollEmployeeRecordVW;
import com.payroll.entity.PayrollError;
import com.payroll.mapper.PayrollDataEntryMapper;
import com.payroll.service.PayrollDataEntryService;
import com.payroll.service.PayrollService;
import com.ptg.payroll.db.sp.payroll.payrollentry.IInitializePayrollGroupNew;
import com.ptg.payroll.db.sp.payroll.payrollentry.IUpdateSpecialPayrollPPData;
import com.ptg.payroll.domain.EmployeeLeaveBalance;
import com.ptg.payroll.domain.payroll.DepartmentEarning;
import com.ptg.payroll.domain.payroll.DepartmentSplit;
import com.ptg.payroll.domain.payroll.EmployeePayLineItem;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.domain.payroll.PayrollEmployeeEarnings;
import com.ptg.payroll.domain.payroll.PayrollEmployeeEarningsDomain;
import com.ptg.payroll.domain.payroll.PayrollEmployeeEarningsEntity;


public class PayrollDataEntryServiceImpl implements PayrollDataEntryService {

	 @Autowired
	 private PayrollDataEntryDao payrollDataEntryDao;
	  
	@Autowired
    private PayrollDataAccessDao payrollDataAccessDao;

    @Autowired
    private PayrollProcessDao payrollProcessDao;

    @Autowired
    private PayrollService payrollService;

    
    @Autowired
    private IInitializePayrollGroupNew spInitializePayrollGroupNew;
    
    @Autowired
    private IUpdateSpecialPayrollPPData spUpdateSpecialPayrollPPData;
    
    @Autowired
    private ConversionService conversionService;

	

	

	public void setPayrollDataEntryDao(PayrollDataEntryDao payrollDataEntryDao) {
		this.payrollDataEntryDao = payrollDataEntryDao;
	}

	public void setPayrollDataAccessDao(PayrollDataAccessDao payrollDataAccessDao) {
		this.payrollDataAccessDao = payrollDataAccessDao;
	}

	public void setPayrollProcessDao(PayrollProcessDao payrollProcessDao) {
		this.payrollProcessDao = payrollProcessDao;
	}

	public void setPayrollService(PayrollService payrollService) {
		this.payrollService = payrollService;
	}

	public void setSpInitializePayrollGroupNew(IInitializePayrollGroupNew spInitializePayrollGroupNew) {
		this.spInitializePayrollGroupNew = spInitializePayrollGroupNew;
	}

	public void setSpUpdateSpecialPayrollPPData(IUpdateSpecialPayrollPPData spUpdateSpecialPayrollPPData) {
		this.spUpdateSpecialPayrollPPData = spUpdateSpecialPayrollPPData;
	}

	public void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	@Override
	public void initializePayrollGroup(PayGroup payGroup, String operatorId) {
		spInitializePayrollGroupNew.execute(payGroup.getCompany(), payGroup.getPayGroup(), payGroup.getPayBeginDate(),payGroup.getPayEndDate(), operatorId, payGroup.getId().toString());
	}

	public void updateSpecialPayrollPPData(PayGroup payGroup, String operatorId) {
		spUpdateSpecialPayrollPPData.execute(payGroup);
	}

	@Override
	public PayrollEmployeeEarnings initializeAndGetPayrollEmployees(PayGroup payGroup, String operatorId) {
		if ("N".equals(payGroup.getStatus())) {
			initializePayrollGroup(payGroup, operatorId);
		} else if ("N".equals(payGroup.getOffCycle()) && ("I".equals(payGroup.getStatus())
				|| "M".equals(payGroup.getStatus()) || "L".equals(payGroup.getStatus())
				|| "X".equals(payGroup.getStatus()) || "R".equals(payGroup.getStatus()))) {
			payrollProcessDao.reInitializePayroll(payGroup, operatorId);
		} else if ("Y".equals(payGroup.getOffCycle())
				&& ("I".equals(payGroup.getStatus()) || "M".equals(payGroup.getStatus())
						|| "L".equals(payGroup.getStatus()) || "X".equals(payGroup.getStatus())
						|| "P".equals(payGroup.getStatus()) || "R".equals(payGroup.getStatus()))) {
			updateSpecialPayrollPPData(payGroup, operatorId);
		}
		return getPayrollEmployeeEarnings(payGroup, operatorId);

	}

	 @Override
	    @Transactional
	    public PayrollEmployeeEarnings getPayrollEmployeeEarnings(PayGroup payGroup, String operatorId) {

	        List<PayrollEmployeeRecordVW> employees = payrollDataEntryDao.getPayrollEmployeesView(payGroup);
	        List<PayrollEmployeeRecordBackend> employeesChangedByBackend = payrollDataEntryDao.getPayrollEmployeesChangedByBackend(payGroup);
	        List<PayrollEarningRecord> earnings = payrollDataEntryDao.getPayrollEmployeeEarnings(payGroup);
	        List<EarnCode> earnCodes = payrollService.getEarningCodesByPayGroup(payGroup.getCompany(), payGroup.getPayGroup());
	        Map<String, String> employeesPaymentMethods = payrollDataEntryDao.getPayrollEmployeesPaymentMethod(payGroup);
	        List<EmployeeLeaveBalance> leaveBalances = payrollDataAccessDao.getPayrollEmployeesLeaveAccrual(payGroup);
	        String defaultRegularEarnCode = payrollDataEntryDao.getDefaultEarnCode(payGroup);
	        PayrollEmployeeEarnings employeeEarnings = new PayrollEmployeeEarnings();
	        employeeEarnings.setPayGroup(payGroup);
	        
	        updateEmployees(employees, employeesChangedByBackend);
	        PayrollDataEntryMapper.EntityToDomainMapper domainMapper = new PayrollDataEntryMapper.EntityToDomainMapper(earnCodes, employeesPaymentMethods,
	                    leaveBalances, payGroup, defaultRegularEarnCode);
	        employeeEarnings.setEmployeePayLineItems(domainMapper.translate(employees, earnings, isJobCostingEnabled(payGroup)));
	        List<PayrollError> payrollErrors = domainMapper.translatePayrollErrors(employeeEarnings.getEmployeePayLineItems(), payGroup, operatorId);
	        if (payrollErrors != null && payrollErrors.size() > 0) {
	        	payrollDataEntryDao.insertPayrollErrors(payrollErrors);
	        }
	        
	        filterEmployees(employeeEarnings, payGroup);
	        
	        conversionServiceSplitPayLinesJobCosting(payGroup, employeeEarnings);        
	        
	        return employeeEarnings;
	    }
	 private void updateEmployees(List<PayrollEmployeeRecordVW> employees,
				List<PayrollEmployeeRecordBackend> employeesChangedByBackend) {
			for (PayrollEmployeeRecordBackend employeeChangedByBackend : employeesChangedByBackend) {
				for (PayrollEmployeeRecordVW employee : employees) {
					if(employee.getEmplId().equals(employeeChangedByBackend.getEmplId()) && 
							employee.getEmplRecord().equals(employeeChangedByBackend.getEmplRecord())){
						employee.setChangedByProc("Y");
						break;
					}
				}
			}
			
		}

		@Override
	    @Transactional
	    public void updatePayrollEmployeePayLineItems(PayGroup payGroup, List<EmployeePayLineItem> employeePayLineItems, String operatorId) {

			if(isJobCostingEnabled(payGroup)){
		        PayrollEmployeeEarnings employeeEarnings = new PayrollEmployeeEarnings();
		        employeeEarnings.setPayGroup(payGroup);
		        employeeEarnings.setEmployeePayLineItems(employeePayLineItems);
	        	PayrollEmployeeEarningsDomain payrollEmployeeEarningsDomain=new PayrollEmployeeEarningsDomain();
	        	payrollEmployeeEarningsDomain.setPayrollEmployeeEarnings(employeeEarnings);
	        	employeeEarnings= conversionService.convert(payrollEmployeeEarningsDomain, PayrollEmployeeEarnings.class);
	        	employeePayLineItems= employeeEarnings.getEmployeePayLineItems();
	        }
			
			
	        String defaultEarnCode = payrollDataEntryDao.getDefaultEarnCode(payGroup);
	        List<EarnCode> earnCodes = payrollService.getEarningCodesByPayGroup(payGroup.getCompany(), payGroup.getPayGroup());
	        
	        PayrollDataEntryMapper.DomainToEntityMapper domainMapper = new PayrollDataEntryMapper.DomainToEntityMapper(payGroup, defaultEarnCode, earnCodes);
	        domainMapper.translate(employeePayLineItems, isJobCostingEnabled(payGroup));
	        List<PayrollEmployeeRecord> employeeRecords = domainMapper.getEmployeeRecords();
	        List<PayrollEarningRecord> earningRecords = domainMapper.getEarningRecords();
	        payrollDataEntryDao.insertPayrollEmployeeLineItems(payGroup, employeeRecords, earningRecords);
	        payrollDataEntryDao.updateAdditionalPay(payGroup);
	        payrollProcessDao.updateOperatorId(payGroup, operatorId);
	        domainMapper.restoreRegularHours(employeePayLineItems);
	        
			if(isJobCostingEnabled(payGroup)){
		        PayrollEmployeeEarnings employeeEarnings = new PayrollEmployeeEarnings();
		        employeeEarnings.setPayGroup(payGroup);
		        employeeEarnings.setEmployeePayLineItems(employeePayLineItems);
		        
		        conversionServiceSplitPayLinesJobCosting(payGroup, employeeEarnings);        
		        cleanUpOverridePayRateOnlyPayLineItems(employeePayLineItems);
			}        
	    }
		
		private void cleanUpOverridePayRateOnlyPayLineItems(List<EmployeePayLineItem> employeePayLineItems){
			
			for(EmployeePayLineItem employeePayLineItem:employeePayLineItems){
				List<DepartmentEarning> cleanupList= new ArrayList<DepartmentEarning>();
				for(DepartmentEarning departmentEarning: employeePayLineItem.getDepartmentEarnings()){
					if ( departmentEarning.getOverridePayRates().size() >0 
							&& departmentEarning.getHours().size() == 0
							&& departmentEarning.getOtherHours().size() == 0 
							&& departmentEarning.getOtherEarnings().size() == 0
							&& departmentEarning.getUnitsPay().size() == 0) {
						cleanupList.add(departmentEarning);
					}
				}
				employeePayLineItem.getDepartmentEarnings().removeAll(cleanupList);
			}
		}
		@Override
		public PayrollEmployeeEarnings resetPayrollEmployee(PayGroup payGroup,
				String operatorId, String employeeId) {
			
			// reset the payroll info for this employee.
	        List<PayrollEarningRecord> earnings = payrollDataEntryDao.resetPayrollForEmployee(payGroup, employeeId);
	        
	        // This may need to be filtered for a single employee...
	 		List<PayrollEmployeeRecordVW> employees = payrollDataEntryDao.getPayrollEmployeesViewForEmployee(payGroup, employeeId);
	 		//List<PayrollEmployeeRecordBackend> employeesChangedByBackend = payrollDataEntryDao.getPayrollEmployeesChangedByBackend(payGroup);
	 		
	        List<EarnCode> earnCodes = payrollService.getEarningCodesByPayGroup(payGroup.getCompany(), payGroup.getPayGroup());
	        
	        Map<String, String> employeesPaymentMethods = payrollDataEntryDao.getPayrollEmployeesPaymentMethodForEmployee(payGroup, employeeId);
	        // just grab all employees for now and let the mapper take care of the "filtering"
	        List<EmployeeLeaveBalance> leaveBalances = payrollDataAccessDao.getPayrollEmployeesLeaveAccrual(payGroup);
	        String defaultRegularEarnCode = payrollDataEntryDao.getDefaultEarnCode(payGroup);
	        PayrollEmployeeEarnings employeeEarnings = new PayrollEmployeeEarnings();
	        employeeEarnings.setPayGroup(payGroup);
	        
	        //updateEmployees(employees,employeesChangedByBackend);
	        PayrollDataEntryMapper.EntityToDomainMapper domainMapper = new PayrollDataEntryMapper.EntityToDomainMapper(earnCodes, employeesPaymentMethods,
	                leaveBalances, payGroup, defaultRegularEarnCode);
	        employeeEarnings.setEmployeePayLineItems(domainMapper.translate(employees, earnings,isJobCostingEnabled(payGroup)));

	        return employeeEarnings;
		}

	
	@Override
	public void deleteEmployeeEarnings(PayGroup payGroup,
			List<EmployeePayLineItem> employeePayLineItems) {
		
		for (EmployeePayLineItem employee : employeePayLineItems) {
			payrollDataEntryDao.deleteEmployeeEarnings(payGroup, employee.getEmployee().getEmployeeId(), employee.getStartDate(), employee.getEndDate(), employee.getLineNumber());
		}
	}

	@Override
	public List<DepartmentSplit> getDepartmentDistributionForPayrollAndEmployee(
			PayGroup payGroup, String employeeId) {
		return payrollDataEntryDao.getDepartmentSplitsForEmployee(payGroup, employeeId);
		
	}

	@Override
	public List<JobCostingCode> getEmployeeJobCode(PayGroup payGroup, String employeeId) {
		List<JobCostingCode> empJobCostingCode= new ArrayList<JobCostingCode>();
        // This may need to be filtered for a single employee...
 		List<PayrollEmployeeJobCodeRecord> employees = payrollDataEntryDao.getPayrollEmployeesJobCodeViewForEmployee(payGroup, employeeId);
 		
 		for(PayrollEmployeeJobCodeRecord employeeJC: employees){
 			JobCostingCode jobCostingCode= new JobCostingCode();
 			jobCostingCode.setJobCode(employeeJC.getJobCode());
 			empJobCostingCode.add(jobCostingCode);
 		}
 		
		return empJobCostingCode;
	}
	private boolean isJobCostingEnabled(PayGroup payGroup){
		return "Y".equals(payGroup.getJobCosting());
	}
	private void filterEmployees(PayrollEmployeeEarnings employeeEarnings, PayGroup payGroup ) {
    	List<EmployeePayLineItem> filtereredPaylineItems = new ArrayList<EmployeePayLineItem>() ;

    	if(!PtgGatewayConstants.PARENT_PAYGROUP.equals(payGroup.getIsParent())){
    		for (EmployeePayLineItem employeePayLineItem : employeeEarnings.getEmployeePayLineItems()) {
    			boolean toInclude = false;
    			for (DepartmentEarning departmentEarning :  employeePayLineItem.getDepartmentEarnings()) {
    				if(departmentEarning.getDepartment().isHomeDepartment()){
						if(!StringUtils.isEmpty(payGroup.getDepartmentId()) 
								&& payGroup.getDepartmentId().equals(departmentEarning.getDepartment().getDeptId())){
							toInclude = true;
							break;
						} else  if(!StringUtils.isEmpty(payGroup.getLocation()) 
								&& payGroup.getLocation().equals(departmentEarning.getDepartment().getLocation())){
							toInclude = true;
							break;
						} else if(!StringUtils.isEmpty(payGroup.getDepartmentId()) && !StringUtils.isEmpty(payGroup.getLocation()) 
								&& payGroup.getLocation().equals(departmentEarning.getDepartment().getLocation())
								&& payGroup.getDepartmentId().equals(departmentEarning.getDepartment().getDeptId())){
							toInclude = true;
							break;
						}
    				}
    			}
    			if(toInclude){
    				filtereredPaylineItems.add(employeePayLineItem);
    			}	

    		}
    		employeeEarnings.setEmployeePayLineItems(filtereredPaylineItems);
    	}
    }
	 private void conversionServiceSplitPayLinesJobCosting(PayGroup payGroup, PayrollEmployeeEarnings employeeEarnings){
	        if(isJobCostingEnabled(payGroup) && !"Y".equals(payGroup.getOffCycle())){
	        	PayrollEmployeeEarningsEntity payrollEmployeeEarningsEntity=new PayrollEmployeeEarningsEntity();
	        	payrollEmployeeEarningsEntity.setPayrollEmployeeEarnings(employeeEarnings);
	        	employeeEarnings= conversionService.convert(payrollEmployeeEarningsEntity, PayrollEmployeeEarnings.class);
	        }

	    }
}
