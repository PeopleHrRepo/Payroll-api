package com.payroll.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@Entity
@NamedNativeQueries({
	@NamedNativeQuery(
			name="getPaygroupReviewer",
			query="select  v.PERSONID, "+
			" v.orgid, "+
			" o.org_desc, "+
			" v.S_ROLE, "+
			" ac.URL WORK_EMAIL, "+
			" nam.FIRST_NAME FIRST_NAME, "+
			" nam.MIDDLE_NAME MIDDLE_NAME, "+
			" nam.NAME as LAST_NAME "+
			" from   allroles_vw v "+
			" INNER JOIN orgs o "+
			"   ON  v.orgid = o.orgid "+
			" INNER JOIN person_names nam "+
			"   ON nam.PERSONID = v.PERSONID "+  
			"    and nam.NAME_TYPE = 'PRI' "+
			"     and sysdate between nam.effdt   AND nam.ENDDT "+
			" LEFT OUTER JOIN person_accesses ac "+
			"     ON ac.PERSONID = v.PERSONID "+
			"     and sysdate between ac.effdt   AND ac.ENDDT "+
			"     and ac.access_type = 'Work' "+
			"     and ac.media  = 'Email' "+
			" where v.ORGID= :company "+
			"     and    v.s_role in ('EPTAG','EPNPI') "+
			"	  and v.PAYROLL_GROUP IN( '***' , :payGroup)	"+
			"     and o.uniqueid = (select max(o1.uniqueid) "+
			"            from  orgs o1 "+
			"                where o.orgid = o1.orgid "+
			"                and o.parent_org = o1.parent_org "+
			"                and o1.enddt >= sysdate) "+
			"     and (nam.uniqueid IS NULL OR  nam.uniqueid = (select max(nam1.uniqueid) "+
			"           from  person_names nam1 "+
			"                where nam.PERSONID = nam1.PERSONID "+
			"                         and nam1.enddt >= sysdate)) "+
			"    and (ac.uniqueid IS NULL OR  ac.uniqueid = (select max(ac1.uniqueid) "+
			"           from  person_accesses ac1 "+
			"                where ac.PERSONID = ac1.PERSONID "+
			"				 and ac.access_type = ac1.access_type "+
            "				 and ac.media = ac1.media "+
			"                         and ac1.enddt >= sysdate))",
			resultClass=PaygroupReviewer.class)
})
public class PaygroupReviewer implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PERSONID")
	private String personId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="MIDDLE_NAME")
	private String middleName;
	
	@Column(name="WORK_EMAIL")
	private String  workEmail;
	
	@Transient
	private boolean active = false;
	
	public String getPersonId() {
		return personId;
	}


	public void setPersonId(String personId) {
		this.personId = personId;
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


	public String getWorkEmail() {
		return workEmail;
	}


	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	

	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public String toString() {
	     return ReflectionToStringBuilder.toString(this);
	}
}
