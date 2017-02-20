package com.payroll.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "PS_T2_PE_CMP_ERNCD")
@NamedNativeQueries({ @NamedNativeQuery(
        name = "SQL.payroll.findEarningCodeByCompany", 
         query = " SELECT DISTINCT A.COMPANY, "+
        	                " A.ERNCD, "+
        	                " A.EFFDT, "+
        	                " A.EFFSEQ, "+
        	                " A.EFF_STATUS, "+
        	                " A.DESCR, "+
        	                " A.LAST_UPDT_DTTM, "+
        	                " A.LAST_UPDT_OPRID, "+
        	                " A.SEQNO, "+
        	                " B.IS_DELETED, "+
        	                " B.PAYMENT_TYPE, "+
        	                " B.ADD_GROSS, "+
        	                " B.PERUNIT_OVR_RT, "+
        	                " B.T2_PE_REDUCE_REG, "+
        	                " +B.FACTOR_MULT, "+
        	                " B.T2_PE_EXCLUDE_GRID, "+
        	                " B.T2_PE_NON_TAXABLE, "+
        	                " B.T2_PEO_ID, "+
        	                " B.T2_PE_401_ERCD_FLG, "+
        	                " B.T2_PE_SUP_ERNCD, "+
        	                " B.T2_PE_GRID_DISPLAY, "+
        	                " B.T2_PE_K1_EE_FLG "+
        	  " FROM PS_T2_PE_CMP_ERNCD A, PS_T2_PE_ERNCMP_VW B "+
        	 " WHERE     A.ERNCD = B.ERNCD "+
        	       " AND A.COMPANY = :company "+
        	       " AND B.COMPANY IN (' ', A.COMPANY) "+
        	       " AND A.EFFDT = "+
        	              " (SELECT MAX (A_ED.EFFDT) "+
        	                 " FROM PS_T2_PE_CMP_ERNCD A_ED "+
        	                " WHERE     A.COMPANY = A_ED.COMPANY "+
        	                      " AND A.ERNCD = A_ED.ERNCD "+
        	                      " AND A_ED.EFFDT <= SYSDATE) "+
        	       " AND A.EFFSEQ = "+
        	              " (SELECT MAX (A_ES.EFFSEQ) "+
        	                 " FROM PS_T2_PE_CMP_ERNCD A_ES "+
        	                " WHERE     A.COMPANY = A_ES.COMPANY "+
        	                      " AND A.ERNCD = A_ES.ERNCD "+
        	                      " AND A.EFFDT = A_ES.EFFDT) "+
        	       " AND A.EFF_STATUS = 'A' "+
        	       " AND (   B.T2_PEO_ID = ('DEF') "+
        	            " OR B.T2_PEO_ID = (SELECT CL.T2_PEO_ID "+
        	                                " FROM PS_T2_CLIENTOPTION CL "+
        	                               " WHERE CL.COMPANY = :company)) "+
        	       " AND NOT EXISTS "+
        	              " (SELECT DAT.ERNCD, DAT.T2_PEO_ID "+
        	                 " FROM (SELECT E.ERNCD, 'DEF' AS T2_PEO_ID "+
        	                         " FROM PS_T2_PE_ERNCMP_VW E "+
        	                        " WHERE E.T2_PEO_ID = (SELECT CL.T2_PEO_ID "+
        	                                               " FROM PS_T2_CLIENTOPTION CL "+
        	                                              " WHERE CL.COMPANY = :company)) DAT "+
        	                " WHERE DAT.ERNCD = A.ERNCD AND DAT.T2_PEO_ID = B.T2_PEO_ID)",
        resultClass = EarnCode.class),
        
        @NamedNativeQuery(
                name = "SQL.payroll.findEarningCodes", 
                 query =   " SELECT "+
                         " B.COMPANY, "+
                         " A.ERNCD, "+
                         " A.DESCR, "+
                         " A.IS_DELETED, "+
                         " A.PAYMENT_TYPE, "+
                         " A.PERUNIT_OVR_RT, "+
                         " A.T2_PE_REDUCE_REG, "+
                         " A.ADD_GROSS, "+
                         " A.T2_PEO_ID, "+
                         " A.T2_PE_401_ERCD_FLG, "+
                         " A.T2_PE_SUP_ERNCD, "+
                         " A.T2_PE_GRID_DISPLAY, "+
                         " A.FACTOR_MULT, "+
                         " CASE "+
                            " WHEN C.COMPONENT_VALUE = 'Y' THEN A.T2_PE_EXCLUDE_GRID "+
                            " ELSE 'N' "+
                         " END "+
                            " AS T2_PE_EXCLUDE_GRID, "+
                         " A.T2_PE_NON_TAXABLE, "+
                         " A.T2_PE_K1_EE_FLG, "+
                         " B.SEQNO, "+
                         " B.EFFDT, "+
                         " B.EFFSEQ, "+
                         " B.EFF_STATUS, "+
                         " B.LAST_UPDT_DTTM, "+
                         " B.LAST_UPDT_OPRID "+
                    " FROM PS_T2_PE_ERNCMP_VW A "+
                         " LEFT OUTER JOIN PS_T2_PE_CMP_ERNCD B "+
                            " ON     B.ERNCD = A.ERNCD "+
                               " AND B.COMPANY = :company "+
                               " AND B.EFF_STATUS = 'A' "+
                         " LEFT OUTER JOIN COMPONENT_SETUP_STEPS@HPDB_HRDB C "+
                            " ON     B.COMPANY = C.ORGID "+
                               " AND C.COMPONENT_NAME = 'LEAVE_REQUEST' "+
                               " AND C.COMPONENT_VALUE = 'Y' "+
                               " AND SYSDATE BETWEEN C.EFFDT AND C.ENDDT "+
                   " WHERE     A.COMPANY IN (' ', :company) "+
                         " AND (   B.EFFDT IS NULL "+
                              " OR B.EFFDT = "+
                                    " (SELECT MAX (B_ED.EFFDT) "+
                                       " FROM PS_T2_PE_CMP_ERNCD B_ED "+
                                      " WHERE     B.COMPANY = B_ED.COMPANY "+
                                            " AND B.ERNCD = B_ED.ERNCD "+
                                            " AND B_ED.EFF_STATUS = 'A' "+
                                            " AND B_ED.EFFDT <= SYSDATE)) "+
                         " AND (   B.EFFSEQ IS NULL "+
                              " OR B.EFFSEQ = "+
                                    " (SELECT MAX (B_ES.EFFSEQ) "+
                                       " FROM PS_T2_PE_CMP_ERNCD B_ES "+
                                      " WHERE     B.COMPANY = B_ES.COMPANY "+
                                            " AND B.ERNCD = B_ES.ERNCD "+
                                            " AND B_ES.EFF_STATUS = 'A' "+
                                            " AND B.EFFDT = B_ES.EFFDT)) "+
                         " AND A.ERNCD IN (SELECT DISTINCT ERNCD "+
                                           " FROM PS_T2_PE_CO_ERN_VW A1 "+
                                          " WHERE A1.COMPANY = :company) "+
                         " AND (   A.T2_PEO_ID = ('DEF') "+
                              " OR A.T2_PEO_ID = (SELECT CL.T2_PEO_ID "+
                                                  " FROM PS_T2_CLIENTOPTION CL "+
                                                 " WHERE CL.COMPANY = :company)) "+
                         " AND NOT EXISTS "+
                                " (SELECT DAT.ERNCD, DAT.T2_PEO_ID "+
                                   " FROM (SELECT E.ERNCD, 'DEF' AS T2_PEO_ID "+
                                           " FROM PS_T2_PE_ERNCMP_VW E "+
                                          " WHERE E.T2_PEO_ID = (SELECT CL.T2_PEO_ID "+
                                                                 " FROM PS_T2_CLIENTOPTION CL "+
                                                                " WHERE CL.COMPANY = :company)) DAT "+
                                  " WHERE DAT.ERNCD = A.ERNCD AND DAT.T2_PEO_ID = A.T2_PEO_ID) "+
                " ORDER BY B.COMPANY, B.SEQNO ",
                resultClass = EarnCode.class),
        
        @NamedNativeQuery(
                name = "SQL.payroll.findEarningCodesByPayGroup", 
                 query =   " SELECT  "+
                         " B.COMPANY, "+
                         " A.ERNCD, "+
                         " A.DESCR, "+
                         " A.IS_DELETED, "+
                         " A.PAYMENT_TYPE, "+
                         " A.PERUNIT_OVR_RT, "+
                         " A.T2_PE_REDUCE_REG, "+
                         " A.ADD_GROSS, "+
                         " A.T2_PEO_ID, "+
                         " A.T2_PE_401_ERCD_FLG, "+
                         " A.T2_PE_SUP_ERNCD, "+
                         " A.T2_PE_GRID_DISPLAY, "+
                         " A.FACTOR_MULT, "+
                         " CASE "+
                            " WHEN C.COMPONENT_VALUE = 'Y' THEN A.T2_PE_EXCLUDE_GRID "+
                            " ELSE 'N' "+
                         " END "+
                            " AS T2_PE_EXCLUDE_GRID, "+
                         " A.T2_PE_NON_TAXABLE, "+
                         " A.T2_PE_K1_EE_FLG, "+
                         " B.SEQNO, "+
                         " B.EFFDT, "+
                         " B.EFFSEQ, "+
                         " B.EFF_STATUS, "+
                         " B.LAST_UPDT_DTTM, "+
                         " B.LAST_UPDT_OPRID "+
                    " FROM PS_T2_PE_ERNCMP_VW A "+
                         " LEFT OUTER JOIN PS_T2_PE_CMP_ERNCD B "+
                            " ON     B.ERNCD = A.ERNCD "+
                               " AND B.COMPANY = :company "+
                               " AND B.EFF_STATUS = 'A' "+
                         " LEFT OUTER JOIN COMPONENT_SETUP_STEPS@HPDB_HRDB C "+
                            " ON     B.COMPANY = C.ORGID "+
                               " AND C.COMPONENT_NAME = 'LEAVE_REQUEST' "+
                               " AND C.COMPONENT_VALUE = 'Y' "+
                               " AND SYSDATE BETWEEN C.EFFDT AND C.ENDDT "+
                   " WHERE     A.COMPANY IN (' ', :company) "+
                         " AND (   B.EFFDT IS NULL "+
                              " OR B.EFFDT = "+
                                    " (SELECT MAX (B_ED.EFFDT) "+
                                       " FROM PS_T2_PE_CMP_ERNCD B_ED "+
                                      " WHERE     B.COMPANY = B_ED.COMPANY "+
                                            " AND B.ERNCD = B_ED.ERNCD "+
                                            " AND B_ED.EFF_STATUS = 'A' "+
                                            " AND B_ED.EFFDT <= SYSDATE)) "+
                         " AND (   B.EFFSEQ IS NULL "+
                              " OR B.EFFSEQ = "+
                                    " (SELECT MAX (B_ES.EFFSEQ) "+
                                       " FROM PS_T2_PE_CMP_ERNCD B_ES "+
                                      " WHERE     B.COMPANY = B_ES.COMPANY "+
                                            " AND B.ERNCD = B_ES.ERNCD "+
                                            " AND B_ES.EFF_STATUS = 'A' "+
                                            " AND B.EFFDT = B_ES.EFFDT)) "+
                         " AND A.ERNCD IN (SELECT DISTINCT ERNCD "+
                                           " FROM PS_T2_PE_CO_ERN_VW A1 "+
                                          " WHERE A1.COMPANY = :company AND A1.PAYGROUP= :payGroup) "+
                         " AND (   A.T2_PEO_ID = ('DEF') "+
                              " OR A.T2_PEO_ID = (SELECT CL.T2_PEO_ID "+
                                                  " FROM PS_T2_CLIENTOPTION CL "+
                                                 " WHERE CL.COMPANY = :company)) "+
                         " AND NOT EXISTS "+
                                " (SELECT DAT.ERNCD, DAT.T2_PEO_ID "+
                                   " FROM (SELECT E.ERNCD, 'DEF' AS T2_PEO_ID "+
                                           " FROM PS_T2_PE_ERNCMP_VW E "+
                                          " WHERE E.T2_PEO_ID = (SELECT CL.T2_PEO_ID "+
                                                                 " FROM PS_T2_CLIENTOPTION CL "+
                                                                " WHERE CL.COMPANY = :company)) DAT "+
                                  " WHERE DAT.ERNCD = A.ERNCD AND DAT.T2_PEO_ID = A.T2_PEO_ID) "+
                " ORDER BY B.COMPANY, B.SEQNO ",
                resultClass = EarnCode.class)        
        

})
//@formatter:on
@SequenceGenerator(sequenceName = "PE_ERNCD_EFFSEQ", name = "effSeqGen")
public class EarnCode implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "COMPANY", nullable = false)
    private String company;

    @Id
    @Column(name = "ERNCD")
    private String code;

    @Id
    @Column(name = "EFFDT")
    @Temporal(TemporalType.DATE)
    private Date effDt;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "effSeqGen")
    @Column(name = "EFFSEQ")
    private Long effSeq;

    @Column(name = "EFF_STATUS")
    private String effStatus;
    
    @Column(name = "DESCR")
    private String description;
    
    @Column(name = "LAST_UPDT_DTTM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdtDttm;
    
    @Column(name = "LAST_UPDT_OPRID")
    private String lastUpdtOprid;

    @Column(name = "IS_DELETED")
    private String deletable;

    @Column(name = "T2_PE_EXCLUDE_GRID", insertable = false, updatable = false)
    private String isLoadLeaveEarnCd;

    @Column(name = "PAYMENT_TYPE", insertable = false, updatable = false)
    private String enrCdPaymentType;

    @Column(name = "t2_pe_reduce_reg", insertable = false, updatable = false)
    private String reduceRegHours;

    @Column(name = "FACTOR_MULT", insertable = false, updatable = false)
    private BigDecimal factorMult;

    @Column(name = "PERUNIT_OVR_RT", insertable = false, updatable = false)
    private BigDecimal unitRate;

    @Column(name = "seqNo")
    private Long seqNo;

    @Column(name = "T2_PE_NON_TAXABLE", insertable = false, updatable = false)
    private String nonTaxable;
    
    @Column(name = "ADD_GROSS", insertable = false, updatable = false)
    private String addToGross;

    @Column(name = "T2_PEO_ID", insertable = false, updatable = false)
    private String brand;
	
    @Column(name = "T2_PE_401_ERCD_FLG", insertable = false, updatable = false)
    private String showExcludeFrom401K;
    
    @Column(name = "T2_PE_SUP_ERNCD", insertable = false, updatable = false)
    private String forceSepChk;
    
    @Column(name = "T2_PE_GRID_DISPLAY", insertable = false, updatable = false)
    private String displayOnly;
    
    @Column(name = "T2_PE_K1_EE_FLG", insertable = false, updatable = false)
    private String partnerShareCode;
    
    @Transient
   	private boolean reported;
    
    public EarnCode() {

    }

    public EarnCode(String company, String ernCd, Date effDt, Long effSeq, String effStatus, String description, Date lastUpdtDttm, String lastUpdtOprid,
            String deletable, String enrCdPaymentType, String reduceRegHours, BigDecimal factorMult, Long seqNo, String addToGross) {
        this.company = company;
        this.code = ernCd;
        this.effDt = effDt;
        this.effSeq = effSeq;
        this.effStatus = effStatus;
        this.description = description;
        this.lastUpdtDttm = lastUpdtDttm;
        this.lastUpdtOprid = lastUpdtOprid;
        this.deletable = deletable;
        this.enrCdPaymentType = enrCdPaymentType;
        this.reduceRegHours = reduceRegHours;
        this.factorMult = factorMult;
        this.seqNo = seqNo;
        this.addToGross = addToGross;
    }

    public EarnCode(String company, String ernCd, String description, String deletable, String enrCdPaymentType, BigDecimal factorMult) {
        this.company = company;
        this.code = ernCd;
        this.description = description;
        this.deletable = deletable;
        this.enrCdPaymentType = enrCdPaymentType;
        this.factorMult = factorMult;
    }

    /** IDE Generated getters & setters */

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String ernCd) {
        this.code = ernCd;
    }

    public Date getEffDt() {
        return effDt;
    }

    public void setEffDt(Date effDt) {
        this.effDt = effDt;
    }

    public Long getEffSeq() {
        return effSeq;
    }

    public void setEffSeq(Long effSeq) {
        this.effSeq = effSeq;
    }

    public String getEffStatus() {
        return effStatus;
    }

    public void setEffStatus(String effStatus) {
        this.effStatus = effStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastUpdtDttm() {
        return lastUpdtDttm;
    }

    public void setLastUpdtDttm(Date lastUpdtDttm) {
        this.lastUpdtDttm = lastUpdtDttm;
    }

    public String getLastUpdtOprid() {
        return lastUpdtOprid;
    }

    public void setLastUpdtOprid(String lastUpdtOprid) {
        this.lastUpdtOprid = lastUpdtOprid;
    }

    public String getDeletable() {
        return deletable;
    }

    public void setDeletable(String deletable) {
        this.deletable = deletable;
    }

    public String getIsLoadLeaveEarnCd() {
        return isLoadLeaveEarnCd;
    }

    public void setIsLoadLeaveEarnCd(String isLoadLeaveEarnCd) {
        this.isLoadLeaveEarnCd = isLoadLeaveEarnCd;
    }

    public String getEnrCdPaymentType() {
        return enrCdPaymentType;
    }

    public void setEnrCdPaymentType(String enrCdPaymentType) {
        this.enrCdPaymentType = enrCdPaymentType;
    }

    public String getReduceRegHours() {
        return reduceRegHours;
    }

    public void setReduceRegHours(String reduceRegHours) {
        this.reduceRegHours = reduceRegHours;
    }

    public BigDecimal getFactorMult() {
        return factorMult;
    }

    public void setFactorMult(BigDecimal factorMult) {
        this.factorMult = factorMult;
    }

    public BigDecimal getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(BigDecimal unitRate) {
        this.unitRate = unitRate;
    }

    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    public String getNonTaxable() {
        return nonTaxable;
    }

    public void setNonTaxable(String nonTaxable) {
        this.nonTaxable = nonTaxable;
    }

	public String getAddToGross() {
		return addToGross;
	}

	public void setAddToGross(String addToGross) {
		this.addToGross = addToGross;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getShowExcludeFrom401K() {
		return showExcludeFrom401K;
	}

	public void setShowExcludeFrom401K(String showExcludeFrom401K) {
		this.showExcludeFrom401K = showExcludeFrom401K;
	}

	public String getForceSepChk() {
		return forceSepChk;
	}

	public void setForceSepChk(String forceSepChk) {
		this.forceSepChk = forceSepChk;
	}

    public String getDisplayOnly() {
        return displayOnly;
    }

    public void setDisplayOnly(String displayOnly) {
        this.displayOnly = displayOnly;
    }
    
    public boolean isReported() {
		return reported;
	}

	public void setReported(boolean reported) {
		this.reported = reported;
	}

	public String getPartnerShareCode() {
		return partnerShareCode;
	}

	public void setPartnerShareCode(String partnerShareCode) {
		this.partnerShareCode = partnerShareCode;
	}
	
	

}