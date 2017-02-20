package com.payroll.entity.ps.NamedQueries;
public enum NewHireQueryEnum {

    /**
     * Returns Current Medical, Dental, Vision Benefits.
     *
     */
    NEW_HIRE_STG_SELECT {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.stg.select";
        }

    },

    NEW_HIRE_STG_INSERT {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.stg.insert";
        }

    },

    NEW_HIRE_STG_SEQ_SELECT {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.stg.select.seq";
        }
    },


    NEW_HIRE_STG_DELETE {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.stg.delete";
        }
    },

    NEW_HIRE_STG_SELECT_BY_EMPLOYEE {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.stg.select.by.company";
        }
    },

    NEW_HIRE_STG_UPDATE_BY_STG_ID {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.stg.update.by.stgid";
        }
    },

    NEW_HIRE_SELECT_TIPPED_STATUS {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.select.tipped.status";
        }
    },

    NEW_HIRE_SELECT_PERSONID_BY_EMPLID {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.select.personid.by.emplid";
        }
    },

    NEW_HIRE_VALIDATE_PAYGROUP_EFFDT {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.validate.paygroup.effdt";
        }
    },

    NEW_HIRE_VALIDATE_LOCATION_EFFDT {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.validate.location.effdt";
        }
    },

    NEW_HIRE_VALIDATE_DEPARTMENT_EFFDT {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.validate.department.effdt";
        }
    },

    NEW_HIRE_VALIDATE_COMPANY_VAR_HOUR {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.validate.company.variable.hour";
        }
    },

    NEW_HIRE_AUDIT_LOG_VALIDATE {

        @Override
        public String getKey() { return "SQL.NewHire.hrp.audit.validate"; }
    },

    NEW_HIRE_AUDIT_LOG_INSERT {

        @Override
        public String getKey() { return "SQL.NewHire.hrp.audit.insert"; }
    },

    NEW_HIRE_AUDIT_LOG_UPDATE {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.audit.update";
        }
    },

    NEW_HIRE_AUDIT_LOG_UPDATE_IB_REQUEST {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.audit.update.broker.transaction";
        }
    },

    NEW_HIRE_AUDIT_LOG_SELECT_BY_COMPANY {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.audit.select.by.company";
        }
    },

    NEW_HIRE_AUDIT_LOG_SELECT_BY_ROWNUM {
        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.audit.select.by.rownum";
        }
    },

    NEW_HIRE_AUDIT_LOG_SELECT_XML {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.audit.select.xml";
        }
    },

    NEW_HIRE_AUDIT_LOG_DELETE {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.audit.delete";
        }
    },

    NEW_HIRE_GLOBAL_COMPANY_DESCRIPTION {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.global.companies";
        }
    },

    NEW_HIRE_GLOBAL_INSERT {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.global.companies.insert";
        }
    },

    NEW_HIRE_GLOBAL_SELECT_EFFSEQ {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.global.companies.select.effseq";
        }
    },

    NEW_HIRE_GLOBAL_VALIDATE_EXISTENCE {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.global.companies.validate.existence";
        }
    },

    NEW_HIRE_GLOBAL_VALIDATE_DEPARTMENT_ID {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.global.companies.validate.department.id";
        }
    },

    NEW_HIRE_GLOBAL_VALIDATE_LOCATION_ID {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.global.companies.validate.location.id";
        }
    },

    NEW_HIRE_GLOBAL_SELECT_GHRIS_BY_COMPANY {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.global.companies.select.by.company";
        }
    },

    NEW_HIRE_GLOBAL_SELECT_USER_NAME {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.global.companies.select.user.name";
        }
    },

    NEW_HIRE_GLOBAL_SELECT_TOTAL_WORKFORCE {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.global.companies.select.company.total.workforce";
        }
    },

    NEW_HIRE_GLOBAL_SELECT_PF_CLIENT_BY_COMPANY{

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.global.companies.select.company.pf.client"; }
    },

    NEW_HIRE_GLOBAL_VALIDATE_USER_ROLES {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.global.companies.validate.user.roles";
        }
    },

    NEW_HIRE_VALIDATE_USER_ROLES {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.validate.user.roles";
        }
    },

    NEW_HIRE_SELECT_CANADIAN_LOCATION_STATE {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.select.canadian.location.state";
        }
    },

    NEW_HIRE_VALIDTAE_IF_AMBROSE_COMPANY {

        @Override
        public String getKey() {
            return "SQL.NewHire.hrp.newhire.validate.if.ambrose.company";
        }
    },

    ;

    /**
     *
     * @return The String representing the key value for this named query.
     */
    public abstract String getKey();


}
