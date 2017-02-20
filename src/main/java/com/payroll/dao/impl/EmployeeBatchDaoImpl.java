package com.payroll.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.payroll.dao.EmployeeBatchDao;
import com.payroll.web.controller.impl.PayrollControllerImpl;
import com.ptg.payroll.domain.NameValueBean;

public class EmployeeBatchDaoImpl implements EmployeeBatchDao{
	private static final Logger LOGGER = LoggerFactory.getLogger(PayrollControllerImpl.class);
private SessionFactory  peoplesoft_sessionFactory;;
	
	
	
	public SessionFactory getPeoplesoft_sessionFactory() {
		return peoplesoft_sessionFactory;
	}

	public void setPeoplesoft_sessionFactory(SessionFactory peoplesoft_sessionFactory) {
		this.peoplesoft_sessionFactory = peoplesoft_sessionFactory;
	}
	
	@Override
	public List<NameValueBean> searchBatchCompanies() {

		List<NameValueBean> nameValues = new ArrayList<NameValueBean>();
		String sql="select distinct(company) from  PS_T2_NEWHIRE_UPLD";
		Session session=peoplesoft_sessionFactory.getCurrentSession();
		
		Query query = session.createSQLQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> resutls =  query.list();
		LOGGER.debug("companies = "+ resutls);
		for (String string : resutls) {
			String value = string != null ? string.toString() : "";
			String name = string!= null ? string.toString() : "";
			nameValues.add(new NameValueBean(name, value));
		}
		
		return nameValues;

	}
	@Override
	 public String getEmployeeName(String empId){
	  String employeeName=null;
	  
	  String sql="select name from ps_names ps where ps.emplid =:empId and effdt = (select max(effdt) from ps_names where emplid=ps.emplid) and name_type='PRI'";
	  
	  Query query = peoplesoft_sessionFactory.getCurrentSession().createSQLQuery(sql);
	  query.setParameter("empId",empId);
	  @SuppressWarnings("unchecked")
	List<String> results =  query.list();
	  if(results!=null && results.size() >0){
	   for (String string : results) {
	    employeeName = string != null ? string.toString() : "";
	   }
	  }
	  LOGGER.debug("names = "+ results);
	  
	  
	  return employeeName;
	 }
}
