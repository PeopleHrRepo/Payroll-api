package com.ptg.payroll.domain;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class PayrollGroup {

	private BigDecimal id;
    private String company;
    private String payGroup;
    private Date payEndDate;
    private String offCycle;
    private Integer payrollNumber;
    private String location;
    private String departmentId;
    private Date payBeginDate;
    private Date checkDate;
    private String payGroupDescr;
    private String payFrequency;
    private String payrollStatus;
    private String payrollStatusDesc;
    private Date reportDate;
    private String blackoutFlag;
    private String payrollName;
    private String brandName;
    private String dataEntryOption;
    private String operatorId;
    private String operatorName;
    private Date lastModifiedDate;
    private String paymentMethod;
    private boolean hasPayrollError;
    private String errorMessage;
    private String currentEngineStep;
    private String deliveryOption;
    private String deliveryType;
    private String addressOption;
    private String attention;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String deliveryInstruction;
    

    /* Auto Generated code below, please don't modify */

    public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPayGroup() {
        return payGroup;
    }

    public void setPayGroup(String payGroup) {
        this.payGroup = payGroup;
    }

    public Date getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(Date payEndDate) {
        this.payEndDate = payEndDate;
    }

    public String getOffCycle() {
        return offCycle;
    }

    public void setOffCycle(String offCycle) {
        this.offCycle = offCycle;
    }

    public Integer getPayrollNumber() {
        return payrollNumber;
    }

    public void setPayrollNumber(Integer payrollNumber) {
        this.payrollNumber = payrollNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public Date getPayBeginDate() {
        return payBeginDate;
    }

    public void setPayBeginDate(Date payBeginDate) {
        this.payBeginDate = payBeginDate;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getPayGroupDescr() {
        return payGroupDescr;
    }

    public void setPayGroupDescr(String payGroupDescr) {
        this.payGroupDescr = payGroupDescr;
    }

    public String getPayFrequency() {
        return payFrequency;
    }

    public void setPayFrequency(String payFrequency) {
        this.payFrequency = payFrequency;
    }

    public String getPayrollStatus() {
        return payrollStatus;
    }

    public void setPayrollStatus(String payrollStatus) {
        this.payrollStatus = payrollStatus;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getBlackoutFlag() {
        return blackoutFlag;
    }

    public void setBlackoutFlag(String blackoutFlag) {
        this.blackoutFlag = blackoutFlag;
    }

    public String getPayrollName() {
        return payrollName;
    }

    public void setPayrollName(String payrollName) {
        this.payrollName = payrollName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDataEntryOption() {
        return dataEntryOption;
    }

    public void setDataEntryOption(String dataEntryOption) {
        this.dataEntryOption = dataEntryOption;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPayrollStatusDesc() {
		return payrollStatusDesc;
	}

	public void setPayrollStatusDesc(String payrollStatusDesc) {
		this.payrollStatusDesc = payrollStatusDesc;
	}

	public boolean isHasPayrollError() {
		return hasPayrollError;
	}

	public void setHasPayrollError(boolean hasPayrollError) {
		this.hasPayrollError = hasPayrollError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCurrentEngineStep() {
		return currentEngineStep;
	}

	public void setCurrentEngineStep(String currentEngineStep) {
		this.currentEngineStep = currentEngineStep;
	}

	public String getDeliveryOption() {
		return deliveryOption;
	}

	public void setDeliveryOption(String deliveryOption) {
		this.deliveryOption = deliveryOption;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getAddressOption() {
		return addressOption;
	}

	public void setAddressOption(String addressOption) {
		this.addressOption = addressOption;
	}

	public String getAttention() {
		return attention;
	}

	public void setAttention(String attention) {
		this.attention = attention;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getDeliveryInstruction() {
		return deliveryInstruction;
	}

	public void setDeliveryInstruction(String deliveryInstruction) {
		this.deliveryInstruction = deliveryInstruction;
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}