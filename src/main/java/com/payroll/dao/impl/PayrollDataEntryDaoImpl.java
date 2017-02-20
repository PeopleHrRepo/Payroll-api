package com.payroll.dao.impl;

import static com.payroll.utils.DaoUtil.bindQueryWithPayGroup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.payroll.dao.PayrollDataEntryDao;
import com.payroll.entity.PayrollEarningRecord;
import com.payroll.entity.PayrollEmployeeJobCodeRecord;
import com.payroll.entity.PayrollEmployeeRecord;
import com.payroll.entity.PayrollEmployeeRecordBackend;
import com.payroll.entity.PayrollEmployeeRecordVW;
import com.payroll.entity.PayrollError;
import com.payroll.entity.ps.NamedQueries.PayrollQueryEnum;
import com.payroll.entity.ps.NamedQueries.QueryEnum;
import com.ptg.payroll.db.sp.payroll.ResetCoPayrollForEmployeeSP;
import com.ptg.payroll.db.sp.payroll.UpdateAdditionalPaySP;
import com.ptg.payroll.domain.Department;
import com.ptg.payroll.domain.payroll.DepartmentSplit;
import com.ptg.payroll.domain.payroll.PayGroup;


public class PayrollDataEntryDaoImpl implements PayrollDataEntryDao {
    private static final Logger log = LoggerFactory.getLogger(PayrollDataEntryDao.class);

    @Autowired
    private SessionFactory  peoplesoft_sessionFactory;

    @Autowired
    private ResetCoPayrollForEmployeeSP spResetCoPayrollForEmployee;
    
    @Autowired
    private UpdateAdditionalPaySP spUpdateAdditionalPayData;
    
    
    public SessionFactory getPeoplesoft_sessionFactory() {
    	  return peoplesoft_sessionFactory;
    	 }

    	 public void setPeoplesoft_sessionFactory(SessionFactory peoplesoft_sessionFactory) {
    	  this.peoplesoft_sessionFactory = peoplesoft_sessionFactory;
    	 }
    @Override
    public List<PayrollEmployeeRecordVW> getPayrollEmployeesView(PayGroup payGroup) {

        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("getPayrollEmployeeRecordsView");
        bindQueryWithPayGroup(query, payGroup);
        @SuppressWarnings("unchecked")
        List<PayrollEmployeeRecordVW> payrollEntryEmployees = (List<PayrollEmployeeRecordVW>) query.list();

        return payrollEntryEmployees;
    }

    @Override
    public List<PayrollEmployeeRecordVW> getPayrollEmployeesViewForEmployee(PayGroup payGroup, String employeeId) {

        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("getPayrollEmployeeRecordsViewForEmployee");
        bindQueryWithPayGroup(query, payGroup);
        query.setParameter("emplid", employeeId);
        @SuppressWarnings("unchecked")
        List<PayrollEmployeeRecordVW> payrollEntryEmployees = (List<PayrollEmployeeRecordVW>) query.list();

        return payrollEntryEmployees;
    }
    
    @Override
    public List<PayrollEmployeeJobCodeRecord> getPayrollEmployeesJobCodeViewForEmployee(PayGroup payGroup, String employeeId) {

        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("getPayrollEmployeeRecordsJobCodeViewForEmployee");
        query.setParameter("company", payGroup.getCompany());
        query.setParameter("emplid", employeeId);
        @SuppressWarnings("unchecked")
        List<PayrollEmployeeJobCodeRecord> payrollEntryEmployeeJobCode = (List<PayrollEmployeeJobCodeRecord>) query.list();

        return payrollEntryEmployeeJobCode;
    }
    
    
    @Override
    public List<PayrollEmployeeRecord> getPayrollEmployeeRecords(PayGroup payGroup) {

        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("getPayrollEmployeeRecords");
        bindQueryWithPayGroup(query, payGroup);

        @SuppressWarnings("unchecked")
        List<PayrollEmployeeRecord> payrollEmployeeRecords = (List<PayrollEmployeeRecord>) query.list();

        return payrollEmployeeRecords;
    }

    @Override
    public List<PayrollEarningRecord> getPayrollEmployeeEarnings(PayGroup payGroup) {

        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("getPayrollEmployeeEarningRecords");
        bindQueryWithPayGroup(query, payGroup);

        @SuppressWarnings("unchecked")
        List<PayrollEarningRecord> payrollEntryEmployees = (List<PayrollEarningRecord>) query.list();

        return payrollEntryEmployees;
    }
    
