package com.ptg.payroll.model;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class UserPermission {

    private String role;
    private String brand;
    private BigDecimal order;
    private String component;
    private String view;
    private String edit;

    /* Auto Generated code below, please don't modify */

    

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public BigDecimal getOrder() {
		return order;
	}

	public void setOrder(BigDecimal order) {
		this.order = order;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
