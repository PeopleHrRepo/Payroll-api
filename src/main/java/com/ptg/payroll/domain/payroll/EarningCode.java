package com.ptg.payroll.domain.payroll;
public class EarningCode {
	private String earnCode;
    private Integer seqNumber;
    private boolean	reported = false;   
    
    
    public boolean isReported() {
		return reported;
	}
	public void setReported(boolean reported) {
		this.reported = reported;
	}
	/* Auto-Generated Code below. Please don't hand edit them. -- Starts */
    public String getEarnCode() {
		return earnCode;
	}
	public void setEarnCode(String earnCode) {
		this.earnCode = earnCode;
	}
	public Integer getSeqNumber() {
		return seqNumber;
	}
	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}
    /* All auto generated code -- Ends */

}