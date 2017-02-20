package com.payroll.utils;
import java.util.Date;

import org.hibernate.Query;

import com.ptg.payroll.domain.payroll.PayGroup;



public class DaoUtil {

   public static Date getDefaultDate(Date date){
    	return date != null ? date : new Date();
    }
    /**
     * This code snippet is used in many places. Hence it made sense to make it create a method. But please don't change anything in here.
     * If you any variation of this method, then use the actual snippet in place and don't create a new method. <neeraj.sanodiya>
     * 
     * @param query
     * @param payGroup
     */
   public static void bindQueryWithPayGroup(Query query, PayGroup payGroup) {
        Date payEndDate = payGroup.getPayEndDate() != null ? payGroup.getPayEndDate() : new Date();

        query.setParameter("company", payGroup.getCompany());
        query.setParameter("payGroup", payGroup.getPayGroup());
        query.setParameter("payEndDate", payEndDate);
        query.setParameter("offCycle", payGroup.getOffCycle());
        query.setParameter("payrollNumber", payGroup.getPayrollNumber());
    }
    
   public static void bindQueryWithPayGroupForPreview(Query query, PayGroup payGroup) {
        Date payEndDate = payGroup.getPayEndDate() != null ? payGroup.getPayEndDate() : new Date();

        query.setParameter("company", payGroup.getCompany());
        query.setParameter("payGroup", payGroup.getPayGroup());
        query.setParameter("payEndDate", payEndDate);
        query.setParameter("offCycle", payGroup.getOffCycle());
        query.setParameter("payrollNumber", payGroup.getPreviewPayrollNumber());
    }

}