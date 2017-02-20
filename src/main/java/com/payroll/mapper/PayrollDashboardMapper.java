package com.payroll.mapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.payroll.entity.EarnCode;
import com.payroll.entity.PayrollEarningCode;
import com.payroll.entity.ps.payrollentry.EmployeeDOM;
import com.ptg.payroll.domain.Employee;
import com.ptg.payroll.domain.EmployeeDetails;
import com.ptg.payroll.domain.PayGroupDelivery;
import com.ptg.payroll.domain.PayGroupDetail;
import com.ptg.payroll.domain.Payroll;
import com.ptg.payroll.domain.PayrollByRole;
import com.ptg.payroll.domain.PayrollGroup;
import com.ptg.payroll.domain.PayrollList;
import com.ptg.payroll.domain.SpecialPayroll;
import com.ptg.payroll.domain.payroll.EarningCode;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.domain.payroll.PayrollEmployee;


public class PayrollDashboardMapper {
	public static final String NEW_RECORD = "N";
	public static final String DELETE_RECORD = "D";
	
	public static class EntityToDomainMapper{
		public PayGroup translatePayrollGroup(PayrollGroup payrollGroup) {
	        
	        PayGroup payGroup = new PayGroup();
	        payGroup.setId(payrollGroup.getId().longValue());
	        payGroup.setCompany(payrollGroup.getCompany());
	        payGroup.setPayGroup(payrollGroup.getPayGroup());
	        payGroup.setPayEndDate(payrollGroup.getPayEndDate());
	        payGroup.setOffCycle(payrollGroup.getOffCycle());
	        payGroup.setPayrollNumber(payrollGroup.getPayrollNumber());
	        payGroup.setPayBeginDate(payrollGroup.getPayBeginDate());
	        payGroup.setPayFrequency(payrollGroup.getPayFrequency());
	        payGroup.setStatus(payrollGroup.getPayrollStatus());
	        payGroup.setReportDate(payrollGroup.getReportDate());

	        return payGroup;
	    }
		
		public SpecialPayroll translate(PayrollGroup payrollGroup, List<EmployeeDOM> employees, List<PayrollEarningCode> earningCodes, List<String> usedEarnCodes) {
	        SpecialPayroll specialPayroll = new SpecialPayroll();
	        Payroll payroll = new Payroll();
	        
	        List<EarningCode> earnCodesList = new ArrayList<EarningCode>();
	        
	        PayGroup payGroup = new PayGroup();
	        payGroup.setId(payrollGroup.getId().longValue());
	        payGroup.setCompany(payrollGroup.getCompany());
	        payGroup.setPayGroup(payrollGroup.getPayGroup());
	        payGroup.setPayEndDate(payrollGroup.getPayEndDate());
	        payGroup.setOffCycle(payrollGroup.getOffCycle());
	        payGroup.setPayrollNumber(payrollGroup.getPayrollNumber());
	        payGroup.setPayBeginDate(payrollGroup.getPayBeginDate());
	        payGroup.setPayFrequency(payrollGroup.getPayFrequency());
	        payGroup.setStatus(payrollGroup.getPayrollStatus());
	        payGroup.setReportDate(payrollGroup.getReportDate());

	        PayGroupDetail detail = new PayGroupDetail();
	        detail.setPayGroupDesc(payrollGroup.getPayGroupDescr());
	        detail.setLocation(payrollGroup.getLocation());
	        detail.setDepartmentId(payrollGroup.getDepartmentId());
	        detail.setCheckDate(payrollGroup.getCheckDate());
	        detail.setBlackoutFlag(payrollGroup.getBlackoutFlag());
	        detail.setPayrollName(payrollGroup.getPayrollName());
	        detail.setBrandName(payrollGroup.getBrandName());
	        detail.setDataEntryOption(payrollGroup.getDataEntryOption());
	        detail.setOperatorId(payrollGroup.getOperatorId());
	        detail.setOperatorName(payrollGroup.getOperatorName());
	        detail.setLastUpdatedDate(payrollGroup.getLastModifiedDate());
	        detail.setPaymentMethod(payrollGroup.getPaymentMethod());
	        
	        PayGroupDelivery delivery = new PayGroupDelivery();	       
	        delivery.setAddress1(payrollGroup.getAddress1());
	        delivery.setAddress2(payrollGroup.getAddress2());
	        delivery.setAddressOption(payrollGroup.getAddressOption());
	        delivery.setAttention(payrollGroup.getAttention());
	        delivery.setCity(payrollGroup.getCity());
	        delivery.setCountry(payrollGroup.getCountry());
	        delivery.setDeliveryInstruction(payrollGroup.getDeliveryInstruction());
	        delivery.setDeliveryOption(payrollGroup.getDeliveryOption());
	        delivery.setDeliveryType(payrollGroup.getDeliveryType());
	        delivery.setPostalCode(payrollGroup.getPostalCode());
	        delivery.setState(payrollGroup.getState());
	        
	        
	        payroll.setPayrollGroup(payGroup);
	        payroll.setPayGroupDetail(detail);
	        payroll.setPayGroupDelivery(delivery);
	        
	        List<PayrollEmployee> payrollEmployees = new PayrollDataAccessMapper.EntityToDomainMapper().translate(employees);
	        
	        for(PayrollEarningCode earnCode: earningCodes){
	        	EarningCode code = new EarningCode();
	        	code.setEarnCode(earnCode.getKey().getEarningCode());
	        	code.setSeqNumber(earnCode.getSeqNumber().intValue());
	        	if(usedEarnCodes.contains(code.getEarnCode()))
	        		code.setReported(true);
	        	earnCodesList.add(code);
	        }
	        specialPayroll.setEarningCodes(earnCodesList);
	        specialPayroll.setEmployees(payrollEmployees);
	        specialPayroll.setPayroll(payroll);

	        return specialPayroll;
	    }
		
