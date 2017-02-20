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



public class PayrollStagingPublishSP extends StoredProcedure {

    private static final Logger log = LoggerFactory.getLogger(PayrollStagingPublishSP.class);

    private static final String SP_NAME = "TRINET_PAYROLL_ENTRY.spc_PayrollFileImport";

    private static final String IN_COMPANY = "i_Companyid";
    private static final String IN_PAYGROUP = "i_Paygroup";
    private static final String IN_PAY_END_DATE = "i_PayEndDt";
    private static final String IN_OFF_CYCLE = "i_OffCycle";
    private static final String IN_T2_PAYROLL_NUM = "i_PayrollNum";
    private static final String IN_PROFILE_ID = "i_ProfileId";
    private static final String IN_SOURCE = "i_Source";
    private static final String IN_OPERATOR_ID = "i_Oprid";

    private static final String OUT_ERR_MSG = "o_ErrMsg";

    public PayrollStagingPublishSP(DataSource dataSource) {

        super(dataSource, SP_NAME);

        declareParameter(new SqlParameter(IN_COMPANY, Types.VARCHAR));
        declareParameter(new SqlParameter(IN_PAYGROUP, Types.VARCHAR));
        declareParameter(new SqlParameter(IN_PAY_END_DATE, Types.DATE));
        declareParameter(new SqlParameter(IN_OFF_CYCLE, Types.VARCHAR));
        declareParameter(new SqlParameter(IN_T2_PAYROLL_NUM, Types.INTEGER));
        declareParameter(new SqlParameter(IN_PROFILE_ID, Types.INTEGER));
        declareParameter(new SqlParameter(IN_SOURCE, Types.VARCHAR));
        declareParameter(new SqlParameter(IN_OPERATOR_ID, Types.VARCHAR));

        declareParameter(new SqlOutParameter(OUT_ERR_MSG, Types.VARCHAR));

        compile();
    }

    public void publishRawPayrollEntryRecords(PayGroup payGroup, long profileId, String source, String userEmployeeId) {

        Map<String, Object> inputs = new HashMap<String, Object>();

        inputs.put(IN_COMPANY, payGroup.getCompany());
        inputs.put(IN_PAYGROUP, payGroup.getPayGroup());
        inputs.put(IN_PAY_END_DATE, payGroup.getPayEndDate());
        inputs.put(IN_OFF_CYCLE, payGroup.getOffCycle());
        inputs.put(IN_T2_PAYROLL_NUM, payGroup.getPayrollNumber());
        inputs.put(IN_PROFILE_ID, profileId);
        inputs.put(IN_SOURCE, source);
        inputs.put(IN_OPERATOR_ID, userEmployeeId);
        
        log.debug("calling sp: {} with parameters: {}", SP_NAME, inputs);

        Map<String, Object> output = super.execute(inputs);
        String error = (String) output.get(OUT_ERR_MSG);

        log.debug("Output from the sp is : {}", error);
        boolean isError = StringUtils.equalsIgnoreCase(error, "N") ? false : true;
        log.debug("isError is : {}", isError);
        if (isError) {
            log.error("SQL Error:" + error + " Payroll Import Data Load Object: sp {}, parameters:{}", SP_NAME, inputs);
            throw new GatewayBackendException("SQL Error:" + " could not Load data from stage table:");
        }

    }

}
