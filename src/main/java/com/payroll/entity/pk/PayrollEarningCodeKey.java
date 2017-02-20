package com.payroll.entity.pk;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@Embeddable
public class PayrollEarningCodeKey implements Serializable {
    
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
    
    @Column(nullable=false, name="ERNCD")
    private String earningCode;

    public PayrollEarningCodeKey() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((earningCode == null) ? 0 : earningCode.hashCode());
		result = prime * result
				+ ((offCycle == null) ? 0 : offCycle.hashCode());
		result = prime * result
				+ ((payEndDate == null) ? 0 : payEndDate.hashCode());
		result = prime * result
				+ ((payrollGroup == null) ? 0 : payrollGroup.hashCode());
		result = prime * result + (int) (payrollNumber ^ (payrollNumber >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PayrollEarningCodeKey other = (PayrollEarningCodeKey) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (earningCode == null) {
			if (other.earningCode != null)
				return false;
		} else if (!earningCode.equals(other.earningCode))
			return false;
		if (offCycle == null) {
			if (other.offCycle != null)
				return false;
		} else if (!offCycle.equals(other.offCycle))
			return false;
		if (payEndDate == null) {
			if (other.payEndDate != null)
				return false;
		} else if (!payEndDate.equals(other.payEndDate))
			return false;
		if (payrollGroup == null) {
			if (other.payrollGroup != null)
				return false;
		} else if (!payrollGroup.equals(other.payrollGroup))
			return false;
		if (payrollNumber != other.payrollNumber)
			return false;
		return true;
	}

	public String getKeyAsString() {
		return ReflectionToStringBuilder.toString(this);	
    }

}