    @Override
    public List<PayrollEarningRecord> getPayrollEmployeeEarningsForEmployee(PayGroup payGroup, String employeeId) {

        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("getPayrollEmployeeEarningRecordsForEmployee");
        bindQueryWithPayGroup(query, payGroup);
        query.setParameter("emplid", employeeId);
        
        @SuppressWarnings("unchecked")
        List<PayrollEarningRecord> payrollEntryEmployees = (List<PayrollEarningRecord>) query.list();

        return payrollEntryEmployees;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, String> getPayrollEmployeesPaymentMethod(PayGroup payGroup) {
        Map<String, String> employeesPaymentMethods = new HashMap<String, String>();
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_EMPLOYEES_PAYMENT_METHOD.getKey());
        bindQueryWithPayGroup(query, payGroup);

        List<Object[]> queryResults = query.list();

        for (Object[] result : queryResults) {
            employeesPaymentMethods.put((String) result[0], (String) result[1]);
        }

        return employeesPaymentMethods;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Map<String, String> getPayrollEmployeesPaymentMethodForEmployee(PayGroup payGroup, String employeeId) {
        Map<String, String> employeesPaymentMethods = new HashMap<String, String>();
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_EMPLOYEES_PAYMENT_METHOD_FOR_EMPLOYEE.getKey());
        bindQueryWithPayGroup(query, payGroup);
        query.setParameter("emplid", employeeId);
        

        List<Object[]> queryResults = query.list();

        for (Object[] result : queryResults) {
            employeesPaymentMethods.put((String) result[0], (String) result[1]);
        }

        return employeesPaymentMethods;
    }
    
 
    @Override
    @Transactional
    /**
     * Resets the employees earnings for the supplied paygroup and employeeid
     */
    public List<PayrollEarningRecord> resetPayrollForEmployee(PayGroup payGroup, String employeeId){
        // call the SP to reset.
    	spResetCoPayrollForEmployee.execute(payGroup, employeeId);
    	// now select the earnings data only for one employee... 
    	Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("getPayrollEmployeeEarningRecordsForEmployee");
        bindQueryWithPayGroup(query, payGroup);
        query.setParameter("emplid", employeeId);
        
        @SuppressWarnings("unchecked")
        List<PayrollEarningRecord> payrollEntryEmployees = (List<PayrollEarningRecord>) query.list();

        return payrollEntryEmployees;
    	
    }
    
    
    @Override
    @Transactional
    public void deleteEmployeeEarnings(PayGroup payGroup, String employeeId, Date periodStartDate, Date periodEndDate, Integer checkNumber){
    	if (employeeId != null && !"".equals(employeeId) && periodStartDate != null && periodEndDate != null) {
    		deleteEmployeeRecords(payGroup, employeeId, periodStartDate, periodEndDate, checkNumber);
    		deleteEmployeeEarningRecords(payGroup, employeeId, periodStartDate, periodEndDate, checkNumber);
    	}
    }
    
    private int deleteEmployeeEarningRecords(PayGroup payGroup, String employeeId, Date periodStartDate, Date periodEndDate, Integer checkNumber) {
        Query query = null;
        if("N".equals(payGroup.getOffCycle())){
        	query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(QueryEnum.DELETE_PAYROLL_EMPLOYEE_EARNING_RECORDS.getKey());
        	query.setParameter("startDate", periodStartDate);
            query.setParameter("endDate", periodEndDate);
        } else {
        	query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(QueryEnum.DELETE_PAYROLL_EMPLOYEE_CHECK_RECORDS.getKey());
        	query.setParameter("checkNumber", checkNumber);
        }
        bindQueryWithPayGroup(query, payGroup);

        query.setParameter("employeeId", employeeId);
        
        return query.executeUpdate();
    }

    private int deleteEmployeeRecords(PayGroup payGroup, String employeeId, Date periodStartDate, Date periodEndDate, Integer lineNumber) {
    	
        Query query = null;
        if("N".equals(payGroup.getOffCycle())){
        	query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(QueryEnum.DELETE_PAYROLL_EMPLOYEE_RECORDS.getKey());
            query.setParameter("startDate", periodStartDate);
            query.setParameter("endDate", periodEndDate);
        } else {
        	query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(QueryEnum.DELETE_PAYROLL_EMPLOYEE_LINE_ITEM.getKey());
        	query.setParameter("lineNumber", lineNumber);
        }
        bindQueryWithPayGroup(query, payGroup);

        query.setParameter("employeeId", employeeId);

        return query.executeUpdate();
    }

