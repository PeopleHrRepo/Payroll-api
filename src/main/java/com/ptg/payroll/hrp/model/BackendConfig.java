package com.ptg.payroll.hrp.model;
public class BackendConfig {
	private static final String internalBuildTimeStamp = "20120726";
	
	private String seekerSignInUrl;
	private String myselfUrl;
	private String userCompaniesUrl;
	private String employeePtoUrl;
	private String employeesPtoUrl;
	private String payCheckUrl;
	private String payCheckSummaryUrl;
	private String payCheckByKeyesUrl;
	private int signonType;  // 2- for emplId, 5 - for custom
	private String currentVersion; // Gateway currentVersion
	private String buildTimestamp;
	private int contactListLimit;
	private int pastDays;
	// this is the seeker service endpoint that verifies the tSessionId
	private String seekerTSessionVerifyUrl;

	//PeopleSoft Integration Broker Connector configuration Details
	private String IBConnectorUrl;
	private String IBFromNode;
	private String IBToNode;
	private String IBExistingFromNode;
	
	private String CRMIBConnectorUrl;
	private String CRMIBFromNode;
	private String CRMIBToNode;
	private String CRMIBExistingFromNode;
	
	private String production;
	private String defaultRecipientEmail; 
	private long   defaultSessionTimeout;
	private String authoriaServer;
	private String holidayCalendarPdfUrl;

	private String hraProfilePath;
	
	//Symmetry software Payroll-Point webservice details
	private String symmetryServiceUserName;
	private String symmetryServicePassword;
	private String symmetryWebServiceUrl;
	private String addressValidationFlag;

	private int payrollHistoryMonths;
	//private long validateTsessionInterval;

	private String soiAcaUploadUrl;

	private String stripeSecretkey;

	private String stripePublishableKey;
	
	// trinet auth properties
	private String openAmCookieName;
	private String authGuidUrl;
	private String authUserGuidUrl;

	public String getHolidayCalendarPdfUrl() {
		return holidayCalendarPdfUrl;
	}

	public void setHolidayCalendarPdfUrl(String holidayCalendarPdfUrl) {
		this.holidayCalendarPdfUrl = holidayCalendarPdfUrl;
	}

	public String getIBConnectorUrl() {
		return IBConnectorUrl;
	}
	
	public void setIBConnectorUrl(String IBConnectorUrl) {
		this.IBConnectorUrl = IBConnectorUrl;
	}	
	
	public String getIBFromNode() {
		return IBFromNode;
	}

	public void setIBFromNode(String iBFromNode) {
		IBFromNode = iBFromNode;
	}

	public String getIBToNode() {
		return IBToNode;
	}

	public void setIBToNode(String iBToNode) {
		IBToNode = iBToNode;
	}	
	
	
	public String getIBExistingFromNode() {
		return IBExistingFromNode;
	}

	public void setIBExistingFromNode(String iBExistingFromNode) {
		IBExistingFromNode = iBExistingFromNode;
	}	
	
