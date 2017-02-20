package com.payroll.mapper;
import java.util.ArrayList;
import java.util.List;

import com.ptg.payroll.domain.PayrollUserSecurity;
import com.ptg.payroll.domain.payroll.Permission;
import com.ptg.payroll.domain.payroll.User;
import com.ptg.payroll.domain.payroll.UserRole;
import com.ptg.payroll.model.UserPermission;

public class PayrollMapper {

    public static class PayrollSecurityMapper {
    	public User translate(List<UserPermission> permissions, User user){
    		List<UserRole> roles = new ArrayList<UserRole>();
    		UserRole role = new UserRole();
    		List<Permission> rolepermissions = new ArrayList<Permission>();
    		
    		for(UserPermission permission: permissions){
    			role.setName(permission.getRole());
    			Permission rolePermission = new Permission();
    			
    			rolePermission.setName(permission.getComponent());
    			
    			List<Permission> permissionList = new ArrayList<Permission>();
    			Permission per = new Permission();
    			per.setName("View");
    			per.setAccess("Y".equals(permission.getView()) ? true : false);
    			
    			Permission per1 = new Permission();
    			per1.setName("Edit");
    			per1.setAccess("Y".equals(permission.getView()) ? true : false);
    			
    			permissionList.add(per);
    			permissionList.add(per1);
    			
    			rolePermission.setPermissions(permissionList);
    			rolePermission.setAccess(true);
    			rolepermissions.add(rolePermission);
    			
    		}
    		
    		role.setPermissions(rolepermissions);
    		role.setAccess(true);
    		roles.add(role);
    		user.setRoles(roles);
    		return user;
    	}
    	
    	public PayrollUserSecurity translate(List<UserPermission> permissions){
    		PayrollUserSecurity security = new PayrollUserSecurity();
    		security.loadUserSecurity(permissions);
    		return security;
    	}
    }

}
