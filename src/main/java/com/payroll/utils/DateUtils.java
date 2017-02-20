package com.payroll.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import com.payroll.exception.PtgPayrollParseException;


public class DateUtils {

  /**
   * 
   * Purpose: This method converts string value of date to yyyy-MM-dd format
   * 
   * 
   * @param  dateValue
   * @throws PTGParseException
   * @return date
   */
  public static Date convertStringToDate(String dateValue) throws PtgPayrollParseException{
    try{
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
      format.setLenient(true);
      Date date = format.parse(dateValue);
      return date;
    }catch(ParseException pe){
      throw new PtgPayrollParseException("ERR-MYCOMP-60004") ;
    }
  }
  /**
   * 
   * Purpose: This method converts string value of date to MM/dd/yyyy format
   * 
   * 
   * @param  dateValue
   * @throws PTGParseException
   * @return date
   */
  public static Date convertStringToDate(String dateValue,String formater) throws PtgPayrollParseException{
    try{
    	System.out.println("dateValue : "+dateValue+" : formater  : "+formater);
      SimpleDateFormat format = new SimpleDateFormat(formater);
      format.setLenient(true);
      Date date = format.parse(dateValue);
      return date;
    }catch(ParseException pe){
      throw new PtgPayrollParseException("ERR-MYCOMP-60004") ;
    }
  }
  

  /**
   * 
   * Purpose: This method converts string value of date to yyyy-MM-dd format
   * 
   * 
   * @param dateValue
   * @return date
   */
  public static String convertDateToString(Date dateValue) {
    String date = null;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    date = format.format(dateValue);
    return date;
  }

  /**
   * 
   * Purpose: This method gets Effective Time Stamp
   * 
   * 
   * @return date
   */
  public static Date getEffectiveTimeStamp() {
	return new Date();
  }
  
  /**
   * 
   * Purpose: This method gets Effective date
   * 
   * 
   * @return date
   */
  public static Date getEffectiveDate() {
    return getZeroTime(new Date());
  }
  
  /**
   * 
   * Purpose: This method gets end date
   * 
   * 
   * @return date
   */
  public static Date getEndDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2099, Calendar.DECEMBER, 31);
    return calendar.getTime();
  }
  
  /**
   * 
   * Purpose: This method gets previous date
   * 
   * 
   * @return date
   */
  public static Date getPreviousEndDate() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -1);
    return calendar.getTime();

  }
  
  /**
   * 
   * Purpose: This method is for comparing dates
   * 
   * 
   * @param date1
   * @param date2
   * @return date
   */
  public static int compareDates(Date date1, Date date2) {
    return getZeroTime(date1).compareTo(getZeroTime(date2));
  }
  
  /**
   * 
   * Purpose: This method is to get zero time
   * 
   * 
   * @return date
   */
  public static Date getZeroTime(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  /**
   * 
   * Purpose: This method is to get previous date
   * 
   * 
   * @return date
   */
  public static Date getPerviousDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int daysToDecrement = -1;
    cal.add(Calendar.DATE, daysToDecrement);
    date = cal.getTime();
    return date;
  }

  public static String getPeriod(Date date){
    Interval interval = new Interval(date.getTime(), DateUtils.getEffectiveDate().getTime());
    Period period = interval.toPeriod().normalizedStandard(PeriodType.yearMonthDay());
    PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendYears()
                .appendSuffix(" year", " years")
                .appendSeparator(", ")
                .appendMonths()
                .appendSuffix(" month", " months")
                .appendSeparator(", ")
                .appendDays()
                .appendSuffix(" day", " days")
                .toFormatter();
    return formatter.print(period);
  }
  public static Date convertDateToNewTimeZone(Date date, TimeZone toTimeZone) {

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      TimeZone fromTimeZone = calendar.getTimeZone();

      calendar.setTimeZone(fromTimeZone);
      calendar.add(Calendar.MILLISECOND, fromTimeZone.getRawOffset() * -1);
      if (fromTimeZone.inDaylightTime(calendar.getTime())) {
          calendar.add(Calendar.MILLISECOND, calendar.getTimeZone().getDSTSavings() * -1);
      }

      calendar.add(Calendar.MILLISECOND, toTimeZone.getRawOffset());
      if (toTimeZone.inDaylightTime(calendar.getTime())) {
          calendar.add(Calendar.MILLISECOND, toTimeZone.getDSTSavings());
      }
      return calendar.getTime();
  }
  
 public static Date reduceHoursAndMinutes(Date date, int hours, int minutes) {

  	Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, -hours);
		cal.add(Calendar.MINUTE, -minutes);
		
		return cal.getTime();
  }
}