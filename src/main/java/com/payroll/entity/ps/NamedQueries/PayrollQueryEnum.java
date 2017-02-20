package com.payroll.entity.ps.NamedQueries;
public enum PayrollQueryEnum {

	GET_PAYROLL_VIEW_EMPLOYEE_EARNINGS {
	   @Override
       public String getKey() {
           return "SQL.Payroll.View.getEmployeeEarnings";
	   }
   },
   
   GET_PAYROLL_VIEW_EMPLOYEE_HOURS {
	   @Override
       public String getKey() {
           return "SQL.Payroll.View.getEmployeeHours";
	   }
   },
   
   GET_PAYROLL_VIEW_HEADER {
	   @Override
       public String getKey() {
           return "SQL.Payroll.View.getHeaderInfo";
	   }
   },
   
   GET_PAYROLL_VIEW_INVOICE_TOTAL {
	   @Override
       public String getKey() {
           return "SQL.Payroll.View.getInvoiceTotal";
	   }
   },
   
   GET_PAYROLL_EMPLOYEES_PAYMENT_METHOD {
	   @Override
       public String getKey() {
            return "SQL.Payroll.DataEntry.getEmployeesPaymentMethod";
	   }
   },
   
   GET_PAYROLL_EMPLOYEES_PAYMENT_METHOD_FOR_EMPLOYEE {
	   @Override
       public String getKey() {
            return "SQL.Payroll.DataEntry.getEmployeesPaymentMethodForEmployee";
	   }
   },
   
   GET_PAYROLL_GET_ON_CYCLE_PAYROLLS {
	   @Override
       public String getKey() {
           return "SQL.Payroll.OffCycle.getOncyclePayrolls";
	   }
   },
   GET_PAYROLL_EMPLOYEES_BY_PAYGROUP {
	   @Override
       public String getKey() {
           return "SQL.Payroll.DataEntry.getEmployeesByPayGroup";
	   }
   },
   GET_PAYROLL_GET_PAYROLL_GROUP {
	   @Override
       public String getKey() {
           return "SQL.payroll.dashboard.getPayrollGroup";
	   }
   },
   GET_PAYROLL_GET_PAYROLL_EMPLOYEES {
	   @Override
       public String getKey() {
           return "SQL.payroll.dataentry.payroll.Employees";
	   }
   },
   GET_PAYROLL_GET_PAYROLL_TERMINATED_EMPLOYEES {
	   @Override
       public String getKey() {
           return "SQL.payroll.dataentry.payroll.terminated.Employees";
	   }
   },
   GET_PAYROLL_ONCYCLE_EMPLOYEES {
	   @Override
       public String getKey() {
           return "SQL.payroll.data.oncycle.Employees";
	   }
   },
   GET_PAYROLL_TERMINATED_EMPLOYEES {
	   @Override
       public String getKey() {
           return "SQL.payroll.data.oncycle.terminated.Employees";
	   }
   },
   GET_PAYROLL_ONCYCLE_ALL_EMPLOYEES {
	   @Override
       public String getKey() {
           return "SQL.payroll.data.oncycle.AllEmployees";
	   }
   },
   GET_PAYROLL_ONCYCLE_EMPLOYEES_BY_DEPT_LOC {
	   @Override
       public String getKey() {
           return "SQL.payroll.data.oncycle.EmployeesByDeptLoc";
	   }
   },
   GET_PAYROLL_OFFCYCLE_EMPLOYEES {
	   @Override
       public String getKey() {
           return "SQL.payroll.data.offcycle.Employees";
	   }
   },
   GET_PAYROLL_COMPANY_DEPARTMENTS {
	   @Override
       public String getKey() {
           return "SQL.payroll.data.company.Departments";
	   }
   },
   GET_PAYROLL_EMPLOYEE_DEPARTMENTS {
	   @Override
       public String getKey() {
           return "SQL.payroll.data.employee.Departments";
	   }
   },
   GET_PAYROLL_EMPLOYEES_PENDING_LEAVES {
	 @Override
	 public String getKey() {
		 return "SQL.payroll.data.employee.PendingLeaves";
	 }
   },
   GET_PAYROLL_ENGINE_NEXT_STEP {
		 @Override
		 public String getKey() {
			 return "SQL.payroll.data.payrollenginee.NextStep";
		 }
   },
		 
