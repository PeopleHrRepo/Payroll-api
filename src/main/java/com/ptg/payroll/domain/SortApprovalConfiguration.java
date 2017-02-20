package com.ptg.payroll.domain;
import java.util.HashMap;
import java.util.Map;


public class SortApprovalConfiguration {

	private String currentSortOrder;
    private String currentSortOrderDesc;
    private String currentApprovalOrder;
    private String currentApprovalOrderDesc;
    private Map	   sortOrderConfig;
    private Map	   approvalOrderConfig;
    private String companyDesc;

    public SortApprovalConfiguration() {
		super();
		Map<String,String> sortConfig = new HashMap<String,String>();
		Map<String,String> approvalConfig = new HashMap<String,String>();
		
		sortConfig.put("1", "Sort by Employee");
		sortConfig.put("3;1", "Sort by Location, Employee");
		sortConfig.put("2;1", "Sort by Department, Employee");
		sortConfig.put("4;1", "Sort by Pay Group, Employee");
		sortConfig.put("3;2;1", "Sort by Location, Department, Employee");
		sortConfig.put("2;3;1", "Sort by Department, Location, Employee");
		
		approvalConfig.put("1", "Approve by Company");
		approvalConfig.put("2", "Approve by Department, Company");
		approvalConfig.put("3", "Approve by Location, Company");
		approvalConfig.put("4", "Approve by Pay Group, Company");
		
		this.approvalOrderConfig = approvalConfig;
		this.sortOrderConfig = sortConfig;
		
	}
    
    /**
     * All auto generated code including getters, setters and constructors would
     * go in this section. Please don't hand edit them -- Starts
     **/
    
    public String getCurrentSortOrder() {
		return currentSortOrder;
	}
	public void setCurrentSortOrder(String currentSortOrder) {
		this.currentSortOrder = currentSortOrder;
	}
	public String getCurrentApprovalOrder() {
		return currentApprovalOrder;
	}
	public void setCurrentApprovalOrder(String currentApprovalOrder) {
		this.currentApprovalOrder = currentApprovalOrder;
	}
	public Map getSortOrderConfig() {
		return sortOrderConfig;
	}
	public void setSortOrderConfig(Map sortOrderConfig) {
		this.sortOrderConfig = sortOrderConfig;
	}
	public Map getApprovalOrderConfig() {
		return approvalOrderConfig;
	}
	public void setApprovalOrderConfig(Map approvalOrderConfig) {
		this.approvalOrderConfig = approvalOrderConfig;
	}
	public String getCurrentSortOrderDesc() {
		return currentSortOrderDesc;
	}
	public void setCurrentSortOrderDesc(String currentSortOrderDesc) {
		this.currentSortOrderDesc = currentSortOrderDesc;
	}
	public String getCurrentApprovalOrderDesc() {
		return currentApprovalOrderDesc;
	}
	public void setCurrentApprovalOrderDesc(String currentApprovalOrderDesc) {
		this.currentApprovalOrderDesc = currentApprovalOrderDesc;
	}

	public String getCompanyDesc() {
		return companyDesc;
	}

	public void setCompanyDesc(String companyDesc) {
		this.companyDesc = companyDesc;
	}
    
   
    /* ******** All auto generated code, Don't hand edit them - Ends ******** */
}