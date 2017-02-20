package com.ptg.payroll.domain.payroll;
import java.util.ArrayList;
import java.util.List;

import com.ptg.payroll.domain.Department;

public class RecurringEarningDistribution {

	private String earnCode;
	private Double totalAmount = 0d;
	private List<RecurringEarningDepartment> recurringEarningDepartments = new ArrayList<RecurringEarningDepartment>();

	public RecurringEarningDistribution(){
		
	}
	
	public RecurringEarningDistribution(Department dept,
			Amount amount) {
		this.earnCode = amount.getEarnCode();
		this.addRecurringEarningDepartments( new RecurringEarningDepartment(dept, amount));
	}
	
	public String getEarnCode() {
		return earnCode;
	}
	
	public void setEarnCode(String earnCode) {
		this.earnCode = earnCode;
	}

	//no setter to set total amount, it should be set when adding a department or adding a list of departments
	public Double getTotalAmount() {
		return totalAmount;
	}

	public List<RecurringEarningDepartment> getRecurringEarningDepartments() {
		return recurringEarningDepartments;
	}

	public void setRecurringEarningDepartments(
			List<RecurringEarningDepartment> recurringEarningDepartments) {
		this.recurringEarningDepartments = recurringEarningDepartments;
	}

	public void addRecurringEarningDepartments(RecurringEarningDepartment recurringEarningDepartment){
		this.recurringEarningDepartments.add(recurringEarningDepartment);
		this.totalAmount = this.totalAmount + recurringEarningDepartment.getAmountForDepartment();
	}

	public void updateRecurringEarningDepartment(Department dept, Amount amount){
		RecurringEarningDepartment recurringDeptToUpdate = null;
		for (RecurringEarningDepartment recurringDept : this.recurringEarningDepartments) {
			if(recurringDept.getDeptId().equals(dept.getDeptId())){
				recurringDeptToUpdate =  recurringDept;
				recurringDeptToUpdate.setAmountForDepartment(recurringDeptToUpdate.getAmountForDepartment() + amount.getAmount());
				this.totalAmount = this.totalAmount + amount.getAmount();
				break;
			}
		}
		if(null == recurringDeptToUpdate){
			recurringDeptToUpdate =  new RecurringEarningDepartment(dept, amount);	
			this.addRecurringEarningDepartments(recurringDeptToUpdate);
		}
	}    
	/* All auto generated code -- Ends */
}
