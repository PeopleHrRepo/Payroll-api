package com.payroll.entity.ps.NamedQueries;

/**
 * Queries for TriNet.
 *
 * @author vjonnakuti
 *
 */
public enum QueryEnum {

	FSA_PLANS_BY_EMPLID_SCHEDID {
		@Override
		public String getKey() {
			return "SQL.CurrentFutureBenefits.FsaPlans.findByEmplidSchedId";
		}
	},

	SQL_FSA_OE_BASPARTIC_CURRENTPLANS_LIMITS {
		@Override
		public String getKey() {
			return "SQL.FSA.OE.BasPartic.CurrentPlans.limits";
		}
	},

	HSA_PLANS_BY_EMPLID_SCHEDID {
		@Override
		public String getKey() {
			return "SQL.CurrentFutureBenefits.HsaPlans.findByEmplidSchedId";
		}
	},

    /**
     * Returns Employee Name.
     *
     */
    EMPLOYEE_NAME_BY_EMPLID {
        @Override
        public String getKey() {
            return "SQL.CurrentBenefits.EmployeeName.findByEmplid";
        }
    },

    /**
     * Returns Current Medical, Dental, Vision Benefits.
     *
     */
    CURRENT_BENEFITS_HEALTHBENEFITS_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentBenefits.HealthBenefits.findByEmplidEmplRcd";
        }
    },

    /* Returns Future Medical, Dental, Vision Benefits.
    *
    */
    CURRENT_FUTURE_BENEFITS_HEALTHBENEFITS_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentFutureBenefits.HealthBenefits.findByEmplidEmplRcd";
        }
    },

   /**
    * Returns Current Simple Benefits Legal plans.
    *
    */
    CURRENT_SIMPLE_BENEFITS_LEGALPLANS_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentSimpleBenefits.LegalPlans.findByEmplidEmplRcd";
        }
    },

   /**
    * Returns Current Future Simple Benefits Legal plans.
    *
    */
    CURRENT_FUTURE_SIMPLE_BENEFITS_LEGALPLANS_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentFutureSimpleBenefits.LegalPlans.findByEmplidEmplRcd";
        }
    },

    /**
     * Returns Current Medical, Dental, Vision Benefits.
     *
     */
    CURRENT_BENEFITS_HEALTHBENEFITS_DEPENDENTS_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentBenefits.HealthBenefitsDependents.findByEmplidEmplRcd";
        }
    },

    /**
     * Returns Current Medical, Dental, Vision Benefits.
     *
     */
    CURRENT_FUTURE_BENEFITS_HEALTHBENEFITS_DEPENDENTS_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentFutureBenefits.HealthBenefitsDependents.findByEmplidEmplRcd";
        }
    },

    /**
     * Returns Current Supplemental Life, Life and AD and D, Dependent Life,
     * Supplemental AD and D, and Spouse/Partner Life Insurance Benefits.
     */
    CURRENT_BENEFITS_SUPPLIFE_LIFEADD_DEPLIFE_SUPPADD_SPOUSE_INSURANCE_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentBenefits.SuppLifeDepSuppADDSpouse.findByEmplidEmplRcd";
        }
    },

    /**
     * Returns Current Supplemental Life, Life and AD and D, Dependent Life,
     * Supplemental AD and D, and Spouse/Partner Life Insurance Benefits.
     */
    CURRENT_FUTURE_BENEFITS_SUPPLIFE_LIFEADD_DEPLIFE_SUPPADD_SPOUSE_INSURANCE_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentFutureBenefits.SuppLifeDepSuppADDSpouse.findByEmplidEmplRcd";
        }
    },

    /**
     *
     * This query searches for CURRENT BENEFITS for SUPPLIFE, LIFEADD, DEPLIFE, SUPPADD, SPOUSE PARTNER INSURANCE DEPENDENTS
     */
    CURRENT_BENEFITS_SUPPLIFE_LIFEADD_DEPLIFE_SUPPADD_SPOUSE_INSURANCE_DEPENDENTS_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentBenefits.SuppLifeDepSuppADDSpouseDependents.findByEmplidEmplRcd";
        }
    },

    /**
     * This query searches for CURRENT BENEFITS for SUPPLIFE, LIFEADD, DEPLIFE, SUPPADD, SPOUSE PARTNER INSURANCE DEPENDENTS
     */
    CURRENT_FUTURE_BENEFITS_SUPPLIFE_LIFEADD_DEPLIFE_SUPPADD_SPOUSE_INSURANCE_DEPENDENTS_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentFutureBenefits.SuppLifeDepSuppADDSpouseDependents.findByEmplidEmplRcd";
        }
    },

    CURRENT_BENEFITS_SHORTTERM_LONGTERM_DISABILITY_BY_EMPLID {
        @Override
        public String getKey() {
            return "SQL.CurrentBenefits.ShortTermLongTermDisability.findByEmplid";
        }
    },

    CURRENT_BENEFITS_CONFIRMATIONID_BY_EMPLID {
        @Override
        public String getKey() {
            return "SQL.CurrentFutureBenefits.confirmationId.findByEmplid";
        }
    },

    CURRENT_FUTURE_BENEFITS_SHORTTERM_LONGTERM_DISABILITY_BY_EMPLID {
        @Override
        public String getKey() {
            return "SQL.CurrentFutureBenefits.ShortTermLongTermDisability.findByEmplid";
        }
    },

    /**
     * This query searches for CURRENT 401K BENEFITS for SUPPLIFE, LIFEADD, DEPLIFE, SUPPADD, SPOUSE PARTNER INSURANCE DEPENDENTS
     *
     */
    CURRENT_BENEFITS_401K_ROTH_COMBINATION_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentBenefits.401KRothCombination.findByEmplidEmplRcd";
        }
    },

    /**
     * This query searches for Future 401K BENEFITS for SUPPLIFE, LIFEADD, DEPLIFE, SUPPADD, SPOUSE PARTNER INSURANCE DEPENDENTS
     *
     */
    CURRENT_FUTURE_BENEFITS_401K_ROTH_COMBINATION_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentFutureBenefits.401KRothCombination.findByEmplidEmplRcd";
        }
    },

    /**
     * This query returns the actuall Open EnrollmentDeadline without correnction
     *
     */
    CURRENT_FUTURE_BENEFITS_ENROLLMENTDEADLINE {
        @Override
        public String getKey() {
            return "SQL.CurrentFutureBenefits.enrollmentDeadline";
        }
    },

    /**
     * This query returns the employment type of employee - Part/Full time
     *
     */
    GET_EMPLOYMENT_TYPE {
        @Override
        public String getKey() {
            return "SQL.CurrentBenefits.employmentType";
        }
    },

    /**
     * This query searches for Emplid from pers_keys table using the personid
     *
     */
    NEWHIRE_HRP_EMPLID_BY_PERSONID {
        @Override
        public String getKey() {
            return "SQL.newhire.emplidFromPersonid";
        }
    },

    /**
     * This query fetch the Ambrose client referrer code
     *
     */
    GET_AMBROSE_CLIENT_REFERRER_CODE {
        @Override
        public String getKey() {
            return "SQL.OE.EmployeeReferrerCode.findByCompany";
        }
    },

    /**
     * This query searches for CURRENT 401K BENEFITS for SUPPLIFE, LIFEADD, DEPLIFE, SUPPADD, SPOUSE PARTNER INSURANCE DEPENDENTS
     *
     */
    CURRENT_BENEFITS_FSAHSA_BENEFITS_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentBenefits.fsaHsaBenefits.findByEmplidEmplRcd";
        }
    },

    /**
     * This query searches for FUTURE 401K BENEFITS for SUPPLIFE, LIFEADD, DEPLIFE, SUPPADD, SPOUSE PARTNER INSURANCE DEPENDENTS
     *
     */
    CURRENT_FUTURE_BENEFITS_FSAHSA_BENEFITS_BY_EMPLIDEMPLRCD {
        @Override
        public String getKey() {
            return "SQL.CurrentFutureBenefits.fsaHsaBenefits.findByEmplidEmplRcd";
        }
    },

    /**
     * This query searches for CURRENT 401K BENEFITS for SUPPLIFE, LIFEADD, DEPLIFE, SUPPADD, SPOUSE PARTNER INSURANCE DEPENDENTS
     *
     */
    CURRENT_BENEFITS_DEPENDENT_DETAILS_BY_EMPLID {
        @Override
        public String getKey() {
            return "SQL.CurrentBenefits.dependentDetails.findByEmplid";
        }
    },

    /**
     * This query searches for FUTURE 401K BENEFITS for SUPPLIFE, LIFEADD, DEPLIFE, SUPPADD, SPOUSE PARTNER INSURANCE DEPENDENTS
     *
     */
    CURRENT_FUTURE_BENEFITS_DEPENDENT_DETAILS_BY_EMPLID {
        @Override
        public String getKey() {
            return "SQL.CurrentFutureBenefits.dependentDetails.findByEmplid";
        }
    },

    /**
     * This query is used by the Enrollment Wizard for the Statement of Health.
     *
     * Reference the procedure check-cur-life-add-elections in the OEBEN.
     *
     */
    ENROLLMENT_WIZARD_STATEMENT_OF_HEALTH_CHECK_CURRENTLIFE_ADD_ELECTION {
        @Override
        public String getKey() {
            return "SQL.EnrollmentWizard.soh.currentLifeAddElection.findByEmplidBenefitrcdnbrPlantype";
        }
    },

    /**
     * This query is used by the Enrollment Wizard for the Statement of Health.
     *
     * Reference the procedure check-cur-disability-elections in the OEBEN.
     */
    ENROLLMENT_WIZARD_STATEMENT_OF_HEALTH_CHECK_CURRENT_DISABILITY_ELECTION {
        @Override
        public String getKey() {
            return "SQL.EnrollmentWizard.soh.currentDisabilityElection.findByEmplidEmplrcdPlantype";
        }
    },

    /**
     * This query is used by the Enrollment Wizard for the Statement of Health.
     *
     * Reference the procedure get-covrg-amt-reqst in the OEBEN.
     */
    ENROLLMENT_WIZARD_STATEMENT_OF_HEALTH_GET_COVERAGE_AMOUNT {
        @Override
        public String getKey() {
            return "SQL.EnrollmentWizard.soh.disaibilityCoverageAmount.findByPlantypeBenefitplanEffdt";
        }
    },

    /**
     * This query is used by the Enrollment Wizard for the Statement of Health.
     *
     * Reference the procedure get-disab-plan-waive-covrg-reqst in the OEBEN.
     */
    ENROLLMENT_WIZARD_STATEMENT_OF_HEALTH_GET_DISAIBILTY_PLAN_WAIVE_COVERAGE {
        @Override
        public String getKey() {
            return "SQL.EnrollmentWizard.soh.disabilityPlanWaiveCoverage.findByPlantypeBenefitplan";
        }
    },

    /**
     * This query is used by the Enrollment Wizard for the Statement of Health.
     *
     * Reference the procedure get_EE_current_ben_prog in the OEBEN.
     */
    ENROLLMENT_WIZARD_STATEMENT_OF_HEALTH_GET_EMPLOYEE_CURRENT_BENEFIT_PROGRAM {
        @Override
        public String getKey() {
            return "SQL.EnrollmentWizard.soh.disabilityCurrentBenProg.findByEmplidBenefitrcdnbr";
        }
    },

    /**
     * This query is used by the Enrollment Wizard for the Statement of Health.
     *
     * Reference the procedure get_prior_co_disbility_plans in the OEBEN.
     */
    ENROLLMENT_WIZARD_STATEMENT_OF_HEALTH_GET_PREVIOUS_DISAIBILITY_PLANS_WAIVE_COVERAGE {
        @Override
        public String getKey() {
            return "SQL.EnrollmentWizard.soh.disabilityPreviousPlanWaiveCoverage.findByBenefitprogramPlantype";
        }
    },

    /**
     * FSA/HSA Pay Period Cost Related SQL Queries
     */
    ENROLLMENT_WIZARD_FSA_HSA_PAYFREQUENCY {
        @Override
        public String getKey() {
            return "SQL.FsaHsa.FindPayFrequency";
        }
    },

    ENROLLMENT_WIZARD_FSA_HSA_REMAINING_PAY_PERIODS {
        @Override
        public String getKey() {
            return "SQL.FsaHsa.FindRemainingPeriods";
        }
    },

    ENROLLMENT_WIZARD_FSA_HSA_DEDUCTION_YTD {
        @Override
        public String getKey() {
            return "SQL.FsaHsa.FindDeductionYTD";
        }
    },

    NEWHIRE_HRP_PERSONID_NEXTVAL {
        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.PersonId.NextVal";
        }
    },

    NEWHIRE_HRP_UNIQGUEID_NEXTVAL {
        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.UniqueId.NextVal";
        }
    },

    NEWHIRE_HRP_NATIONAL_ID_NEXTVAL {
        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.NationalId.NextVal";
        }
    },
    
    NEWHIRE_DUPLICATE_NATIONAL_ID_CHECK {
        @Override
        public String getKey() {
            return "SQL.NewHire.Duplicate.NationalId.Check";
        }
    },

    NEWHIRE_HRP_AUDIT_KEY_NEXTVAL {
        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.AuditKey.NextVal";
        }
    },

    NEWHIRE_HRP_ACTION_ID_NEXTVAL {
        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.ActionId.NextVal";
        }
    },

    NEWHIRE_HRP_MESSAGE_ID_NEXTVAL {
        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.MessageId.NextVal";
        }
    },

    NEWHIRE_HRP_COMPANY_OLP_ID {
        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.CompanyOlpId";
        }
    },

    NEWHIRE_HRP_NOTIFICATION_ID_NEXTVAL {
        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.NotificationId.NextVal";
        }
    },

    NEWHIRE_HRP_TRANSACTION_ID_NEXTVAL {
        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.TransactionId.NextVal";
        }
    },

    NEWHIRE_COMPANY_LIVE_DATE {
        @Override
        public String getKey() {
            return "SQL.newhire.companyLiveDate";
        }
    },

    NEWHIRE_COMPANY_COUNTRY {
        @Override
        public String getKey() {
            return "SQL.newhire.companyCountry";
        }
    },

    NEWHIRE_USER_HR_ROLES {
        @Override
        public String getKey() {
            return "SQL.newhire.userHrRoles";
        }
    },

    NEWHIRE_HR_AUTH {
        @Override
        public String getKey() {
            return "SQL.newhire.hrauth";
        }
    },

    NEWHIRE_INITIATOR {
        @Override
        public String getKey() {
            return "SQL.newhire.initiator";
        }
    },

    NEWHIRE_HRP_DEFAULT_WC_CODE {
        @Override
        public String getKey() {
            return "SQL.newhire.hrp.default.wc.code";
        }
    },

    NEWHIRE_HRP_ORG_NOTIFICATIONS {
        @Override
        public String getKey() {
            return "SQL.newhire.hrp.org.notifications";
        }
    },

    NEWHIRE_HRP_NOTIFICATION_EMAILLIST {
        @Override
        public String getKey() {
            return "SQL.newhire.hrp.notification.emaillist";
        }
    },

    NEWHIRE_HRP_STATE_POLICY_NOTIFICATION_EMAILLIST {
        @Override
        public String getKey() {
            return "SQL.newhire.hrp.state.policy.notification.emaillist";
        }
    },

    NEWHIRE_COMPANY_STATUS {
        @Override
        public String getKey() {
            return "SQL.newhire.companyStatus";
        }
    },

    OE_PS_BCR_BATCHID_NEXTVAL {
        @Override
        public String getKey() {
            return "SQL.OE.PS.BcrBatchId.NextVal";
        }
    },

    NEWHIRE_HRP_WC_CODES {
        @Override
        public String getKey() {
            return "SQL.newhire.hrpWCCodes";
        }
    },

    NEWHIRE_HRP_WC_STATE {
        @Override
        public String getKey() {
            return "SQL.newhire.hrp.wc.state";
        }
    },

    NEWHIRE_PS_WC_CODE_DESC {
        @Override
        public String getKey() {
            return "SQL.newhire.ps.wc.descs";
        }
    },

    NEWHIRE_HRP_MESSAGE_SEQ {
        @Override
        public String getKey() {
            return "SQL.newhire.hrp.messageseq";
        }
    },

    NEWHIRE_PS_MESSAGE_SEQ_UPDATE {
        @Override
        public String getKey() {
            return "SQL.newhire.ps.messageseq.update";
        }
    },

    NEWHIRE_PS_COMPANY_EA_DATE {
        @Override
        public String getKey() {
            return "SQL.newhire.ps.company.ea.date";
        }
    },

    NEWHIRE_PS_COMPANY_LIVE_DATE {
        @Override
        public String getKey() {
            return "SQL.newhire.ps.company.live.date";
        }
    },

    NEWHIRE_OHIO_POLICIES {
        @Override
        public String getKey() {
            return "SQL.newhire.ohiopolicies";
        }
    },

    ENROLLMENT_HSA_LIMITS {
        @Override
        public String getKey() {
            return "SQL.enrollment.hsa.limits";
        }
    },

    ENROLLMENT_HSA_FRONTLOAD {
        @Override
        public String getKey() {
            return "SQL.enrollment.hsa.frontload";
        }
    },

    ENROLLMENT_HSA_COVERAGE_BEGIN_DATE {
        @Override
        public String getKey() {
            return "SQL.enrollment.hsa.coverage.begin.date";
        }
    },

    ENROLLMENT_HSA_EMPLOYEE_VALIDATE_SELECTED_DEPENDENTS {
        @Override
        public String getKey() {
            return "SQL.enrollment.hsa.validate.employee.selected.dependents";
        }
    },

    ENROLLMENT_PLAN_START_DATE {
        @Override
        public String getKey() {
            return "SQL.enrollment.hsa.validate.oe.plan.start.date";
        }
    },

    /**
     * Returns boolean value for hrp section.  This value will determine whether or not to render the section to the client user.
     *
     */
    COMPANY_POLICY_DISPLAY {
        @Override
        public String getKey() {
            return "SQL.company.hrp.policy.display";
        }
    },

    LKP_BATCH_INSERT {
        @Override
        public String getKey() {
            return "SQL.lkp.batch.insert";
        }
    },

    SQL_LSC_PSJOB_ELIGCONFIG1 {
        @Override
        public String getKey() {
            return "SQL.lsc.psjob.eligconfig1";
        }
    },

    /* Returns List of employees as per role and personId.
    *
    */
    GET_COMPANIES_BY_ROLE {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findCompanyByRole";
        }
    },

    GET_COMPANY_SORTAPPROVALCONFG {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findCompanySortApprovalConfig";
        }
    },

    GET_COMPANY_EFFECSORTAPPROVALCONFG {

        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findCompanyEffectiveSortApprovalConfig";
        }

    },

    GET_STATES_BY_COUNTRIES {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findStatesByCompanies";
        }
    },

    GET_PAYROLL_PREVIEW {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findPreviewData";
        }
    },

    GET_PAYROLL_PREVIEW_ADV_BY_EMPL {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findAdvDataForEmpl";
        }
    },

    GET_PAYROLL_PREVIEW_ADV_BY_EMPL_YTD {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findAdvDataForEmplYTD";
        }
    },

    GET_PAYROLL_PREVIEW_BY_EMPL {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findPreviewDataByEmpl";
        }
    },

    GET_PAYROLL_PREVIEW_WITH_EMPL {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findPreviewDataWithEmpl";
        }
    },

    GET_PAYROLL_PREVIEW_HEADER {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.summaryHeader";
        }
    },

    GET_PAYROLL_PREVIEW_EMPL_ADDR_AND_TAX {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findEmplAddressAndTax";
        }
    },

    GET_PAYROLL_PREVIEW_CHECK_ADVICE {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findCheckAdviceInfo";
        }
    },

    GET_PAYROLL_PREVIEW_EMPL_DEMO_AND_COMPANY {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findEmplDemoAndCompany";
        }
    },

    GET_PAYROLL_PREVIEW_HEADER_WITH_EMPL {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.summaryHeaderWithEmpl";
        }
    },

    GET_PAYROLL_PREVIEW_BY_DESCR_TYPE {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findPreviewDataByDescrForType";
        }
    },

    GET_PAYROLL_PREVIEW_HOURS {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findHours";
        }
    },

    GET_PAYROLL_PREVIEW_EMPLOYEES_HOURS {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findAllWithEmployeeHours";
        }
    },

    GET_PAYROLL_PREVIEW_YTD {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findPreviewYTD";
        }
    },

    GET_PAYROLL_PREVIEW_PAYMENT_OPTIONS {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findPaymentOptions";
        }
    },

    GET_PAYROLL_PREVIEW_PRO_FORMA {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findProForma";
        }
    },

    GET_PAYROLL_PREVIEW_INVOICE_TOTAL {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findInvoiceTotal";
        }
    },

    GET_PAYROLL_PREVIEW_STATUS {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.status";
        }
    },

    GET_PAYROLL_PREVIEW_RUN_DATETIME {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findRunDateTime";
        }
    },

    GET_PAYROLL_PREVIEW_HEADER_INFO {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findPayrollHeaderInfo";
        }
    },

    GET_PAYROLL_PREVIEW_YTD_CONTRIBUTIONS {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findYtdContributions";
        }
    },

    GET_PAYROLL_PREVIEW_DIRECT_DEPOSIT {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findDirectDepositInfoByEmpl";
        }
    },

    GET_PAYROLL_PREVIEW_LEAVE_YTD_BY_EMPL {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.findLeaveYTDByEmpl";
        }
    },

    DELETE_SPECIAL_PAYROLLGROUP_BY_PAYROLL_NUMBER {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.deletePayrollGroupByPayrollNumber";
        }
    },

    DELETE_SPECIAL_PAYROLLGROUP_ADDRESS_BY_PAYROLL_NUMBER {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.deletePayrollGroupAddressByPayrollNumber";
        }
    },

    GET_MASTER_EARNING_CODES_BY_COMPANY {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.getMasterEarningCodesByCompany";
        }
    },

    GET_ALL_EARNING_CODES_BY_COMPANY {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.getAllEarningCodesByCompany";
        }
    },

    GET_PAYROLL_DEADLINE {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.getPayrollDeadline";
        }
    },

    GET_CLIENT_OPTION {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.getClientOption";
        }
    },

    GET_CLIENT_LEAVE_ACCRUAL_STATUS {
        @Override
        public String getKey() {
            return "SQL.timesheetPayroll.isLeaveAccrualSystemClient";
        }
    },

   DELETE_PAYROLL_ENTRY_EMPLOYEE_PAYLINE_ITEMS{
        @Override
        public String getKey() {
            return "SQL.payroll.deletePayrollEmployeePayLineItems";
        }
    },

    DELETE_PAYROLL_EMPLOYEE_EARNING_RECORDS{
        @Override
        public String getKey() {
            return "SQL.payroll.deleteEmployeeEarningRecords";
        }
    },

    DELETE_PAYROLL_EMPLOYEE_CHECK_RECORDS{
        @Override
        public String getKey() {
            return "SQL.payroll.deleteEmployeeCheckRecords";
        }
    },

    DELETE_PAYROLL_EMPLOYEE_RECORDS{
        @Override
        public String getKey() {
            return "SQL.payroll.deleteEmployeeRecords";
        }
    },

    DELETE_PAYROLL_EMPLOYEE_LINE_ITEM{
        @Override
        public String getKey() {
            return "SQL.payroll.deleteEmployeeLineItem";
        }
    },

    GET_PAYROLL_ENTRY_VALIDATORS {
        @Override
        public String getKey() {
            return "SQL.PayrollEntry.getPayrollEntryValidators";
        }
    },

    EXPENSE_CLOUD_SELECT_MESSAGE_SEQ {
        @Override
        public String getKey() {
            return "SQL.expensecloud.message.select.seq";
        }
    },

    EXPENSE_CLOUD_INSERT_MESSAGE {
        @Override
        public String getKey() {
            return "SQL.expensecloud.message.insert";
        }
    },

    EXPENSE_CLOUD_INSERT_MESSAGE_DETAILS {
        @Override
        public String getKey() {
            return "SQL.expensecloud.message.details.insert";
        }
    },

    EXPENSE_CLOUD_UPDATE_MESSAGE_DETAILS_STATUS {
        @Override
        public String getKey() {
            return "SQL.expensecloud.message.details.status.update";
        }
    },

    EXPENSE_CLOUD_INSERT_REPORT_DETAILS {
        @Override
        public String getKey() {
            return "SQL.expensecloud.report.details.insert";
        }
    },

    EXPENSE_CLOUD_FIND_MESSAGE_STATUS {
        @Override
        public String getKey() {
            return "SQL.expensecloud.message.status";
        }
    },

    EXPENSE_CLOUD_FIND_REPORT_STATUS {
        @Override
        public String getKey() {
            return "SQL.expensecloud.report.status";
        }
    },

    EXPENSE_CLOUD_FIND_COMPANY_REPORT {
        @Override
        public String getKey() {
            return "SQL.expensecloud.company.report";
        }
    },

    EXPENSE_CLOUD_FIND_COMPANY_REPORT_BY_DEPTID {
        @Override
        public String getKey() {
            return "SQL.expensecloud.company.report.by.deptid";
        }
    },

    EXPENSE_CLOUD_FIND_COMPANY_REPORT_BY_LOCATION {
        @Override
        public String getKey() {
            return "SQL.expensecloud.company.report.by.location";
        }
    },

    EXPENSE_CLOUD_FIND_COMPANY_EXPENSE_DETAILS {
        @Override
        public String getKey() {
            return "SQL.expensecloud.company.expense.detail";
        }
    },

    EXPENSE_CLOUD_FIND_EARNINGS_CODES {
        @Override
        public String getKey() {
            return "SQL.expensecloud.earnings.codes";
        }
    },

    EXPENSE_CLOUD_EMPLOYEE_HOURS_INSERT {
        @Override
        public String getKey() {
            return "SQL.expensecloud.employee.hours.insert";
        }
    },

    EXPENSE_CLOUD_FIND_EMPLOYEE_DETAILS {
        @Override
        public String getKey() {
            return "SQL.expensecloud.employee.details";
        }
    },

    EXPENSE_CLOUD_EMPLOYEE_HOURS_DELETE {
        @Override
        public String getKey() {
            return "SQL.expensecloud.employee.hours.delete";
        }
    },

    EXPENSE_CLOUD_EMPLOYEE_SEPCHK_DELETE {
        @Override
        public String getKey() {
            return "SQL.expensecloud.employee.sepchk.delete";
        }
    },

    EXPENSE_CLOUD_EMPLOYEE_SEPCHK_EARNCODE_DELETE {
        @Override
        public String getKey() {
            return "SQL.expensecloud.employee.sepchk.earncode.delete";
        }
    },

    EXPENSE_CLOUD_EMPLOYEE_VALIDATE_PAY_PERIOD {
        @Override
        public String getKey() {
            return "SQL.expensecloud.employee.validate.pay.period";
        }
    },

    EXPENSE_CLOUD_VALIDATE_EXPENSE_ON_PAYSHEET {
        @Override
        public String getKey() {
            return "SQL.expensecloud.validate.expense.on.paysheet";
        }
    },

    EXPENSE_CLOUD_SELECT_EXPENSE_CODE_DESCR {
        @Override
        public String getKey() {
            return "SQL.expensecloud.earnings.select.code.desc";
        }
    },

    EXPENSE_CLOUD_SELECT_PAYSHEET_EXPENSE_AMOUNT {
        @Override
        public String getKey() {
            return "SQL.expensecloud.select.paysheet.expense.amount";
        }
    },

    EXPENSE_CLOUD_UPDATE_PAYSHEET_EXPENSE {
        @Override
        public String getKey() {
            return "SQL.expensecloud.validate.expense.update.paysheet.expense";
        }
    },

    EXPENSE_CLOUD_VALIDATE_IF_COMPANY_EXPENSE_CODE_EXISTS {
        @Override
        public String getKey() {
            return "SQL.expensecloud.validate.company.expense.code.exists";
        }
    },

    EXPENSE_CLOUD_EMPLOYEE_VALIDATE_LEAVE_OF_ABSENCE {
        @Override
        public String getKey() {
            return "SQL.trinet.employee.validate.leave.of.absence";
        }
    },

    TRINET_EMPLOYEE_COMPANIES {
        @Override
        public String getKey() {
            return "SQL.trinet.employee.companies";
        }
    },

    EXPENSE_CLOUD_VALIDATE_EMPLOYEE_PAYROLL_ROLE {
        @Override
        public String getKey() {
            return "SQL.expensecloud.validate.employee.payroll.role";
        }
    },

    EXPENSE_CLOUD_TOKEN_VALIDATE {
        @Override
        public String getKey() {
            return "SQL.expensecloud.token.validate";
        }
    },

    BENEFIT_ENROLLMENT_FIND_UI_DEPENDENTS {
        @Override
        public String getKey() {
            return "SQL.benefit.enrollment.find.ui.dependents";
        }
    },

    BENEFIT_ENROLLMENT_FIND_EE_HASDEPENDENTS {
		@Override
		public String getKey() {
			return "SQL.benefit.enrollment.find.ee.hasDependent";
		}
	},

    FIND_EMPLOYEE_STATUS_BY_EMPLID {
        @Override
        public String getKey() {
            return "SQL.find.employee.status.by.emplid";
        }
    },

    FIND_PREVIOUS_COMPANY_BY_EMPLID {
        @Override
        public String getKey() {
            return "SQL.FIND_PREVIOUS_COMPANY_BY_EMPLID";
        }
    },

    GET_PRODUCT_CODE_WAITING_TIME_BY_COMPANY {
        @Override
        public String getKey() {
            return "SQL.GET_PRODUCT_CODE_WAITING_TIME_BY_COMPANY";
        }
    },

    UPDATE_BASPARTIC_COST_FOR_DUAL_COVERAGE {
        @Override
        public String getKey() {
            return "SQL.OE.DualCoverage.BasPartic.Cost.update";
        }
    },

    UPDATE_BASPARTIC_OPTN_FOR_DUAL_COVERAGE {
        @Override
        public String getKey() {
            return "SQL.OE.DualCoverage.BasPartic.Optn.update";
        }
    },

    UPDATE_SOH_TMP_FOR_DUAL_COVERAGE {
        @Override
        public String getKey() {
            return "SQL.OE.DualCoverage.Soh.Tmp.update";
        }
    },

    EMPLID_ROLE_BDM {

        @Override
        public String getKey() {
            return "SQL.emplid.role.bdm";
        }
    },

    LSC_REQUESTS_LOG_INSERT {
        @Override
		public String getKey() {
            return "SQL.lsc.requests.log.insert";
        }
    },

    LSC_RECENT_EVENTS {
        @Override
		public String getKey() {
            return "SQL.lsc.recent.events";
        }
    },

    COMPANY_HRAUTH_ROLE_STATUS {
        @Override
        public String getKey() {
            return "SQL.company.hrauth.role.status";
        }
    },

    TA_STATUS {
        @Override
        public String getKey() {
            return "SQL.ta.role.status";
        }
    },

    CLIENT_LEAVE_PLANS {
        @Override
        public String getKey() {
            return "SQL.client.leave.plans";
        }
    },

    INSERT_INTO_CUSTOMCHANGE{
		@Override
		public String getKey() { return "SQL.custom.change.insert";
		}
    },

    IS_ACCORD_COMPANY {
        @Override
        public String getKey() { return "SQL.OE.check.if.accord.company"; }
    },

    IS_AMBROSE_COMPAMY {
        @Override
        public String getKey() { return "SQL.OE.check.if.ambrose.company";}
    };

    /**
     *
     * @return The String representing the key value for this named query.
     */
    public abstract String getKey();
}
