package com.payroll.db;

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

import com.payroll.entity.DualCoverageElg;


public class GetDualCoverageElg extends StoredProcedure {
	private static final Logger log = LoggerFactory.getLogger(GetDualCoverageElg.class);
	
	private static final String SP_NAME = "SYSADM.T2_BEN_DUALCVG_ELIG";
	private static final String IN_EMPLID = "i_Emplid";
	
	private static final String OUT_EMPL_RCD = "o_EMPL_RCD";
	private static final String OUT_EFFDT = "o_EFFDT";
	private static final String OUT_COMPANY = "o_COMPANY";
	private static final String OUT_T2_PEO_ID = "o_T2_PEO_ID";
	private static final String OUT_COVERAGE_BEGIN_DT = "o_COVERAGE_BEGIN_DT";
	private static final String OUT_DUAL_CVG_ELIG = "o_DUAL_CVG_ELIG";
	private static final String OUT_ERRMSG = "o_errMsg";
	
	    
	public GetDualCoverageElg(DataSource dataSource) {
		super(dataSource, SP_NAME);
		
		this.declareParameter(new SqlParameter(IN_EMPLID, Types.VARCHAR));
		this.declareParameter(new SqlOutParameter(OUT_EMPL_RCD, Types.INTEGER));
		this.declareParameter(new SqlOutParameter(OUT_EFFDT, Types.DATE));
		this.declareParameter(new SqlOutParameter(OUT_COMPANY, Types.VARCHAR));
		this.declareParameter(new SqlOutParameter(OUT_T2_PEO_ID, Types.VARCHAR));
		this.declareParameter(new SqlOutParameter(OUT_COVERAGE_BEGIN_DT, Types.DATE));
		this.declareParameter(new SqlOutParameter(OUT_DUAL_CVG_ELIG, Types.VARCHAR));
		this.declareParameter(new SqlOutParameter(OUT_ERRMSG, Types.VARCHAR));
		
		
		this.compile();
	}
	
	public DualCoverageElg execute(String emplid) {
		if (StringUtils.isEmpty(emplid)) 
			throw new IllegalArgumentException("Emplid can't be empty or null");
		
		DualCoverageElg DualCvgElgSp = new DualCoverageElg(); 
		
		Map<String, Object> inputs = new HashMap<String, Object>();
		inputs.put(IN_EMPLID, emplid);
		log.debug("calling sp: {} with parameters: {}", SP_NAME, inputs);
		
		Map<String, Object> output = super.execute(inputs);
		DualCvgElgSp.setO_empl_rcd((Integer) output.get(OUT_EMPL_RCD));
		DualCvgElgSp.setO_effdt((Date)output.get(OUT_EFFDT));
		DualCvgElgSp.setO_company(String.valueOf(output.get(OUT_COMPANY)));
		DualCvgElgSp.setO_t2_peo_id(String.valueOf(output.get(OUT_T2_PEO_ID)));
		DualCvgElgSp.setO_coverage_begin_dt((Date)output.get(OUT_COVERAGE_BEGIN_DT));
		DualCvgElgSp.setO_dual_cvg_elig(String.valueOf(output.get(OUT_DUAL_CVG_ELIG)));
		DualCvgElgSp.setO_errmsg(String.valueOf(output.get(OUT_ERRMSG)));
		log.debug("calling sp: {} with outputs: {}", SP_NAME, output);
		
		return DualCvgElgSp;
	}
}
