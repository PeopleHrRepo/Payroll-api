package com.payroll.dao.impl;

import java.math.BigDecimal;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.payroll.dao.PayrollEntryDao;
import com.payroll.entity.ps.ClientOption;
import com.payroll.entity.ps.NamedQueries.PayrollQueryEnum;
import com.payroll.entity.ps.NamedQueries.QueryEnum;

public class PayrollEntryDaoImpl implements PayrollEntryDao{
	private static final Logger LOGGER = LoggerFactory.getLogger(PayrollEntryDaoImpl.class);

	private SessionFactory  peoplesoft_sessionFactory;
	
	
	
	public SessionFactory getPeoplesoft_sessionFactory() {
		return peoplesoft_sessionFactory;
	}

	public void setPeoplesoft_sessionFactory(SessionFactory peoplesoft_sessionFactory) {
		this.peoplesoft_sessionFactory = peoplesoft_sessionFactory;
	}

	@Override
	public boolean is401KAmbroseClient(String company) {
		Query query=peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.payroll.data.payroll.Is401KAmbroseClient");
        query.setParameter("company", company);

        BigDecimal count = (BigDecimal) query.uniqueResult();
		return count != null && count.intValue() == 1;
	}

	@Override
	public ClientOption getClientOption(String company) {
		LOGGER.info("getClientOption - begin");
       
		Query query=peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(QueryEnum.GET_CLIENT_OPTION.getKey());
        query.setParameter("company", company);

        @SuppressWarnings("unchecked")
        List<Object[]> queryResult = query.list();
        ClientOption clientOption = new ClientOption();
        int i = 0;
        for(Object[] r : queryResult){
        	if(i == 0){
	            clientOption.setCompany(company);
	            clientOption.setT2ReviewCustomer((String) r[0]);
	            clientOption.setWebDeadline((String) r[1]);
                clientOption.setOffCycleWebDeadline((String) r[2]); //-- ?? dunno what order @TODO check value
	            clientOption.setTimeZone((String) r[3]);
	            clientOption.setTimeZoneStr((String) r[4]);
	            clientOption.setPeoId((String) r[7]);
        	}
        	if("C".equals((String) r[5])){
        		clientOption.setCheckReportLeadDate((BigDecimal) r[6]);
            } else if("A".equals((String) r[5])){
            	clientOption.setAdviceReportLeadDate((BigDecimal) r[6]);
            }
        	i++;
        }
        LOGGER.info("getClientOption - end");
        return clientOption;

    }

	@Override
	@SuppressWarnings("unchecked")
	public List<String> getActiveEarnCodeReported(String company) {
		LOGGER.info("getActiveEarnCodeReported - begin");
		Query query =peoplesoft_sessionFactory.getCurrentSession().getNamedQuery(PayrollQueryEnum.GET_ACTIVE_EARN_CODES_BY_COMPANY.getKey());
		query.setParameter("company", company);
		LOGGER.info("getActiveEarnCodeReported - end");
		return (List<String>)query.list();
				
	}
}
