package com.payroll.entity.pk;

import java.io.Serializable;
import java.util.Date;

public class QuarterlyEnrollmentSetupPK implements Serializable {
	private static final long serialVersionUID = 798845107188899017L;
	
	private Date effdt;
	private String t2_oe_quarter;
	private Date t2_plnyr_start_dt;
	private Date t2_plnyr_end_dt;
	
	public Date getEffdt() {
		return effdt;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((effdt == null) ? 0 : effdt.hashCode());
		result = prime * result
				+ ((t2_oe_quarter == null) ? 0 : t2_oe_quarter.hashCode());
		result = prime * result
				+ ((t2_plnyr_end_dt == null) ? 0 : t2_plnyr_end_dt.hashCode());
		result = prime
				* result
				+ ((t2_plnyr_start_dt == null) ? 0 : t2_plnyr_start_dt
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuarterlyEnrollmentSetupPK other = (QuarterlyEnrollmentSetupPK) obj;
		if (effdt == null) {
			if (other.effdt != null)
				return false;
		} else if (!effdt.equals(other.effdt))
			return false;
		if (t2_oe_quarter == null) {
			if (other.t2_oe_quarter != null)
				return false;
		} else if (!t2_oe_quarter.equals(other.t2_oe_quarter))
			return false;
		if (t2_plnyr_end_dt == null) {
			if (other.t2_plnyr_end_dt != null)
				return false;
		} else if (!t2_plnyr_end_dt.equals(other.t2_plnyr_end_dt))
			return false;
		if (t2_plnyr_start_dt == null) {
			if (other.t2_plnyr_start_dt != null)
				return false;
		} else if (!t2_plnyr_start_dt.equals(other.t2_plnyr_start_dt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QuarterlyEnrollmentSetupPK [effdt=").append(effdt)
				.append(", t2_oe_quarter=").append(t2_oe_quarter)
				.append(", t2_plnyr_start_dt=").append(t2_plnyr_start_dt)
				.append(", t2_plnyr_end_dt=").append(t2_plnyr_end_dt)
				.append("]");
		return builder.toString();
	}
	
}
