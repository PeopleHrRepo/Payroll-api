package com.payroll.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * The persistent class for the PS_HOLIDAY_DATE database table.
 * 
 */
@Entity
@Table(name="PS_HOLIDAY_DATE")
@NamedNativeQueries({
	@NamedNativeQuery(
		name="getHolidays",
		query="select HOLIDAY_SCHEDULE, HOLIDAY,descr from PS_HOLIDAY_DATE where holiday_Schedule = :company",
		resultClass=PayrollHolidanBean.class),
})	
public class PayrollHolidanBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String descr;

	
	@Id
	@Column(name="HOLIDAY_SCHEDULE")
	private String holidaySchedule;
	
	@Id
	@Column(name="HOLIDAY")
	@Temporal(TemporalType.DATE)
	private Date holiday;
	
	
	public PayrollHolidanBean() {
	}

	public String getDescr() {
		return this.descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	

	public String getHolidaySchedule() {
		return this.holidaySchedule;
	}

	public void setHolidaySchedule(String holidaySchedule) {
		this.holidaySchedule = holidaySchedule;
	}

	public Date getHoliday() {
		return holiday;
	}

	public void setHoliday(Date holiday) {
		this.holiday = holiday;
	}

	@Override
	public String toString() {
		return "PayrollHolidanBean [descr=" + descr +  ", holidaySchedule=" + holidaySchedule
				+ ", holiday=" + holiday + "]";
	}

	

	

}