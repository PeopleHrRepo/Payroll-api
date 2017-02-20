package com.ptg.payroll.db.sp.payroll;

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
import com.ptg.payroll.domain.payroll.PayGroup;

public class PayrollReInitializeSP extends StoredProcedure {

    private static final Logger log = LoggerFactory.getLogger(PayrollReInitializeSP.class);

    private static final String SP_NAME = "TRINET_PAYROLL_ENTRY.spc_ReInitializeEEData";

    private static final String IN_COMPANY = "i_Companyid";
    private static final String IN_PAYGROUP = "i_Paygroup";
    private static final String IN_PAY_BEGIN_DATE = "i_PayBeginDt";
    private static final String IN_PAY_END_DATE = "i_PayEndDt";
    private static final String IN_OFF_CYCLE = "i_OffCycle";
    private static final String IN_T2_PAYROLL_NUM = "i_PayrollNum";

    private static final String OUT_ERR_MSG = "o_ErrMsg";

    public PayrollReInitializeSP(DataSource dataSource) {

        super(dataSource, SP_NAME);

        declareParameter(new SqlParameter(IN_COMPANY, Types.VARCHAR));
        declareParameter(new SqlParameter(IN_PAYGROUP, Types.VARCHAR));
        declareParameter(new SqlParameter(IN_PAY_BEGIN_DATE, Types.DATE));
        declareParameter(new SqlParameter(IN_PAY_END_DATE, Types.DATE));
        declareParameter(new SqlParameter(IN_OFF_CYCLE, Types.VARCHAR));
        declareParameter(new SqlParameter(IN_T2_PAYROLL_NUM, Types.INTEGER));

        declareParameter(new SqlOutParameter(OUT_ERR_MSG, Types.VARCHAR));

        compile();
    }

    public void reInitializePayroll(PayGroup payGroup, String userEmployeeId) {

        Map<String, Object> inputs = new HashMap<String, Object>();

        inputs.put(IN_COMPANY, payGroup.getCompany());
        inputs.put(IN_PAYGROUP, payGroup.getPayGroup());
        inputs.put(IN_PAY_BEGIN_DATE, payGroup.getPayBeginDate());
        inputs.put(IN_PAY_END_DATE, payGroup.getPayEndDate());
        inputs.put(IN_OFF_CYCLE, payGroup.getOffCycle());
        inputs.put(IN_T2_PAYROLL_NUM, payGroup.getPayrollNumber());

        log.debug("calling sp: {} with parameters: {}", SP_NAME, inputs);

        Map<String, Object> output = super.execute(inputs);
        String error = (String) output.get(OUT_ERR_MSG);

        log.debug("Output from the sp is : {}", error);
        boolean isError = StringUtils.equalsIgnoreCase(error, "N") ? false : true;
        log.debug("isError is : {}", isError);
        if (isError) {
            log.error("SQL Error:" + error + " Payroll Re-Initialize failed : sp {}, parameters:{}", SP_NAME, inputs);
            throw new GatewayBackendException("SQL Error:" + " couldn't re-initialize payroll:");
        }

    }

}