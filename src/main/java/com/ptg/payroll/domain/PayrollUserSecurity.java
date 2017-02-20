package com.ptg.payroll.domain;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ptg.payroll.model.UserPermission;


public class PayrollUserSecurity {
	
	public static final String OTHER_EARNINGS = "Entry.Grid.Column.OtherEarnings"; 
	public static final String OTHER_HOURS = "Entry.Grid.Column.OtherHours";
	public static final String REGULAR_HOURS = "Entry.Grid.Column.RegularHours";
	public static final String UNIT_PAY = "Entry.Grid.Column.UnitPay";
	public static final String WAGE_OVERIDE = "Entry.Grid.Column.WageOverride";
	public static final String DELETE_ALL_SPECIALPAYROLL= "Entry.SpecialPayroll.All.Delete";

	
	private Map<String, UserPermission> permissions = new HashMap<String, UserPermission>();
	
	
	public boolean hasPermission(String permission, String action){
		boolean hasPermission = false;
		
		UserPermission userPermission = (UserPermission) permissions.get(permission);
		if (userPermission != null){
			if (action.equals("View") && "Y".equals(userPermission.getView())) {
				hasPermission = true;
			} else if (action.equals("Edit") && "Y".equals(userPermission.getEdit())) {
				hasPermission = true;
			}
		} else {
			hasPermission = true;
		}
		
		return hasPermission;
	}
	
	public boolean hasPermission(String permission){
		boolean hasPermission = false;
		
		UserPermission userPermission = (UserPermission) this.permissions.get(permission);
		if (userPermission != null){
			if ("Y".equals(userPermission.getView()) && "Y".equals(userPermission.getEdit())) {
				hasPermission = true;
			}
		} else {
			hasPermission = true;
		}
		
		return hasPermission;
	}
	
	public void loadUserSecurity(List<UserPermission> userPermissions){
		for (UserPermission permission : userPermissions) {
			this.permissions.put(permission.getComponent(), permission);
		}
	}

	public Map<String, UserPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Map<String, UserPermission> permissions) {
		this.permissions = permissions;
	}
}
