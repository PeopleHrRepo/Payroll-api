package com.payroll.constant;
public class PtgGatewayConstants {

	/**
	 * token sent back in header of each message
	 */
	public static final String USER_TOKEN = "userToken";

	/**
	 * Company id to use for processing this request
	 */
	public static final String COMPANYID = "companyId";
	/**
	 * login request name
	 */
	public static final String LOGIN_REQUEST_NAME = "signon";
	/**
	 * validate seekersession
	 */
	public static final String SEEKER_SESSION_VALIDATE_REQUEST_NAME = "hrpsessionsignon";


	/**
	 * logout request name
	 */
	public static final String LOGOUT_REQUEST_NAME = "signoff";
	/**
	 * Used for testing
	 * No Auth required
	 */
	public static final String ECHO_REQUEST_NAME = "echo";
	/**
	 * For schema doc
	 * No Auth required
	 */
	public static final String API_DOC_SITE = "schema";

	/**
	 * tag name for Gateway session that is stored in
	 * message context
	 */
	public static final String GATEWAY_SESSION = "gatewaySession";

	/**
	 * Message name currently being processed by the messageContext
	 * The name is stored in messageContext property with the name
	 */
	public static final String CURRENT_MESSAGE_NAME = "payloadName";

	/**
	 * Server header element name
	 */
	public static final String SERVER_HEADER_ROOT_ELEMENT = "header";

	/**
	 * Type of message contained in this Gateway Exchange
	 */
	public static final String EXCHANGE_TYPE_JSON = "JSON";
	public static final String EXCHANGE_TYPE_XML = "XML";

	/**
	 * default main schema for api
	 */
	public static final String DEFAULT_MAIN_NAMESPACE = "http://www.trinet.com/TrinetAPI/schemas";

	/**
	 * Authentication tag is gateway session, if value is tru the user auth else false
	 */
	public static final String USER_AUTH_TAG = "GATEWAY_USER_AUTH";

	/**
	 * uri template to get the request name
	 * restful -> is the servlet name that processes the restful messages
	 *
	 */
	//public static final String RESTFUL_URI_TEMPLATE = "/**/restful/{requestName}/**";
	public static final String RESTFUL_URI_TEMPLATE = "/**/api/*/{requestName}/**";

	public static final String RESTFUL_REQUEST_METHOD ="RESTFUL_REQUEST_METHOD";
	public static final String REQUEST_QUERY_STRING ="requestQueryString";
	public static final String REQUEST_QUERY_MAP ="requestQueryMap";
	public static final String REQUEST_CONTENT_TYPE ="type";
	public static final String MESSAGE_PROCESSINFO = "MESSAGE_PROCESS_INFO";
	public static final String MESSAGE_PROCESS_STATUS_PROCESSING = "Processing";
	public static final String MESSAGE_PROCESS_STATUS_SUCCESS = "SUCCESS";
	public static final String MESSAGE_PROCESS_STATUS_ERRORED = "ERRORED";
	public static final String CURRENT_MESSAGE_PROCESS_INFO ="currentMessageProcessInfo";
	public static final String MESSSAGE_PROCESS_START = "Start";
	public static final String MESSSAGE_PROCESS_END = "End";

	/**
	 * Praveen T
	 * bluecrush constants starts
	 */
	public static final String HIRE_EVENT = "HIR";
	public static final String ELIGIBLE_EVENT = "ELG";
	public static final String RE_HIRE_EVENT = "REH";
	public static final String BIRTH_EVENT = "BIR";
	public static final String MARRIAGE_EVENT = "MAR";
	public static final String GAIN_OF_COVERAGE_EVENT = "GOC";
	public static final String LOSS_OF_COVERAGE_EVENT = "LOC";
	public static final String DIVORCE_EVENT = "DIV";
	public static final String OPEN_ENROLLMENT_EVENT = "OE";
    public static final String LIFE_STATUS_CHANGE = "LC";
	public static final String BENEFIT_PLAN_GUARANTEED = "000EWA";
	public static final String BENEFIT_PLAN_GUARANTEED_Q1 = "000N5J";
	public static final String BENEFIT_PLAN_GUARANTEED_Q2 = "000OP0";
	public static final String BENEFIT_PLAN_GUARANTEED_Q3 = "000Q60";
    public static final String Q1 = "Q1";
    public static final String Q2 = "Q2";
    public static final String Q3 = "Q3";
    public static final String Q4 = "Q4";
    public static final String MEDCOM = "MEDCOM";
    public static final String NEWHIRE = "NEWHIRE";
    public static final Integer MAXIMUM_COVERAGE_AMOUNT_FOR_LIFE = 300000;
    public static final Integer MAXIMUM_COVERAGE_AMOUNT_FOR_SPOUSE_LIFE = 30000;

