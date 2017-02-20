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
import com.ptg.payroll.db.sp.payroll.payrollentry.InsertSpecialPayrollSpService;
import com.ptg.payroll.domain.PayGroupDelivery;
import com.ptg.payroll.domain.PayGroupDetail;
import com.ptg.payroll.domain.SpecialPayroll;
import com.ptg.payroll.domain.payroll.PayGroup;


public class InsertSpecialPayroll extends StoredProcedure implements InsertSpecialPayrollSpService{
	
	private static final Logger log = LoggerFactory.getLogger(InsertSpecialPayroll.class);

	private static final String SP_NAME = "TRINET_PAYROLL_ENTRY.spc_InsOrUpdSpecialPayroll";
    
	private static final String IN_COMPANY 				= "i_Companyid";
	private static final String IN_PAYGROUP 			= "i_Paygroup";
	private static final String IN_PAYROLL_NUMBER 		= "i_HRP_Payroll_Num";
	private static final String IN_PAYROLL_NAME 		= "i_Payroll_Name";
	private static final String IN_CHECK_DATE 			= "i_Check_Dt";
	private static final String IN_PAYMENT_METHOD 		= "i_Payment_Method";
	private static final String IN_DELIVERY_OPTION 		= "i_Delivery_Option";
	private static final String IN_DELIVERY_METHOD 		= "i_Delivery_Method";
	private static final String IN_ADDRESS_OPTION 		= "i_Addr_Optn";
	private static final String IN_ATTENTION 			= "i_Attention";
	private static final String IN_ADDRESS1 			= "i_Address1";
	private static final String IN_ADDRESS2 			= "i_Address2";
	private static final String IN_CITY 				= "i_City";
	private static final String IN_STATE 				= "i_State";
	private static final String IN_POSTAL_CODE 			= "i_Postal_Code";
	private static final String IN_COUNTRY 				= "i_Country";
	private static final String IN_COMMENTS 			= "i_Comments";
	private static final String IN_PERSON_ID 			= "i_oprid";

    private static final String OUT_ERR_MSG 			= "o_errMsg";
    private static final String OUT_PAY_END_DATE 		= "o_PayEndDt";
    private static final String OUT_PAYROLL_NUM 		= "o_PayrollNum";
     
     
    /**
     * initialize the parameters and stored procedure name. 
     * @param dataSource
     */
	public InsertSpecialPayroll(DataSource dataSource) {
		super(dataSource, SP_NAME);
		
		declareParameter(new SqlParameter(IN_COMPANY, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PAYGROUP, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PAYROLL_NUMBER, 	Types.INTEGER));
		declareParameter(new SqlParameter(IN_PAYROLL_NAME, 		Types.VARCHAR));
		declareParameter(new SqlParameter(IN_CHECK_DATE, 		Types.DATE));
		declareParameter(new SqlParameter(IN_PAYMENT_METHOD, 	Types.VARCHAR));
		declareParameter(new SqlParameter(IN_DELIVERY_OPTION, 	Types.VARCHAR));
		declareParameter(new SqlParameter(IN_DELIVERY_METHOD, 	Types.VARCHAR));
		declareParameter(new SqlParameter(IN_ADDRESS_OPTION,	Types.VARCHAR));
		declareParameter(new SqlParameter(IN_ATTENTION, 		Types.VARCHAR));
		declareParameter(new SqlParameter(IN_ADDRESS1, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_ADDRESS2, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_CITY, 				Types.VARCHAR));
		declareParameter(new SqlParameter(IN_STATE, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_POSTAL_CODE, 		Types.VARCHAR));
		declareParameter(new SqlParameter(IN_COUNTRY, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_COMMENTS, 			Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PERSON_ID, 		Types.VARCHAR));
		        
        declareParameter(new SqlOutParameter(OUT_PAYROLL_NUM, 	Types.INTEGER));
        declareParameter(new SqlOutParameter(OUT_PAY_END_DATE, 	Types.DATE));
        declareParameter(new SqlOutParameter(OUT_ERR_MSG, 		Types.VARCHAR));
                        
        compile();
	}
	
