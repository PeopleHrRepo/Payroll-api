package com.ptg.payroll.db.sp;
import java.sql.Types;
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
import com.ptg.payroll.db.sp.payroll.payrollentry.DeleteSpecialPayrollSpService;
import com.ptg.payroll.domain.payroll.PayGroup;


public class DeleteSpecialPayroll extends StoredProcedure implements DeleteSpecialPayrollSpService {
	
	// Logger
	private static final Logger log = LoggerFactory.getLogger(DeleteSpecialPayroll.class);
	
	// Store procedure to be called
	private static final String SP_NAME = "TRINET_PAYROLL_ENTRY.spc_UnsheetSpclPayroll";
	
	// Store procedure input/output param constants
	private static final String IN_COMPANY 				= "i_Companyid";
	private static final String IN_PAYGROUP 			= "i_Paygroup";
	private static final String IN_PAY_END_DATE 		= "i_PayEndDt";
	private static final String IN_OFFCYCLE 			= "i_OffCycle";
	private static final String IN_PAYROLL_NUMBER 		= "i_HRPPayrollNum";
	private static final String IN_STATUS 				= "i_Status";
    private static final String OUT_ERR_MSG 			= "o_ErrMsg";
    
    /**
     * Initialize the parameters and stored procedure name. 
     * @param dataSource
     */
	public DeleteSpecialPayroll(DataSource dataSource) {
		super(dataSource, SP_NAME);
		
		declareParameter(new SqlParameter(IN_COMPANY, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PAYGROUP, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PAY_END_DATE, 		Types.DATE));
		declareParameter(new SqlParameter(IN_OFFCYCLE, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PAYROLL_NUMBER, 	Types.INTEGER));
		declareParameter(new SqlParameter(IN_STATUS, 			Types.VARCHAR));
        declareParameter(new SqlOutParameter(OUT_ERR_MSG, 		Types.VARCHAR));
                        
        compile();
	}

	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.db.ps.sp.IDeleteSpecialPayroll#execute(com.trinet.domain.payroll.PayGroup)
	 */
	@Override
	public void execute(PayGroup payGroup, String action) throws GatewayBackendException {
		log.debug("Inside delete special payroll");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put(IN_COMPANY, payGroup.getCompany());
		paramMap.put(IN_PAYGROUP, payGroup.getPayGroup());
		paramMap.put(IN_PAY_END_DATE, payGroup.getPayEndDate());
		paramMap.put(IN_OFFCYCLE, payGroup.getOffCycle());
		paramMap.put(IN_PAYROLL_NUMBER, payGroup.getPayrollNumber());
		paramMap.put(IN_STATUS, action);
		
		log.debug("Calling sp: {} with parameters: {}", SP_NAME, paramMap);
		 
		Map<String, Object>  output = super.execute(paramMap);
		String error = (String) output.get(OUT_ERR_MSG);
		
		log.debug("Output from the sp is : {}", error);
		boolean isError = StringUtils.equalsIgnoreCase(error, "N") ? false : true;
		log.debug("isError is : {}", isError);
		if (isError) {
			log.error("SQL Error:" + error + " Delete Special Payroll Object: sp {}, parameters:{}", SP_NAME, paramMap);
			throw new GatewayBackendException("SQL Error:"+ " Could not delete Special Payroll:");
		}
	}

}