	public String getSeekerSignInUrl() {
		return seekerSignInUrl;
	}
	public void setSeekerSignInUrl(String seekerSignInUrl) {
		this.seekerSignInUrl = seekerSignInUrl;
	}
	public String getMyselfUrl() {
		return myselfUrl;
	}
	public void setMyselfUrl(String myselfUrl) {
		this.myselfUrl = myselfUrl;
	}
	public String getUserCompaniesUrl() {
		return userCompaniesUrl;
	}
	public void setUserCompaniesUrl(String userCompaniesUrl) {
		this.userCompaniesUrl = userCompaniesUrl;
	}
	public String getEmployeePtoUrl() {
		return employeePtoUrl;
	}
	public void setEmployeePtoUrl(String ptoUrl) {
		this.employeePtoUrl = ptoUrl;
	}
	public String getEmployeesPtoUrl() {
		return employeesPtoUrl;
	}
	public void setEmployeesPtoUrl(String employeesPtoUrl) {
		this.employeesPtoUrl = employeesPtoUrl;
	}
	public String getPayCheckUrl() {
		return payCheckUrl;
	}
	public void setPayCheckUrl(String payCheckUrl) {
		this.payCheckUrl = payCheckUrl;
	}
	public int getSignonType() {
		return signonType;
	}
	public void setSignonType(int signonType) {
		this.signonType = signonType;
	}
	public String getPayCheckSummaryUrl() {
		return payCheckSummaryUrl;
	}
	public void setPayCheckSummaryUrl(String payCheckSummaryUrl) {
		this.payCheckSummaryUrl = payCheckSummaryUrl;
	}
	public String getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	public String getBuildTimestamp() {
		return buildTimestamp;
	}
	public void setBuildTimestamp(String buildTimestamp) {
		this.buildTimestamp = buildTimestamp;
	}
	public String getPayCheckByKeyesUrl() {
		return payCheckByKeyesUrl;
	}
	public void setPayCheckByKeyesUrl(String payCheckByKeyesUrl) {
		this.payCheckByKeyesUrl = payCheckByKeyesUrl;
	}
	public int getContactListLimit() {
		return contactListLimit;
	}
	public void setContactListLimit(int contactListLimit) {
		this.contactListLimit = contactListLimit;
	}
	public static String getInternalbuildtimestamp() {
		return internalBuildTimeStamp;
	}
	public int getPastDays() {
		return pastDays;
	}
	public void setPastDays(int pastDays) {
		this.pastDays = pastDays;
	}
	public String getSeekerTSessionVerifyUrl() {
		return seekerTSessionVerifyUrl;
	}
	public void setSeekerTSessionVerifyUrl(String seekerTSessionVerifyUrl) {
		this.seekerTSessionVerifyUrl = seekerTSessionVerifyUrl;
	}

	public String getCRMIBConnectorUrl() {
		return CRMIBConnectorUrl;
	}

	public void setCRMIBConnectorUrl(String cRMIBConnectorUrl) {
		CRMIBConnectorUrl = cRMIBConnectorUrl;
	}

	public String getCRMIBFromNode() {
		return CRMIBFromNode;
	}

	public void setCRMIBFromNode(String cRMIBFromNode) {
		CRMIBFromNode = cRMIBFromNode;
	}

	public String getCRMIBToNode() {
		return CRMIBToNode;
	}

	public void setCRMIBToNode(String cRMIBToNode) {
		CRMIBToNode = cRMIBToNode;
	}

	public String getCRMIBExistingFromNode() {
		return CRMIBExistingFromNode;
	}

