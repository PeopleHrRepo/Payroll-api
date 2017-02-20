package com.payroll.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import com.payroll.entity.pk.PsNamesPK;


/**
 * The persistent class for the PS_NAMES database table.
 * 
 */
@Entity
@Table(name="PS_NAMES")
public class PsNames extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	private PsNamesPK id;

	
	@Column(name="first_name", nullable=false, length=30)
	private String firstName;
	
	@Column(name="last_name", nullable=false, length=30)
	private String lastName;


	public String toString() {
		return ToStringBuilder.reflectionToString(this);
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


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public void setId(PsNamesPK id) {
		this.id = id;
	}

	
}