	/**
	 * Excute the insert/update of special payroll by stored procedure.
	 * @param specialPayroll
	 * @throws GatewayBackendException
	 */
	public SpecialPayroll execute(SpecialPayroll specialPayroll) throws GatewayBackendException{
		PayGroup payGroup = specialPayroll.getPayroll().getPayrollGroup();
		PayGroupDetail payGroupDetail =  specialPayroll.getPayroll().getPayGroupDetail();
		PayGroupDelivery delivery =  specialPayroll.getPayroll().getPayGroupDelivery();
		if (StringUtils.isEmpty(payGroup.getCompany())){
			throw new IllegalArgumentException("Company can't be empty or null");
		}
		if (StringUtils.isEmpty(payGroup.getPayGroup())){
			throw new IllegalArgumentException("Paygroup can't be empty or null");
		}
		if (payGroupDetail.getCheckDate() == null){
			throw new IllegalArgumentException("Check Date can't be empty or null");
		}
		if (StringUtils.isEmpty(payGroupDetail.getPaymentMethod())){
			throw new IllegalArgumentException("Payment Method can't be empty or null");
		}
				
		log.debug("inside insert special payroll");
		
        Map<String, Object> inputs = new HashMap<String, Object>();
		
		inputs.put(IN_COMPANY, payGroup.getCompany());
		inputs.put(IN_PAYGROUP, payGroup.getPayGroup());
		int payrollNum = payGroup.getPayrollNumber();
		inputs.put(IN_PAYROLL_NUMBER, payrollNum);
		String payrollName = StringUtils.isEmpty(payGroupDetail.getPayrollName()) ? " " : payGroupDetail.getPayrollName();
		inputs.put(IN_PAYROLL_NAME, payrollName);
		inputs.put(IN_CHECK_DATE, payGroupDetail.getCheckDate());
		inputs.put(IN_PAYMENT_METHOD, payGroupDetail.getPaymentMethod());
		String deliveryOption = StringUtils.isEmpty(delivery.getDeliveryOption()) ? " " : delivery.getDeliveryOption();
		inputs.put(IN_DELIVERY_OPTION, deliveryOption);
		String deliveryMethod = StringUtils.isEmpty(delivery.getDeliveryType()) ? " " : delivery.getDeliveryType();
		inputs.put(IN_DELIVERY_METHOD, deliveryMethod);
		String addressOption = StringUtils.isEmpty(delivery.getAddressOption()) ? " " : delivery.getAddressOption();
		inputs.put(IN_ADDRESS_OPTION, addressOption);
		String attention = StringUtils.isEmpty(delivery.getAttention()) ? " " : delivery.getAttention();
		inputs.put(IN_ATTENTION, attention);
		String address1 = StringUtils.isEmpty(delivery.getAddress1()) ? " " : delivery.getAddress1();
		inputs.put(IN_ADDRESS1, address1);
		String address2 = StringUtils.isEmpty(delivery.getAddress2()) ? " " : delivery.getAddress2();
		inputs.put(IN_ADDRESS2, address2);
		String city = StringUtils.isEmpty(delivery.getCity()) ? " " : delivery.getCity();
		inputs.put(IN_CITY, city);
		String state = StringUtils.isEmpty(delivery.getState()) ? " " : delivery.getState();
		inputs.put(IN_STATE, state);
		String postalCode = StringUtils.isEmpty(delivery.getPostalCode()) ? " " : delivery.getPostalCode();
		inputs.put(IN_POSTAL_CODE, postalCode);
		String country = StringUtils.isEmpty(delivery.getCountry()) ? " " : delivery.getCountry();
		inputs.put(IN_COUNTRY, country);
		String specialDeliveryInstructions = StringUtils.isEmpty(delivery.getDeliveryInstruction()) ? " " : delivery.getDeliveryInstruction();
		inputs.put(IN_COMMENTS, specialDeliveryInstructions);
		String oprid = StringUtils.isEmpty(payGroupDetail.getOperatorId()) ? " " : payGroupDetail.getOperatorId();
		inputs.put(IN_PERSON_ID, oprid);
				
		log.debug("calling sp: {} with parameters: {}",SP_NAME, inputs);
		 
		Map<String, Object>  output = super.execute(inputs);
		String error = (String) output.get(OUT_ERR_MSG);
		 
		log.debug("Output from the sp is : {}", error);
		boolean isError = StringUtils.equalsIgnoreCase(error, "N") ? false : true;
		log.debug("isError is : {}", isError);
		if (isError){
			log.error("SQL Error:" + error + " Insert Special Payroll Object: sp {}, parameters:{}", SP_NAME,inputs);
			throw new GatewayBackendException("SQL Error:"+ " could not insert Special Payroll:");
		} else if(payrollNum == 0){
			//Update payroll number for only create special payroll.
			int payrollNumber = (Integer) output.get(OUT_PAYROLL_NUM);
			Date payEndDate = (Date) output.get(OUT_PAY_END_DATE);
			payGroup.setPayrollNumber(payrollNumber);
			payGroup.setPayEndDate(payEndDate);
		}
		
		return specialPayroll;
		
	}

}
