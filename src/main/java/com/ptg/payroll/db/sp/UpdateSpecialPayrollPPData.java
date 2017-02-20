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
import com.ptg.payroll.db.sp.payroll.payrollentry.IUpdateSpecialPayrollPPData;
import com.ptg.payroll.domain.payroll.PayGroup;



public class UpdateSpecialPayrollPPData extends StoredProcedure implements IUpdateSpecialPayrollPPData {
	
	private static final Logger log = LoggerFactory.getLogger(UpdateSpecialPayrollPPData.class);

	private static final String SP_NAME = "TRINET_PAYROLL_ENTRY.spc_UpdateSpecialPayrollPPData";
    
	private static final String IN_COMPANY 				= "i_Companyid";
	private static final String IN_PAYGROUP 			= "i_Paygroup";
	private static final String IN_PAY_END_DATE 	    = "i_PayEndDt";
	private static final String IN_OFF_CYCLE 			= "i_OffCycle";
	private static final String IN_PAYROLL_NUMBER 		= "i_PayrollNum";

	private static final String OUT_ERROR_MSG		 	= "o_ErrMsg";
     
     
    /**
     * initialize the parameters and stored procedure name. 
     * @param dataSource
     */
	public UpdateSpecialPayrollPPData(DataSource dataSource) {
		super(dataSource, SP_NAME);
		
		declareParameter(new SqlParameter(IN_COMPANY, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PAYGROUP, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PAY_END_DATE, 		Types.DATE));;
		declareParameter(new SqlParameter(IN_OFF_CYCLE, 		Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PAYROLL_NUMBER, 	Types.INTEGER));

		declareParameter(new SqlOutParameter(OUT_ERROR_MSG, 	Types.VARCHAR));
                        
        compile();
	}
	
	@Override
	public void execute(PayGroup payGroup) throws GatewayBackendException {
				
		if (StringUtils.isEmpty(payGroup.getCompany())){
			throw new IllegalArgumentException("Company can't be empty or null");
		}
		if (StringUtils.isEmpty(payGroup.getPayGroup())){
			throw new IllegalArgumentException("Paygroup can't be empty or null");
		}
		if (payGroup.getPayEndDate() == null){
			throw new IllegalArgumentException("Pay End Date can't be empty or null");
		}
		if (StringUtils.isEmpty(payGroup.getOffCycle())){
			throw new IllegalArgumentException("Off cycle can't be empty or null");
		}
				
		log.debug("inside get special payroll employee hours");
		
        Map<String, Object> inputs = new HashMap<String, Object>();
		
		inputs.put(IN_COMPANY, 			payGroup.getCompany());
		inputs.put(IN_PAYGROUP, 		payGroup.getPayGroup());
		inputs.put(IN_PAY_END_DATE, 	payGroup.getPayEndDate());
		inputs.put(IN_OFF_CYCLE, 		payGroup.getOffCycle());
		inputs.put(IN_PAYROLL_NUMBER, 	payGroup.getPayrollNumber());
		
		log.debug("calling sp: {} with parameters: {}",SP_NAME, inputs);
		 
		Map<String, Object>  output = super.execute(inputs);
		String error = (String) output.get(OUT_ERROR_MSG);
		 
		log.debug("Output from the sp is : {}", error);
		boolean isError = StringUtils.equalsIgnoreCase(error, "N") ? false : true;
		log.debug("isError is : {}", isError);
		if (isError){
			log.error("SQL Error:" + error + " Insert Special Payroll Object: sp {}, parameters:{}", SP_NAME,inputs);
			throw new GatewayBackendException("SQL Error:"+ " could not insert Special Payroll:");
		}
		
	}

}