    public static final String WAIVE_OPTION_CD = "W";
    public static final String GRAND_PARENT_OPTION = "SLG";
    public static final String DELETE_OPTION_CD = "DEL";
    public static final String BASIC_LIFE_PLAN_TYPE = "23";
    public static final String SUPP_LIFE_ADD_PLAN_TYPE = "27";
    public static final String SUPP_TERM_LIFE_PLAN_TYPE = "21";
    public static final String LIFE_SPOUSE_PLAN_TYPE = "2Y";
    public static final String LIFE_CHILD_PLAN_TYPE = "25";
    public static final String LTD_PLAN_TYPE = "31";
    public static final String STD_PLAN_TYPE = "30";

    /**
     * Health Plan names
     */
    public static final String MEDICAL_PLAN = "10";
    public static final String MEDICAL_DP_PLAN = "15";
    public static final String VISION_PLAN = "14";
    public static final String VISION_DP_PLAN = "17";
    public static final String VISION_OPT_PLAN = "1V";
    public static final String VISION_OPT_DP_PLAN = "1U";
    public static final String DENTAL_PLAN = "11";
    public static final String DENTAL_DP_PLAN = "16";
    public static final String DENTAL_OPT_PLAN = "1D";
    public static final String DENTAL_OPT_DP_PLAN = "1E";

    /**
     * Timesheet Payroll Constants
     */
    public static final String ON_CYCLE = "N";
    public static final String RESULTS_KEY = "results";
    public static final String ON_CYCLE_PAYROLL_GROUPS_KEY = "onCyclePayrollGroups";
    public static final String OFF_CYCLE_PAYROLL_GROUPS_KEY = "offCyclePayrollGroups";

    //PS_t2_Pe_process column names
    public static final String CHECK_DT = "check_dt";
    public static final String COMPANY = "company";
    public static final String PAYGROUP = "paygroup";
    public static final String PAYROLLNAME = "t2_pe_payroll_name";
    public static final String PAYGROUPDESC = "DESCR";
    public static final String PAYBEGINDT = "PAY_BEGIN_DT";
    public static final String PAYENDDT = "PAY_END_DT";
    public static final String OFFCYCLE = "OFF_CYCLE";
    public static final String OFF_CYCLE_FLAG = "Y";
    public static final String PAYROLLNUMBER = "T2_HRP_PAYROLL_NUM";
    public static final String LOCATION = "LOCATION";
    public static final String DEPTID = "DEPTID";
    public static final String PAYFREQUENCY = "PAY_FREQUENCY";
    public static final String STATUS = "T2_PE_STATUS";
    public static final String STATUSDESC = "T2_PE_STAT_DESCR";
    public static final String TIMESTAMP = "TIME_STAMP";
    public static final String OPRNAME = "OPR_NAME";
    public static final String OCREASONCD = "T2_OC_REASON_CD";
    public static final String REPORTDATE = "T2_PP_REPORT_DT";
    public static final String OPRID = "OPRID";
    public static final String PAYROLL_ENTRY_OPTN = "T2_PE_ENTRY_OPTN";
    public static final String PAYROLL_RUN_ID = "T2_RUN_ID";
    public static final String PAYROLL_ERROR_MSG = "MESSAGE_TEXT";
    public static final String BLACKOUT_FLAG = "T2_PE_BLACKOUT_FLG";
    public static final String PAYMENT_METHOD = "T2_PE_PYMT_METHOD";
    public static final String DEFAULT_REG_EARN_CODE = "ERNCD_REG_HRS";


    public static final String PE_PROCESS_SUBMITTED = "S";
    public static final String PE_PROCESS_APPROVED = "A";
    public static final String PE_PROCESS_PRELIMINARY_APPROVAL = "L";
    public static final String PE_PROCESS_READY_FOR_PREVIEW = "R";
    public static final String PE_PROCESS_FINALIZED = "F";
    public static final String PE_PROCESS_INITIATED = "I";
    public static final String PE_PROCESS_NEW 		= "N";
    public static final String PE_PROCESS_TOUCHLESS_SUBMIT = "T";
    public static final String PE_PROCESS_RESUBMITTED = "B";
    public static final String PE_PROCESS_MODIFIED = "M";
    public static final String PE_PROCESS_ERROR = "E";
    public static final String PE_PROCESS_ERROR_TEXT = "Error";

