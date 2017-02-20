package com.ptg.payroll.db.sp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.payroll.constant.PtgGatewayConstants;
import com.ptg.payroll.db.sp.payroll.payrollentry.PayrollGroupsSpService;
import com.ptg.payroll.domain.PayGroupDetail;
import com.ptg.payroll.domain.Payroll;
import com.ptg.payroll.domain.payroll.PayGroup;


import oracle.jdbc.OracleTypes;

public class PayrollGroups extends StoredProcedure implements PayrollGroupsSpService {

	private static final Logger log = LoggerFactory
			.getLogger(PayrollGroups.class);

	private static final String SP_NAME = "TRINET_PAYROLL_ENTRY.spc_PECompanyExists";
	private static final String IN_COMPANYID = "i_Companyid";
	private static final String IN_STARTDATE = "i_StartDt";
	private static final String IN_ENDDATE = "i_EndDt";
	private static final String OUT_COMPANYCOUNT = "o_CompanyCount";
	private static final String INOUT_GETCOMPANYPAYROLL = "io_GetCompanyPayroll";
	private static final String OUT_ERRMSG = "o_ErrMsg";

	public PayrollGroups(DataSource dataSource) {
		super(dataSource, SP_NAME);

		declareParameter(new SqlParameter(IN_COMPANYID, Types.VARCHAR));
		declareParameter(new SqlParameter(IN_STARTDATE, Types.DATE));
		declareParameter(new SqlParameter(IN_ENDDATE, Types.DATE));
		declareParameter(new SqlOutParameter(OUT_COMPANYCOUNT, Types.INTEGER));
		declareParameter(new SqlOutParameter(INOUT_GETCOMPANYPAYROLL,
				OracleTypes.CURSOR, new PayrollRowMapper()));
		declareParameter(new SqlOutParameter(OUT_ERRMSG, Types.VARCHAR));
		compile();
	}

	/* (non-Javadoc)
	 * @see com.trinet.fx.gateway.db.ps.sp.IGetPayrollGroups#execute(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Payroll> execute(String companyId, Date startDate, Date endDate) {

		if (StringUtils.isEmpty(companyId)) {
			throw new IllegalArgumentException(
					"Company Id can't be empty or null");
		}

		Map<String, Object> inputs = new HashMap<String, Object>();

		inputs.put(IN_COMPANYID, companyId);
		inputs.put(IN_STARTDATE, startDate);
		inputs.put(IN_ENDDATE, endDate);
		

		log.debug("calling sp: {} with parameters: {}", SP_NAME, inputs);

		Map<String, Object> output = super.execute(inputs);
		
		return (List<Payroll>) output
				.get(INOUT_GETCOMPANYPAYROLL);
	}

	private class PayrollRowMapper implements
			RowMapper<Payroll> {

		public Payroll mapRow(ResultSet resultSet, int row)
				throws SQLException {
			Payroll payroll = new Payroll();
			PayGroup payGroup = new PayGroup();
			PayGroupDetail details = new PayGroupDetail();

			payGroup.setId(resultSet.getLong("SEQ_NBR"));
			payGroup.setOffCycle(resultSet.getString(PtgGatewayConstants.OFFCYCLE));
			payGroup.setCompany(resultSet.getString(PtgGatewayConstants.COMPANY));
			payGroup.setPayBeginDate(resultSet.getDate(PtgGatewayConstants.PAYBEGINDT));
			payGroup.setPayEndDate(resultSet.getDate(PtgGatewayConstants.PAYENDDT));
			payGroup.setPayFrequency(resultSet.getString(PtgGatewayConstants.PAYFREQUENCY));
			payGroup.setPayGroup(resultSet.getString(PtgGatewayConstants.PAYGROUP));
			payGroup.setPayrollNumber(resultSet.getInt(PtgGatewayConstants.PAYROLLNUMBER));
			payGroup.setReportDate(resultSet.getDate(PtgGatewayConstants.REPORTDATE));
			payGroup.setStatus(resultSet.getString(PtgGatewayConstants.STATUS));
			
			payGroup.setJobCosting(resultSet.getString(PtgGatewayConstants.JOB_COSTING_FLAG));
			payGroup.setCertifiedPayroll(resultSet.getString(PtgGatewayConstants.CERTIFIED_PAYROLL_FLAG));
			
			details.setCheckDate(resultSet.getDate(PtgGatewayConstants.CHECK_DT));
			details.setDepartmentId(resultSet.getString(PtgGatewayConstants.DEPTID));
			details.setLocation(resultSet.getString(PtgGatewayConstants.LOCATION));
			details.setBrandName(resultSet.getString(PtgGatewayConstants.OCREASONCD));
			details.setOperatorId(resultSet.getString(PtgGatewayConstants.OPRID));
			details.setPayGroupDesc(resultSet.getString(PtgGatewayConstants.PAYGROUPDESC));
			details.setPayrollName(resultSet.getString(PtgGatewayConstants.PAYROLLNAME));
			details.setPayrollStatusDesc(resultSet.getString(PtgGatewayConstants.STATUSDESC));
			details.setOperatorName(resultSet.getString(PtgGatewayConstants.OPRNAME));
			details.setLastUpdatedDate(resultSet.getTimestamp(PtgGatewayConstants.TIMESTAMP));
			details.setDataEntryOption(resultSet.getString(PtgGatewayConstants.PAYROLL_ENTRY_OPTN));
			details.setCurrentEngineStep(resultSet.getString(PtgGatewayConstants.PAYROLL_RUN_ID));
			details.setErrorMessage(resultSet.getString(PtgGatewayConstants.PAYROLL_ERROR_MSG));
			details.setBlackoutFlag(resultSet.getString(PtgGatewayConstants.BLACKOUT_FLAG));
			details.setPaymentMethod(resultSet.getString(PtgGatewayConstants.PAYMENT_METHOD));
			details.setDefaultRegEarnCode(resultSet.getString(PtgGatewayConstants.DEFAULT_REG_EARN_CODE));
			
			payroll.setPayrollGroup(payGroup);
			payroll.setPayGroupDetail(details);
			
			return payroll;
		}
	}

}
