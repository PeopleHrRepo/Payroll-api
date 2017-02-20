package com.ptg.payroll.domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Domain object for the Payroll Import wizards
 */

public class PayrollImportEarning implements Serializable {
    private static final long serialVersionUID = -4502610804537618417L;
    public static final String STAGING_SOURCE_FILE_UPLOAD = "FLUP";
    public static final String DELIMITER = "-";

    private int virtualId; // Tagging a virtual Id to keep track of this record
    private String emplId;
    private String departmentId = " ";
    private String location = " "; 
    private String jobCode = " ";
    private String profileId;
    private String earningCode;
    private String alternateEmployeeId;
    private String reportedDate;

    private String employeeName;

    private String earningBeginDate;
    private String earningEndDate;
    private String hours;
    private String dollarAmount;
    private String overrideAmount;
    private String units;
    private String sendFlag = "N";
    private String overrideUnitRate;
    private String overrideHourlyRate;

    private String isValid = "Y";

    private List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
    
    private String unitHourAmt;
    
    private String validationStatus = "N";

    /* IDE Generated please don't modify */

    public int getVirtualId() {
        return virtualId;
    }

    public void setVirtualId(int virtualId) {
        this.virtualId = virtualId;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getEarningCode() {
        return earningCode;
    }

    public void setEarningCode(String earningCode) {
        this.earningCode = earningCode;
    }

    public String getAlternateEmployeeId() {
        return alternateEmployeeId;
    }

    public void setAlternateEmployeeId(String alternateEmployeeId) {
        this.alternateEmployeeId = alternateEmployeeId;
    }

    public String getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(String reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName != null && !employeeName.isEmpty() ? employeeName.replaceFirst(",", ", ") : employeeName;
    }

    public String getEarningBeginDate() {
        return earningBeginDate;
    }

    public void setEarningBeginDate(String earningBeginDate) {
        this.earningBeginDate = earningBeginDate;
    }

    public String getEarningEndDate() {
        return earningEndDate;
    }

    public void setEarningEndDate(String earningEndDate) {
        this.earningEndDate = earningEndDate;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDollarAmount() {
        return dollarAmount;
    }

    public void setDollarAmount(String dollarAmount) {
        this.dollarAmount = dollarAmount;
    }

    public String getOverrideAmount() {
        return overrideAmount;
    }

    public void setOverrideAmount(String overrideAmount) {
        this.overrideAmount = overrideAmount;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(String sendFlag) {
        this.sendFlag = sendFlag;
    }

    public String getOverrideUnitRate() {
        return overrideUnitRate;
    }

    public void setOverrideUnitRate(String overrideUnitRate) {
        this.overrideUnitRate = overrideUnitRate;
    }

    public String getOverrideHourlyRate() {
        return overrideHourlyRate;
    }

    public void setOverrideHourlyRate(String overrideHourlyRate) {
        this.overrideHourlyRate = overrideHourlyRate;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(List<ErrorMessage> errorMessages) {
        this.errorMessages = errorMessages;
    }

	public String getUnitHourAmt() {
		return unitHourAmt;
	}

	public void setUnitHourAmt(String unitHourAmt) {
		this.unitHourAmt = unitHourAmt;
	}

    /**
     * @return the validationStatus
     */
    public String getValidationStatus() {
        return validationStatus;
    }

    /**
     * @param validationStatus the validationStatus to set
     */
    public void setValidationStatus(String validationStatus) {
        this.validationStatus = validationStatus;
    }

	public void setDiscarded(String message) {
		this.setIsValid("N");
		this.setValidationStatus("I");
        this.getErrorMessages().add(new ErrorMessage(message));
	}

	public void setErroredRecords(String message) {
		this.setIsValid("N");
		this.setValidationStatus("N");
        this.getErrorMessages().add(new ErrorMessage(message));
	}
}
