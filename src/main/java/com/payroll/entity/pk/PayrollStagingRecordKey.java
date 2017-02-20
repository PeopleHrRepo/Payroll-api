package com.payroll.entity.pk;
import static com.ptg.payroll.domain.PayrollImportEarning.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Embeddable
public class PayrollStagingRecordKey implements Serializable {
    
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
    private long payrollNum = 0; 

    @Column(nullable=false, name="EMPLID")
    private String emplId = " ";

    @Column(nullable=false, name="LOCATION")
    private String location=" ";

    @Column(nullable=false, name="DEPTID")
    private String deptId=" ";

    @Column(nullable=false, name="JOBCODE")
    private String jobCode=" ";

    @Column(nullable=false, name="T2_DUR")
    @Temporal (TemporalType.DATE)
    private Date reportedDate;

    @Column(nullable=false, name="T2_PE_PROFILE_ID")
    private int profileId;
    
    @Column(nullable=false, name="T2_PE_SOURCE")
    private String source;
    
    @Column(nullable=false, name="ERNCD")
    private String earningCode;
    
    @Column(nullable = false, name = "T2_PE_SEND_FLAG")
    private String sendFlag = "N";
    
    @Column(nullable = false, name = "REQUEST_NBR")
    private BigDecimal leaveRequestId = new BigDecimal(0);
    
    @Column(nullable = false, name = "SEQNUM")
    private Integer addLineNum = 0;

    
    public PayrollStagingRecordKey() {
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

    public long getPayrollNum() {
        return payrollNum;
    }

    public void setPayrollNum(long payrollNum) {
        this.payrollNum = payrollNum;
    }

    public String getEmplId() {
        return emplId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public String getLocation() {
        return location;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getEarningCode() {
        return earningCode;
    }

    public void setEarningCode(String earningCode) {
        this.earningCode = earningCode;
    }
    
    public String getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(String sendFlag) {
        this.sendFlag = sendFlag;
    }
    
    public Integer getAddLineNum() {
		return addLineNum;
	}

	public void setAddLineNum(Integer addLineNum) {
		this.addLineNum = addLineNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result
				+ ((earningCode == null) ? 0 : earningCode.hashCode());
		result = prime * result + ((emplId == null) ? 0 : emplId.hashCode());
		result = prime * result + ((jobCode == null) ? 0 : jobCode.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((offCycle == null) ? 0 : offCycle.hashCode());
		result = prime * result
				+ ((payEndDate == null) ? 0 : payEndDate.hashCode());
		result = prime * result
				+ ((payrollGroup == null) ? 0 : payrollGroup.hashCode());
		result = prime * result + (int) (payrollNum ^ (payrollNum >>> 32));
		result = prime * result + profileId;
		result = prime * result
				+ ((sendFlag == null) ? 0 : sendFlag.hashCode());
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		return result;
	}

	public BigDecimal getLeaveRequestId() {
		return leaveRequestId;
	}

	public void setLeaveRequestId(BigDecimal leaveRequestId) {
		this.leaveRequestId = leaveRequestId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PayrollStagingRecordKey other = (PayrollStagingRecordKey) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (deptId == null) {
			if (other.deptId != null)
				return false;
		} else if (!deptId.equals(other.deptId))
			return false;
		if (earningCode == null) {
			if (other.earningCode != null)
				return false;
		} else if (!earningCode.equals(other.earningCode))
			return false;
		if (emplId == null) {
			if (other.emplId != null)
				return false;
		} else if (!emplId.equals(other.emplId))
			return false;
		if (jobCode == null) {
			if (other.jobCode != null)
				return false;
		} else if (!jobCode.equals(other.jobCode))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (offCycle == null) {
			if (other.offCycle != null)
				return false;
		} else if (!offCycle.equals(other.offCycle))
			return false;
		if (payEndDate == null) {
			if (other.payEndDate != null)
				return false;
		} else if (!(0 == payEndDate.compareTo(other.payEndDate)))
			return false;
		if (payrollGroup == null) {
			if (other.payrollGroup != null)
				return false;
		} else if (!payrollGroup.equals(other.payrollGroup))
			return false;
		if (payrollNum != other.payrollNum)
			return false;
		if (profileId != other.profileId)
			return false;
		if (sendFlag == null) {
			if (other.sendFlag != null)
				return false;
		} else if (!sendFlag.equals(other.sendFlag))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		if (reportedDate == null) {
			if (other.reportedDate != null)
				return false;
		} else if (!(0 == reportedDate.compareTo(other.reportedDate)))
			return false;
		if (addLineNum == null) {
			if (other.addLineNum != null)
				return false;
		} else if (!addLineNum.equals(other.addLineNum))
			return false;
		return true;
	}

	public String getKeyAsString(Long id) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(id).append(DELIMITER).append(this.emplId).append(DELIMITER).
    	append(this.location).append(DELIMITER).append(this.jobCode).append(DELIMITER).
    	append(this.reportedDate).append(DELIMITER).append(this.profileId).append(DELIMITER).
    	append(this.source).append(DELIMITER).append(this.earningCode).append(DELIMITER).
    	append(deptId).append(DELIMITER).append(reportedDate).append(DELIMITER).append(addLineNum);
    	return sb.toString();		
    }

	@Override
	public String toString() {
		return "PayrollStagingRecordKey [company=" + company
				+ ", payrollGroup=" + payrollGroup + ", payEndDate="
				+ payEndDate + ", offCycle=" + offCycle + ", payrollNum="
				+ payrollNum + ", emplId=" + emplId + ", location=" + location
				+ ", deptId=" + deptId + ", jobCode=" + jobCode
				+ ", reportedDate=" + reportedDate + ", profileId=" + profileId
				+ ", source=" + source + ", earningCode=" + earningCode
				+ ", addLineNum= " + addLineNum 
				+ ", sendFlag=" + sendFlag + ", leaveRequestId="
				+ leaveRequestId + "]";
	}

}
