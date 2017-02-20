package com.payroll.entity;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.payroll.entity.pk.QuarterlyEnrollmentSetupPK;

@Entity
@Table(name="PS_T2_QTR_OE_SETUP")
@IdClass(QuarterlyEnrollmentSetupPK.class)
@NamedNativeQuery(
	name="getQESetUpByQuarterEffDt",
	query="select a.* from PS_T2_QTR_OE_SETUP a where  a.t2_oe_quarter= :t2_oe_quarter and a.effdt =(SELECT MAX(QES_ED.EFFDT) FROM PS_T2_QTR_OE_SETUP QES_ED WHERE QES_ED.EFFDT <= :effdt)",
    resultClass=QuarterlyEnrollmentSetup.class
		
)
public class QuarterlyEnrollmentSetup {
	@Id @Temporal(TemporalType.DATE)
	private Date effdt;
	@Id private String t2_oe_quarter;
	@Id @Temporal(TemporalType.DATE)
	private Date t2_plnyr_start_dt;
	@Id @Temporal(TemporalType.DATE)
	private Date t2_plnyr_end_dt;
	private String benefit_program;
	@Transient 
	private String t2_product_line;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuarterlyEnrollmentSetup [effdt=").append(effdt)
				.append(", t2_oe_quarter=").append(t2_oe_quarter)
				.append(", t2_plnyr_start_dt=").append(t2_plnyr_start_dt)
				.append(", t2_plnyr_end_dt=").append(t2_plnyr_end_dt)
				.append(", benefit_program=").append(benefit_program)
				.append(", t2_product_line=").append(t2_product_line)
				.append("]");
		return builder.toString();
	}

	public Date getEffdt() {
		return effdt;
	}

	public String getT2_product_line() {
		return t2_product_line;
	}

	public void setT2_product_line(String t2_product_line) {
		this.t2_product_line = t2_product_line;
	}

	public void setEffdt(Date effdt) {
		this.effdt = effdt;
	}

	public String getT2_oe_quarter() {
		return t2_oe_quarter;
	}

	public void setT2_oe_quarter(String t2_oe_quarter) {
		this.t2_oe_quarter = t2_oe_quarter;
	}

	public Date getT2_plnyr_start_dt() {
		return t2_plnyr_start_dt;
	}

	public void setT2_plnyr_start_dt(Date t2_plnyr_start_dt) {
		this.t2_plnyr_start_dt = t2_plnyr_start_dt;
	}

	public Date getT2_plnyr_end_dt() {
		return t2_plnyr_end_dt;
	}

	public void setT2_plnyr_end_dt(Date t2_plnyr_end_dt) {
		this.t2_plnyr_end_dt = t2_plnyr_end_dt;
	}

	public String getBenefit_program() {
		return benefit_program;
	}

	public void setBenefit_program(String benefit_program) {
		this.benefit_program = benefit_program;
	}
	
}