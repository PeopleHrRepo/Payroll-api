package com.ptg.payroll.domain.payroll;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EarnUnit {
    private String earnCode;
    private String paymentType;
    private Date reportedDate;
    private BigDecimal multiFactor;
    private Long seqNumber;
    private Integer additionalNumber;
    private String source;
    private Date earnBeginDate;
    private Date earnEndDate; // yyyy-MM-dd
    private Integer sepCheck = 0;
    private String displayOnly;
    private Integer addlSeq=0;
    private Integer orderIndex= 0;
    
    /* Auto-Generated Code below. Please don't hand edit them. -- Starts */

    public String getEarnCode() {
        return earnCode;
    }

    public void setEarnCode(String earnCode) {
        this.earnCode = earnCode;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public BigDecimal getMultiFactor() {
        return multiFactor;
    }

    public void setMultiFactor(BigDecimal multiFactor) {
        this.multiFactor = multiFactor;
    }

    public Long getSeqNumber() {
        return seqNumber;
    }

    public void setSeqNumber(Long seqNumber) {
        this.seqNumber = seqNumber;
    }

    public Integer getAdditionalNumber() {
        return additionalNumber;
    }

    public void setAdditionalNumber(Integer additionalNumber) {
        this.additionalNumber = additionalNumber;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getEarnBeginDate() {
        return earnBeginDate;
    }

    public void setEarnBeginDate(Date earnBeginDate) {
        this.earnBeginDate = earnBeginDate;
    }

    public Date getEarnEndDate() {
        return earnEndDate;
    }

    public void setEarnEndDate(Date earnEndDate) {
        this.earnEndDate = earnEndDate;
    }

    public Integer getSepCheck() {
        return sepCheck;
    }

    public void setSepCheck(Integer sepCheck) {
        this.sepCheck = sepCheck;
    }

	public String getDisplayOnly() {
		return displayOnly;
	}

	public void setDisplayOnly(String displayOnly) {
		this.displayOnly = displayOnly;
	}

	public Integer getAddlSeq() {
		return addlSeq;
	}

	public void setAddlSeq(Integer addlSeq) {
		this.addlSeq = addlSeq;
	}

	public Integer getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Integer orderIndex) {
		this.orderIndex = orderIndex;
	}

    /* All auto generated code -- Ends */
}