  GET_PAYROLL_COMPANY_ADDRESS{
	   @Override
		 public String getKey() {
			 return "SQL.payroll.company.address";
		 }
   },
	 
   GET_PAYROLL_EARNING_CODES{
	 @Override
		 public String getKey() {
			 return "SQL.payroll.earning.codes";
		 }
	}, 
	DELETE_PAYROLL_EARNING_CODES{
       @Override
       public String getKey() {
           return "SQL.payroll.deletePayrollEarningCodes";
       }
	},
       
   PAYROLL_ENTRY_BY_EMPLOYEE{
       @Override
       public String getKey() {
           return "SQL.payroll.entry.employee";
       }
   },
   
   GET_PAYROLL_BLACKOUT_TIME {
	   @Override
       public String getKey() {
           return "SQL.payroll.getPayrollBlackoutTime";
	   }
   },
   
   
   UPDATE_PE_PROCESS_CHECKDT {
	   @Override
       public String getKey() {
           return "SQL.payroll.updatePeProcessCheckDt";
	   }
	},

	GET_OFFCYCLE_EMPLID_EMPLNAME_MAP {

		@Override
		public String getKey() {
			return "SQL.payroll.offcycle.emplIdEmplNameMap";
		}
	},
	
	GET_OFFCYCLE_EMPLID_ALTEREMPLID_MAP {

		@Override
		public String getKey() {
			return "SQL.payroll.offcycle.emplIdAlterEmplIdMap";
		}
	},
	
	GET_PAYROLL_REPORTED_EARNCODES {

		@Override
		public String getKey() {
			return "SQL.payroll.getReportedEarncodes";
		}

   },
   
   DELETE_REPORTED_EARNCODERECORDS {

		@Override
		public String getKey() {
			return "SQL.delete.reported.earncodeRecords";
		}

  },
  
  UPDATE_DATE_OPRID_PE_PROCESS {
		@Override
		public String getKey() {
			return "SQL.update.date.operId.pe.process";
		}

  },
  
  GET_SPECIAL_PAYROLL_CREATED_USR {
		@Override
		public String getKey() {
			return "SQL.GET.SPECIAL_PAYROLL_CREATED_USR";
		}

  },
  
  GET_SPECIAL_PAYROLL_UPDATED_USRS {
		@Override
		public String getKey() {
			return "SQL.GET.SPECIAL_PAYROLL_UPDATED_USRS";
		}

  },
  
  UPDATE_STATUS_ALL_SUB_FROM_PROCESS {
		@Override
		public String getKey() {
			return "SQL.payroll.data.payroll.UpdateStatusSubProcessFromProcess";
		}

},
GET_EMPLOYEE_MAX_REG_HOURS {
	@Override
	public String getKey() {
		return "SQL.payroll.data.payroll.getEmployeeMaxRegHours";
	}
},

GET_UNIQUE_EMPLOYEE_FOR_PAYGROUP{
	@Override
	public String getKey() {
		return "SQL.timesheetPayroll.getUniqueEmployeeForPaygroup";
	}
},

  GET_ACTIVE_EARN_CODES_BY_COMPANY {
		@Override
		public String getKey() {
			return "SQL.payroll.activeEarncodesByCompany";
		}
 
},GET_PAYROLL_DEADLINE_TIME {
    @Override
    public String getKey() {
        return "SQL.payroll.process.getPayrollDeadlineTime";
    }
},
  
  GET_TOP_USER_ROLE {
    @Override
    public String getKey() {
        return "SQL.payroll.security.getTopUserRole";
    }
},
  
  GET_USER_DEPT_LOCATION_ACCESS {
    @Override
    public String getKey() {
        return "SQL.payroll.security.getUserDeptLocationAccess";
    }
}
;
  
  ;
    
    /**
     * 
     * @return The String representing the key value for this named query.
     */
    public abstract String getKey();
    
}