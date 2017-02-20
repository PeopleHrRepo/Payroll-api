package com.payroll.entity.pk;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@Embeddable
public class PayrollErrorKey implements Serializable {
    
    private static final long serialVersionUID = -8762014140207338063L;

    @Column(nullable=false, name="COMPANY")
    private String company;
    
    @Column(nullable=false, name="PAYGROUP")
    private String payrollGroup;

    @Column(nullable=false, name="PAY_END_DT")
    @Temporal (TemporalType.DATE)
    private Date payEndDate;

    @Column(nullable=false, name="OFF_CYCLE")
    private String offCycle = "N";

    @Column(nullable=false, name="T2_HRP_PAYROLL_NUM")
    private long payrollNumber = 0; 
    
    @Column(nullable=false, name="EMPLID")
    private String emplId = " ";
    
    @Column(nullable=false, name="DEPTID")
    private String deptId  = " ";
    
    @Column(nullable=false, name="ERNCD")
    private String earningCode = " ";
    
    @Column(nullable=false, name="T2_PE_ERROR_CODE")
    private String errorCode = " ";

    public PayrollErrorKey() {
        //super();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPayrollGroup() {
        return payrollGroup;
    }

    public void setPayrollGroup(String payrollGroup) {
        this.payrollGroup = payrollGroup;
    }

    public Date getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(Date payEndDate) {
        this.payEndDate = payEndDate;
    }

    public String getOffCycle() {
        return offCycle;
    }

    public void setOffCycle(String offCycle) {
        this.offCycle = offCycle;
    }

    public String getEarningCode() {
        return earningCode;
    }

    public void setEarningCode(String earningCode) {
        this.earningCode = earningCode;
    }

    public long getPayrollNumber() {
		return payrollNumber;
	}

	public void setPayrollNumber(long payrollNumber) {
		this.payrollNumber = payrollNumber;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getKeyAsString() {
		return ReflectionToStringBuilder.toString(this);	
    }

}
