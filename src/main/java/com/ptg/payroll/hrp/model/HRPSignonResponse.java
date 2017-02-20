package com.ptg.payroll.hrp.model;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="SignonResponse")
public class HRPSignonResponse {
	
	private HRPSignonData data = new HRPSignonData();
	
	public HRPSignonResponse(){}

	public HRPSignonData getData() {
		return data;
	}

	public void setData(HRPSignonData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "HRPSignonResponse [data=" + data.toString() + "]";
	}



	
}