		public List<EarningCode> translateEarningCodes(List<PayrollEarningCode> earningCodes) {
	        List<EarningCode> earnCodesList = new ArrayList<EarningCode>();
	        
	        for(PayrollEarningCode earnCode: earningCodes){
	        	EarningCode code = new EarningCode();
	        	code.setEarnCode(earnCode.getKey().getEarningCode());
	        	code.setSeqNumber(earnCode.getSeqNumber().intValue());
	        	earnCodesList.add(code);
	        }

	        return earnCodesList;
	    }
		
		public List<PayrollList> translate(List<PayrollByRole> dbPayrollsList, String userRole, int currentSortApprovalOrder){
			List<PayrollList> payrollsList = new ArrayList<PayrollList>();
			
			//first populate all the parents -- possible performace hit but we will have to anyway iterate this list twice. so this is cleaner
			for (PayrollByRole payrollByRole : dbPayrollsList) {
				PayrollList payrollList = null;
				if("1".equalsIgnoreCase(payrollByRole.getIsParent())) {
					payrollList = new PayrollList();
					if(!"D".equals(payrollByRole.getPayrollGroup().getStatus())){
						if("N".equals(payrollByRole.getPayrollGroup().getOffCycle())){
							if ("EPTAG".equals(userRole) || currentSortApprovalOrder == 1) {
								payrollList.setParentPayroll(payrollByRole);
							}else{
								payrollList.setParentPayroll(null);
							}
						}else{
							payrollList.setParentPayroll(payrollByRole);
						}

						payrollsList.add(payrollList);
					}
				}
			}
			
			//now populate the childs
			for (PayrollByRole payrollByRole : dbPayrollsList) {
				PayrollList payrollList = null;
				//PayrollByRole parentGroup = null;
				//PayrollByRole childGroup = null;
				
				if(!"1".equalsIgnoreCase(payrollByRole.getIsParent()) && "N".equals(payrollByRole.getPayrollGroup().getOffCycle())) {
					//identify if we already have parent for this, if not create one.
					payrollList = this.isParentExist(payrollsList,payrollByRole);
					if(null==payrollList){
						payrollList = new PayrollList();
						payrollList.setParentPayroll(null);
						payrollsList.add(payrollList);
					}	
					//update the values from parent ///quick fix to update status from child status, child properties will be ignored in UI
                	//we will probably change this payrollByROle with payrolls only if need
                	payrollByRole.getPayrollGroup().setStatus(payrollByRole.getChildPayrollStatus());
                	payrollByRole.getPayGroupDetail().setPayrollStatusDesc(payrollByRole.getChildPayrollStatusDesc());
                	payrollByRole.getPayGroupDetail().setOperatorId(payrollByRole.getChildOperatorId());
                	payrollByRole.getPayGroupDetail().setOperatorName(payrollByRole.getChildOperatorName());
                	payrollByRole.getPayGroupDetail().setLastUpdatedDate(payrollByRole.getChildLastUpdatedDate());
                	
					payrollList.getChildPayrolls().add(payrollByRole);
				}
				
			}
			
			return payrollsList;
		}
		
