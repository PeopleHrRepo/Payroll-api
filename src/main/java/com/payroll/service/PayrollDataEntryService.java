package com.payroll.service;

import java.util.List;

import com.payroll.entity.JobCostingCode;
import com.ptg.payroll.domain.payroll.DepartmentSplit;
import com.ptg.payroll.domain.payroll.EmployeePayLineItem;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.domain.payroll.PayrollEmployeeEarnings;


/**
 * <code>PayrollDataEntryService</code> provides the interface for the Payroll Entry Grid business methods
 * 
 */

public interface PayrollDataEntryService {

    /**
     * Initializes the payroll for that pay period
     * 
     * @param payGroup
     * @param operatorId
     */
    public void initializePayrollGroup(PayGroup payGroup, String operatorId);

    /**
     * Initializes and fetches all the employee earnings for that particular pay period
     * 
     * @param payGroup
     * @param operatorId
     * @return
     */
    public PayrollEmployeeEarnings initializeAndGetPayrollEmployees(PayGroup payGroup, String operatorId);
    
    /**
     * Fetches all the employee earnings for that particular pay period.
     * 
     * @param payGroup
     * @return
     */
    public PayrollEmployeeEarnings getPayrollEmployeeEarnings(PayGroup payGroup, String operatorId);
    
    /**
     * Fetches all the employee earnings for that particular pay period.
     * 
     * @param payGroup
     * @return
     */
    
    /**
     * Resets an individual employees earnings for a particular pay period.
     * @param payGroup
     * @param operatorId
     * @param employeeId
     * @return
     */
    public PayrollEmployeeEarnings resetPayrollEmployee(PayGroup payGroup, String operatorId, String employeeId);


    /**
     * Updates the employee earnings
     * 
     * @param payGroup
     * @param employeePayLineItems
     * @param operatorId
     */
    public void updatePayrollEmployeePayLineItems(PayGroup payGroup, List<EmployeePayLineItem> employeePayLineItems, String operatorId);
    
    /**
     * Delete Employee Earnings for the given pay period
     * 
     * @param payGroup
     * @param employeePayLineItems
     */
    public void deleteEmployeeEarnings(PayGroup payGroup, List<EmployeePayLineItem> employeePayLineItems);

    /**
     * Returns the department distributions for the paygroup and employeeid
     * @param payGroup
     * @param employeeId
     * @return
     */
	public List<DepartmentSplit> getDepartmentDistributionForPayrollAndEmployee(PayGroup payGroup, String employeeId);
	

    /**
     * Returns the employee default Job Code in a list 
     * @param payGroup
     * @param employeeId
     * @return
     */
	public List<JobCostingCode> getEmployeeJobCode(PayGroup payGroup, String employeeId);
}
