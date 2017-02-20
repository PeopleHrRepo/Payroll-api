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

@Entity
@Table(name = "PS_T2_PE_JOBCD_TBL")
@NamedNativeQueries({ @NamedNativeQuery(
        name = "SQL.payroll.findJobCostingCodeByCompany", 
         query = " SELECT A.JOBCODE, "
        		 +" A.DESCR,"
        		 +" A.DESCRSHORT, "
        		 +" A.EFFDT, "
        		 +" C.COMPANY, "
        		 +" A.PF_CLIENT, "
        		 +" B.T2_PE_JC_FLAG, "
        		 +" B.T2_PE_CP_FLAG, "
        		 +" B.T2_PE_PROJECT_ID "
        		 +" FROM PS_JOBCODE_TBL A, PS_T2_PE_JOBCD_TBL B, PS_T2_CLIENTOPTION C "
        		 +" WHERE C.COMPANY = :company "
        		 +" AND A.EFFDT = "
        		 +" (SELECT MAX (A_ED.EFFDT) "
        		 +" FROM PS_JOBCODE_TBL A_ED "
        		 +" WHERE A.SETID = A_ED.SETID "
        		 +" AND A.JOBCODE = A_ED.JOBCODE "
        		 +" AND A_ED.EFFDT <= :payEndDate) "
        		 +" AND A.SETID = B.SETID "
        		 +" AND A.JOBCODE = B.JOBCODE "
        		 +" AND A.EFFDT = B.EFFDT "
        		 + "AND A.EFF_STATUS = 'A'"
// Commented out for issue in production where new job codes are populated with 001 company id. May need to put back if fixed.        		 
//        		 +" AND C.COMPANY = A.COMPANY "
        		 +" AND C.PF_CLIENT = A.PF_CLIENT ",
        resultClass = JobCostingCode.class)})
public class JobCostingCode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "COMPANY", nullable = false)
    private String company;

    @Id
    @Column(name = "JOBCODE")
    private String jobCode;
    
    @Column(name = "DESCR")
    private String description;
    
    @Column(name = "DESCRSHORT")
    private String descriptionShort;
    
    @Column(name = "EFFDT")
    @Temporal(TemporalType.DATE)
    private Date effDt;
    
    @Column(name = "PF_CLIENT")
    private String pfclient;

    @Column(name = "T2_PE_JC_FLAG")
    private String jobCosting;
    
    @Column(name = "T2_PE_CP_FLAG")
    private String certifiedPayroll;    
    
    @Column(name = "T2_PE_PROJECT_ID")
    private String projectId;
    
    
    public JobCostingCode() {

    }
    
    public JobCostingCode(String company, String jobCode, String description, String descriptionShort, String pfclient, String jobCosting, String certifiedPayroll, String projectId ) {
    	this.company = company;
    	this.pfclient= pfclient;
    	this.jobCode = jobCode;
    	this.description = description;
    	this.descriptionShort = descriptionShort;
    	this.jobCosting = jobCosting;
    	this.certifiedPayroll = certifiedPayroll;
    	this.projectId = projectId;
    }

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionShort() {
		return descriptionShort;
	}

	public void setDescriptionShort(String descriptionShort) {
		this.descriptionShort = descriptionShort;
	}

	public Date getEffDt() {
		return effDt;
	}

	public void setEffDt(Date effDt) {
		this.effDt = effDt;
	}

	public String getPfclient() {
		return pfclient;
	}

	public void setPfclient(String pfclient) {
		this.pfclient = pfclient;
	}

	public String getJobCosting() {
		return jobCosting;
	}

	public void setJobCosting(String jobCosting) {
		this.jobCosting = jobCosting;
	}

	public String getCertifiedPayroll() {
		return certifiedPayroll;
	}

	public void setCertifiedPayroll(String certifiedPayroll) {
		this.certifiedPayroll = certifiedPayroll;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
