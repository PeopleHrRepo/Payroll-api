package com.ptg.payroll.domain.payroll;

import java.util.ArrayList;
import java.util.List;

public class PayrollEmployeeEarnings {
    private PayGroup payGroup;
    private List<EmployeePayLineItem> employeePayLineItems = new ArrayList<EmployeePayLineItem>();

    /* Auto-Generated Code below. Please don't hand edit them. -- Starts */
    public PayGroup getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(PayGroup payGroup) {
        this.payGroup = payGroup;
    }

    public List<EmployeePayLineItem> getEmployeePayLineItems() {
        return employeePayLineItems;
    }

    public void setEmployeePayLineItems(List<EmployeePayLineItem> employeePayLineItems) {
        this.employeePayLineItems = employeePayLineItems;
    }
    /* All auto generated code -- Ends */
}
