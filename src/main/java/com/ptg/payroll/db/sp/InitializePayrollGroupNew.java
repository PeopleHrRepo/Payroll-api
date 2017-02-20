package com.ptg.payroll.db.sp;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.payroll.exception.GatewayBackendException;
import com.ptg.payroll.db.sp.payroll.payrollentry.IInitializePayrollGroupNew;


/**
 * @author ashrivastava
 *
 */
public class InitializePayrollGroupNew extends StoredProcedure implements IInitializePayrollGroupNew {
	
	private static final Logger log = LoggerFactory.getLogger(InitializePayrollGroupNew.class);

	private static final String SP_NAME = "TRINET_PAYROLL_ENTRY.spc_InitializeCoPayrollSubProc";
    
	private static final String IN_COMPANY 				= "i_Companyid";
	private static final String IN_PAYGROUP 			= "i_Paygroup";
	private static final String IN_PAY_BEGIN_DATE 		= "i_PayBeginDt";
	private static final String IN_PAY_END_DATE 		= "i_PayEndDt";
	private static final String IN_OPR_ID 				= "i_Oprid";
	private static final String IN_KEY 					= "i_Seqnbr";
	
    private static final String OUT_ERR_MSG 			= "o_ErrMsg";
    
    public InitializePayrollGroupNew(DataSource dataSource){
    	super(dataSource, SP_NAME);
		
		declareParameter(new SqlParameter(IN_COMPANY, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PAYGROUP, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PAY_END_DATE, 		Types.DATE));
		declareParameter(new SqlParameter(IN_PAY_BEGIN_DATE, 	Types.DATE));
		declareParameter(new SqlParameter(IN_OPR_ID, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_KEY, 			Types.VARCHAR));
        declareParameter(new SqlOutParameter(OUT_ERR_MSG, 		Types.VARCHAR));
                        
        compile();
    }
	
	@Override
	public void execute(String company, String payGroup, Date payBeginDate,
			Date payEndDate, String oprId , String key) throws GatewayBackendException {
		if (StringUtils.isEmpty(company)){
			throw new IllegalArgumentException("Company can't be empty or null");
		}
		if (StringUtils.isEmpty(payGroup)){
			throw new IllegalArgumentException("Paygroup can't be empty or null");
		}
		if (payBeginDate == null){
			throw new IllegalArgumentException("Pay Begin Date can't be empty or null");
		}
		if (payEndDate == null){
			throw new IllegalArgumentException("Pay End Date can't be empty or null");
		}
		
		if (oprId == null){
			throw new IllegalArgumentException("Operator Id can't be empty or null");
		}
		
		if (key == null){
			throw new IllegalArgumentException("Key can't be empty or null");
		}
				
		log.debug("inside Initialize Payroll Group with sub process");
		
        Map<String, Object> inputs = new HashMap<String, Object>();
		
		inputs.put(IN_COMPANY, company);
		inputs.put(IN_PAYGROUP, payGroup);
		inputs.put(IN_PAY_BEGIN_DATE, payBeginDate);
		inputs.put(IN_PAY_END_DATE, payEndDate);
		inputs.put(IN_OPR_ID, oprId);
		inputs.put(IN_KEY, key);
				
		log.debug("calling sp: {} with parameters: {}",SP_NAME, inputs);
		 
		Map<String, Object>  output = super.execute(inputs);
		String error = (String) output.get(OUT_ERR_MSG);
		 
		log.debug("Output from the sp is : {}", error);
		boolean isError = StringUtils.equalsIgnoreCase(error, "N") ? false : true;
		log.debug("isError is : {}", isError);
		if (isError){
			log.error("SQL Error:" + error + " Initialize Payroll Group Object: sp {}, parameters:{}", SP_NAME, inputs);
			throw new GatewayBackendException("SQL Error:"+ " could not Initialize Payroll Group:");
		}

	}

	

}