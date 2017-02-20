package com.payroll.dao.impl;

import static com.payroll.utils.DaoUtil.*;
import java.math.BigDecimal;
import java.util.*;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.payroll.dao.PayrollDataAccessDao;
import com.payroll.entity.PayrollEarningCode;
import com.payroll.entity.pk.PayrollStagingRecordKey;
import com.payroll.entity.ps.NamedQueries.PayrollQueryEnum;
import com.payroll.entity.ps.NamedQueries.QueryEnum;
import com.payroll.entity.ps.payrollentry.EmployeeDOM;
import com.payroll.entity.ps.payrollentry.PayrollEntryValidator;
import com.ptg.payroll.db.sp.payroll.EmployeesLeaveAccrualSP;
import com.ptg.payroll.domain.Department;
import com.ptg.payroll.domain.EmployeeDepartmentSplit;
import com.ptg.payroll.domain.EmployeeLeaveBalance;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.entity.ps.pe.dashboard.PayrollDeadline;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class PayrollDataAccessDaoImpl implements PayrollDataAccessDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayrollDataAccessDaoImpl.class);
    @Autowired
    private SessionFactory  peoplesoft_sessionFactory;
    	public SessionFactory getPeoplesoft_sessionFactory() {
    	  return peoplesoft_sessionFactory;
    	 }

    	 public void setPeoplesoft_sessionFactory(SessionFactory peoplesoft_sessionFactory) {
    	  this.peoplesoft_sessionFactory = peoplesoft_sessionFactory;
    	 }

    @Autowired
    private EmployeesLeaveAccrualSP spEmployeesLeaveAccrual;

    
    
    public void setSpEmployeesLeaveAccrual(EmployeesLeaveAccrualSP spEmployeesLeaveAccrual) {
		this.spEmployeesLeaveAccrual = spEmployeesLeaveAccrual;
	}

	@Override
    public List<EmployeeDOM> getOnCycleEmployees(PayGroup payGroup) {
		LOGGER.info("getOnCycleEmployees - begin");
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_ONCYCLE_EMPLOYEES.getKey());
        bindQueryWithPayGroup(query, payGroup);

        List<EmployeeDOM> payrollEmployees = new ArrayList<EmployeeDOM>();
        @SuppressWarnings("unchecked")
        List<Object[]> queryResults = query.list();

        for (Object[] r : queryResults) {
            EmployeeDOM employee = new EmployeeDOM();
            employee.setEmployeeId((String) r[0]);
            employee.setEmployeeRecord((BigDecimal) r[1]);
            employee.setDepartmentId((String) r[2]);
            employee.setDepartmentDesc((String) r[3]);
            employee.setJobCode((String) r[4]);
            employee.setLocation((String) r[5]);
            employee.setLocationDesc((String) r[6]);
            employee.setLastName((String) r[7]);
            employee.setFirstName((String) r[8]);
            employee.setMiddleName((String) r[9]);
            employee.setEmplStatus((String) r[10]);
            employee.setEmplStatusDescr((String) r[11]);
            employee.setRegOrTemp((String) r[12]);
            employee.setFullOrPartTime((String) r[13]);
            employee.setEmployeeType((String) r[14]);
            employee.setEmployeeClass((String) r[15]);
            employee.setHireDt((Date) r[16]);
            employee.setTerminationDt((Date) r[17]);
            employee.setLastWorkDt((Date) r[18]);
            employee.setUsWorkEligibilty((String) r[19]);

            payrollEmployees.add(employee);
        }
        LOGGER.info("getOnCycleEmployees - end");
        return payrollEmployees;
    }

    @Override
    public List<EmployeeDOM> getOnCycleAllEmployees(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_ONCYCLE_ALL_EMPLOYEES.getKey());
        bindQueryWithPayGroup(query, payGroup);

        List<EmployeeDOM> payrollEmployees = new ArrayList<EmployeeDOM>();
        @SuppressWarnings("unchecked")
        List<Object[]> queryResults = query.list();

        for (Object[] r : queryResults) {
            EmployeeDOM employee = new EmployeeDOM();
            employee.setEmployeeId((String) r[0]);
            employee.setEmployeeRecord((BigDecimal) r[1]);
            employee.setDepartmentId((String) r[2]);
            employee.setDepartmentDesc((String) r[3]);
            employee.setJobCode((String) r[4]);
            employee.setLocation((String) r[5]);
            employee.setLocationDesc((String) r[6]);
            employee.setLastName((String) r[7]);
            employee.setFirstName((String) r[8]);
            employee.setMiddleName((String) r[9]);
            employee.setEmplStatus((String) r[10]);
            employee.setEmplStatusDescr((String) r[11]);
            employee.setRegOrTemp((String) r[12]);
            employee.setFullOrPartTime((String) r[13]);
            employee.setEmployeeType((String) r[14]);
            employee.setEmployeeClass((String) r[15]);
            employee.setHireDt((Date) r[16]);
            employee.setTerminationDt((Date) r[17]);
            employee.setLastWorkDt((Date) r[18]);
            employee.setUsWorkEligibilty((String) r[19]);

            payrollEmployees.add(employee);
        }

        return payrollEmployees;
    }

    @Override
    public List<EmployeeDOM> getOffCycleEmployees(PayGroup payGroup) {
        Query q = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_OFFCYCLE_EMPLOYEES.getKey());
        q.setParameter("company", payGroup.getCompany());
        q.setParameter("payGroup", payGroup.getPayGroup());

        List<EmployeeDOM> payrollEmployees = new ArrayList<EmployeeDOM>();
        @SuppressWarnings("unchecked")
        List<Object[]> queryResults = q.list();

        for (Object[] r : queryResults) {
            EmployeeDOM employee = new EmployeeDOM();
            employee.setEmployeeId((String) r[0]);
            employee.setEmployeeRecord((BigDecimal) r[1]);
            employee.setDepartmentId((String) r[2]);
            employee.setDepartmentDesc((String) r[3]);
            employee.setJobCode((String) r[4]);
            employee.setLocation((String) r[5]);
            employee.setLocationDesc((String) r[6]);
            employee.setLastName((String) r[7]);
            employee.setFirstName((String) r[8]);
            employee.setMiddleName((String) r[9]);
            employee.setEmplStatus((String) r[10]);
            employee.setEmplStatusDescr((String) r[11]);
            employee.setRegOrTemp((String) r[12]);
            employee.setFullOrPartTime((String) r[13]);
            employee.setEmployeeType((String) r[14]);
            employee.setEmployeeClass((String) r[15]);
            employee.setHireDt((Date) r[16]);
            employee.setTerminationDt((Date) r[17]);
            employee.setLastWorkDt((Date) r[18]);
            employee.setUsWorkEligibilty((String) r[19]);

            payrollEmployees.add(employee);
        }

        return payrollEmployees;
    }

    @Override
    public List<EmployeeDOM> getPayrollEmployees(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_GET_PAYROLL_EMPLOYEES.getKey());
        bindQueryWithPayGroup(query, payGroup);

        List<EmployeeDOM> payrollEmployees = new ArrayList<EmployeeDOM>();
        @SuppressWarnings("unchecked")
        List<Object[]> queryResults = query.list();

        for (Object[] r : queryResults) {
            EmployeeDOM employee = new EmployeeDOM();
            employee.setEmployeeId((String) r[0]);
            employee.setEmployeeRecord((BigDecimal) r[1]);
            employee.setDepartmentId((String) r[2]);
            employee.setDepartmentDesc((String) r[3]);
            employee.setJobCode((String) r[4]);
            employee.setLocation((String) r[5]);
            employee.setLocationDesc((String) r[6]);
            employee.setLastName((String) r[7]);
            employee.setFirstName((String) r[8]);
            employee.setMiddleName((String) r[9]);
            employee.setEmplStatus((String) r[10]);
            employee.setEmplStatusDescr((String) r[11]);
            employee.setRegOrTemp((String) r[12]);
            employee.setFullOrPartTime((String) r[13]);
            employee.setEmployeeType((String) r[14]);
            employee.setEmployeeClass((String) r[15]);
            employee.setHireDt((Date) r[16]);
            employee.setTerminationDt((Date) r[17]);
            employee.setLastWorkDt((Date) r[18]);
            employee.setUsWorkEligibilty((String) r[19]);

            payrollEmployees.add(employee);
        }

        return payrollEmployees;
    }

    @Override
    public List<EmployeeDOM> getPayrollTerminatedEmployees(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_GET_PAYROLL_TERMINATED_EMPLOYEES.getKey());
        bindQueryWithPayGroup(query, payGroup);

        List<EmployeeDOM> payrollEmployees = new ArrayList<EmployeeDOM>();
        @SuppressWarnings("unchecked")
        List<Object[]> queryResults = query.list();

        for (Object[] r : queryResults) {
            EmployeeDOM employee = new EmployeeDOM();
            employee.setEmployeeId((String) r[0]);
            employee.setEmployeeRecord((BigDecimal) r[1]);
            employee.setDepartmentId((String) r[2]);
            employee.setDepartmentDesc((String) r[3]);
            employee.setJobCode((String) r[4]);
            employee.setLocation((String) r[5]);
            employee.setLocationDesc((String) r[6]);
            employee.setLastName((String) r[7]);
            employee.setFirstName((String) r[8]);
            employee.setMiddleName((String) r[9]);
            employee.setEmplStatus((String) r[10]);
            employee.setEmplStatusDescr((String) r[11]);
            employee.setRegOrTemp((String) r[12]);
            employee.setFullOrPartTime((String) r[13]);
            employee.setEmployeeType((String) r[14]);
            employee.setEmployeeClass((String) r[15]);
            employee.setHireDt((Date) r[16]);
            employee.setTerminationDt((Date) r[17]);
            employee.setLastWorkDt((Date) r[18]);
            employee.setUsWorkEligibilty((String) r[19]);

            payrollEmployees.add(employee);
        }

        return payrollEmployees;
    }

    @Override
    public List<Department> getCompanyDepartments(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_COMPANY_DEPARTMENTS.getKey());

        query.setParameter("company", payGroup.getCompany());
        query.setParameter("payEndDate", payGroup.getPayEndDate());

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.list();
        List<Department> departments = new ArrayList<Department>();
        for (Object[] values : results) {
            Department dept = new Department();
            dept.setDeptId((String) values[0]);
            dept.setDeptDesc((String) values[1]);
            dept.setDeptLongDesc((String) values[2]);
            dept.setLocation((String) values[3]);
            departments.add(dept);
        }
        return departments;
    }

    @Override
    public List<EmployeeDepartmentSplit> getEmployeeDepartments(PayGroup payGroup, String emplId) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_EMPLOYEE_DEPARTMENTS.getKey());

        query.setParameter("company", payGroup.getCompany());
        query.setParameter("payGroup", payGroup.getPayGroup());
         @SuppressWarnings("unchecked")
        List<Object[]> results = query.list();
        List<EmployeeDepartmentSplit> deptSplitList = new ArrayList<EmployeeDepartmentSplit>();

        for (Object[] values : results) {
            Boolean offCycle = Boolean.TRUE;
            if (((String) values[2]).equalsIgnoreCase("N")) {
                offCycle = Boolean.FALSE;
            }
            final String companyIdFromDb = (String) values[0];
            final String payGrp = (String) values[1];
            final String deptId = (String) values[3];
            final String emplIdFromDb = (String) values[4];
            final double splitPercentage = ((BigDecimal) values[5]).doubleValue();
            final String earnCode = (String) values[6];

            deptSplitList.add(new EmployeeDepartmentSplit(companyIdFromDb, payGrp, offCycle, deptId, emplIdFromDb, splitPercentage, earnCode));
        }

        return deptSplitList;
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<PayrollEntryValidator> getPayrollEntryValidators(PayGroup payGroup) {

        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(QueryEnum.GET_PAYROLL_ENTRY_VALIDATORS.getKey());
        bindQueryWithPayGroup(query, payGroup);
        query.setParameter("payFrequency", payGroup.getPayFrequency());

        List<Object[]> queryResult = query.list();
        List<PayrollEntryValidator> validators = new ArrayList<PayrollEntryValidator>();

        if (queryResult != null && queryResult.size() > 0) {
            for (Object[] result : queryResult) {
                PayrollEntryValidator validator = new PayrollEntryValidator((Character) result[0], (String) result[1], (String) result[2], (String) result[3],
                        (BigDecimal) result[4], (BigDecimal) result[5], (String) result[6], (String) result[7]);
                validators.add(validator);
            }
        }

        return validators;
    }

    @Override
    public List<EmployeeLeaveBalance> getPayrollEmployeesLeaveAccrual(PayGroup payGroup) {

        return spEmployeesLeaveAccrual.execute(payGroup);
    }

    
    @Override
    public List<PayrollEarningCode> getPayrollEarningCodes(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("getPayrollEarningCodes");
        bindQueryWithPayGroup(query, payGroup);

        @SuppressWarnings("unchecked")
        List<PayrollEarningCode> results = (List<PayrollEarningCode>) query.list();
        return results;
    }

    @Override
    public void insertPayrollEarningCodes(PayGroup payGroup, List<PayrollEarningCode> payrollEaningCodes) {
        // TODO Auto-generated method stub
        deletePayrollEarningCodes(payGroup);

        peoplesoft_sessionFactory.getCurrentSession().flush();
        for (PayrollEarningCode payrollEarningCode : payrollEaningCodes) {
            peoplesoft_sessionFactory.getCurrentSession().persist(payrollEarningCode);
        }
    }

    private int deletePayrollEarningCodes(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.DELETE_PAYROLL_EARNING_CODES.getKey());
        bindQueryWithPayGroup(query, payGroup);

        return query.executeUpdate();
    }

    @Override
    public boolean checkifPayrollEntryByEmployee(Long id, String employeeId) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.PAYROLL_ENTRY_BY_EMPLOYEE.getKey());

        query.setParameter("id", id);
        query.setParameter("employeeId", employeeId);
        @SuppressWarnings("rawtypes")
		List results = query.list();
        if (results != null && results.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public PayrollDeadline getPayrollBlackoutTime(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_BLACKOUT_TIME.getKey());

        bindQueryWithPayGroup(query, payGroup);

        @SuppressWarnings("unchecked")
        List<Object[]> results = (List<Object[]>) query.list();
        PayrollDeadline payrollDeadline = new PayrollDeadline();
        for (Object[] result : results) {

            payrollDeadline.setCompany((String) result[0]);
            payrollDeadline.setWebDeadline((String) result[1]);
            payrollDeadline.setOffCycleWebDeadline((String) result[2]);
            payrollDeadline.setPreviewHours((String) result[3]);
            payrollDeadline.setPreviewMinutes((String) result[4]);
            payrollDeadline.setTimezone((String) result[5]);
            payrollDeadline.setTimeZoneFullText((String) result[6]);
            payrollDeadline.setTimeApproval((String) result[7]);
        }

        return payrollDeadline;
    }

    @Override
    public List<EmployeeDOM> getTerminatedEmployees(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_TERMINATED_EMPLOYEES.getKey());

        bindQueryWithPayGroup(query, payGroup);

        List<EmployeeDOM> payrollEmployees = new ArrayList<EmployeeDOM>();
        @SuppressWarnings("unchecked")
        List<Object[]> queryResults = query.list();

        for (Object[] r : queryResults) {
            EmployeeDOM employee = new EmployeeDOM();
            employee.setEmployeeId((String) r[0]);
            employee.setEmployeeRecord((BigDecimal) r[1]);
            employee.setDepartmentId((String) r[2]);
            employee.setDepartmentDesc((String) r[3]);
            employee.setJobCode((String) r[4]);
            employee.setLocation((String) r[5]);
            employee.setLocationDesc((String) r[6]);
            employee.setLastName((String) r[7]);
            employee.setFirstName((String) r[8]);
            employee.setMiddleName((String) r[9]);
            employee.setEmplStatus((String) r[10]);
            employee.setEmplStatusDescr((String) r[11]);
            employee.setRegOrTemp((String) r[12]);
            employee.setFullOrPartTime((String) r[13]);
            employee.setEmployeeType((String) r[14]);
            employee.setEmployeeClass((String) r[15]);
            employee.setHireDt((Date) r[16]);
            employee.setTerminationDt((Date) r[17]);
            employee.setLastWorkDt((Date) r[18]);
            employee.setUsWorkEligibilty((String) r[19]);

            payrollEmployees.add(employee);
        }

        return payrollEmployees;
    }

	@Override
	public List<String> getReportedEarnCodesForPayroll(PayGroup payGroup) {
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_REPORTED_EARNCODES.getKey());
		bindQueryWithPayGroup(query, payGroup);
		@SuppressWarnings("unchecked")
	    List<String> queryResults = query.list();

		return queryResults;
	}

	@Override
	@Transactional
	public void deleteReportedEarnCodeRecords(String deletedEarnCodes,
			PayGroup payGroup) {
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.DELETE_REPORTED_EARNCODERECORDS.getKey());
		bindQueryWithPayGroup(query, payGroup);
		query.setParameter("earnCodes", deletedEarnCodes);
		query.executeUpdate();		
	}

	@Override
	public List<EmployeeDOM> getOnCycleEmployeesWithDeptLoc(PayGroup payGroup) {
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_ONCYCLE_EMPLOYEES_BY_DEPT_LOC.getKey());
        bindQueryWithPayGroup(query, payGroup);
        
        //NOTE : possible hit on performace, but its a trade off as this query will be used seldomly and or getting the approval information for a company will be the issue.
        
        if(StringUtils.isEmpty(payGroup.getLocation()) || " ".equals(payGroup.getLocation())){
        	query.setParameter("location", "%");
        }else{
        	query.setParameter("location", payGroup.getLocation());
        }
        	
        if(StringUtils.isEmpty(payGroup.getDepartmentId()) || " ".equals(payGroup.getDepartmentId())){
        	query.setParameter("deptId", "%");
        }else{
        	query.setParameter("deptId", payGroup.getDepartmentId());
        }
        
        List<EmployeeDOM> payrollEmployees = new ArrayList<EmployeeDOM>();
        @SuppressWarnings("unchecked")
        List<Object[]> queryResults = query.list();

        for (Object[] r : queryResults) {
            EmployeeDOM employee = new EmployeeDOM();
            employee.setEmployeeId((String) r[0]);
            employee.setEmployeeRecord((BigDecimal) r[1]);
            employee.setDepartmentId((String) r[2]);
            employee.setDepartmentDesc((String) r[3]);
            employee.setJobCode((String) r[4]);
            employee.setLocation((String) r[5]);
            employee.setLocationDesc((String) r[6]);
            employee.setLastName((String) r[7]);
            employee.setFirstName((String) r[8]);
            employee.setMiddleName((String) r[9]);
            employee.setEmplStatus((String) r[10]);
            employee.setEmplStatusDescr((String) r[11]);
            employee.setRegOrTemp((String) r[12]);
            employee.setFullOrPartTime((String) r[13]);
            employee.setEmployeeType((String) r[14]);
            employee.setEmployeeClass((String) r[15]);
            employee.setHireDt((Date) r[16]);
            employee.setTerminationDt((Date) r[17]);
            employee.setLastWorkDt((Date) r[18]);
            employee.setUsWorkEligibilty((String) r[19]);

            payrollEmployees.add(employee);
        }

        return payrollEmployees;
	}

	@Override
	public boolean checkPayrollBlackout(PayGroup payGroup) {
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.checkPayrollBlackout");

		bindQueryWithPayGroupForPreview(query, payGroup);
		
		BigDecimal count = (BigDecimal)query.uniqueResult();
        
        return count.intValue() > 0 ? true : false;
	}

	@Override
	public List<PayrollStagingRecordKey> getUniqueEmployeeForPaygroup(
			PayGroup payGroup) {
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_UNIQUE_EMPLOYEE_FOR_PAYGROUP.getKey());
        bindQueryWithPayGroup(query, payGroup);
		 
        @SuppressWarnings("unchecked")
        List<Object[]> results = (List<Object[]>) query.list();
        List<PayrollStagingRecordKey> paygroupEmployees = new ArrayList<PayrollStagingRecordKey>();
        for (Object[] result : results) {
        	PayrollStagingRecordKey paygroupEmpl = new PayrollStagingRecordKey();
        	paygroupEmpl.setEmplId((String) result[0]);
        	paygroupEmpl.setCompany((String) result[1]);
        	paygroupEmpl.setPayrollGroup((String) result[2]);
        	paygroupEmpl.setPayEndDate((Date) result[3]);
        	paygroupEmpl.setOffCycle((String) result[4]);
        	paygroupEmpl.setPayrollNum(((BigDecimal) result[5]).longValue());
        	
        	paygroupEmployees.add(paygroupEmpl);
        }
		return paygroupEmployees;
	}
}
