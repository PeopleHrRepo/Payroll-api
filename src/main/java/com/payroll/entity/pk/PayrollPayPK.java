package com.payroll.entity.pk;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The primary key class for the PS_T2_PE_PAY_TBL database table.
 * 
 */
@Embeddable
public class PayrollPayPK  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public PayrollPayPK(){}
	
	@Column(nullable=false, name="COMPANY")
	private String company;
	
	@Column(nullable=false, name="PAYGROUP")
	private String payGroup;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, name="PAY_END_DT")
	private Date payEndDt;
	
	@Column(nullable=false, name="T2_HRP_PAYROLL_NUM")
	private Integer hrpPayrollNum;
	
	@Column(nullable=false, name="OFF_CYCLE")
	private String offCycle;
	
	@Column(nullable=false, name="SEQNUM")
	private Integer addLineNum;
	
	@Column(nullable=false, name="SEPCHK")
	private Integer sepChk;
	
	@Column(nullable=false, name="GROSSUP")
	private String grossup;
	
	@Column(nullable=false, name="EMPLID")
	private String emplId;
	
	@Column(nullable=false, name="EMPL_RCD")
	private Integer emplRcd;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false, name="T2_DUR")
	private Date timesheetDt;
	
	@Column(nullable=false, name="DEPTID")
	private String deptId;
	
	@Column(nullable=false, name="JOBCODE")
	private String jobCode;
	
	@Column(nullable=false, name="LOCATION")
	private String location;
	
	@Column(nullable=false, name="ERNCD")
	private String ernCd;		

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPayGroup() {
		return payGroup;
	}

	public void setPayGroup(String payGroup) {
		this.payGroup = payGroup;
	}

	public Date getPayEndDt() {
		return payEndDt;
	}

	public void setPayEndDt(Date payEndDt) {
		this.payEndDt = payEndDt;
	}

	public String getOffCycle() {
		return offCycle;
	}

	public void setOffCycle(String offCycle) {
		this.offCycle = offCycle;
	}

	public Integer getAddLineNum() {
		return addLineNum;
	}

	public void setAddLineNum(Integer addLineNum) {
		this.addLineNum = addLineNum;
	}

	public Integer getSepChk() {
		return sepChk;
	}

	public void setSepChk(Integer sepChk) {
		this.sepChk = sepChk;
	}

	public String getGrossup() {
		return grossup;
	}

	public void setGrossup(String grossup) {
		this.grossup = grossup;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public Integer getEmplRcd() {
		return emplRcd;
	}

	public void setEmplRcd(Integer emplRcd) {
		this.emplRcd = emplRcd;
	}

	public Date getTimesheetDt() {
		return timesheetDt;
	}

	public void setTimesheetDt(Date timesheetDt) {
		this.timesheetDt = timesheetDt;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}	

	public String getErnCd() {
		return ernCd;
	}

	public void setErnCd(String ernCd) {
		this.ernCd = ernCd;
	}

	public Integer getHrpPayrollNum() {
		return hrpPayrollNum;
	}

	public void setHrpPayrollNum(Integer hrpPayrollNum) {
		this.hrpPayrollNum = hrpPayrollNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((payGroup == null) ? 0 : payGroup.hashCode());
		result = prime * result
				+ ((payEndDt == null) ? 0 : payEndDt.hashCode());
		result = prime * result
				+ ((hrpPayrollNum == null) ? 0 : hrpPayrollNum.hashCode());
		result = prime * result
				+ ((offCycle == null) ? 0 : offCycle.hashCode());
		result = prime * result
				+ ((addLineNum == null) ? 0 : addLineNum.hashCode());
		result = prime * result
				+ ((sepChk == null) ? 0 : sepChk.hashCode());
		result = prime * result
				+ ((grossup == null) ? 0 : grossup.hashCode());
		result = prime * result
				+ ((emplId == null) ? 0 : emplId.hashCode());
		result = prime * result
				+ ((emplRcd == null) ? 0 : emplRcd.hashCode());
		result = prime * result
				+ ((timesheetDt == null) ? 0 : timesheetDt.hashCode());
		result = prime * result
				+ ((deptId == null) ? 0 : deptId.hashCode());
		result = prime * result
				+ ((jobCode == null) ? 0 : jobCode.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result
				+ ((ernCd == null) ? 0 : ernCd.hashCode());
				
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
		PayrollPayPK other = (PayrollPayPK) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company)){
			return false;
		}
		
		if (payGroup == null) {
			if (other.payGroup != null)
				return false;
		} else if (!payGroup.equals(other.payGroup)){
			return false;
		}
		
		if (payEndDt == null) {
			if (other.payEndDt != null)
				return false;
		} else if (!payEndDt.equals(other.payEndDt)){
			return false;
		}
		
		if (hrpPayrollNum == null) {
			if (other.hrpPayrollNum != null)
				return false;
		} else if (!hrpPayrollNum.equals(other.hrpPayrollNum)){
			return false;
		}
		
		if (offCycle == null) {
			if (other.offCycle != null)
				return false;
		} else if (!offCycle.equals(other.offCycle)){
			return false;
		}
		
		if (addLineNum == null) {
			if (other.addLineNum != null)
				return false;
		} else if (!addLineNum.equals(other.addLineNum)){
			return false;
		}
		
		if (sepChk == null) {
			if (other.sepChk != null)
				return false;
		} else if (!sepChk.equals(other.sepChk)){
			return false;
		}
		
		if (grossup == null) {
			if (other.grossup != null)
				return false;
		} else if (!grossup.equals(other.grossup)){
			return false;
		}
		
		if (emplId == null) {
			if (other.emplId != null)
				return false;
		} else if (!emplId.equals(other.emplId)){
			return false;
		}
		
		if (emplRcd == null) {
			if (other.emplRcd != null)
				return false;
		} else if (!emplRcd.equals(other.emplRcd)){
			return false;
		}
		
		if (timesheetDt == null) {
			if (other.timesheetDt != null)
				return false;
		} else if (!timesheetDt.equals(other.timesheetDt)){
			return false;
		}
		
		if (deptId == null) {
			if (other.deptId != null)
				return false;
		} else if (!deptId.equals(other.deptId)){
			return false;
		}
		
		if (jobCode == null) {
			if (other.jobCode != null)
				return false;
		} else if (!jobCode.equals(other.jobCode)){
			return false;
		}
		
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location)){
			return false;
		}
		
		if (ernCd == null) {
			if (other.ernCd != null)
				return false;
		} else if (!ernCd.equals(other.ernCd)){
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return "PayrollPayPK [company=" + company + ", payGroup="
				+ payGroup + ", payEndDt=" + payEndDt + ", hrpPayrollNum=" 
				+ hrpPayrollNum +", offCycle=" + offCycle + ", addLineNum="
				+ addLineNum + ", sepChk=" + sepChk + ", grossup=" + grossup
				+ ", emplId=" + emplId + ", emplRcd=" + emplRcd
				+ ", timesheetDt=" + timesheetDt + ", deptId=" + deptId
				+ ", jobCode=" + jobCode + ", location=" + location
				+ ", ernCd=" + ernCd + "]";
	}
	
	
}
