package com.ptg.payroll.domain.payroll;
public class Amount extends EarnUnit {
    private Double amount = 0d;
    private Boolean isRecurring;
    private String jedRecurring;
    private String recurringId;
    private boolean excludeFrom401K;
    private boolean forceSepChk;

    public boolean isForceSepChk() {
		return forceSepChk;
	}

	public void setForceSepChk(boolean forceSepChk) {
		this.forceSepChk = forceSepChk;
	}

	public boolean isExcludeFrom401K() {
		return excludeFrom401K;
	}

	public void setExcludeFrom401K(boolean excludeFrom401K) {
		this.excludeFrom401K = excludeFrom401K;
	}

	/* Auto-Generated Code below. Please don't hand edit them. -- Starts */
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(Boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    public Boolean getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(Boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    public String getRecurringId() {
        return recurringId;
    }

    public void setRecurringId(String recurringId) {
        this.recurringId = recurringId;
    }

	public String getJedRecurring() {
		return jedRecurring;
	}

	public void setJedRecurring(String jedRecurring) {
		this.jedRecurring = jedRecurring;
	}

    /* All auto generated code -- Ends */

}
