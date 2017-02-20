package com.ptg.payroll.db.sp.payroll;
import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

import com.payroll.constant.PtgGatewayConstants;
import com.ptg.payroll.domain.EmployeeLeaveBalance;
import com.ptg.payroll.domain.payroll.PayGroup;


public class EmployeesLeaveAccrualSP extends StoredProcedure {

    private static final Logger log = LoggerFactory.getLogger(EmployeesLeaveAccrualSP.class);

    private static final String SP_NAME = "TRINET_PAYROLL_ENTRY.spc_GetEELeaveAccrualBal";
    private static final String IN_COMPANYID = "i_Companyid";
    private static final String IN_PAYGROUP = "i_Paygroup";
    private static final String IN_PAY_END_DATE = "i_PayEndDt";
    private static final String IN_PAYROLL_NUMBER = "i_HRPPayrollNum";
    private static final String IN_OFF_CYCLE = "i_OffCycle";

    private static final String INOUT_GETEMPLOYEESLEAVES = "o_GetEELeaveAccrlBal";
    private static final String OUT_ERRMSG = "o_ErrMsg";

    public EmployeesLeaveAccrualSP(DataSource dataSource) {
        super(dataSource, SP_NAME);

        declareParameter(new SqlParameter(IN_COMPANYID, Types.VARCHAR));
        declareParameter(new SqlParameter(IN_PAYGROUP, Types.VARCHAR));
        declareParameter(new SqlParameter(IN_PAY_END_DATE, Types.DATE));
        declareParameter(new SqlParameter(IN_OFF_CYCLE, Types.VARCHAR));
        declareParameter(new SqlParameter(IN_PAYROLL_NUMBER, Types.INTEGER));
        declareParameter(new SqlOutParameter(INOUT_GETEMPLOYEESLEAVES, OracleTypes.CURSOR, new EmployeeLeaveAccrualMapper()));
        declareParameter(new SqlOutParameter(OUT_ERRMSG, Types.VARCHAR));
        compile();
    }

    /**
     * 
     */
    @SuppressWarnings("unchecked")
    public List<EmployeeLeaveBalance> execute(PayGroup payGroup) {

        Map<String, Object> inputs = new HashMap<String, Object>();

        inputs.put(IN_COMPANYID, payGroup.getCompany());
        inputs.put(IN_PAYGROUP, payGroup.getPayGroup());
        inputs.put(IN_PAY_END_DATE, payGroup.getPayEndDate());
        inputs.put(IN_PAYROLL_NUMBER, payGroup.getPayrollNumber());
        inputs.put(IN_OFF_CYCLE, payGroup.getOffCycle());

        log.debug("calling sp: {} with parameters: {}", SP_NAME, inputs);

        Map<String, Object> output = super.execute(inputs);

        return (List<EmployeeLeaveBalance>) output.get(INOUT_GETEMPLOYEESLEAVES);
    }

    private class EmployeeLeaveAccrualMapper implements RowMapper<EmployeeLeaveBalance> {

        public EmployeeLeaveBalance mapRow(ResultSet resultSet, int row) throws SQLException {

            EmployeeLeaveBalance leaveAccrual = new EmployeeLeaveBalance();
            leaveAccrual.setEmployeeId(resultSet.getString(PtgGatewayConstants.LEAVE_EMPLID));
            leaveAccrual.setEmployeeRecord(resultSet.getInt(PtgGatewayConstants.LEAVE_EMPLRCD));
            leaveAccrual.setEarnCode(resultSet.getString(PtgGatewayConstants.LEAVE_ERNCD));
            leaveAccrual.setBalanceHours(resultSet.getDouble(PtgGatewayConstants.LEAVE_BAL_HOURS));

            return leaveAccrual;
        }
    }

}