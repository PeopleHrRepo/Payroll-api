package com.ptg.payroll.domain;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
@JsonInclude(Include.NON_NULL)
public class Payroll {
    @JsonUnwrapped
    private PayGroup payrollGroup;
    @JsonUnwrapped
    private PayGroupDetail payGroupDetail;
    private PayGroupDelivery payGroupDelivery;
    private boolean isRecallable = false;

    /* Auto-Generated Code below. Please don't hand edit them. -- Starts */
    

    public PayGroup getPayrollGroup() {
		return payrollGroup;
	}

	public void setPayrollGroup(PayGroup payrollGroup) {
		this.payrollGroup = payrollGroup;
	}

	public PayGroupDetail getPayGroupDetail() {
        return payGroupDetail;
    }

    public void setPayGroupDetail(PayGroupDetail payGroupDetail) {
        this.payGroupDetail = payGroupDetail;
    }
    
	public PayGroupDelivery getPayGroupDelivery() {
		return payGroupDelivery;
	}

	public void setPayGroupDelivery(PayGroupDelivery payGroupDelivery) {
		this.payGroupDelivery = payGroupDelivery;
	}

	public boolean isRecallable() {
		return isRecallable;
	}

	public void setRecallable(boolean isRecallable) {
		this.isRecallable = isRecallable;
	}
	
	/* All auto generated code -- Ends */
}
