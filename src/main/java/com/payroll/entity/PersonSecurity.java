package com.payroll.entity;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * The persistent class for the PERSON_SECURITY database table.
 * 
 */
@Entity
@Table(name="PERSON_SECURITY")
public class PersonSecurity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PERSONID")
	private String id;

	@Temporal(TemporalType.DATE)
	@Column(name="effdt", nullable=false)
	private Date effDt;
	
	@Temporal(TemporalType.DATE)
	@Column(name="enddt", nullable=false)
	private Date endDt;
		
	@Column(name="session_active", nullable=true, length=1)
	private String sessionActive;
	
	@Column(name="encrypted_session", nullable=true, length=256)
	private String tsessionId;
	
	@Column(name = "PIN")
    private String pin;
    
    @Column(name = "FAILCOUNT")
    private Integer failCount;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "LOGON_TIMESTAMP")
    private Date loginTimeStamp;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "USE_TIMESTAMP")
    private Date useTimeStamp;
    
    @Column(name = "SET_BY_ADMIN")
    private String setByAdmin;
    
    @Column(name = "USER_LEVEL")
    private Integer userLevel;
    
    @Column(name = "CLIENT_IP_ADDRESS")
    private String clientIpAddress;
    
    @Column(name = "AUDIT_KEY")
    private BigDecimal auditKey;
    
    @Column(name = "UPDATE_KEY")
    private BigDecimal updateKey;
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Date getEffDt() {
		return effDt;
	}


	public void setEffDt(Date effDt) {
		this.effDt = effDt;
	}


	public Date getEndDt() {
		return endDt;
	}


	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}


	public String getSessionActive() {
		return sessionActive;
	}


	public void setSessionActive(String sessionActive) {
		this.sessionActive = sessionActive;
	}


	public String getTsessionId() {
		return tsessionId;
	}


	public void setTsessionId(String tsessionId) {
		this.tsessionId = tsessionId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getPin() {
		return pin;
	}


	public void setPin(String pin) {
		this.pin = pin;
	}


	public Integer getFailCount() {
		return failCount;
	}


	public void setFailCount(Integer failCount) {
		this.failCount = failCount;
	}


	public Date getLoginTimeStamp() {
		return loginTimeStamp;
	}


	public void setLoginTimeStamp(Date loginTimeStamp) {
		this.loginTimeStamp = loginTimeStamp;
	}


	public Date getUseTimeStamp() {
		return useTimeStamp;
	}


	public void setUseTimeStamp(Date useTimeStamp) {
		this.useTimeStamp = useTimeStamp;
	}


	public String getSetByAdmin() {
		return setByAdmin;
	}


	public void setSetByAdmin(String setByAdmin) {
		this.setByAdmin = setByAdmin;
	}


	public Integer getUserLevel() {
		return userLevel;
	}


	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}


	public String getClientIpAddress() {
		return clientIpAddress;
	}


	public void setClientIpAddress(String clientIpAddress) {
		this.clientIpAddress = clientIpAddress;
	}


	public BigDecimal getAuditKey() {
		return auditKey;
	}


	public void setAuditKey(BigDecimal auditKey) {
		this.auditKey = auditKey;
	}


	public BigDecimal getUpdateKey() {
		return updateKey;
	}


	public void setUpdateKey(BigDecimal updateKey) {
		this.updateKey = updateKey;
	}


}