	public void setCRMIBExistingFromNode(String cRMIBExistingFromNode) {
		CRMIBExistingFromNode = cRMIBExistingFromNode;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getDefaultRecipientEmail() {
		return defaultRecipientEmail;
	}

	public void setDefaultRecipientEmail(String defaultRecipientEmail) {
		this.defaultRecipientEmail = defaultRecipientEmail;
	}
	
	public long getDefaultSessionTimeout() {
		return defaultSessionTimeout;
	}

	public void setDefaultSessionTimeout(long defaultSessionTimeout) {
		this.defaultSessionTimeout = defaultSessionTimeout;
	}

	public String getAuthoriaServer() {
		return authoriaServer;
	}

	public void setAuthoriaServer(String authoriaServer) {
		this.authoriaServer = authoriaServer;
	}
	

	public String getHraProfilePath() {
		return hraProfilePath;
	}

	public void setHraProfilePath(String hraProfilePath) {
		this.hraProfilePath = hraProfilePath;
	}

	public String getSymmetryServiceUserName() {
		return symmetryServiceUserName;
	}

	public void setSymmetryServiceUserName(String symmetryServiceUserName) {
		this.symmetryServiceUserName = symmetryServiceUserName;
	}

	public String getSymmetryServicePassword() {
		return symmetryServicePassword;
	}

	public void setSymmetryServicePassword(String symmetryServicePassword) {
		this.symmetryServicePassword = symmetryServicePassword;
	}

	public String getAddressValidationFlag() {
		return addressValidationFlag;
	}

	public void setAddressValidationFlag(String addressValidationFlag) {
		this.addressValidationFlag = addressValidationFlag;
	}

	public String getSymmetryWebServiceUrl() {
		return symmetryWebServiceUrl;
	}

	public void setSymmetryWebServiceUrl(String symmetryWebServiceUrl) {
		this.symmetryWebServiceUrl = symmetryWebServiceUrl;
	}

	public int getPayrollHistoryMonths() {
		return payrollHistoryMonths;
	}

	public void setPayrollHistoryMonths(int payrollHistoryMonths) {
		this.payrollHistoryMonths = payrollHistoryMonths;

	}

	public static String getInternalBuildTimeStamp() {
		return internalBuildTimeStamp;
	}

	public String getSoiAcaUploadUrl() {
		return soiAcaUploadUrl;
	}

	public void setSoiAcaUploadUrl(String soiAcaUploadUrl) {
		this.soiAcaUploadUrl = soiAcaUploadUrl;
	}

	public String getStripeSecretkey() {
		return stripeSecretkey;
	}

	public void setStripeSecretkey(String stripeSecretkey) {
		this.stripeSecretkey = stripeSecretkey;
	}

	public String getStripePublishableKey() {
		return stripePublishableKey;
	}

	public void setStripePublishableKey(String stripePublishableKey) {
		this.stripePublishableKey = stripePublishableKey;
	}
	
	public String getAuthUserGuidUrl() {
		return authUserGuidUrl;
	}
	
	public void setAuthUserGuidUrl(String authUserGuidUrl) {
		this.authUserGuidUrl = authUserGuidUrl;
	}
	
	public String getAuthGuidUrl() {
		return authGuidUrl;
	}
	
	public void setAuthGuidUrl(String authGuidUrl) {
		this.authGuidUrl = authGuidUrl;
	}
	
	public String getOpenAmCookieName() {
		return openAmCookieName;
	}
	
	public void setOpenAmCookieName(String openAmCookieName) {
		this.openAmCookieName = openAmCookieName;
	}

	@Override
	public String toString() {
		return "BackendConfig{" +
				"seekerSignInUrl='" + seekerSignInUrl + '\'' +
				", myselfUrl='" + myselfUrl + '\'' +
				", userCompaniesUrl='" + userCompaniesUrl + '\'' +
				", employeePtoUrl='" + employeePtoUrl + '\'' +
				", employeesPtoUrl='" + employeesPtoUrl + '\'' +
				", payCheckUrl='" + payCheckUrl + '\'' +
				", payCheckSummaryUrl='" + payCheckSummaryUrl + '\'' +
				", payCheckByKeyesUrl='" + payCheckByKeyesUrl + '\'' +
				", signonType=" + signonType +
				", currentVersion='" + currentVersion + '\'' +
				", buildTimestamp='" + buildTimestamp + '\'' +
				", contactListLimit=" + contactListLimit +
				", pastDays=" + pastDays +
				", seekerTSessionVerifyUrl='" + seekerTSessionVerifyUrl + '\'' +
				", IBConnectorUrl='" + IBConnectorUrl + '\'' +
				", IBFromNode='" + IBFromNode + '\'' +
				", IBToNode='" + IBToNode + '\'' +
				", IBExistingFromNode='" + IBExistingFromNode + '\'' +
				", CRMIBConnectorUrl='" + CRMIBConnectorUrl + '\'' +
				", CRMIBFromNode='" + CRMIBFromNode + '\'' +
				", CRMIBToNode='" + CRMIBToNode + '\'' +
				", CRMIBExistingFromNode='" + CRMIBExistingFromNode + '\'' +
				", production='" + production + '\'' +
				", defaultRecipientEmail='" + defaultRecipientEmail + '\'' +
				", defaultSessionTimeout=" + defaultSessionTimeout +
				", authoriaServer='" + authoriaServer + '\'' +
				", holidayCalendarPdfUrl='" + holidayCalendarPdfUrl + '\'' +
				", hraProfilePath='" + hraProfilePath + '\'' +
				", symmetryServiceUserName='" + symmetryServiceUserName + '\'' +
				", symmetryServicePassword='" + symmetryServicePassword + '\'' +
				", symmetryWebServiceUrl='" + symmetryWebServiceUrl + '\'' +
				", addressValidationFlag='" + addressValidationFlag + '\'' +
				", payrollHistoryMonths=" + payrollHistoryMonths +
				", soiAcaUploadUrl='" + soiAcaUploadUrl + '\'' +
				", stripeSecretkey='" + stripeSecretkey + '\'' +
				", stripePublishableKey='" + stripePublishableKey + '\'' +
				", forgeRockCookieName='" + authUserGuidUrl + '\'' +
				", triNetAuthUrl='" + authGuidUrl + '\'' +
				", triNetAuthUrl='" + openAmCookieName + '\'' +
				'}';
	}
}
