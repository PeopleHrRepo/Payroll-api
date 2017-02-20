package com.payroll.entity;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * 
 * @author bshetty
 *
 */
@Entity
@Table(name="PS_T2_NEWHIRE_UPLD", uniqueConstraints=@UniqueConstraint(columnNames={"T2_EE_UPLD_RECD","T2_BATCH_NUMBER","COMPANY"}))
@JsonIgnoreProperties(ignoreUnknown=true, value={"new"})
@SequenceGenerator(sequenceName="T2_EE_UPLD_RECD", name="employeeRecordSeq")
//@IdClass(EmployeeRecordPK.class)
public class EmployeeRecord extends BaseEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="employeeRecordSeq")
	@Column(nullable=false, name="T2_EE_UPLD_RECD")
	private Long emplRecId;
	
	@Column(nullable=false, name="T2_BATCH_NUMBER")
	private Long employeeBatch;
	
	@Column(nullable=false, name="COMPANY", length=3)
	private String company;

	@Column(nullable=false, length=20, name="NATIONAL_ID")
	private String nationalId;
	@Column(nullable=true, length=11, name="ALTER_EMPLID")
	private String altEmplId = " ";
	@Column(nullable=false, length=25, name="FIRST_NAME")
	private String firstName;
	@Column(nullable=true, name="MIDDLE_INITIAL ")
	private Character middleInitial = ' ';
	@Column(nullable=false, length=25, name="LAST_NAME")
	private String lastName;
	@Column(nullable=false, length=10, name="BIRTHDATE")
	@Temporal (TemporalType.DATE)
	private Date birthday;
	@Column(nullable=false, name="SEX")
	private Character gender = ' ';
	@Column(nullable=true, length=10, name="T2_COMPANY_HIRE_DT")
	@Temporal (TemporalType.DATE)

	private Date employmentDate;
	@Column(nullable=true, length=2, name="T2_CITIZEN_STATUS")
	private String citizenshipStatus;
	@Column(nullable=true, length=10, name="HIRE_DT")
	@Temporal (TemporalType.DATE)
	private Date hireDate;
	@Column(nullable=false, length=30, name="ADDRESS1")
	private String homeAddressLine1;
	@Column(nullable=true, length=30, name="ADDRESS2")
	private String homeAddressLine2 = " ";
	@Column(nullable=false, length=35, name="CITY")
	private String homeCity = " ";
	@Column(nullable=false, length=3, name="COUNTRY")
	private String homeCountry = " ";
	@Column(nullable=false, length=12, name="PHONE")
	private String homePhone = " ";
	@Column(nullable=false, length=10, name="POSTAL")
	private String homePostalCode = " ";
	@Column(nullable=false, length=2, name="STATE")
	private String homeState = " ";
	@Column(nullable=false, length=10, name="ETHNIC_GRP_CD")
	private String ethnicity = " "; 
	@Column(nullable=false, length=3, name="EMPL_CLASS")
	private String employeeClass = " ";
	@Column(nullable=true, length=2, name="T2_LEAVE_TYPE")
	private String employeeOnLeave = " ";
	@Column(nullable=false, length=6, name="DEPTID")
	private String deptCode = " ";
	@Column(nullable=true, length=30, name="DEPTNAME")
	private String deptName = " ";
	@Column(nullable=true, name="EEO1CODE")
	private Character eeo1JobCategory = ' ';
	@Column(nullable=false,length=2, name="FLSA_DATA_TYPE")
	private String flsaStatus = " ";
	@Column(nullable=false, name="FULL_PART_TIME")
	private Character fullPartTime = ' ';
	@Column(nullable=true, name="MAR_STATUS")
	private Character maritalStatus = ' ';
	@Column(nullable=true, scale=7, precision=2, name="COMPRATE")
	private double compensationRate;
	@Column(nullable=true, scale=9, precision=2, name="ANNUAL_RT")
	private double estimatedAnnualWage;
	@Column(nullable=true, name="T2_FED_ALLOWANCES")
	private int fedTaxAllowances;
	@Column(nullable=true, scale=7, precision=2, name="T2_FED_WITHHOLD")
	private double fedTaxWithheldAmount;
	@Column(nullable=true, length=25, name="JOBCODE")
	private String jobCode = " ";
	@Column(nullable=true, length=30, name="JOBCODE_DESCR")
	private String jobTitle = " ";
	@Column(nullable=false, name="STATUS_CODE")
	private Character status = ' ';
	@Column(nullable=false, name="PAY_FREQUENCY")
	private Character payFrequency = ' ';
	@Column(nullable=true, length=10, name="PAYGROUP")
	private String payGroup = " ";
	@Column(nullable=false, name="REG_TEMP")
	private Character regularTemp = ' ';
	@Column(nullable=false, name="EMPL_TYPE")
	private Character salariedHourly = ' ';
	@Column(nullable=false, length=30, name="T2_WORK_ADDRESS")
	private String workAddressLine1 = " ";
	@Column(nullable=false, name="ELIGIBILITY_STATUS") 
	private Character workElibilityConfirmation = ' ';
	@Column(nullable=false, length=70, name="EMAIL_ADDR")
	private String workEmail = " ";
	@Column(nullable=true, scale=2, name="STD_HOURS")
	private int workWeekHours;
	@Column(nullable=true, length=25, name="WORKERS_COMP_CD")
	private String workerCompCode = " ";
	/* 4 digit class code */
	@Column(nullable=true, length=4, name="T2_CLASS_CODE")
	private String benefitsClass = " ";
	@Column(nullable=true, length=25, name="T2_LEAVE_PLAN_50")
	private String leavePlan50 = " ";
	@Column(nullable=true, length=25, name="T2_LEAVE_PLAN_51")
	private String leavePlan51 = " ";
	@Column(nullable=true, length=20, name="T2_PERSON_ID")
	private BigDecimal personId = BigDecimal.ZERO;
	@Column(nullable=false, length=10, name="LASTUPDDTTM")
	@Temporal (TemporalType.TIMESTAMP)
	private Date lastUpdated;
	
	
	
	/*
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name="T2_EE_UPLD_RECD", referencedColumnName="T2_EE_UPLD_RECD")
	private List<EmployeeRecordError> errors = new LinkedList();
	*/
	
	@Column(name="location", nullable=true, length=10)
	private String workLocationId = " ";
	@Column(name="T2_WORK_STATE", nullable=true, length=6)
	private String workLocationState  = " ";
	@Column(name="ADDRESS_TYPE", nullable=true, length=10)
	private String addressType = " ";
	@Column(name="T2_WORK_COUNTRY", length=2, nullable=true)
	private String workCountry = " ";
	
	/*
	@Transient
	private String deptId;
	@Transient
	private String supervisorId;
	*/
	
	@Column(nullable=true, length=26, name="BUSINESS_TITLE")
	private String businessTitle = " ";
	@Column(nullable=true, length=55, name="ERROR_TEXT")
	private String errorText = " ";
	
	@Column(nullable=true, length=80, name="FILENAME")
	private String fileName=" ";
	
	@Column(length=1, name="T2_PRE_HIRE_FLAG")
	private Character preHireFlag=' ';
	
	
	@Column(name="T2_PRE_HIRE_DT")
	@Temporal(TemporalType.DATE)
	private Date preHireDate;
	
	@Column(length=1, name="T2_I9_FLAG")
	private Character i9Flag= ' ';
	
	@Column(length=3, name="T2_ACTION_REASON")
	private String actionReason=" ";
	
	@Column(length=11, name="SUPERVISOR_ID")
	private String supervisorId=" ";

	@Column(nullable=false, length=1, name="DIRECTLY_TIPPED")
	private String tipped = " ";
	
	
	
	public Character getPreHireFlag() {
		return preHireFlag;
	}
	public void setPreHireFlag(Character preHireFlag) {
		this.preHireFlag = preHireFlag;
	}
	public Date getPreHireDate() {
		return preHireDate;
	}
	public void setPreHireDate(Date preHireDate) {
		this.preHireDate = preHireDate;
	}
	public Character getI9Flag() {
		return i9Flag;
	}
	public void setI9Flag(Character i9Flag) {
		this.i9Flag = i9Flag;
	}
	public String getActionReason() {
		return actionReason;
	}
	public void setActionReason(String actionReason) {
		this.actionReason = actionReason;
	}
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getEmployeeBatch() {
		return employeeBatch;
	}
	public void setEmployeeBatch(Long employeeBatch) {
		this.employeeBatch = employeeBatch;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getBusinessTitle() {
		return businessTitle;
	}
	
	public void setBusinessTitle(String businessTitle) {
		this.businessTitle = businessTitle;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	public Long getEmplRecId() {
		return emplRecId;
	}
	public void setEmplRecId(Long emplRecId) {
		this.emplRecId = emplRecId;
	}
	
	
	/*
	public List<EmployeeRecordError> getErrors() {
		return errors;
	}
	public void setErrors(List<EmployeeRecordError> errors) {
		this.errors = errors;
	}
	*/
	public BigDecimal getPersonId() {
		return personId;
	}
	public void setPersonId(BigDecimal personId) {
		this.personId = personId;
	}
	public String getNationalId() {
		return nationalId;
	}
	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}
	public String getAltEmplId() {
		return altEmplId;
	}
	public void setAltEmplId(String altEmplId) {
		this.altEmplId = altEmplId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Character getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(Character middleInitial) {
		this.middleInitial = middleInitial;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Character getGender() {
		return gender;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public Date getEmploymentDate() {
		return employmentDate;
	}
	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}
	public String getCitizenshipStatus() {
		return citizenshipStatus;
	}
	public void setCitizenshipStatus(String citizenshipStatus) {
		this.citizenshipStatus = citizenshipStatus;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getHomeAddressLine1() {
		return homeAddressLine1;
	}
	public void setHomeAddressLine1(String homeAddressLine1) {
		this.homeAddressLine1 = homeAddressLine1;
	}
	public String getHomeAddressLine2() {
		return homeAddressLine2;
	}
	public void setHomeAddressLine2(String homeAddressLine2) {
		this.homeAddressLine2 = homeAddressLine2;
	}
	public String getHomeCity() {
		return homeCity;
	}
	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}
	public String getHomeCountry() {
		return homeCountry;
	}
	public void setHomeCountry(String homeCountry) {
		this.homeCountry = homeCountry;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getHomePostalCode() {
		return homePostalCode;
	}
	public void setHomePostalCode(String homePostalCode) {
		this.homePostalCode = homePostalCode;
	}
	public String getHomeState() {
		return homeState;
	}
	public void setHomeState(String homeState) {
		this.homeState = homeState;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getEmployeeClass() {
		return employeeClass;
	}
	public void setEmployeeClass(String employeeClass) {
		this.employeeClass = employeeClass;
	}
	public String getEmployeeOnLeave() {
		return employeeOnLeave;
	}
	public void setEmployeeOnLeave(String employeeOnLeave) {
		this.employeeOnLeave = employeeOnLeave;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Character getEeo1JobCategory() {
		return eeo1JobCategory;
	}
	public void setEeo1JobCategory(Character eeo1JobCategory) {
		this.eeo1JobCategory = eeo1JobCategory;
	}
	public String getFlsaStatus() {
		return flsaStatus;
	}
	public void setFlsaStatus(String flsaStatus) {
		this.flsaStatus = flsaStatus;
	}
	public Character getFullPartTime() {
		return fullPartTime;
	}
	public void setFullPartTime(Character fullPartTime) {
		this.fullPartTime = fullPartTime;
	}
	public Character getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(Character maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public int getFedTaxAllowances() {
		return fedTaxAllowances;
	}
	public void setFedTaxAllowances(int fedTaxAllowances) {
		this.fedTaxAllowances = fedTaxAllowances;
	}
	public double getCompensationRate() {
		return compensationRate;
	}
	public void setCompensationRate(double compensationRate) {
		this.compensationRate = compensationRate;
	}
	public double getEstimatedAnnualWage() {
		return estimatedAnnualWage;
	}
	public void setEstimatedAnnualWage(double estimatedAnnualWage) {
		this.estimatedAnnualWage = estimatedAnnualWage;
	}
	public double getFedTaxWithheldAmount() {
		return fedTaxWithheldAmount;
	}
	public void setFedTaxWithheldAmount(double fedTaxWithheldAmount) {
		this.fedTaxWithheldAmount = fedTaxWithheldAmount;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public Character getStatus() {
		return status;
	}
	public void setStatus(Character status) {
		this.status = status;
	}
	public Character getPayFrequency() {
		return payFrequency;
	}
	public void setPayFrequency(Character payFrequency) {
		this.payFrequency = payFrequency;
	}
	public String getPayGroup() {
		return payGroup;
	}
	public void setPayGroup(String payGroup) {
		this.payGroup = payGroup;
	}
	public Character getRegularTemp() {
		return regularTemp;
	}
	public void setRegularTemp(Character regularTemp) {
		this.regularTemp = regularTemp;
	}
	public Character getSalariedHourly() {
		return salariedHourly;
	}
	public void setSalariedHourly(Character salariedHourly) {
		this.salariedHourly = salariedHourly;
	}
	public String getWorkAddressLine1() {
		return workAddressLine1;
	}
	public void setWorkAddressLine1(String workAddressLine1) {
		this.workAddressLine1 = workAddressLine1;
	}
	public Character getWorkElibilityConfirmation() {
		return workElibilityConfirmation;
	}
	public void setWorkElibilityConfirmation(Character workElibilityConfirmation) {
		this.workElibilityConfirmation = workElibilityConfirmation;
	}
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	public int getWorkWeekHours() {
		return workWeekHours;
	}
	public void setWorkWeekHours(int workWeekHours) {
		this.workWeekHours = workWeekHours;
	}
	public String getWorkerCompCode() {
		return workerCompCode;
	}
	public void setWorkerCompCode(String workerCompCode) {
		this.workerCompCode = workerCompCode;
	}
	public String getBenefitsClass() {
		return benefitsClass;
	}
	public void setBenefitsClass(String benefitsClass) {
		this.benefitsClass = benefitsClass;
	}
	public String getLeavePlan50() {
		return leavePlan50;
	}
	public void setLeavePlan50(String leavePlan50) {
		this.leavePlan50 = leavePlan50;
	}
	public String getLeavePlan51() {
		return leavePlan51;
	}
	public void setLeavePlan51(String leavePlan51) {
		this.leavePlan51 = leavePlan51;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	/*
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}*/
	public String getWorkLocationId() {
		return workLocationId;
	}
	public void setWorkLocationId(String workLocationId) {
		this.workLocationId = workLocationId;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getWorkCountry() {
		return workCountry;
	}
	public void setWorkCountry(String workCountry) {
		this.workCountry = workCountry;
	}
	public String getWorkLocationState() {
		return workLocationState;
	}
	public void setWorkLocationState(String workLocationState) {
		this.workLocationState = workLocationState;
	}
	public void setTipped(String tipped) {
		this.tipped = tipped;
	}
	public String getTipped() {
		return tipped;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emplRecId == null) ? 0 : emplRecId.hashCode());
		result = prime * result
				+ ((employeeBatch == null) ? 0 : employeeBatch.hashCode());
		result = prime * result
				+ ((nationalId == null) ? 0 : nationalId.hashCode());
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
		EmployeeRecord other = (EmployeeRecord) obj;
		if (emplRecId == null) {
			if (other.emplRecId != null)
				return false;
		} else if (!emplRecId.equals(other.emplRecId))
			return false;
		if (employeeBatch == null) {
			if (other.employeeBatch != null)
				return false;
		} else if (!employeeBatch.equals(other.employeeBatch))
			return false;
		if (nationalId == null) {
			if (other.nationalId != null)
				return false;
		} else if (!nationalId.equals(other.nationalId))
			return false;
		return true;
	}
}