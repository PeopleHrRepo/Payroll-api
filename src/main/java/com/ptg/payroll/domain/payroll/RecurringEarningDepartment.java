package com.ptg.payroll.domain.payroll;
import com.ptg.payroll.domain.Department;

public class RecurringEarningDepartment {
	private String deptId;
	private String deptDesc;
	private String deptLongDesc;
	private Double amountForDepartment = 0d;
	
	public RecurringEarningDepartment() {
		
	}
	public RecurringEarningDepartment(Department dept) {
		this.deptId = dept.getDeptId();
		this.deptDesc = dept.getDeptDesc();
		this.deptLongDesc = dept.getDeptLongDesc();
	}

	public RecurringEarningDepartment(Department dept, Amount amount) {
		this.deptId = dept.getDeptId();
		this.deptDesc = dept.getDeptDesc();
		this.deptLongDesc = dept.getDeptLongDesc();
		this.amountForDepartment = amount.getAmount();
	}

	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptDesc() {
		return deptDesc;
	}
	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}
	public String getDeptLongDesc() {
		return deptLongDesc;
	}
	public void setDeptLongDesc(String deptLongDesc) {
		this.deptLongDesc = deptLongDesc;
	}

	public Double getAmountForDepartment() {
		return amountForDepartment;
	}
	public void setAmountForDepartment(Double amountForDepartment) {
		this.amountForDepartment = amountForDepartment;
	}

	/* All auto generated code -- Ends */

}
