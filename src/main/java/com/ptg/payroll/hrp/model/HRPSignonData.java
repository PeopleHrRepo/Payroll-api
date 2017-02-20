package com.ptg.payroll.hrp.model;
public class HRPSignonData {
	  private String userid;
	  private String user_company;
	  private String user_position;
	  private String tsessionid;
	  private String logon_timestamp;
	  
	  public HRPSignonData(){}
	  
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUser_company() {
		return user_company;
	}
	public void setUser_company(String user_company) {
		this.user_company = user_company;
	}
	public String getUser_position() {
		return user_position;
	}
	public void setUser_position(String user_position) {
		this.user_position = user_position;
	}
	public String getTsessionid() {
		return tsessionid;
	}
	public void setTsessionid(String tsessionid) {
		this.tsessionid = tsessionid;
	}
	public String getLogon_timestamp() {
		return logon_timestamp;
	}
	public void setLogon_timestamp(String logon_timestamp) {
		this.logon_timestamp = logon_timestamp;
	}

	@Override
	public String toString() {
		return "HRPSignonData [userid=" + userid + ", user_company=" + user_company + ", user_position=" + user_position + ", tsessionid=" + tsessionid
				+ ", logon_timestamp=" + logon_timestamp + "]";
	}


}
