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
import com.ptg.payroll.db.sp.payroll.payrollentry.PayrollGroupsByRoleSpService;
import com.ptg.payroll.domain.PayGroupDetail;
import com.ptg.payroll.domain.PayrollByRole;
import com.ptg.payroll.domain.payroll.PayGroup;

import oracle.jdbc.OracleTypes;


/**
 * @author rlakshmiraj
 * 
 */
public class PayrollGroupsByRole extends StoredProcedure implements
		PayrollGroupsByRoleSpService {

	private static final Logger log = LoggerFactory
			.getLogger(PayrollGroupsByRole.class);

	private static final String SP_NAME = "TRINET_PAYROLL_ENTRY.spc_GetCompanyForListView";
	private static final String IN_COMPANYID = "i_Companyid";
	private static final String IN_PERSONID = "i_PersonId";
	private static final String IN_STARTDATE = "i_StartDt";
	private static final String IN_ENDDATE = "i_EndDt";
	private static final String INOUT_GETCOMPANYPAYROLLS = "o_GetCompanyPayrolls";
	private static final String OUT_ERRMSG = "o_ErrMsg";

	public PayrollGroupsByRole(DataSource dataSource) {
		super(dataSource, SP_NAME);

		declareParameter(new SqlParameter(IN_COMPANYID, Types.VARCHAR));
		declareParameter(new SqlParameter(IN_PERSONID, Types.VARCHAR));
		declareParameter(new SqlParameter(IN_STARTDATE, Types.DATE));
		declareParameter(new SqlParameter(IN_ENDDATE, Types.DATE));
		declareParameter(new SqlOutParameter(INOUT_GETCOMPANYPAYROLLS,
				OracleTypes.CURSOR, new PayrollListRowMapper()));
		declareParameter(new SqlOutParameter(OUT_ERRMSG, Types.VARCHAR));
		compile();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.trinet.fx.gateway.db.ps.sp.IGetPayrollGroupsList#execute(java.lang
	 * .String, java.lang.String, java.lang.String, java.util.Date,
	 * java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<PayrollByRole> execute(String company, String personId,
			Date startDate, Date endDate) {

		if (StringUtils.isEmpty(company)) {
			throw new IllegalArgumentException("Company can't be empty or null");
		}

		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put(IN_COMPANYID, company);
		inputs.put(IN_PERSONID, personId);
		inputs.put(IN_STARTDATE, startDate);
		inputs.put(IN_ENDDATE, endDate);

		log.debug("calling sp: {} with parameters: {}", SP_NAME, inputs);

		Map<String, Object> output = super.execute(inputs);
		System.out.println("output : "+output.size());

		return (List<PayrollByRole>) output.get(INOUT_GETCOMPANYPAYROLLS);
	}

	private class PayrollListRowMapper implements RowMapper<PayrollByRole> {

		public PayrollByRole mapRow(ResultSet resultSet, int row)
				throws SQLException {
			PayrollByRole payrollByRole = new PayrollByRole();
			PayGroup payGroup = new PayGroup();
			PayGroupDetail details = new PayGroupDetail();

			payGroup.setId(resultSet.getLong(PtgGatewayConstants.SEQ_NBR));
			payGroup.setOffCycle(resultSet.getString(PtgGatewayConstants.OFFCYCLE));
			payGroup.setCompany(resultSet.getString(PtgGatewayConstants.COMPANY));
			payGroup.setPayBeginDate(resultSet
					.getDate(PtgGatewayConstants.PAYBEGINDT));
			payGroup.setPayEndDate(resultSet.getDate(PtgGatewayConstants.PAYENDDT));
			payGroup.setPayFrequency(resultSet
					.getString(PtgGatewayConstants.PAYFREQUENCY));
			payGroup.setPayGroup(resultSet.getString(PtgGatewayConstants.PAYGROUP));
			payGroup.setPayrollNumber(resultSet
					.getInt(PtgGatewayConstants.PAYROLLNUMBER));
			payGroup.setReportDate(resultSet
					.getDate(PtgGatewayConstants.REPORTDATE));
			payGroup.setStatus(resultSet
					.getString(PtgGatewayConstants.PARENT_STATUS));
			payGroup.setPayFrequencyDesc(resultSet
					.getString(PtgGatewayConstants.PAYGROUP_FREQ_DESC));
			
			payGroup.setCertifiedPayroll(resultSet
					.getString(PtgGatewayConstants.CERTIFIED_PAYROLL_FLAG));
			payGroup.setJobCosting(resultSet
					.getString(PtgGatewayConstants.JOB_COSTING_FLAG));
			
			details.setCheckDate(resultSet.getDate(PtgGatewayConstants.CHECK_DT));
			details.setDepartmentId(resultSet
					.getString(PtgGatewayConstants.DEPTID));
			details.setLocation(resultSet.getString(PtgGatewayConstants.LOCATION));
			details.setBrandName(resultSet
					.getString(PtgGatewayConstants.OCREASONCD));
			details.setOperatorId(resultSet
					.getString(PtgGatewayConstants.PARENT_OPRID));
			details.setOperatorName(resultSet
					.getString(PtgGatewayConstants.PARENT_OPRNAME));

			details.setPayGroupDesc(resultSet
					.getString(PtgGatewayConstants.PAYGROUP_DESCR));
			details.setPayrollName(resultSet
					.getString(PtgGatewayConstants.PAYROLLNAME));
			details.setPayrollStatusDesc(resultSet
					.getString(PtgGatewayConstants.PARENT_STATUSDESCR));
			details.setLastUpdatedDate(resultSet
					.getTimestamp(PtgGatewayConstants.PARENT_TIMESTAMP));
			details.setDataEntryOption(resultSet
					.getString(PtgGatewayConstants.PAYROLL_ENTRY_OPTN));
			details.setCurrentEngineStep(resultSet
					.getString(PtgGatewayConstants.PAYROLL_RUN_ID));
			// details.setErrorMessage(resultSet
			// .getString(PtgGatewayConstants.PAYROLL_ERROR_MSG));
			details.setBlackoutFlag(resultSet
					.getString(PtgGatewayConstants.BLACKOUT_FLAG));
			details.setPaymentMethod(resultSet
					.getString(PtgGatewayConstants.PAYMENT_METHOD));
			details.setDefaultRegEarnCode(resultSet
					.getString(PtgGatewayConstants.DEFAULT_REG_EARN_CODE));

			payrollByRole.setIsParent(resultSet
					.getString(PtgGatewayConstants.IS_PARENT));
			payrollByRole.setLocationDesc(resultSet
					.getString(PtgGatewayConstants.LOCATION_DESCR));
			payrollByRole.setDepartmentDesc(resultSet
					.getString(PtgGatewayConstants.DEPT_DESCR));
			payrollByRole.setChildPayrollStatus(resultSet
					.getString(PtgGatewayConstants.CHILD_STATUS));
			payrollByRole.setChildPayrollStatusDesc(resultSet
					.getString(PtgGatewayConstants.CHILD_STATUSDESCR));
			payrollByRole.setChildOperatorId(resultSet
					.getString(PtgGatewayConstants.CHILD_OPRID));
			payrollByRole.setChildOperatorName(resultSet
					.getString(PtgGatewayConstants.CHILD_OPRNAME));
			payrollByRole.setChildLastUpdatedDate(resultSet
					.getTimestamp(PtgGatewayConstants.CHILD_TIMESTAMP));
			payrollByRole.setPayrollGroup(payGroup);
			payrollByRole.setPayGroupDetail(details);

			return payrollByRole;
		}
	}

}
