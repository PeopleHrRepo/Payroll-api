package com.payroll.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@Table(name="PS_T2_PE_PROCESS")
@NamedNativeQueries({
	@NamedNativeQuery(
			name="getPayrollGroupsByPayrollNumber",
			query="SELECT PR.COMPANY, PR.T2_HRP_PAYROLL_NUM, PR.LOCATION, PR.DEPTID, PR.PAYGROUP, PR.DESCR, PR.OFF_CYCLE, " + 
						"PR.PAY_BEGIN_DT, PR.PAY_END_DT, PR.CHECK_DT, PR.PAY_FREQUENCY, PR.T2_PE_STAT_DESCR, PR.T2_PE_PAYROLL_NAME, " + 
						"PR.T2_PE_STATUS, PR.T2_PP_REPORT_DT, PR.T2_OC_REASON_CD, PR.T2_PE_ENTRY_OPTN, PR.OPRID, NVL(PN.NAME,  'Admin') as OPR_NAME, PR.TIME_STAMP, " + 
						"PRA.T2_PE_PYMT_METHOD, PRA.T2_PE_DEL_OPTN, PRA.T2_DEL_TYPE, PRA.T2_PE_ADDR_OPTN, PRA.ATTN_TO, PRA.ADDRESS1, " + 
						"PRA.ADDRESS2, PRA.CITY, PRA.STATE, PRA.POSTAL, PRA.COUNTRY, PRA.DESCR254 "+
						"FROM PS_T2_PE_PROCESS pr "+
						"    LEFT JOIN PS_PERSON_NAME pn ON PR.OPRID = PN.EMPLID "+
						"    LEFT JOIN  PS_T2_PE_SPCL_ADDR PRA "+
						"        ON PR.COMPANY = PRA.COMPANY "+
						"        AND PR.T2_HRP_PAYROLL_NUM = PRA.T2_HRP_PAYROLL_NUM "+
						"        AND PR.PAYGROUP = PRA.PAYGROUP "+
						"WHERE PR.COMPANY = :company "+
						"AND PR.T2_HRP_PAYROLL_NUM = :payroll_number "+
						"AND PR.PAYGROUP = :paygroup", 
			resultClass=TimesheetPayrollGroup.class
	),
	@NamedNativeQuery(
			name="updatePayrollProcess",
			query="update ps_t2_pe_process prs "+ 
						"set prs.T2_PE_STATUS = :pe_status, "+
						"prs.OPRID = :oprId, "+
						"prs.TIME_STAMP = SYSDATE, "+
						"prs.T2_PE_STAT_DESCR = (select XLATSHORTNAME from psxlatitem "+  
						"where fieldname = 'T2_PE_STATUS' and fieldvalue = :pe_status) "+
						"where prs.company = :company  "+
						"AND PRS.T2_HRP_PAYROLL_NUM = :payroll_number "+
						"AND PRS.PAY_END_DT = :payEndDt "+
						"and prs.paygroup = :payGroup ",
			resultClass=TimesheetPayrollGroup.class
	),
	@NamedNativeQuery(
			name="updatePayGroupTimeEntryOption",
			query="update ps_t2_pe_process prs "+ 
						"set prs.T2_PE_ENTRY_OPTN = :payrollTimeEntryOption "+
						"where prs.company = :company  "+
						"AND PRS.T2_HRP_PAYROLL_NUM = :payroll_number "+
						"AND PRS.PAY_END_DT = :payEndDt "+
						"and prs.paygroup = :payGroup ",
			resultClass=TimesheetPayrollGroup.class
	)
})
public class TimesheetPayrollGroup implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="COMPANY")     
	private String company;
	
	@Id
	@Column(name="T2_HRP_PAYROLL_NUM")
	private Integer payrollNumber;
	
	@Id
	@Column(name="LOCATION")
	private String location;
		
	@Id
	@Column(name="DEPTID")
	private String deptId;
		
	@Id
	@Column(name="PAYGROUP")     
	private String payGroup;
	
	@Column(name="DESCR")     
	private String payGroupDesc;
	
	@Id
	@Column(name="OFF_CYCLE")     
	private String offCycle;
	
	@Temporal(TemporalType.DATE)
	@Column(name="PAY_BEGIN_DT")     
	private Date payBeginDt;
	
	@Id
	@Temporal(TemporalType.DATE)
	@Column(name="PAY_END_DT")     
	private Date payEndDt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CHECK_DT")     
	private Date checkDt;
	
	@Column(name="PAY_FREQUENCY")     
	private String payFrequency;
	
	@Column(name="T2_PE_STAT_DESCR")     
	private String statusDesc;
	
	@Column(name="T2_PE_PAYROLL_NAME")     
	private String payrollName;
		
	@Column(name="T2_PE_STATUS")     
	private String status;
	
	@Temporal(TemporalType.DATE)
	@Column(name="T2_PP_REPORT_DT")
	private Date reportDate;
	
	@Column(name="T2_OC_REASON_CD")
	private String ocReasonCd;
	
	@Column(name="T2_PE_ENTRY_OPTN")
	private String payrollEntryOption;
	
	@Column(name="T2_PE_BLACKOUT_FLG")
	private String blackoutFlag;
	
	@Column(name="OPRID")
	private String oprid;
	
	@Column(name="OPR_NAME")
	private String oprName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TIME_STAMP")
	private Date timeStamp;
	
	@Column(name="T2_PE_PYMT_METHOD")
	private String paymentMethod;
	
	@Column(name="T2_PE_DEL_OPTN")
	private String deliveryOption;

	@Column(name="T2_DEL_TYPE")
	private String deliveryMethod;

	@Column(name="T2_PE_ADDR_OPTN")
	private String addressOption;

	@Column(name="ATTN_TO")
	private String attention;

	@Column(name="ADDRESS1")
	private String address1;

	@Column(name="ADDRESS2")
	private String address2;

	@Column(name="CITY")
	private String city;

	@Column(name="STATE")
	private String state;

	@Column(name="POSTAL")
	private String postalCode;

	@Column(name="COUNTRY")
	private String country;

	@Column(name="DESCR254")
	private String specialDeliveryInstructions;
	
	@Column(name="SEQ_NBR")
    private String key;
	
	//Added - 7024
	@Column(name="T2_PE_JC_FLAG")
    private String cp;
	
	private String payrollRunId;
		
	private String payrollErrorMsg;
	


	public TimesheetPayrollGroup(){
		this.address1 = " ";
		this.address2 = " ";
		this.attention = " ";
		this.checkDt = null;
		this.city = " ";
		this.company = " ";
		this.country = " ";
		this.deliveryMethod = " ";
		this.deliveryOption = " ";
		this.deptId = " ";
		this.location = " ";
		this.ocReasonCd = " ";
		this.offCycle = " ";
		this.oprid = " ";
		this.payBeginDt = null;
		this.payEndDt = null;
		this.payFrequency = " ";
		this.payGroup = " ";
		this.payGroupDesc = " ";
		this.paymentMethod = " ";
		this.payrollName = " ";
		this.payrollNumber = 0;
		this.postalCode = " ";
		this.reportDate = null;
		this.specialDeliveryInstructions = " ";
		this.state = " ";
		this.status = " ";
		this.statusDesc = " ";
		this.timeStamp = null;
		//Added 7024
		this.cp="N";
	}

	
	
	public String getBlackoutFlag() {
		return blackoutFlag;
	}



	public void setBlackoutFlag(String blackoutFlag) {
		this.blackoutFlag = blackoutFlag;
	}



	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	

	public String getLocation() {
		return location;
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

	public String getPayGroup() {
		return payGroup;
	}

	public void setPayGroup(String payGroup) {
		this.payGroup = payGroup;
	}

	public String getPayGroupDesc() {
		return payGroupDesc;
	}

	public void setPayGroupDesc(String payGroupDesc) {
		this.payGroupDesc = payGroupDesc;
	}

	public String getOffCycle() {
		return offCycle;
	}

	public void setOffCycle(String offCycle) {
		this.offCycle = offCycle;
	}

	public Date getPayBeginDt() {
		return payBeginDt;
	}

	public void setPayBeginDt(Date payBeginDt) {
		this.payBeginDt = payBeginDt;
	}

	public Date getPayEndDt() {
		return payEndDt;
	}

	public void setPayEndDt(Date payEndDt) {
		this.payEndDt = payEndDt;
	}

	public Date getCheckDt() {
		return checkDt;
	}

	public void setCheckDt(Date checkDt) {
		this.checkDt = checkDt;
	}

	public String getPayFrequency() {
		return payFrequency;
	}

	public void setPayFrequency(String payFrequency) {
		this.payFrequency = payFrequency;
	}


	public String getPayrollName() {
		return payrollName;
	}

	public void setPayrollName(String payrollName) {
		this.payrollName = payrollName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public String getOcReasonCd() {
		return ocReasonCd;
	}

	public void setOcReasonCd(String ocReasonCd) {
		this.ocReasonCd = ocReasonCd;
	}

	public String getOprid() {
		return oprid;
	}

	public void setOprid(String oprid) {
		this.oprid = oprid;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	

	public Integer getPayrollNumber() {
		return payrollNumber;
	}

	public void setPayrollNumber(Integer payrollNumber) {
		this.payrollNumber = payrollNumber;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getDeliveryOption() {
		return deliveryOption;
	}

	public void setDeliveryOption(String deliveryOption) {
		this.deliveryOption = deliveryOption;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public String getAddressOption() {
		return addressOption;
	}

	public void setAddressOption(String addressOption) {
		this.addressOption = addressOption;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSpecialDeliveryInstructions() {
		return specialDeliveryInstructions;
	}

	public void setSpecialDeliveryInstructions(String specialDeliveryInstructions) {
		this.specialDeliveryInstructions = specialDeliveryInstructions;
	}

	public String getOprName() {
		return oprName;
	}

	public void setOprName(String oprName) {
		this.oprName = oprName;
	}

	public String getPayrollEntryOption() {
		return payrollEntryOption;
	}

	public void setPayrollEntryOption(String payrollEntryOption) {
		this.payrollEntryOption = payrollEntryOption;
	}

	public String getPayrollRunId() {
		return payrollRunId;
	}

	public void setPayrollRunId(String payrollRunId) {
		this.payrollRunId = payrollRunId;
	}

	public String getPayrollErrorMsg() {
		return payrollErrorMsg;
	}

	public void setPayrollErrorMsg(String payrollErrorMsg) {
		this.payrollErrorMsg = payrollErrorMsg;
	}

	public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }



    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}



	public String getCertifiedPayroll() {
		return cp;
	}



	public void setCertifiedPayroll(String certifiedPayroll) {
		this.cp = certifiedPayroll;
	} 
	
	
	
}