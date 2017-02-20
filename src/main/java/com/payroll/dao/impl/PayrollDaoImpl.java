package com.payroll.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.payroll.constant.PtgGatewayConstants;
import com.payroll.dao.PayrollDao;
import com.payroll.entity.EarnCode;
import com.payroll.entity.PayrollHolidanBean;
import com.payroll.entity.TimesheetPayrollGroup;
import com.payroll.entity.ps.NamedQueries.PayrollQueryEnum;
import com.payroll.entity.ps.NamedQueries.QueryEnum;
import com.ptg.payroll.domain.PayrollGroup;
import com.ptg.payroll.domain.PayrollUserCompany;
import com.ptg.payroll.domain.SortApprovalConfiguration;
import com.ptg.payroll.domain.payroll.PayGroup;
import com.ptg.payroll.entity.ps.pe.dashboard.PayrollDeadline;
import com.ptg.payroll.model.UserPermission;

public class PayrollDaoImpl  implements PayrollDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayrollDaoImpl.class);
	private SessionFactory  peoplesoft_sessionFactory;
	private SessionFactory  hrp_sessionFactory;
		
	public SessionFactory getHrp_sessionFactory() {
		return hrp_sessionFactory;
	}

	public void setHrp_sessionFactory(SessionFactory hrp_sessionFactory) {
		this.hrp_sessionFactory = hrp_sessionFactory;
	}

	public SessionFactory getPeoplesoft_sessionFactory() {
		return peoplesoft_sessionFactory;
	}

	public void setPeoplesoft_sessionFactory(SessionFactory peoplesoft_sessionFactory) {
		this.peoplesoft_sessionFactory = peoplesoft_sessionFactory;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EarnCode> getEarningCodes(String company) {
		//Query query = getHibernateTemplate().createNamedQuery("SQL.payroll.findEarningCodes");
		Query query=peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.findEarningCodes");
        query.setParameter("company", company);
        return query.list();
	}

	@Override
	public List<EarnCode> getEarningCodesByCompany(String company) {
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.findEarningCodeByCompany");
		query.setParameter("company", company);
		@SuppressWarnings("unchecked")
		List<EarnCode> earningCodeList = (List<EarnCode>)query.list();
		return earningCodeList;
	}
	
	@Override
	public boolean getLeaveAccrualSystemClientStatus(String company) {
		Session hrp_session=hrp_sessionFactory.openSession();
		
		Query query = hrp_session.getNamedQuery(QueryEnum.GET_CLIENT_LEAVE_ACCRUAL_STATUS
				.getKey());
		query.setParameter("company", company);
		
		@SuppressWarnings("unchecked")
		List<Object> results = (List<Object>) query.list();
		if(results != null && results.size() > 0){
			String result = (String)results.get(0);
			if("Y".equals(result)){
				return true;
			}
		}
		hrp_session.close();
		return false;
	}

	@Override
    public PayGroup findPayrollInstance(Long id) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.process.findProcessByID");
        query.setParameter("seqNumber", id);

        Object[] result = (Object[]) query.uniqueResult();

        PayGroup payGroup = new PayGroup();
        // SEQ_NBR, COMPANY, PAYGROUP, PAY_BEGIN_DT, PAY_END_DT, OFF_CYCLE, T2_HRP_PAYROLL_NUM, PAY_FREQUENCY,
        // T2_PE_STATUS, T2_PP_REPORT_DT
        payGroup.setId(((BigDecimal) result[0]).longValue());
        payGroup.setCompany((String) result[1]);
        payGroup.setPayGroup((String) result[2]);
        payGroup.setPayBeginDate((Date) result[3]);
        payGroup.setPayEndDate((Date) result[4]);
        payGroup.setOffCycle((String) result[5]);
        payGroup.setPayrollNumber(((BigDecimal) result[6]).intValue());
        payGroup.setPayFrequency((String) result[7]);
        payGroup.setStatus((String) result[8]);
        payGroup.setReportDate((Date) result[9]);
        payGroup.setPreviewPayrollNumber(((BigDecimal) result[10]).intValue());
        payGroup.setCheckDate((Date) result[11]);
        payGroup.setIsParent(((Character) result[12]).toString());
        payGroup.setLocation((String) result[13]);
        payGroup.setDepartmentId((String) result[14]);
        payGroup.setCertifiedPayroll((String) result[15]);
        payGroup.setJobCosting((String) result[16]);

        return payGroup;
    }
	 @SuppressWarnings("unchecked")
	    @Override
	    public List<EarnCode> getEarningCodesByPayGroup(String company, String payGroup) {
		 Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.findEarningCodesByPayGroup");
	        query.setParameter("company", company);
	        query.setParameter("payGroup", payGroup);
	        
	        return query.list();
	    }

	 @Override
	    public String getUserRole(String company, String personId) {
	        String userRole = "";
	        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.security.getTopUserRole");

	        query.setParameter("company", company);
	        query.setParameter("personId", personId);

	        @SuppressWarnings("unchecked")
	        List<Object> results = (List<Object>) query.list();
	        if (results != null && results.size() > 0) {
	            userRole = (String) results.get(0);
	        }

	        return userRole;
	    }

	 @Override
	    public List<UserPermission> getUserPermissions(String company, String userRole) {
	        List<UserPermission> userPermissions = new ArrayList<UserPermission>();
	        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.security.getUserPermissions");

	        query.setParameter("company", company);
	        query.setParameter("userRole", userRole);

	        @SuppressWarnings("unchecked")
	        List<Object[]> results = (List<Object[]>) query.list();
	        for (Object[] r : results) {
	            UserPermission permission = new UserPermission();
	            permission.setRole((String) r[0]);
	            permission.setBrand((String) r[1]);
	            permission.setOrder((BigDecimal) r[2]);
	            permission.setComponent((String) r[3]);
	            permission.setView((String) r[4]);
	            permission.setEdit((String) r[5]);

	            userPermissions.add(permission);
	        }

	        return userPermissions;
	    }

	 @Override
	 public String getUserDeptLocationAccess(String company, String personId, String userRole) {
	  Session session = peoplesoft_sessionFactory.openSession();
	        Query query = session.getNamedQuery(PayrollQueryEnum.GET_USER_DEPT_LOCATION_ACCESS.getKey());

	        query.setParameter("company", company);
	        query.setParameter("personId", personId);
	        query.setParameter("userRole", userRole);

	        @SuppressWarnings("unchecked")
	        List<Object[]> results = (List<Object[]>) query.list();
	        if (results.size() == 0) {
	            return "R";
	        }
	        Object[] r = results.get(0);
	        String department = (String) r[0];
	        String location = (String) r[1];
	        if (PtgGatewayConstants.DEPT_LOCATION_ALL_ACCESSCODE
	                .equalsIgnoreCase(department)
	                && PtgGatewayConstants.DEPT_LOCATION_ALL_ACCESSCODE
	                        .equalsIgnoreCase(location)) {
	            return "ALL";
	        }
	        return "R";
	    }
	 @Override
	 public List<PayrollUserCompany> getUserCompanies(String  personId) {
	  //Session session = peoplesoft_sessionFactory.openSession();
	  Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(QueryEnum.GET_COMPANIES_BY_ROLE.getKey());
	  query.setParameter("personId", personId);
	  @SuppressWarnings("unchecked")
	  List<Object[]> userCompanies = query.list(); 
	  List<PayrollUserCompany> companyList = new ArrayList<PayrollUserCompany>();
	  
	  for (Object[] userCompany : userCompanies){
	   PayrollUserCompany company = new PayrollUserCompany();
	   company.setOrgId((String)userCompany[0]);
	   company.setOrgDesc((String)userCompany[1]);
	   if(userCompany.equals((String)userCompany[0])){
	    company.setIsCurrentCompany(true);
	   }else{
	    company.setIsCurrentCompany(false);
	   }
	   company.setIsTriNetLeaveSystemCompany((String)userCompany[2]);
	   company.setCountry((String)userCompany[3]);
	   company.setHasHistoricData(""+userCompany[4]);
	   company.setIsSortApprovalConfigured(""+userCompany[5]);
	   company.setBrand((String)userCompany[6]);
	   company.setVertical((String)userCompany[7]);
	   companyList.add(company);
	  }

	  return companyList;
	 }

	 @Override
	 public PayrollDeadline getPayrollDeadline(String company) {
	  
	  //Session session = peoplesoft_sessionFactory.openSession();  
	  Query query =  peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_DEADLINE_TIME.getKey());
	  
	  query.setParameter("company", company);

	  @SuppressWarnings("unchecked")
	  List<Object[]> results = (List<Object[]>) query.list();
	  PayrollDeadline payrollDeadline = new PayrollDeadline();
	  for (Object[] result : results){
	   payrollDeadline.setWebDeadline((String)result[0]);
	   payrollDeadline.setTimezone((String)result[1]);
	   payrollDeadline.setTimeZoneFullText((String)result[2]);
	  }

	  return payrollDeadline;
	 }
	 
	 @Override
		public SortApprovalConfiguration getCompanyEffectiveCurrentSortApprovalOrder(
				String company) {
		 Session session = peoplesoft_sessionFactory.openSession();  
		  Query query = session.getNamedQuery(QueryEnum.GET_COMPANY_EFFECSORTAPPROVALCONFG.getKey());

		        query.setParameter("company", company);

		        @SuppressWarnings("unchecked")
		        List<Object[]> results = (List<Object[]>) query.list();

		        SortApprovalConfiguration currentSortApprovalOrder = null;

		        if (null != results) {
		            Object[] result = results.get(0);
		            currentSortApprovalOrder = new SortApprovalConfiguration();
		            currentSortApprovalOrder.setCurrentSortOrder((String) result[0]);
		            currentSortApprovalOrder.setCurrentSortOrderDesc((String) result[1]);
		            if (null != result[2]) {
		                currentSortApprovalOrder.setCurrentApprovalOrder((String) result[2]);
		            }
		            if (null != result[3]) {
		                currentSortApprovalOrder.setCurrentApprovalOrderDesc((String) result[3]);
		            }

		        }
		        return currentSortApprovalOrder;
		}

	 @Override
		public PayrollGroup getPayrollGroup(PayGroup payGroup) {
			Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(
								PayrollQueryEnum.GET_PAYROLL_GET_PAYROLL_GROUP.getKey());
			
			query.setParameter("company", payGroup.getCompany());
			query.setParameter("payGroup", payGroup.getPayGroup());
			query.setParameter("payEndDate", payGroup.getPayEndDate());
			query.setParameter("offCycle", payGroup.getOffCycle());
			query.setParameter("payrollNumber", payGroup.getPayrollNumber());
			
			PayrollGroup payrollGroup = new PayrollGroup();
			
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.list();
			if(results.size() == 0){
				return null;
			}
			Object[] r = results.get(0);
				
			payrollGroup.setCompany((String)r[0]);
			payrollGroup.setPayGroup((String)r[1]);
			payrollGroup.setPayEndDate((Date)(r[2]));
			payrollGroup.setOffCycle((String)r[3]);
			BigDecimal payrollNumber = (BigDecimal)r[4];
			payrollGroup.setPayrollNumber(payrollNumber.intValue());
			payrollGroup.setLocation((String)r[5]);
			payrollGroup.setDepartmentId((String)r[6]);
			payrollGroup.setPayBeginDate((Date)r[7]);
			payrollGroup.setCheckDate((Date)r[8]);
			payrollGroup.setPayGroupDescr((String)r[9]);
			payrollGroup.setPayFrequency((String)r[10]);
			payrollGroup.setPayrollStatus((String)r[11]);
			payrollGroup.setReportDate((Date)r[12]);
			payrollGroup.setBlackoutFlag((String)r[13]);
			payrollGroup.setPayrollName((String)r[14]);
			payrollGroup.setBrandName((String)r[15]);
			payrollGroup.setDataEntryOption((String)r[16]);
			payrollGroup.setOperatorId((String)r[17]);
			payrollGroup.setOperatorName((String)r[18]);
			payrollGroup.setLastModifiedDate((Date)r[19]);
			payrollGroup.setPaymentMethod((String)r[20]);
			payrollGroup.setId((BigDecimal)r[21]);
			payrollGroup.setDeliveryOption((String)r[22]);
			payrollGroup.setDeliveryType((String)r[23]);   
			payrollGroup.setAddressOption((String)r[24]);
			payrollGroup.setAddress1((String)r[25]);
			payrollGroup.setAddress2((String)r[26]);
			payrollGroup.setCity((String)r[27]);
			payrollGroup.setState((String)r[28]);
			payrollGroup.setCountry((String)r[29]);
			payrollGroup.setPostalCode((String)r[30]);
			payrollGroup.setDeliveryInstruction((String)r[31]);
			payrollGroup.setAttention((String)r[32]); 
			return payrollGroup;
		}

		@Override
		public String getCompanyAddress(String companyId) {
			Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(
					PayrollQueryEnum.GET_PAYROLL_COMPANY_ADDRESS.getKey());

			query.setParameter("company",companyId);
			String address = (String) query.uniqueResult();
			return address;
		}

		@Override
		public List<PayrollHolidanBean> getHolidays(String company) {
			
			Query query =peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("getHolidays");
			query.setParameter("company", company);
			@SuppressWarnings("unchecked")
			List<PayrollHolidanBean> holidays = (List<PayrollHolidanBean> )query.list();
			return holidays;
		}

		@Override
		public List<TimesheetPayrollGroup> getOnCyclePayrolls(String company) {
			LOGGER.info("updatePayGroupTimeEntryOption - START");
			
			Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_GET_ON_CYCLE_PAYROLLS.getKey());
			
			query.setParameter("company", company);

			List<TimesheetPayrollGroup> payrollGroups = new ArrayList<TimesheetPayrollGroup>();
			
			@SuppressWarnings("unchecked")
			List<Object[]> results = query.list();
			for(Object[] r : results){
				TimesheetPayrollGroup payroll = new TimesheetPayrollGroup();
				
				payroll.setCompany((String)r[0]);
				BigDecimal payrollNumber = (BigDecimal)r[1];
				payroll.setPayrollNumber(payrollNumber.intValue());
				payroll.setLocation((String)r[2]);
				payroll.setDeptId((String)r[3]);
				payroll.setPayGroup((String)r[4]);
				payroll.setPayGroupDesc((String)r[5]);
				payroll.setOffCycle((String)r[6]);
				payroll.setPayBeginDt((Date)r[7]);
				payroll.setPayEndDt((Date)r[8]);
				payroll.setCheckDt((Date)r[9]);
				payroll.setPayFrequency((String)r[10]);
				payroll.setPayrollName((String)r[11]);
				payroll.setStatus((String)r[12]);
				payroll.setReportDate((Date)r[13]);
				payroll.setOcReasonCd((String)r[14]);
				payroll.setPayrollEntryOption((String)r[15]);
				payroll.setOprid((String)r[16]);
				payroll.setOprName((String)r[17]);
				payroll.setTimeStamp((Date)r[18]);
				//certified payroll flag PAYR 7024
				payroll.setCertifiedPayroll((String)r[19]);
				payrollGroups.add(payroll);
			}
			
			return payrollGroups;
		}

	
}
