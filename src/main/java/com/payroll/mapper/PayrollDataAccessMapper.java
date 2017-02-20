package com.payroll.mapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.payroll.entity.PayrollEarningCode;
import com.payroll.entity.pk.PayrollEarningCodeKey;
import com.payroll.entity.PayrollStagingRecord;
import com.payroll.entity.pk.PayrollStagingRecordKey;
import com.payroll.entity.ps.payrollentry.EmployeeDOM;
import com.ptg.payroll.domain.Employee;
import com.ptg.payroll.domain.EmployeeDetails;
import com.ptg.payroll.domain.payroll.EarningCode;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.domain.payroll.PayrollEmployee;

public class PayrollDataAccessMapper {
	
	
	public static class EntityToDomainMapper {
		public List<PayrollEmployee> translate(List<EmployeeDOM> employees) {
	        List<PayrollEmployee> employeeList = new ArrayList<PayrollEmployee>();
	        for (EmployeeDOM employee : employees) {
	        	PayrollEmployee payrollEmployee = new PayrollEmployee();
	        	Employee emp = new Employee();
		        EmployeeDetails details = new EmployeeDetails();
		        
		        emp.setEmployeeId(employee.getEmployeeId());
		        emp.setEmployeeRecord(employee.getEmployeeRecord().intValue());
		        emp.setEmployeeType(employee.getEmployeeType());
		        emp.setFirstName(employee.getFirstName());
		        emp.setFullOrPartTime(employee.getFullOrPartTime());
		        emp.setHireDate(employee.getHireDt());
		        emp.setLastName(employee.getLastName());
		        emp.setMiddleName(employee.getMiddleName());
		        emp.setRegOrTemp(employee.getRegOrTemp());
		        emp.setStatus(employee.getEmplStatus());
		        emp.setStatusDesc(employee.getEmplStatusDescr());
		        emp.setTerminationDate(employee.getTerminationDt());
		        emp.setUsWorkEligibility(employee.getUsWorkEligibilty());
		        
		        details.setDepartmentDesc(employee.getDepartmentDesc());
		        details.setDepartmentId(employee.getDepartmentId());
		        details.setJobCode(employee.getJobCode());
		        details.setLocation(employee.getLocation());
		        details.setLocationDesc(employee.getLocationDesc());
		        
		        payrollEmployee.setEmployee(emp);
		        payrollEmployee.setEmployeeDetails(details);
	            employeeList.add(payrollEmployee);
	        }

	        return employeeList;
	    }
		
		public List<PayrollEmployee> translate(List<EmployeeDOM> employees, List<EmployeeDOM> dbEmployees) {
	        List<PayrollEmployee> employeeList = new ArrayList<PayrollEmployee>();
	        for (EmployeeDOM employee : employees) {
	        	PayrollEmployee payrollEmployee = new PayrollEmployee();
	        	Employee emp = new Employee();
		        EmployeeDetails details = new EmployeeDetails();
		        
		        emp.setEmployeeId(employee.getEmployeeId());
		        emp.setEmployeeRecord(employee.getEmployeeRecord().intValue());
		        emp.setEmployeeType(employee.getEmployeeType());
		        emp.setFirstName(employee.getFirstName());
		        emp.setFullOrPartTime(employee.getFullOrPartTime());
		        emp.setHireDate(employee.getHireDt());
		        emp.setLastName(employee.getLastName());
		        emp.setMiddleName(employee.getMiddleName());
		        emp.setRegOrTemp(employee.getRegOrTemp());
		        emp.setStatus(employee.getEmplStatus());
		        emp.setStatusDesc(employee.getEmplStatusDescr());
		        emp.setTerminationDate(employee.getTerminationDt());
		        emp.setUsWorkEligibility(employee.getUsWorkEligibilty());
		        
		        details.setDepartmentDesc(employee.getDepartmentDesc());
		        details.setDepartmentId(employee.getDepartmentId());
		        details.setJobCode(employee.getJobCode());
		        details.setLocation(employee.getLocation());
		        details.setLocationDesc(employee.getLocationDesc());
		        
		        if(this.checkEmployeeExists(dbEmployees, employee.getEmployeeId())){
		        	details.setSelected(true);
		        }
		        
		        payrollEmployee.setEmployee(emp);
		        payrollEmployee.setEmployeeDetails(details);
	            employeeList.add(payrollEmployee);
	        }

	        return employeeList;
	    }
		
		private boolean checkEmployeeExists(List<EmployeeDOM> dbEmployees, String emplId){
			boolean exists = false;
			if(dbEmployees != null){
				for(EmployeeDOM employee : dbEmployees){
					if(emplId.equals(employee.getEmployeeId())){
						exists = true;
						break;
					}
				}
			}
			return exists;
		}
	}
	
	public static class DomainToEntityMapper {
		public List<PayrollStagingRecord> translateEmployees(List<PayrollEmployee> employees, PayGroup payGroup, String defaultRegErnCd, boolean isMove, String source) {
			
	        List<PayrollStagingRecord> payrollStagingRecordList = new ArrayList<PayrollStagingRecord>();
	        for (PayrollEmployee employee : employees) {
	        	
	        	PayrollStagingRecord entity = new PayrollStagingRecord();
				PayrollStagingRecordKey key = new PayrollStagingRecordKey();
				
				
				key.setCompany(payGroup.getCompany());
				key.setPayrollGroup(payGroup.getPayGroup());
				key.setPayEndDate(payGroup.getPayEndDate());
				key.setOffCycle(payGroup.getOffCycle());
				key.setPayrollNum(payGroup.getPayrollNumber());
				key.setProfileId(0);
				key.setSource(source);
				key.setSendFlag(isMove ? "N" : "U");
				
				key.setEarningCode(defaultRegErnCd);
				key.setEmplId(employee.getEmployee().getEmployeeId());
				key.setLocation(employee.getEmployeeDetails().getLocation());
				key.setDeptId(employee.getEmployeeDetails().getDepartmentId());
				key.setJobCode(employee.getEmployeeDetails().getJobCode());
				key.setReportedDate(payGroup.getPayEndDate());
				
				entity.setRecordKey(key);
				
				entity.setIsValid("Y");
				
				
				payrollStagingRecordList.add(entity);
	        }

	        return payrollStagingRecordList;
	    }
		
		public List<PayrollEarningCode> translateEarningCode(List<EarningCode> earningCodes, PayGroup payGroup, String operatorId) {
			
	        List<PayrollEarningCode> payrollEarningCodeList = new ArrayList<PayrollEarningCode>();
	        if(earningCodes != null) {
		        for (EarningCode earningCode : earningCodes) {
		        	
		        	PayrollEarningCode earnCode = new PayrollEarningCode();
		        	PayrollEarningCodeKey key = new PayrollEarningCodeKey();
					
					
					key.setCompany(payGroup.getCompany());
					key.setPayrollGroup(payGroup.getPayGroup());
					key.setPayEndDate(payGroup.getPayEndDate());
					key.setOffCycle(payGroup.getOffCycle());
					key.setPayrollNumber(payGroup.getPayrollNumber());
					key.setEarningCode(earningCode.getEarnCode());
					
					earnCode.setKey(key);
					earnCode.setLastUpdatedDate(new Date());
					earnCode.setOperatorId(operatorId);
					earnCode.setSeqNumber(new BigDecimal(earningCode.getSeqNumber()));
					earnCode.setStatus("A");
					
					
					payrollEarningCodeList.add(earnCode);
		        }
	        }

	        return payrollEarningCodeList;
	    }
	}
}
