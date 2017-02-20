package com.payroll.entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "PS_T2_PE_PYRL_E_VW")
@NamedNativeQueries({
    @NamedNativeQuery(
            name="getEmployeeChangedByBackend",
            query=" 	SELECT  AERN1.COMPANY,						"+
            		" 	                         AERN1.PAYGROUP,						"+
            		" 	                         AERN1.PAY_END_DT,						"+
            		" 	                         AERN1.OFF_CYCLE,						"+
            		//" 	                         AERN1.PAGE_NUM,						"+
            		//" 	                         AERN1.LINE_NUM,						"+
            		" 	                         HDR1.T2_HRP_PAYROLL_NUM,						"+
            		" 	                         DTL1.EMPLID,						"+
            		" 	                         DTL1.EMPL_RCD						"+
            		" 	           FROM PS_AUDIT_T2_PAYERN AERN1,						"+
            		" 	                PS_T2_PAYROLL_HDR HDR1,						"+
            		" 	                PS_T2_PAYROLL_DTL DTL1						"+
            		" 	          WHERE     HDR1.COMPANY = :company						"+
            		" 	                AND HDR1.PAYGROUP = :payGroup						"+
            		" 	                AND HDR1.PAY_END_DT = :payEndDate						"+
            		" 	                AND HDR1.OFF_CYCLE = :offCycle						"+
            		" 	                AND HDR1.T2_HRP_PAYROLL_NUM = :payrollNumber						"+
            		" 	                AND HDR1.COMPANY = DTL1.COMPANY						"+
            		" 	                AND HDR1.PAYGROUP = DTL1.PAYGROUP						"+
            		" 	                AND HDR1.PAY_END_DT = DTL1.PAY_END_DT						"+
            		" 	                AND HDR1.OFF_CYCLE = DTL1.OFF_CYCLE						"+
            		" 	                AND HDR1.T2_PAYROLL_NUM = DTL1.T2_PAYROLL_NUM						"+
            		" 	                AND HDR1.COMPANY = AERN1.COMPANY						"+
            		" 	                AND HDR1.PAYGROUP = AERN1.PAYGROUP						"+
            		" 	                AND HDR1.PAY_END_DT = AERN1.PAY_END_DT						"+
            		" 	                AND HDR1.OFF_CYCLE = AERN1.OFF_CYCLE						"+
            		" 	                AND DTL1.PAGE_NUM = AERN1.PAGE_NUM						"+
            		" 	                AND DTL1.LINE_NUM = AERN1.LINE_NUM						"+
            		" 	                AND AERN1.AUDIT_STAMP =						"+
            		" 	                       (SELECT MAX (AERN_ED1.AUDIT_STAMP)						"+
            		" 	                          FROM PS_AUDIT_T2_PAYERN AERN_ED1						"+
            		" 	                         WHERE     AERN1.COMPANY = AERN_ED1.COMPANY						"+
            		" 	                               AND AERN1.PAYGROUP = AERN_ED1.PAYGROUP						"+
            		" 	                               AND AERN1.PAY_END_DT = AERN_ED1.PAY_END_DT						"+
            		" 	                               AND AERN1.OFF_CYCLE = AERN_ED1.OFF_CYCLE						"+
            		" 	                               AND AERN1.PAGE_NUM = AERN_ED1.PAGE_NUM						"+
            		" 	                               AND AERN1.LINE_NUM = AERN_ED1.LINE_NUM)						"+
            		" 	         UNION ALL					"+
            		" 	         SELECT  AERN1.COMPANY,						"+
            		" 	                         AERN1.PAYGROUP,						"+
            		" 	                         AERN1.PAY_END_DT,						"+
            		" 	                         AERN1.OFF_CYCLE,						"+
            		//" 	                         AERN1.PAGE_NUM,						"+
            		//" 	                         AERN1.LINE_NUM,						"+
            		" 	                         HDR1.T2_HRP_PAYROLL_NUM,						"+
            		" 	                         DTL1.EMPLID,						"+
            		" 	                         DTL1.EMPL_RCD						"+
            		" 	           FROM PS_AUDIT_T2_PAYOE AERN1,						"+
            		" 	                PS_T2_PAYROLL_HDR HDR1,						"+
            		" 	                PS_T2_PAYROLL_DTL DTL1						"+
            		" 	          WHERE     HDR1.COMPANY = :company						"+
            		" 	                AND HDR1.PAYGROUP = :payGroup						"+
            		" 	                AND HDR1.PAY_END_DT = :payEndDate						"+
            		" 	                AND HDR1.OFF_CYCLE = :offCycle						"+
            		" 	                AND HDR1.T2_HRP_PAYROLL_NUM = :payrollNumber						"+
            		" 	                AND HDR1.COMPANY = DTL1.COMPANY						"+
            		" 	                AND HDR1.PAYGROUP = DTL1.PAYGROUP						"+
            		" 	                AND HDR1.PAY_END_DT = DTL1.PAY_END_DT						"+
            		" 	                AND HDR1.OFF_CYCLE = DTL1.OFF_CYCLE						"+
            		" 	                AND HDR1.T2_PAYROLL_NUM = DTL1.T2_PAYROLL_NUM						"+
            		" 	                AND HDR1.COMPANY = AERN1.COMPANY						"+
            		" 	                AND HDR1.PAYGROUP = AERN1.PAYGROUP						"+
            		" 	                AND HDR1.PAY_END_DT = AERN1.PAY_END_DT						"+
            		" 	                AND HDR1.OFF_CYCLE = AERN1.OFF_CYCLE						"+
            		" 	                AND DTL1.PAGE_NUM = AERN1.PAGE_NUM						"+
            		" 	                AND DTL1.LINE_NUM = AERN1.LINE_NUM              						"+
            		" 	                AND AERN1.AUDIT_STAMP =						"+
            		" 	                       (SELECT MAX (AERN_ED1.AUDIT_STAMP)						"+
            		" 	                          FROM PS_AUDIT_T2_PAYOE AERN_ED1						"+
            		" 	                         WHERE     AERN1.COMPANY = AERN_ED1.COMPANY						"+
            		" 	                               AND AERN1.PAYGROUP = AERN_ED1.PAYGROUP						"+
            		" 	                               AND AERN1.PAY_END_DT = AERN_ED1.PAY_END_DT						"+
            		" 	                               AND AERN1.OFF_CYCLE = AERN_ED1.OFF_CYCLE						"+
            		" 	                               AND AERN1.PAGE_NUM = AERN_ED1.PAGE_NUM						"+
            		" 	                               AND AERN1.LINE_NUM = AERN_ED1.LINE_NUM)						"+
            		" 	         UNION	ALL					"+
            		" 	         SELECT  AERN1.COMPANY,						"+
            		" 	                         AERN1.PAYGROUP,						"+
            		" 	                         AERN1.PAY_END_DT,						"+
            		" 	                         AERN1.OFF_CYCLE,						"+
            		//" 	                         AERN1.PAGE_NUM,						"+
            		//" 	                         AERN1.LINE_NUM,						"+
            		" 	                         HDR1.T2_HRP_PAYROLL_NUM,						"+
            		" 	                         DTL1.EMPLID,						"+
            		" 	                         DTL1.EMPL_RCD						"+
            		" 	           FROM PS_AUDIT_T2_PAY1T AERN1,						"+
            		" 	                PS_T2_PAYROLL_HDR HDR1,						"+
            		" 	                PS_T2_PAYROLL_DTL DTL1						"+
            		" 	          WHERE     HDR1.COMPANY = :company						"+
            		" 	                AND HDR1.PAYGROUP = :payGroup						"+
            		" 	                AND HDR1.PAY_END_DT = :payEndDate						"+
            		" 	                AND HDR1.OFF_CYCLE = :offCycle						"+
            		" 	                AND HDR1.T2_HRP_PAYROLL_NUM = :payrollNumber						"+
            		" 	                AND HDR1.COMPANY = DTL1.COMPANY						"+
            		" 	                AND HDR1.PAYGROUP = DTL1.PAYGROUP						"+
            		" 	                AND HDR1.PAY_END_DT = DTL1.PAY_END_DT						"+
            		" 	                AND HDR1.OFF_CYCLE = DTL1.OFF_CYCLE						"+
            		" 	                AND HDR1.T2_PAYROLL_NUM = DTL1.T2_PAYROLL_NUM						"+
            		" 	                AND HDR1.COMPANY = AERN1.COMPANY						"+
            		" 	                AND HDR1.PAYGROUP = AERN1.PAYGROUP						"+
            		" 	                AND HDR1.PAY_END_DT = AERN1.PAY_END_DT						"+
            		" 	                AND HDR1.OFF_CYCLE = AERN1.OFF_CYCLE						"+
            		" 	                AND DTL1.PAGE_NUM = AERN1.PAGE_NUM						"+
            		" 	                AND DTL1.LINE_NUM = AERN1.LINE_NUM						"+
            		" 	                AND AERN1.AUDIT_STAMP =						"+
            		" 	                       (SELECT MAX (AERN_ED1.AUDIT_STAMP)						"+
            		" 	                          FROM PS_AUDIT_T2_PAY1T AERN_ED1						"+
            		" 	                         WHERE     AERN1.COMPANY = AERN_ED1.COMPANY						"+
            		" 	                               AND AERN1.PAYGROUP = AERN_ED1.PAYGROUP						"+
            		" 	                               AND AERN1.PAY_END_DT = AERN_ED1.PAY_END_DT						"+
            		" 	                               AND AERN1.OFF_CYCLE = AERN_ED1.OFF_CYCLE						"+
            		" 	                               AND AERN1.PAGE_NUM = AERN_ED1.PAGE_NUM						"+
            		" 	                               AND AERN1.LINE_NUM = AERN_ED1.LINE_NUM)						"+
            		" 	         UNION ALL				"+
            		" 	         SELECT  AERN1.COMPANY,						"+
            		" 	                         AERN1.PAYGROUP,						"+
            		" 	                         AERN1.PAY_END_DT,						"+
            		" 	                         AERN1.OFF_CYCLE,						"+
            		//" 	                         AERN1.PAGE_NUM,						"+
            		//" 	                         AERN1.LINE_NUM,						"+
            		" 	                         HDR1.T2_HRP_PAYROLL_NUM,						"+
            		" 	                         DTL1.EMPLID,						"+
            		" 	                         DTL1.EMPL_RCD						"+
            		" 	           FROM PS_AUDIT_T2_PAYGOV AERN1,						"+
            		" 	                PS_T2_PAYROLL_HDR HDR1,						"+
            		" 	                PS_T2_PAYROLL_DTL DTL1						"+
            		" 	          WHERE     HDR1.COMPANY = :company						"+
            		" 	                AND HDR1.PAYGROUP = :payGroup						"+
            		" 	                AND HDR1.PAY_END_DT = :payEndDate						"+
            		" 	                AND HDR1.OFF_CYCLE = :offCycle						"+
            		" 	                AND HDR1.T2_HRP_PAYROLL_NUM = :payrollNumber						"+
            		" 	                AND HDR1.COMPANY = DTL1.COMPANY						"+
            		" 	                AND HDR1.PAYGROUP = DTL1.PAYGROUP						"+
            		" 	                AND HDR1.PAY_END_DT = DTL1.PAY_END_DT						"+
            		" 	               AND HDR1.OFF_CYCLE = DTL1.OFF_CYCLE						"+
            		" 	                AND HDR1.T2_PAYROLL_NUM = DTL1.T2_PAYROLL_NUM						"+
            		" 	                AND HDR1.COMPANY = AERN1.COMPANY						"+
            		" 	                AND HDR1.PAYGROUP = AERN1.PAYGROUP						"+
            		" 	                AND HDR1.PAY_END_DT = AERN1.PAY_END_DT						"+
            		" 	                AND HDR1.OFF_CYCLE = AERN1.OFF_CYCLE						"+
            		" 	                AND DTL1.PAGE_NUM = AERN1.PAGE_NUM						"+
            		" 	                AND DTL1.LINE_NUM = AERN1.LINE_NUM						"+
            		" 	                AND AERN1.AUDIT_STAMP =						"+
            		" 	                       (SELECT MAX (AERN_ED1.AUDIT_STAMP)						"+
            		" 	                          FROM PS_AUDIT_T2_PAYGOV AERN_ED1						"+
            		" 	                         WHERE     AERN1.COMPANY = AERN_ED1.COMPANY						"+
            		" 	                               AND AERN1.PAYGROUP = AERN_ED1.PAYGROUP						"+
            		" 	                               AND AERN1.PAY_END_DT = AERN_ED1.PAY_END_DT						"+
            		" 	                               AND AERN1.OFF_CYCLE = AERN_ED1.OFF_CYCLE						"+
            		" 	                               AND AERN1.PAGE_NUM = AERN_ED1.PAGE_NUM						"+
            		" 	                               AND AERN1.LINE_NUM = AERN_ED1.LINE_NUM)						"+
            		" 	         UNION	ALL					"+
            		" 	         SELECT  AERN1.COMPANY,						"+
            		" 	                         AERN1.PAYGROUP,						"+
            		" 	                         AERN1.PAY_END_DT,						"+
            		" 	                         AERN1.OFF_CYCLE,						"+
            		//" 	                         AERN1.PAGE_NUM,						"+
            		//" 	                         AERN1.LINE_NUM,						"+
            		" 	                         HDR1.T2_HRP_PAYROLL_NUM,						"+
            		" 	                         DTL1.EMPLID,						"+
            		" 	                         DTL1.EMPL_RCD						"+
            		" 	           FROM PS_AUDIT_T2_PAYTOV AERN1,						"+
            		" 	                PS_T2_PAYROLL_HDR HDR1,						"+
            		" 	                PS_T2_PAYROLL_DTL DTL1						"+
            		" 	          WHERE     HDR1.COMPANY = :company						"+
            		" 	                AND HDR1.PAYGROUP = :payGroup						"+
            		" 	                AND HDR1.PAY_END_DT = :payEndDate						"+
            		" 	                AND HDR1.OFF_CYCLE = :offCycle						"+
            		" 	                AND HDR1.T2_HRP_PAYROLL_NUM = :payrollNumber						"+
            		" 	                AND HDR1.COMPANY = DTL1.COMPANY						"+
            		" 	                AND HDR1.PAYGROUP = DTL1.PAYGROUP						"+
            		" 	                AND HDR1.PAY_END_DT = DTL1.PAY_END_DT						"+
            		" 	                AND HDR1.OFF_CYCLE = DTL1.OFF_CYCLE						"+
            		" 	                AND HDR1.T2_PAYROLL_NUM = DTL1.T2_PAYROLL_NUM						"+
            		" 	                AND HDR1.COMPANY = AERN1.COMPANY						"+
            		" 	                AND HDR1.PAYGROUP = AERN1.PAYGROUP						"+
            		" 	                AND HDR1.PAY_END_DT = AERN1.PAY_END_DT						"+
            		" 	                AND HDR1.OFF_CYCLE = AERN1.OFF_CYCLE						"+
            		" 	                AND DTL1.PAGE_NUM = AERN1.PAGE_NUM						"+
            		" 	                AND DTL1.LINE_NUM = AERN1.LINE_NUM						"+
            		" 	                AND AERN1.AUDIT_STAMP =						"+
            		" 	                       (SELECT MAX (AERN_ED1.AUDIT_STAMP)						"+
            		" 	                          FROM PS_AUDIT_T2_PAYTOV AERN_ED1						"+
            		" 	                         WHERE     AERN1.COMPANY = AERN_ED1.COMPANY						"+
            		" 	                               AND AERN1.PAYGROUP = AERN_ED1.PAYGROUP						"+
            		" 	                               AND AERN1.PAY_END_DT = AERN_ED1.PAY_END_DT						"+
            		" 	                               AND AERN1.OFF_CYCLE = AERN_ED1.OFF_CYCLE						"+
            		" 	                               AND AERN1.PAGE_NUM = AERN_ED1.PAGE_NUM						"+
            		" 	                               AND AERN1.LINE_NUM = AERN_ED1.LINE_NUM)						",
            resultClass = PayrollEmployeeRecordBackend.class
    )
})
public class PayrollEmployeeRecordBackend implements Serializable {

