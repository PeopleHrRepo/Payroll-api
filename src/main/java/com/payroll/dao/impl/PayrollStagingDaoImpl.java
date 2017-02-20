package com.payroll.dao.impl;

import static com.payroll.utils.DaoUtil.bindQueryWithPayGroup;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.payroll.dao.PayrollStagingDao;
import com.payroll.entity.PayrollStagingRecord;
import com.ptg.payroll.db.sp.payroll.PayrollStagingPublishSP;
import com.ptg.payroll.domain.payroll.PayGroup;


public class PayrollStagingDaoImpl implements PayrollStagingDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(PayrollEntryDaoImpl.class);

	private SessionFactory  peoplesoft_sessionFactory;
	
	@Autowired
    private PayrollStagingPublishSP payrollStagingPublishSP;
	
	public SessionFactory getPeoplesoft_sessionFactory() {
		return peoplesoft_sessionFactory;
	}

	public void setPeoplesoft_sessionFactory(SessionFactory peoplesoft_sessionFactory) {
		this.peoplesoft_sessionFactory = peoplesoft_sessionFactory;
	}
	
	public PayrollStagingPublishSP getPayrollStagingPublishSP() {
		return payrollStagingPublishSP;
	}

	public void setPayrollStagingPublishSP(PayrollStagingPublishSP payrollStagingPublishSP) {
		this.payrollStagingPublishSP = payrollStagingPublishSP;
	}

	@Override
    public void insertStagingRecords(List<PayrollStagingRecord> records, PayGroup paygroup, String source) {
		LOGGER.debug("insertStagingRecords start");
    	this.deleteStagingRecords(paygroup, source);
    	
    	int i = 0;
        for (PayrollStagingRecord record : records) {
        	peoplesoft_sessionFactory.getCurrentSession().persist(record);
            if (++i % 20 == 0) {
                this.peoplesoft_sessionFactory.getCurrentSession().flush();
                this.peoplesoft_sessionFactory.getCurrentSession().clear();
            }
        }
        LOGGER.debug("insertStagingRecords end");
    }

	 @Override
	    public void publishStagingRecords(PayGroup payGroup, long profileId, String source, String userEmployeeId) {
	        payrollStagingPublishSP.publishRawPayrollEntryRecords(payGroup, profileId, source, userEmployeeId);
	    }

	    @Override
	    public int deleteStagingRecords(PayGroup payGroup, long profileId, String source) {
	    	 LOGGER.debug("deleteStagingRecords start");
	    	Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.dataimport.deleteStagingRecords");
	        bindQueryWithPayGroup(query, payGroup);
	        query.setParameter("profileId", profileId);
	        query.setParameter("source", source);

	        int count = query.executeUpdate();
	        LOGGER.debug("deleteStagingRecords end");
	        return count;
	    }
	    

	    @Override
	    public int deleteStagingRecords(PayGroup payGroup, String source) {
	    	LOGGER.debug("deleteStagingRecords start");
	        Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.dataimport.deleteStagingRecordsByPaygroup");
	        bindQueryWithPayGroup(query, payGroup);
	        query.setParameter("source", source);

	        int count = query.executeUpdate();
	        LOGGER.debug("deleteStagingRecords end");
	        return count;
	    }

}