    @Override
    @Transactional
    public void insertPayrollEmployeeLineItems(PayGroup payGroup, List<PayrollEmployeeRecord> employeeRecords, List<PayrollEarningRecord> earningRecords) {
                
        // Delete the employee records
        for (PayrollEmployeeRecord record : employeeRecords) {
            deleteEmployeeRecords(payGroup, record.getEmplId(), record.getStartDate(), record.getEndDate(), record.getLineNumber());
            deleteEmployeeEarningRecords(payGroup, record.getEmplId(), record.getStartDate(), record.getEndDate(), record.getLineNumber());
        }

        // Insert the employee pay line items in RPTD table
        peoplesoft_sessionFactory.getCurrentSession().flush();
        peoplesoft_sessionFactory.getCurrentSession().clear();
        for (PayrollEmployeeRecord employeeRecord : employeeRecords) {
            peoplesoft_sessionFactory.getCurrentSession().persist(employeeRecord);
        }
        for (PayrollEarningRecord earningRecord : earningRecords) {
            if (!"RECR".equals(earningRecord.getSource())) {
                peoplesoft_sessionFactory.getCurrentSession().persist(earningRecord);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Department> getCompanyDepartments(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.dataentry.findDepartmentsByPayrollInstance");

        query.setParameter("company", payGroup.getCompany());
        query.setParameter("payEndDate", payGroup.getPayEndDate());

        List<Object[]> results = query.list();
        List<Department> departments = new ArrayList<Department>();
        for (Object[] values : results) {
            Department dept = new Department();
            dept.setDeptId((String) values[0]);
            dept.setDeptDesc((String) values[1]);
            dept.setDeptLongDesc((String) values[2]);
            departments.add(dept);
        }
        return departments;

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<DepartmentSplit> getDepartmentSplits(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.dataentry.findDepartmentDistributionByPayrollInstance");

        query.setParameter("company", payGroup.getCompany());
        query.setParameter("payGroup", payGroup.getPayGroup());
        query.setParameter("payEndDate", payGroup.getPayEndDate());

        List<Object[]> results = query.list();
        List<DepartmentSplit> deptSplits = new ArrayList<DepartmentSplit>();
        for (Object[] values : results) {
            DepartmentSplit deptSplit = new DepartmentSplit();
            deptSplit.setEmployeeId((String) values[0]);
            deptSplit.setEmployeeRecord(((BigDecimal) values[1]).intValue());
            deptSplit.setDepartmentId((String) values[2]);
            deptSplit.setDepartmentDescription((String) values[3]);
            deptSplit.setSplitPercent((BigDecimal) values[4]);
            deptSplits.add(deptSplit);
        }
        return deptSplits;

    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<DepartmentSplit> getDepartmentSplitsForEmployee(PayGroup payGroup, String employeeId) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.dataentry.findDepartmentDistributionByPayrollInstanceForEmployee");

        query.setParameter("company", payGroup.getCompany());
        query.setParameter("payGroup", payGroup.getPayGroup());
        query.setParameter("payEndDate", payGroup.getPayEndDate());
        query.setParameter("emplid", employeeId);
        

        List<Object[]> results = query.list();
        List<DepartmentSplit> deptSplits = new ArrayList<DepartmentSplit>();
        for (Object[] values : results) {
            DepartmentSplit deptSplit = new DepartmentSplit();
            deptSplit.setEmployeeId((String) values[0]);
            deptSplit.setEmployeeRecord(((BigDecimal) values[1]).intValue());
            deptSplit.setDepartmentId((String) values[2]);
            deptSplit.setDepartmentDescription((String) values[3]);
            deptSplit.setSplitPercent((BigDecimal) values[4]);
            deptSplits.add(deptSplit);
        }
        return deptSplits;

    }
    

    @Override
    public String getDefaultEarnCode(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.dataentry.getDefaultRegularEarnCodeByPayrollInstance");

        query.setParameter("company", payGroup.getCompany());
        query.setParameter("payGroup", payGroup.getPayGroup());
        query.setParameter("payEndDate", payGroup.getPayEndDate() == null ? new Date() : payGroup.getPayEndDate());

        return (String) query.uniqueResult();
    }

	@Override
	public List<PayrollEmployeeRecordBackend> getPayrollEmployeesChangedByBackend(
			PayGroup payGroup) {
		log.info("DONE Start");
		System.out.println("DONE Start");
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("getEmployeeChangedByBackend");
        bindQueryWithPayGroup(query, payGroup);

        @SuppressWarnings("unchecked")
        List<PayrollEmployeeRecordBackend> payrollEntryEmployees = (List<PayrollEmployeeRecordBackend>) query.list();
        log.info("DONE");
        return payrollEntryEmployees;
        
	}

	@Override
	@Transactional
	public void insertPayrollErrors(List<PayrollError> payrollErrors) {
		peoplesoft_sessionFactory.getCurrentSession().flush();
        peoplesoft_sessionFactory.getCurrentSession().clear();
        for (PayrollError error : payrollErrors) {
            peoplesoft_sessionFactory.getCurrentSession().merge(error);
        }
		
	}

	@Override
    @Transactional
	public void updateAdditionalPay(PayGroup payGroup) {
		spUpdateAdditionalPayData.execute(payGroup, null);
	}

}

