package com.payroll.dao.impl;

import static com.payroll.utils.DaoUtil.bindQueryWithPayGroup;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.payroll.constant.PtgGatewayConstants;
import com.payroll.dao.PayrollProcessDao;
import com.payroll.entity.PaygroupReviewer;
import com.payroll.entity.PayrollPay;
import com.payroll.entity.ps.NamedQueries.PayrollQueryEnum;
import com.ptg.payroll.db.sp.payroll.DeleteFromRsn1TableSP;
import com.ptg.payroll.db.sp.payroll.PayrollReInitializeSP;
import com.ptg.payroll.db.sp.payroll.VerifyPayrollReInitializeSP;
import com.ptg.payroll.domain.PayrollGroup;
import com.ptg.payroll.domain.payroll.PayGroup;


public class PayrollProcessDaoImpl implements PayrollProcessDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayrollProcessDaoImpl.class);

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

    @Autowired
    private PayrollReInitializeSP spPayrollReInitialize;

    @Autowired
    private DeleteFromRsn1TableSP spDeleteFromRsn1Table;

    @Autowired
    private VerifyPayrollReInitializeSP spVerifyPayrollReInitialize;
    
    
    public void setSpPayrollReInitialize(PayrollReInitializeSP spPayrollReInitialize) {
		this.spPayrollReInitialize = spPayrollReInitialize;
	}

	public void setSpDeleteFromRsn1Table(DeleteFromRsn1TableSP spDeleteFromRsn1Table) {
		this.spDeleteFromRsn1Table = spDeleteFromRsn1Table;
	}

	public void setSpVerifyPayrollReInitialize(VerifyPayrollReInitializeSP spVerifyPayrollReInitialize) {
		this.spVerifyPayrollReInitialize = spVerifyPayrollReInitialize;
	}

	@Override
    public boolean checkForMidPayEvent(PayGroup payGroup) {
    	return spVerifyPayrollReInitialize.verifyPayrollReInitialize(payGroup);
    }
    
    @Override
    public boolean checkPayrollEngineStatus() {
    	LOGGER.info("checkPayrollEngineStatus - begin");
    	Query query = peoplesoft_sessionFactory.getCurrentSession().createQuery("SELECT T2_STRT_STP_ENGINE FROM PS_T2_ENG_SETTINGS");

    	String results = (String)query.uniqueResult();
    	LOGGER.info("checkPayrollEngineStatus - end");
        return results != null && results.equals("Y");
    }

    @Override
    public boolean retroPayCheck(PayGroup payGroup) {
    	LOGGER.info("retroPayCheck - start");
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.retroPayCheck");
        query.setParameter("company", payGroup.getCompany());
        query.setParameter("payGroup", payGroup.getPayGroup());
        query.setParameter("payEndDate", payGroup.getPayEndDate());

        @SuppressWarnings("unchecked")
        List<BigDecimal> queryResults = query.list();
        BigDecimal count = queryResults.get(0);
        LOGGER.info("retroPayCheck - end");
        return count.intValue() > 0;
    }
    
    @Override
    public void reInitializePayroll(PayGroup payGroup, String userEmployeeId) {
        spPayrollReInitialize.reInitializePayroll(payGroup, userEmployeeId);
        spDeleteFromRsn1Table.deleteFromRsn1Table(payGroup, userEmployeeId);
    }

    @Override
    public String getPayrollEngineNextStep(PayGroup payGroup) {
    	LOGGER.debug("getPayrollEngineNextStep - start");
        String nextStep = null;
        String canSubmitAPI = null;
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_PAYROLL_ENGINE_NEXT_STEP.getKey());
        query.setParameter("company", payGroup.getCompany());
        query.setParameter("status", payGroup.getStatus());
        query.setParameter("runType", "Y".equals(payGroup.getOffCycle()) ? "OFF" : "ON");
        //bindQueryWithPayGroup(query, payGroup);

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.list();

        if (results != null && results.size() > 0) {
            nextStep = (String) results.get(0)[0];
            canSubmitAPI = (String) results.get(0)[1];
            
            //Make next step to null if API is not allowed to submit to engine.
            if ("N".equals(canSubmitAPI)) {
            	nextStep = null;
            }
        }
        LOGGER.debug("getPayrollEngineNextStep - end");
        return nextStep;
    }

    @Override
    @Transactional
    public void updatePayrollStatus(PayGroup payGroup, String operatorId, String status) {
        
    	LOGGER.debug("updatePayrollStatus - start");
    	Query query;
    	
    	if(PtgGatewayConstants.PE_PROCESS_PRELIMINARY_APPROVAL.equals(status) && PtgGatewayConstants.CHILD_PAYGROUP.equals(payGroup.getIsParent())){
    		query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.data.payroll.UpdateStatusSubProcess");
    		bindQueryWithPayGroup(query, payGroup);
    		query.setParameter("id", payGroup.getId());
    		query.setParameter("operatorId", operatorId);
            query.setParameter("status", status);
            query.executeUpdate();
            
    		
    	}else{
    		//TODO refine this on monday
    		query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.data.payroll.UpdateStatus");
    		bindQueryWithPayGroup(query, payGroup);
            query.setParameter("operatorId", operatorId);
            query.setParameter("status", status);
            query.executeUpdate();
            
            
    		//payrollEntryDao.updatePayrollProcessSubStatus(payGroup.getCompany(), payGroup.getPayGroup(), payGroup.getPayEndDate(),  payGroup.getPayrollNumber(), operatorId);
    		Query query1 = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.UPDATE_STATUS_ALL_SUB_FROM_PROCESS.getKey());
    		query1.setParameter("company", payGroup.getCompany());
    		query1.setParameter("payGroup", payGroup.getPayGroup());
    		query1.setParameter("payEndDate", payGroup.getPayEndDate());
    		query1.setParameter("payrollNumber", payGroup.getPayrollNumber());
    		query1.setParameter("operatorId", operatorId);
    		query1.setParameter("offCycle", "N");
    		query1.executeUpdate();
    	}
    	
    	LOGGER.debug("updatePayrollStatus - end");
    }

    @Override
    @Transactional
    public void updatePayrollDataEntryOption(PayGroup payGroup, String dataEntryOption, String operatorId) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.data.payroll.UpdateDataEntryOption");
        bindQueryWithPayGroup(query, payGroup);
        query.setParameter("dataEntryOption", dataEntryOption);
        query.setParameter("operatorId", operatorId);

        query.executeUpdate();
    }

    @Override
    @Transactional
    public void createPayrollPay(PayGroup payGroup, List<PayrollPay> payrollPays) {

        this.deletePayrollPay(payGroup);

        int i = 0;
        for (PayrollPay payrollPay : payrollPays) {
        	peoplesoft_sessionFactory.getCurrentSession().persist(payrollPay);
            if (++i % 20 == 0) {
            	peoplesoft_sessionFactory.getCurrentSession().flush();
                peoplesoft_sessionFactory.getCurrentSession().clear();
            }
        }
    }

    private void deletePayrollPay(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.data.payroll.DeletePayRecords");
        bindQueryWithPayGroup(query, payGroup);

        query.executeUpdate();
    }

    @Override
    public PayrollGroup getPayrollEngineStatus(PayGroup payGroup) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.preview.getPayrollEngineStatus");
        bindQueryWithPayGroup(query, payGroup);

        PayrollGroup payrollGroup = new PayrollGroup();

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.list();
        if (results.size() == 0) {
            return null;
        }
        Object[] r = results.get(0);

        payrollGroup.setCompany((String) r[0]);
        payrollGroup.setPayGroup((String) r[1]);
        payrollGroup.setPayEndDate((Date) (r[2]));
        payrollGroup.setOffCycle((String) r[3]);
        BigDecimal payrollNumber = (BigDecimal) r[4];
        payrollGroup.setPayrollNumber(payrollNumber.intValue());
        payrollGroup.setLocation((String) r[5]);
        payrollGroup.setDepartmentId((String) r[6]);
        payrollGroup.setPayBeginDate((Date) r[7]);
        payrollGroup.setCheckDate((Date) r[8]);
        payrollGroup.setPayGroupDescr((String) r[9]);
        payrollGroup.setPayFrequency((String) r[10]);
        payrollGroup.setPayrollStatus((String) r[11]);
        payrollGroup.setReportDate((Date) r[12]);
        payrollGroup.setBlackoutFlag((String) r[13]);
        payrollGroup.setPayrollName((String) r[14]);
        payrollGroup.setBrandName((String) r[15]);
        payrollGroup.setDataEntryOption((String) r[16]);
        payrollGroup.setOperatorId((String) r[17]);
        payrollGroup.setOperatorName((String) r[18]);
        payrollGroup.setLastModifiedDate((Date) r[19]);
        payrollGroup.setPaymentMethod((String) r[20]);
        payrollGroup.setId((BigDecimal) r[21]);
        payrollGroup.setCurrentEngineStep((String) r[22]);
        int val = ((BigDecimal) r[23]).intValue();
        payrollGroup.setHasPayrollError(val == 1);
        payrollGroup.setErrorMessage((String) r[24]);

        return payrollGroup;
    }

    @Override
    public List<PaygroupReviewer> getPaygroupReviewers(PayGroup payGroup) {
        Query query = hrp_sessionFactory.getCurrentSession().getNamedQuery("getPaygroupReviewer");
        query.setParameter("company", payGroup.getCompany());
        query.setParameter("payGroup", payGroup.getPayGroup());

        @SuppressWarnings("unchecked")
        List<PaygroupReviewer> payrollEntryEmployees = (List<PaygroupReviewer>) query.list();

        return payrollEntryEmployees;

    }

    @Override
    @Transactional
    public void updateOperatorId(PayGroup payGroup, String operatorId) {
        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.UPDATE_DATE_OPRID_PE_PROCESS.getKey());
        query.setParameter("operatorId", operatorId);
        query.setParameter("id", payGroup.getId());
        query.executeUpdate();
    }

	@Override
	public boolean verifySpecialPayrollCreatedUsr(PayGroup payGroup,
			String operatorId) {
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_SPECIAL_PAYROLL_CREATED_USR.getKey());
        query.setParameter("operatorId", operatorId);
        query.setParameter("id", payGroup.getId());
		
        @SuppressWarnings("rawtypes")
		List results = query.list();
        
        if(results !=null && results.size() > 0 ){
        	return true;
        }
        
        return false;
	}

	@Override
	public boolean verifySpecialPayrollUpdatedUsrs(PayGroup payGroup,
			String operatorId) {
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_SPECIAL_PAYROLL_UPDATED_USRS.getKey());
        query.setParameter("operatorId", operatorId);
        query.setParameter("id", payGroup.getId());
		
        @SuppressWarnings("rawtypes")
		List results = query.list();
        
        if(results !=null && results.size() > 0 ){
        	return true;
        }
        
        return false;
		
	}

	@Override
	public boolean checkPayrollFLSA(PayGroup payGroup) {
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.preview.flsa.count");
		bindQueryWithPayGroup(query, payGroup);
        
        BigDecimal count = (BigDecimal) query.uniqueResult();
        return count.intValue() > 0;
	}
	
	@Override
	public boolean checkPayrollTip(PayGroup payGroup) {
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.preview.tip.count");
		bindQueryWithPayGroup(query, payGroup);
        
		BigDecimal count = (BigDecimal) query.list();
        return count.intValue() > 0;
	}

}