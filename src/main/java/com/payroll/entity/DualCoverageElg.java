package com.payroll.entity;

import java.util.Date;

public class DualCoverageElg {
	private Integer o_empl_rcd;
	private Date o_effdt;
	private String o_company;
	private String o_t2_peo_id;
	private Date o_coverage_begin_dt;
	private String o_dual_cvg_elig;
	private String o_errmsg;
	public Integer getO_empl_rcd() {
		return o_empl_rcd;
	}
	public void setO_empl_rcd(Integer o_empl_rcd) {
		this.o_empl_rcd = o_empl_rcd;
	}
	public Date getO_effdt() {
		return o_effdt;
	}
	public void setO_effdt(Date o_effdt) {
		this.o_effdt = o_effdt;
	}
	public String getO_company() {
		return o_company;
	}
	public void setO_company(String o_company) {
		this.o_company = o_company;
	}
	public String getO_t2_peo_id() {
		return o_t2_peo_id;
	}
	public void setO_t2_peo_id(String o_t2_peo_id) {
		this.o_t2_peo_id = o_t2_peo_id;
	}
	public Date getO_coverage_begin_dt() {
		return o_coverage_begin_dt;
	}
	public void setO_coverage_begin_dt(Date o_coverage_begin_dt) {
		this.o_coverage_begin_dt = o_coverage_begin_dt;
	}
	public String getO_dual_cvg_elig() {
		return o_dual_cvg_elig;
	}
	public void setO_dual_cvg_elig(String o_dual_cvg_elig) {
		this.o_dual_cvg_elig = o_dual_cvg_elig;
	}
	public String getO_errmsg() {
		return o_errmsg;
	}
	public void setO_errmsg(String o_errmsg) {
		this.o_errmsg = o_errmsg;
	}
	
}