    public static final String PAYSHEET_STEP = "PAYSHEET";
    public static final String PAYIMP_STEP = "PAYIMP";
	public static final String PAYCONF_STEP = "PAYCONF";
	public static final String PROCESS_TYPE = "FROMGRID";

	public static final String PAYROLL_EXCEPTIONS = "payrollExceptions";

	public static final String ERN_CD_AMOUNTS_FLG = "A";
	public static final String ERN_CD_BOTH_FLG = "B";
	public static final String ERN_CD_EITHER_FLG = "E";
	public static final String ERN_CD_FLAT_AMOUNT_FLG = "F";
	public static final String ERN_CD_HOURS_FLG = "H";

	public static final String ERN_CD_REG = "REG";
	public static final String ERN_CD_01 = "01";

    public static final String TAB_DELIMITTER = "9";
    public static final String PAYROLL_IMPORT_PROFILE_DEFAULT_LAST_USED_DATE = "01/01/1970";

    public static final String LEAVE_COMPANY = "COMPANY";
    public static final String LEAVE_EMPLID = "EMPLID";
    public static final String LEAVE_EMPLRCD = "EMPL_RCD";
    public static final String LEAVE_ERNCD = "ERNCD";
    public static final String LEAVE_BAL_HOURS = "LEAVE_HOURS_BALANCE";

    public static final String PE_RECURRING_NEW_STATUS = "R";
    public static final String PE_RECURRING_MODIFIED_STATUS = "M";
    public static final String PE_CHANGE_STATUS = "C";
    public static final String PE_NEW_STATUS = "N";
    public static final String PE_DELETE_STATUS = "D";

    public static final String PE_PTO_SOURCE = "PTO";

    public static final String PE_CLIENT_TIMEZONE = "CLIENT_TIMEZONE";

    public static final String STAGE_TERMINATED_EMPLOYEE = "TER";
    public static final String STAGE_SPECIAL_PAYROLL_EMPLOYEE = "SPCL";


    /*
     * Ambrose constants
     */
    public static final String  AMBROSE_QUARTER = Q4;

    public static final String  AMBROSE_ELIGCONFIG1 = "B001";

	/* Columns for dashboard list view by role */
    public static final String IS_PARENT = "T2_PE_TYPE";
    public static final String PARENT_PAYGROUP = "1";
    public static final String CHILD_PAYGROUP = "2";
    public static final String PAYGROUP_DESCR = "PAYGROUP_DESCR";
    public static final String SEQ_NBR = "SEQ_NBR";
    public static final String LOCATION_DESCR = "T2_PE_LOC_DESCR";
    public static final String DEPT_DESCR = "T2_PE_DEPT_DESCR";
    public static final String PARENT_STATUS = "PARENT_STATUS";
    public static final String PARENT_STATUSDESCR = "PARENT_STAT_DESCR";
    public static final String CHILD_STATUS = "CHILD_STATUS";
    public static final String CHILD_STATUSDESCR = "CHILD_STAT_DESCR";
    public static final String PARENT_OPRNAME = "PARENT_OPR_NAME";
    public static final String PARENT_OPRID = "PARENT_OPRID";
    public static final String CHILD_OPRNAME = "CHILD_OPR_NAME";
    public static final String CHILD_OPRID = "CHILD_OPRID";
    public static final String PARENT_TIMESTAMP = "PARENT_TIME_STAMP";
    public static final String CHILD_TIMESTAMP = "CHILD_TIMESTAMP";
    public static final String PAYGROUP_FREQ_DESC = "DESCR50";

	public static final String AMBROSE_REFERRER_CODE = "AMB";

	public static final Integer MAXIMUM_COVERAGE_AMOUNT_FOR_AMB_SPOUSE_LIFE = 25000;

    public static final String CERTIFIED_PAYROLL_FLAG = "T2_PE_CP_FLAG";
    public static final String JOB_COSTING_FLAG = "T2_PE_JC_FLAG";

    /* TriNet provider id for CAN */
    public static final String TRINET_CANADA_PROVIDER_ID = "002";
    public static final String CANADA_CHECK_TEXT = "Cheque";

    /* Upload file validation message */
    public static final String PAYROLL_TERM_EMPLOYEE_180_DAY_VAL_MSG = " Employee's termination date is > 180 days from today.";

    /* Special payroll setup table message type */
    public static final String SEPARATE_CHECK_TYPE = "XSC";
    public static final String SPECIAL_PAYROLL_TYPE = "XSP";

    public static final String DEPT_LOCATION_ALL_ACCESSCODE = "***";
}
