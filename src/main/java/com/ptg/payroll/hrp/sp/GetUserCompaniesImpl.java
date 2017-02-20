package com.ptg.payroll.hrp.sp;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import com.ptg.payroll.hrp.model.HRPUserCompanies;
import com.ptg.payroll.hrp.model.HRPUserCompany;
import com.ptg.payroll.hrp.service.GetUserCompanies;




public class GetUserCompaniesImpl extends StoredProcedure implements GetUserCompanies {

	private static final Logger log = LoggerFactory.getLogger(GetUserCompaniesImpl.class);
	
	private static final String SP_NAME = "TRINET_API.spc_getmycompanies";
	private static final String i_personId = "i_personid";
	private static final String OUT_RESULT = "o_Cursor";
	
	public GetUserCompaniesImpl(DataSource dataSource) {
	    super(dataSource, SP_NAME);
	    
	    declareParameter(new SqlParameter(i_personId, Types.VARCHAR));
	    declareParameter(new SqlOutParameter(OUT_RESULT, OracleTypes.CURSOR, BeanPropertyRowMapper.newInstance(HRPUserCompany.class)));
	    compile();
	}
	
	public HRPUserCompanies execute(String personId) {
		
			if (StringUtils.isEmpty(personId)) {
				throw new IllegalArgumentException("Person Id can't be empty or null");
		}

         HRPUserCompanies userCompanies = new HRPUserCompanies();
         Map<String, Object> inputs = new HashMap<String, Object>();
	
         inputs.put(i_personId, personId);

         log.debug("calling sp: {} with parameters: {}",SP_NAME,inputs);
	 
         Map<String, Object> output = super.execute(inputs);
         userCompanies.setCompanies((List<HRPUserCompany>) output.get(OUT_RESULT));
		
		return userCompanies;
	}
	
}