		private PayrollList isParentExist(List<PayrollList> payrollsList,
				PayrollByRole childPaygroup) {
			PayrollList recordFound = null;
			
			for (PayrollList payrollList : payrollsList) {
				if(null != payrollList.getParentPayroll() ){
					PayGroup parentPaygroup = payrollList.getParentPayroll().getPayrollGroup();
					if(parentPaygroup.getCompany().equals(childPaygroup.getPayrollGroup().getCompany())
						&& parentPaygroup.getPayEndDate().equals(childPaygroup.getPayrollGroup().getPayEndDate())
						&& parentPaygroup.getPayGroup().equals(childPaygroup.getPayrollGroup().getPayGroup())
						&& parentPaygroup.getPayrollNumber() == childPaygroup.getPayrollGroup().getPayrollNumber()
					){
						recordFound = payrollList;	
					}
					
				}
			}
			return recordFound;
		}

	
		
		public List<PayrollEmployee> translateEmployees(List<EmployeeDOM> employees){
			List<PayrollEmployee> employeesList = new ArrayList<PayrollEmployee>();
			for(EmployeeDOM empl : employees){
				PayrollEmployee employee = new PayrollEmployee();
				
				Employee emp = new Employee();
				EmployeeDetails details = new EmployeeDetails();
				
				emp.setEmployeeId(empl.getEmployeeId());
				details.setDepartmentId(empl.getDepartmentId());
				details.setLocation(empl.getLocation());
				details.setJobCode(empl.getJobCode());
				
				employee.setEmployee(emp);
				employee.setEmployeeDetails(details);
				employeesList.add(employee);
			}
			return employeesList;
		}
	}

	public static class DomainToEntityMapper{
    
	    public List<SpecialPayroll> translate(List<PayrollEmployee> employees){
	    	
	    	return null;
	    }
	}
	
	public static Map<String, List<PayrollEmployee>> findUpdatedEmployees(List<PayrollEmployee> existingEmployees, List<PayrollEmployee> newEmployees){
		Map<String, List<PayrollEmployee>> employeesMap = new HashMap<String, List<PayrollEmployee>>();
		
		List<String> existingEmployeeIds = new ArrayList<String>();
		List<String> newEmployeeIds = new ArrayList<String>();
		
		Map<String, PayrollEmployee> existingEmployeesMap = new HashMap<String, PayrollEmployee>();
		Map<String, PayrollEmployee> newEmployeesMap = new HashMap<String, PayrollEmployee>();
		
		List<PayrollEmployee> createEmployees = new ArrayList<PayrollEmployee>();
		List<PayrollEmployee> deleteEmployees = new ArrayList<PayrollEmployee>();
		
		
		for(PayrollEmployee existingEmp : existingEmployees){
			String emplId = existingEmp.getEmployee().getEmployeeId();
			existingEmployeeIds.add(emplId);
			existingEmployeesMap.put(emplId, existingEmp);
		}
		
		for(PayrollEmployee newEmp : newEmployees){
			String emplId = newEmp.getEmployee().getEmployeeId();
			newEmployeeIds.add(emplId);
			newEmployeesMap.put(emplId, newEmp);
		}
		
		for(Object obj : subtract(existingEmployeeIds, newEmployeeIds)){
			createEmployees.add(newEmployeesMap.get(obj.toString()));
		}
		for(Object obj : subtract(newEmployeeIds, existingEmployeeIds)){
			deleteEmployees.add(existingEmployeesMap.get(obj.toString()));
		}
		
		employeesMap.put(NEW_RECORD, createEmployees);
		employeesMap.put(DELETE_RECORD, deleteEmployees);
		return employeesMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Collection subtract(Collection coll1, Collection coll2)
	{
	    Collection result = new ArrayList(coll2);
	    result.removeAll(coll1);
	    return result;
	}
	
	public static List<String> findDeletedEarnCodes(List<PayrollEarningCode> existingEarnCodes, List<PayrollEarningCode> newEarnCodes){
		List<String> earnCodes =  new ArrayList<String>();
		
		if(existingEarnCodes == null || newEarnCodes ==  null ){
			return earnCodes;
		}
		
		for(PayrollEarningCode payrollEarnCode : existingEarnCodes){
			earnCodes.add(payrollEarnCode.getKey().getEarningCode());
		}
		
		for(PayrollEarningCode payrollEarnCode : newEarnCodes){
			String earnCode = payrollEarnCode.getKey().getEarningCode();
			if(earnCodes.contains(earnCode))
				earnCodes.remove(earnCode);
		}
		
		return earnCodes;
	}
	
	public static void disableEarnCode401KFlag(List<EarnCode> earnCodeList, boolean isAmbroseClient) {
		if (earnCodeList != null) {
			for (EarnCode earnCode : earnCodeList) {
				if (!isAmbroseClient) {
					earnCode.setForceSepChk("N");
				}
				earnCode.setShowExcludeFrom401K("N");
			}
		}
	}
	
}