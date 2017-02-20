package com.ptg.payroll.domain;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rlakshmiraj
 * 
 */
public class PayrollList {

	private PayrollByRole parentPayroll;

	private List<PayrollByRole> childPayrolls = new ArrayList<PayrollByRole>();

	/**
	 * @return the parentPayroll
	 */
	public Payroll getParentPayroll() {
		return parentPayroll;
	}

	/**
	 * @param parentPayroll
	 *            the parentPayroll to set
	 */
	public void setParentPayroll(PayrollByRole parentPayroll) {
		this.parentPayroll = parentPayroll;
	}

	/**
	 * @return the childPayrolls
	 */
	public List<PayrollByRole> getChildPayrolls() {
		return childPayrolls;
	}

	/**
	 * @param childPayrolls
	 *            the childPayrolls to set
	 */
	public void setChildPayrolls(List<PayrollByRole> childPayrolls) {
		this.childPayrolls = childPayrolls;
	}
}