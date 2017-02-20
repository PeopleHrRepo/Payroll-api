package com.payroll.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "PS_JOB")
@NamedNativeQueries({
   @NamedNativeQuery(
           name="getPayrollEmployeeRecordsJobCodeViewForEmployee",
           query =  "SELECT AA.JOBCODE "
          		 +" FROM PS_JOB AA, PS_T2_CLIENTOPTION C "
        		 +" WHERE C.COMPANY = :company "
            		+ " AND AA.EMPLID = :emplid ",
           resultClass = PayrollEmployeeJobCodeRecord.class
		   )
})
public class PayrollEmployeeJobCodeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(nullable = false, name = "JOBCODE")
    private String jobCode = " ";

    
    /* Generated Getter & Setter */
    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }
}