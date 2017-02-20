package com.ptg.payroll.domain.payroll;
import java.math.BigDecimal;

public class Hours extends EarnUnit {

    private Double hours;
    private boolean isLeave;
	private BigDecimal overridePayRate;

    /* Auto-Generated Code below. Please don't hand edit them. -- Starts */
    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public boolean isLeave() {
        return isLeave;
    }

    public void setLeave(boolean isLeave) {
        this.isLeave = isLeave;
    }
    
	public BigDecimal getOverridePayRate() {
		return overridePayRate;
	}

	public void setOverridePayRate(BigDecimal overridePayRate) {
		this.overridePayRate = overridePayRate;
	}
    /* All auto generated code -- Ends */

}
