package com.payroll.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.payroll.dao.PsNamesSecurityDao;
import com.payroll.entity.PersonSecurity;
import com.payroll.entity.PsNames;

public class PsNamesSecurityDaoImpl implements PsNamesSecurityDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(PsNamesSecurityDaoImpl.class);
	@Autowired
	private SessionFactory  peoplesoft_sessionFactory;

	public SessionFactory getPeoplesoft_sessionFactory() {
		return peoplesoft_sessionFactory;
	}

	public void setPeoplesoft_sessionFactory(SessionFactory peoplesoft_sessionFactory) {
		this.peoplesoft_sessionFactory = peoplesoft_sessionFactory;
	}
	
	@Override
	public PsNames findByIdEmplIdAndMaxEffectiveDate(String emplid) {
		 LOGGER.debug("findByIdEmplIdAndMaxEffectiveDate start");
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.psName.findByIdEmplIdAndMaxEffectiveDate");
		query.setParameter("emplid", emplid);
		PsNames psNames = (PsNames)query.uniqueResult();
		 LOGGER.debug("findByIdEmplIdAndMaxEffectiveDate end");
        return psNames;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getEmployeeName(String emplid) {
		 LOGGER.debug("getEmployeeName start");
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.psName.employeeName");
		query.setParameter("emplid", emplid);
		 LOGGER.debug("getEmployeeName end");
        return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getSepCheckMessageForCompany(String company, String msgType) {
		 LOGGER.debug("getSepCheckMessageForCompany start");
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.psName.sepCheckMessageForCompany");
		query.setParameter("company", company);
		query.setParameter("msgType", msgType);
		 LOGGER.debug("getSepCheckMessageForCompany end");
        return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonSecurity> findPersonSecurityByPersonIdTsessionId(String personId, String tsessionId) {
		 LOGGER.debug("findPersonSecurityByPersonIdTsessionId start");
		Query query = peoplesoft_sessionFactory.getCurrentSession().getNamedQuery("SQL.psName.findPersonSecurityByPersonId");
		query.setParameter("personId", personId);
		//query.setParameter("tsessionId", tsessionId);
		 LOGGER.debug("findPersonSecurityByPersonIdTsessionId end");
        return query.list();
	}

}
