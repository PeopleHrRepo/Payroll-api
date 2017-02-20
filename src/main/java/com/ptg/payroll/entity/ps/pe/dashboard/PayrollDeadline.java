package com.ptg.payroll.entity.ps.pe.dashboard;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_NULL)
public class PayrollDeadline implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String company;
	private String webDeadline;
	private String offCycleWebDeadline;
	private String previewHours;
	private String previewMinutes;
	private String timezone;
	//private String previewDeadline;
	private String timeZoneFullText;
	private String timeApproval;

	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getWebDeadline() {
		return webDeadline;
	}
	public void setWebDeadline(String webDeadline) {
		this.webDeadline = webDeadline;
	}

	public String getOffCycleWebDeadline() {
		return offCycleWebDeadline;
	}
	public void setOffCycleWebDeadline(String offCycleWebDeadline) {
		this.offCycleWebDeadline = offCycleWebDeadline;
	}
	
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/*public String getPreviewDeadline() {
		return previewDeadline;
	}
	public void setPreviewDeadline(String previewDeadline) {
		this.previewDeadline = previewDeadline;
	}*/
	public String getTimeZoneFullText() {
		return timeZoneFullText;
	}
	public void setTimeZoneFullText(String timeZoneFullText) {
		this.timeZoneFullText = timeZoneFullText;
	}

	public String getPreviewHours() {
		return previewHours;
	}
	public void setPreviewHours(String previewHours) {
		this.previewHours = previewHours;
	}
	public String getPreviewMinutes() {
		return previewMinutes;
	}
	public void setPreviewMinutes(String previewMinutes) {
		this.previewMinutes = previewMinutes;
	}
	public String getTimeApproval() {
		return timeApproval;
	}
	public void setTimeApproval(String timeApproval) {
		this.timeApproval = timeApproval;
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
	
	
}
