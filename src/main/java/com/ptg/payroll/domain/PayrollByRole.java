package com.ptg.payroll.domain;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.payroll.utils.CustomJsonDateDeserializer;
import com.payroll.utils.CustomJsonDateSerializer;

/**
 * @author rlakshmiraj
 *
 */
@JsonInclude(Include.NON_NULL)
public class PayrollByRole extends Payroll {
	
	private String isParent;
	private String locationDesc;
	private String departmentDesc;
	private String childPayrollStatus;
	private String childPayrollStatusDesc;
	private String childOperatorId;
	private String childOperatorName;
	private Date childLastUpdatedDate;
	private String isTriNetLeaveSystemCompany;
	
	/**
	 * @return the isParent
	 */
	public String getIsParent() {
		return isParent;
	}
	/**
	 * @param isParent the isParent to set
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	/**
	 * @return the locationDesc
	 */
	public String getLocationDesc() {
		return locationDesc;
	}
	/**
	 * @param locationDesc the locationDesc to set
	 */
	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}
	/**
	 * @return the departmentDesc
	 */
	public String getDepartmentDesc() {
		return departmentDesc;
	}
	/**
	 * @param departmentDesc the departmentDesc to set
	 */
	public void setDepartmentDesc(String departmentDesc) {
		this.departmentDesc = departmentDesc;
	}
	/**
	 * @return the childPayrollStatus
	 */
	public String getChildPayrollStatus() {
		return childPayrollStatus;
	}
	/**
	 * @param childPayrollStatus the childPayrollStatus to set
	 */
	public void setChildPayrollStatus(String childPayrollStatus) {
		this.childPayrollStatus = childPayrollStatus;
	}
	/**
	 * @return the childPayrollStatusDesc
	 */
	public String getChildPayrollStatusDesc() {
		return childPayrollStatusDesc;
	}
	/**
	 * @param childPayrollStatusDesc the childPayrollStatusDesc to set
	 */
	public void setChildPayrollStatusDesc(String childPayrollStatusDesc) {
		this.childPayrollStatusDesc = childPayrollStatusDesc;
	}
	/**
	 * @return the childOperatorId
	 */
	public String getChildOperatorId() {
		return childOperatorId;
	}
	/**
	 * @param childOperatorId the childOperatorId to set
	 */
	public void setChildOperatorId(String childOperatorId) {
		this.childOperatorId = childOperatorId;
	}
	/**
	 * @return the childOperatorName
	 */
	public String getChildOperatorName() {
		return childOperatorName;
	}
	/**
	 * @param childOperatorName the childOperatorName to set
	 */
	public void setChildOperatorName(String childOperatorName) {
		this.childOperatorName = childOperatorName;
	}
	/**
	 * @return the childLastUpdatedDate
	 */
	@JsonSerialize(using = CustomJsonDateSerializer.class)
	public Date getChildLastUpdatedDate() {
		return childLastUpdatedDate;
	}
	/**
	 * @param childLastUpdatedDate the childLastUpdatedDate to set
	 */
	@JsonDeserialize(using = CustomJsonDateDeserializer.class)
	public void setChildLastUpdatedDate(Date childLastUpdatedDate) {
		this.childLastUpdatedDate = childLastUpdatedDate;
	}
    /**
     * @return the isTriNetLeaveSystemCompany
     */
    public String getIsTriNetLeaveSystemCompany() {
        return isTriNetLeaveSystemCompany;
    }
    /**
     * @param isTriNetLeaveSystemCompany the isTriNetLeaveSystemCompany to set
     */
    public void setIsTriNetLeaveSystemCompany(String isTriNetLeaveSystemCompany) {
        this.isTriNetLeaveSystemCompany = isTriNetLeaveSystemCompany;
    }
	
}
