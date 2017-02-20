package com.ptg.payroll.domain.payroll;
public class CheckDetail {
    public static String PAYMENT_OPTION_CHECK = "C";
    public static String PAYMENT_OPTION_DIRECT = "D";

    private int checkNumber; // Check Sequence Number
    private String grossUp = "N"; // Gross up flag
    private String paymentOption; // Is it Check or Advice
    private String excludeFrom401K = "N"; // Exclude From 401K flag


    /* IDE generated getter setter */
    public int getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(int checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getGrossUp() {
        return grossUp;
    }

    public void setGrossUp(String grossUp) {
        this.grossUp = grossUp;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getExcludeFrom401K() {
        return excludeFrom401K;
    }

    public void setExcludeFrom401K(String excludeFrom401K) {
        this.excludeFrom401K = excludeFrom401K;
    }

}
