package com.ptg.payroll.db.sp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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

import com.payroll.entity.TimesheetPayrollGroup;
import com.ptg.payroll.db.sp.payroll.payrollentry.PayGroupByCompanyPersonSpService;

import oracle.jdbc.OracleTypes;


	public class PayGroupByCompanyPerson extends StoredProcedure  implements
	PayGroupByCompanyPersonSpService{

		private static final Logger log = LoggerFactory
				.getLogger(PayGroupByCompanyPerson.class);

		private static final String SP_NAME = "TRINET_PAYROLL_ENTRY.spc_GetPayGrpForListView";
		private static final String IN_COMPANYID = "i_Companyid";
		private static final String IN_PERSONID = "i_PersonId";
		private static final String INOUT_GETCOMPANYPAYROLLS = "o_GetCompanyPayrolls";
		private static final String OUT_ERRMSG = "o_ErrMsg";

		public PayGroupByCompanyPerson(DataSource dataSource) {
			super(dataSource, SP_NAME);

			declareParameter(new SqlParameter(IN_COMPANYID, Types.VARCHAR));
			declareParameter(new SqlParameter(IN_PERSONID, Types.VARCHAR));
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
		public List<TimesheetPayrollGroup> execute(String company, String personId) {

			if (StringUtils.isEmpty(company)) {
				throw new IllegalArgumentException("Company can't be empty or null");
			}

			Map<String, Object> inputs = new HashMap<String, Object>();
			inputs.put(IN_COMPANYID, company);
			inputs.put(IN_PERSONID, personId);
			log.debug("calling sp: {} with parameters: {}", SP_NAME, inputs);

			Map<String, Object> output = super.execute(inputs);

			return (List<TimesheetPayrollGroup>) output.get(INOUT_GETCOMPANYPAYROLLS);
		}

		private class PayrollListRowMapper implements RowMapper<TimesheetPayrollGroup> {

			public TimesheetPayrollGroup mapRow(ResultSet r, int i)
					throws SQLException {
					TimesheetPayrollGroup payroll = new TimesheetPayrollGroup();
					payroll.setCompany(r.getString("Company"));
					payroll.setPayrollNumber(r.getInt("T2_HRP_PAYROLL_NUM"));
					payroll.setLocation(r.getString("LOCATION"));
					payroll.setDeptId(r.getString("DEPTID"));
					payroll.setPayGroup(r.getString("PAYGROUP"));
					payroll.setPayGroupDesc(r.getString("DESCR"));
					payroll.setOffCycle(r.getString("OFF_CYCLE"));
					payroll.setPayBeginDt(r.getDate("PAY_BEGIN_DT"));
					payroll.setPayEndDt(r.getDate("PAY_END_DT"));
					payroll.setCheckDt(r.getDate("CHECK_DT"));
					payroll.setPayFrequency(r.getString("PAY_FREQUENCY"));
					payroll.setPayrollName(r.getString("T2_PE_PAYROLL_NAME"));
					payroll.setStatus(r.getString("T2_PE_STATUS"));
					payroll.setReportDate(r.getDate("T2_PP_REPORT_DT"));
					payroll.setOcReasonCd(r.getString("T2_OC_REASON_CD"));
					payroll.setPayrollEntryOption(r.getString("T2_PE_ENTRY_OPTN"));
					payroll.setOprid(r.getString("OPRID"));
					payroll.setOprName(r.getString("OPR_NAME"));
					payroll.setTimeStamp(r.getTime("TIME_STAMP"));
					//certified payroll flag PAYR 7024
					payroll.setCertifiedPayroll(r.getString("T2_PE_CP_FLAG"));
				return payroll;
			}
		}


	}