    private static final long serialVersionUID = 1L;

    /* Primary Key Definition Starts */
    @Id
    @Column(nullable = false, name = "COMPANY")
    private String company;

    @Id
    @Column(nullable = false, name = "PAYGROUP")
    private String payrollGroup;

    @Id
    @Column(nullable = false, name = "PAY_END_DT")
    @Temporal(TemporalType.DATE)
    private Date payEndDate;

    @Id
    @Column(nullable = false, name = "OFF_CYCLE")
    private String offCycle = "N";

    @Id
    @Column(nullable = false, name = "T2_HRP_PAYROLL_NUM")
    private Integer payrollNum;

    @Id
    @Column(nullable = false, name = "EMPLID")
    private String emplId = " ";

    @Id
    @Column(nullable = false, name = "EMPL_RCD")
    private Integer emplRecord;
 
    /* Generated Getter & Setter */
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPayrollGroup() {
        return payrollGroup;
    }

    public void setPayrollGroup(String payrollGroup) {
        this.payrollGroup = payrollGroup;
    }

    public Date getPayEndDate() {
        return payEndDate;
    }

    public void setPayEndDate(Date payEndDate) {
        this.payEndDate = payEndDate;
    }

    public String getOffCycle() {
        return offCycle;
    }

    public void setOffCycle(String offCycle) {
        this.offCycle = offCycle;
    }

    public Integer getPayrollNum() {
        return payrollNum;
    }

    public void setPayrollNum(Integer payrollNum) {
        this.payrollNum = payrollNum;
    }

    public String getEmplId() {
        return emplId;
    }

    public void setEmplId(String emplId) {
        this.emplId = emplId;
    }

    public Integer getEmplRecord() {
        return emplRecord;
    }

    public void setEmplRecord(Integer emplRecord) {
        this.emplRecord = emplRecord;
    }


}