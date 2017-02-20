package com.ptg.payroll.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.payroll.constant.CommonConstants;
import com.payroll.constant.ValidationConstants;
import com.payroll.entity.BaseEntity;

public class PersonDetails extends BaseEntity {

	private static final long serialVersionUID = -7389585423540910114L;

	private String pEmplid;

	@NotNull(message = ValidationConstants.PERSON_COMPANY_NOT_NULL)
	@Size(min = 1, max = 3, message = ValidationConstants.PERSON_COMPANY_SIZE)
	private String companyId;

	@NotNull(message = ValidationConstants.PERSON_NATIONAL_ID_SIZE)
	@Size(max = 20, message = ValidationConstants.PERSON_NATIONAL_ID_SIZE)
	private String nationalId;

	@NotNull(message = ValidationConstants.PERSON_FORMOF_ADDRESS_NOT_NULL)
	@Size(min = 1, max = 4, message = ValidationConstants.PERSON_FORMOF_ADDRESS_SIZE)
	@Pattern(regexp = CommonConstants.ALPHABET_REGEX, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String formOfAddressId;

	@NotNull(message = ValidationConstants.PERSON_FIRST_NAME_NOT_NULL)
	@Size(max = 30, message = ValidationConstants.PERSON_FIRST_NAME_SIZE)
	@Pattern(regexp = CommonConstants.ALPHABET_REGEX, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String firstName;

	@NotNull(message = ValidationConstants.PERSON_LAST_NAME_NOT_NULL)
	@Size(max = 30, message = ValidationConstants.PERSON_LAST_NAME_SIZE)
	@Pattern(regexp = CommonConstants.ALPHABET_REGEX, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String lastName;

	@NotNull(message = ValidationConstants.PERSON_MIDDLE_NAME_NOT_NULL)
	@Size(max = 30, message = ValidationConstants.PERSON_MIDDLE_NAME_SIZE)
	@Pattern(regexp = CommonConstants.ALPHABET_REGEX, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String middleName;

	@NotNull(message = ValidationConstants.PERSON_COUNTRY_ID_NOT_NULL)
	@Size(max = 3, message = ValidationConstants.PERSON_COUNTRY_ID_SIZE)
	private String countryId;

	@NotNull(message = ValidationConstants.PERSON_ADDRESS1_NOT_NULL)
	@Size(max = 55, message = ValidationConstants.PERSON_ADDRESS1_SIZE)
	private String address1;

	@NotNull(message = ValidationConstants.PERSON_ADDRESS2_NOT_NULL)
	@Size(max = 55, message = ValidationConstants.PERSON_ADDRESS2_SIZE)
	private String address2;

	@NotNull(message = ValidationConstants.PERSON_CITY_NOT_NULL)
	@Size(max = 30, message = ValidationConstants.PERSON_CITY_SIZE)
	@Pattern(regexp = CommonConstants.ALPHABET_REGEX, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String city;

	@NotNull(message = ValidationConstants.PERSON_COUNTRY_NOT_NULL)
	@Size(min = 1, message = ValidationConstants.PERSON_COUNTRY_SIZE)
	@Pattern(regexp = CommonConstants.ALPHABET_REGEX_SPACE, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String country;

	@NotNull(message = ValidationConstants.PERSON_STATE_NOT_NULL)
	@Size(max = 6, message = ValidationConstants.PERSON_STATE_SIZE)
	@Pattern(regexp = CommonConstants.ALPHABET_REGEX, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String stateId;

	@NotNull(message = ValidationConstants.PERSON_POSTAL_NOT_NULL)
	@Size(max = 12, message = ValidationConstants.PERSON_POSTAL_SIZE)
	@Pattern(regexp = CommonConstants.NUMBER_REGEX, message = ValidationConstants.PERSON_NUMBER_INVALID)
	private String postalCode;

	@Size(max = 24, message = ValidationConstants.PERSON_HOME_PHONE_SIZE)
	@Pattern(regexp = CommonConstants.NUMBER_REGEX, message = ValidationConstants.PERSON_NUMBER_INVALID)
	private String homePhone;

	@NotNull(message = ValidationConstants.PERSON_HOME_EMAIL_NOT_NULL)
	@Size(max = 70, message = ValidationConstants.PERSON_HOME_EMAIL_SIZE)
	@Email(message = ValidationConstants.PERSON_EMAIL_VALIDATION)
	private String homeEmail;

	@NotNull(message = ValidationConstants.PERSON_GENDER_ID_NOT_NULL)
	@Size(max = 1, message = ValidationConstants.PERSON_GENDER_ID_SIZE)
	@Pattern(regexp = CommonConstants.ALPHABET_REGEX, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String genderId;

	@Pattern(regexp = CommonConstants.ALPHABET_REGEX, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String ethnicityId;

	@NotNull(message = ValidationConstants.PERSON_MILITARY_STATUS_NOT_NULL)
	@Size(max = 1, message = ValidationConstants.PERSON_MILITARY_STATUS_SIZE)
	@Pattern(regexp = CommonConstants.ALPHABET_REGEX, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String militryStatus;

	@NotNull(message = ValidationConstants.PERSON_ENTRY_DATE_NOT_NULL)
	@Pattern(regexp = CommonConstants.DATE_REGEX, message = ValidationConstants.PERSON_DATE_INVALID)
	private String entryDate;

	@NotNull(message = ValidationConstants.PERSON_ENTRY_REASON_NOT_NULL)
	@Size(max = 3, message = ValidationConstants.PERSON_ENTRY_REASON_SIZE)
	@Pattern(regexp = CommonConstants.ALPHABET_REGEX, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String entryReason;

	@NotNull(message = ValidationConstants.PERSON_EMP_TYPE_NOT_NULL)
	@Size(max = 3, message = ValidationConstants.PERSON_EMP_TYPE_SIZE)
	@Pattern(regexp = CommonConstants.ALPHABET_REGEX, message = ValidationConstants.PERSON_ALPHABETS_INVALID)
	private String emplType;

	@NotNull(message = ValidationConstants.PERSON_EMP_REQUEST_TEMP_NOT_NULL)
	@Size(max = 1, message = ValidationConstants.PERSON_EMP_REQUEST_TEMP_SIZE)
	private String empReqTemp;

	@NotNull(message = ValidationConstants.PERSON_EMP_CLASS_NOT_NULL)
	@Size(max = 3, message = ValidationConstants.PERSON_EMP_CLASS_SIZE)
	private String empClass;

	@NotNull(message = ValidationConstants.PERSON_EMP_ANNUAL_WAGES_NOT_NULL)
	@Pattern(regexp = CommonConstants.ANNUAL_WAGES_REGEX, message = ValidationConstants.PERSON_NUMBER_OR_DECIMAL_NUMBER_INVALID)
	private String annual_wages;

	@NotNull(message = ValidationConstants.PERSON_BENIFIT_CLASS_NOT_NULL)
	@Size(max = 2, message = ValidationConstants.PERSON_BENIFIT_CLASS_SIZE)
	private String benefitClassId;

	@NotNull(message = ValidationConstants.PERSON_STANDARD_HOURS_NOT_NULL)
	@Pattern(regexp = CommonConstants.STANDARD_HOURS_REGEX, message = ValidationConstants.PERSON_NUMBER_OR_DECIMAL_NUMBER_INVALID)
	private String stdHours;

	@NotNull(message = ValidationConstants.PERSON_BUSINESS_TITLE_NOT_NULL)
	@Size(max = 5, message = ValidationConstants.PERSON_BUSINESS_TITLE_SIZE)
	private String bussinessTitle;

	@NotNull(message = ValidationConstants.PERSON_JOB_CODE_NOT_NULL)
	@Size(max = 6, message = ValidationConstants.PERSON_JOB_CODE_SIZE)
	private String jobCode;

	@NotNull(message = ValidationConstants.PERSON_FLSA_STATUS_NOT_NULL)
	@Size(max = 1, message = ValidationConstants.PERSON_FLSA_STATUS_SIZE)
	private String flsaStatus;

	@NotNull(message = ValidationConstants.PERSON_SUPERVISOR_NOT_NULL)
	@Size(max = 11, message = ValidationConstants.PERSON_SUPERVISOR_SIZE)
	private String supervisorId;

	@NotNull(message = ValidationConstants.PERSON_COMPENSATION_BASIC_NOT_NULL)
	@Size(max = 5, message = ValidationConstants.PERSON_COMPENSATION_BASIC_SIZE)
	private String compensationBasicId;

	@NotNull(message = ValidationConstants.PERSON_COMPENSATION_RATE_NOT_NULL)
	@Pattern(regexp = CommonConstants.NUMBER_REGEX, message = ValidationConstants.PERSON_NUMBER_INVALID)
	private String compensationRate;

	@NotNull(message = ValidationConstants.PERSON_DEPARTMENT_NOT_NULL)
	@Size(max = 10, message = ValidationConstants.PERSON_DEPARTMENT_SIZE)
	private String departmentId;

	@NotNull(message = ValidationConstants.PERSON_WORK_LOCATION_NOT_NULL)
	@Size(max = 10, message = ValidationConstants.PERSON_WORK_LOCATION_SIZE)
	private String workLocationId;

	@NotNull(message = ValidationConstants.PERSON_PAY_GROUPS_NOT_NULL)
	@Size(max = 3, message = ValidationConstants.PERSON_PAY_GROUPS_SIZE)
	private String payGroupsIds;

	@NotNull(message = ValidationConstants.PERSON_ALTERNATE_EMPID_NOT_NULL)
	@Size(max = 11, message = ValidationConstants.PERSON_ALTERNATE_EMPID_SIZE)
	private String alternateEmpId;

	@NotNull(message = ValidationConstants.PERSON_WORK_EMAIL_NOT_NULL)
	@Size(max = 70, message = ValidationConstants.PERSON_WORK_EMAIL_SIZE)
	@Email(message = ValidationConstants.PERSON_EMAIL_VALIDATION)
	private String wokfEmailAddress;
	private String level;
	private String sponsor;
	private String vacation;
	private String sick;
	private String personalTime;
	private String floatingHolidays;

	// @DateTimeFormat(pattern = "yyyy-MM-DD")
	@Pattern(regexp = CommonConstants.DATE_REGEX, message = ValidationConstants.PERSON_DATE_INVALID)
	private String dateOfBirth;

	private String isBased;
	private String empVariableHour;
	private String empJobDes;

	public String getpEmplid() {
		return pEmplid;
	}

	public void setpEmplid(String pEmplid) {
		this.pEmplid = pEmplid;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getNationalId() {
		return nationalId;
	}

	public void setNationalId(String nationalId) {
		this.nationalId = nationalId;
	}

	public String getFormOfAddressId() {
		return formOfAddressId;
	}

	public void setFormOfAddressId(String formOfAddressId) {
		this.formOfAddressId = formOfAddressId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getHomeEmail() {
		return homeEmail;
	}

	public void setHomeEmail(String homeEmail) {
		this.homeEmail = homeEmail;
	}

	public String getGenderId() {
		return genderId;
	}

	public void setGenderId(String genderId) {
		this.genderId = genderId;
	}

	public String getEthnicityId() {
		return ethnicityId;
	}

	public void setEthnicityId(String ethnicityId) {
		this.ethnicityId = ethnicityId;
	}

	public String getMilitryStatus() {
		return militryStatus;
	}

	public void setMilitryStatus(String militryStatus) {
		this.militryStatus = militryStatus;
	}

	public String getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}

	public String getEntryReason() {
		return entryReason;
	}

	public void setEntryReason(String entryReason) {
		this.entryReason = entryReason;
	}

	public String getEmplType() {
		return emplType;
	}

	public void setEmplType(String emplType) {
		this.emplType = emplType;
	}

	public String getEmpReqTemp() {
		return empReqTemp;
	}

	public void setEmpReqTemp(String empReqTemp) {
		this.empReqTemp = empReqTemp;
	}

	public String getEmpClass() {
		return empClass;
	}

	public void setEmpClass(String empClass) {
		this.empClass = empClass;
	}

	public String getAnnual_wages() {
		return annual_wages;
	}

	public void setAnnual_wages(String annual_wages) {
		this.annual_wages = annual_wages;
	}

	public String getBenefitClassId() {
		return benefitClassId;
	}

	public void setBenefitClassId(String benefitClassId) {
		this.benefitClassId = benefitClassId;
	}

	public String getStdHours() {
		return stdHours;
	}

	public void setStdHours(String stdHours) {
		this.stdHours = stdHours;
	}

	public String getBussinessTitle() {
		return bussinessTitle;
	}

	public void setBussinessTitle(String bussinessTitle) {
		this.bussinessTitle = bussinessTitle;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getFlsaStatus() {
		return flsaStatus;
	}

	public void setFlsaStatus(String flsaStatus) {
		this.flsaStatus = flsaStatus;
	}

	public String getSupervisorId() {
		return supervisorId;
	}

	public void setSupervisorId(String supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getCompensationBasicId() {
		return compensationBasicId;
	}

	public void setCompensationBasicId(String compensationBasicId) {
		this.compensationBasicId = compensationBasicId;
	}

	public String getCompensationRate() {
		return compensationRate;
	}

	public void setCompensationRate(String compensationRate) {
		this.compensationRate = compensationRate;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getWorkLocationId() {
		return workLocationId;
	}

	public void setWorkLocationId(String workLocationId) {
		this.workLocationId = workLocationId;
	}

	public String getPayGroupsIds() {
		return payGroupsIds;
	}

	public void setPayGroupsIds(String payGroupsIds) {
		this.payGroupsIds = payGroupsIds;
	}

	public String getAlternateEmpId() {
		return alternateEmpId;
	}

	public void setAlternateEmpId(String alternateEmpId) {
		this.alternateEmpId = alternateEmpId;
	}

	public String getWokfEmailAddress() {
		return wokfEmailAddress;
	}

	public void setWokfEmailAddress(String wokfEmailAddress) {
		this.wokfEmailAddress = wokfEmailAddress;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getVacation() {
		return vacation;
	}

	public void setVacation(String vacation) {
		this.vacation = vacation;
	}

	public String getSick() {
		return sick;
	}

	public void setSick(String sick) {
		this.sick = sick;
	}

	public String getPersonalTime() {
		return personalTime;
	}

	public void setPersonalTime(String personalTime) {
		this.personalTime = personalTime;
	}

	public String getFloatingHolidays() {
		return floatingHolidays;
	}

	public void setFloatingHolidays(String floatingHolidays) {
		this.floatingHolidays = floatingHolidays;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIsBased() {
		return isBased;
	}

	public void setIsBased(String isBased) {
		this.isBased = isBased;
	}

	public String getEmpVariableHour() {
		return empVariableHour;
	}

	public void setEmpVariableHour(String empVariableHour) {
		this.empVariableHour = empVariableHour;
	}

	public String getEmpJobDes() {
		return empJobDes;
	}

	public void setEmpJobDes(String empJobDes) {
		this.empJobDes = empJobDes;
	}
}