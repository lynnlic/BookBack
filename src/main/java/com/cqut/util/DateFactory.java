package com.cqut.util;


import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class DateFactory {
	
	/**
	 * 日期格式的转换
	 */
	private static SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static SimpleDateFormat format2 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat format3 = new SimpleDateFormat(
			"HH:mm:ss");
     
	static Calendar  calendar ;
	
	public static Date getDate(int year, int month, int date) {
		calendar = new GregorianCalendar(year, month - 1, date);
		return calendar.getTime();
	}
	
	public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
		calendar = new GregorianCalendar(year, month - 1, date, hourOfDay, minute, second);
		return calendar.getTime();
	}
	
	/** 
	 * 方法简述：得到当前年月
	 * @return String
	 * @author superc
	 * @date 2016-3-11 下午10:04:05
	 */
	public static String getYearAndMonth(){
		Calendar cal = Calendar.getInstance();
	    int month = cal.get(Calendar.MONTH) + 1;
	    int year = cal.get(Calendar.YEAR);
	    String yearAndMonth=String.valueOf(year)+"-";
	    if(month<10){
	    	yearAndMonth+="0"+String.valueOf(month);
	    }
		return yearAndMonth;
	}
	/** 
	 * 方法简述：得到前月年月
	 * @return String
	 * @author superc
	 * @date 2016-3-11 下午10:04:05
	 */
	public static String getLastYearAndMonth(){
		Calendar cal = Calendar.getInstance();
	    int month = cal.get(Calendar.MONTH);
	    int year = cal.get(Calendar.YEAR);
	    String yearAndMonth=String.valueOf(year)+"-";
	    if(month<10){
	    	yearAndMonth+="0"+String.valueOf(month);
	    }
	    
		return yearAndMonth;
	}
	
	/** 
	 * 方法简述：得到前月年月
	 * @return String
	 * @author superc
	 * @date 2016-3-11 下午10:04:05
	 */
	public static String getLastYearAndMonth(String dateTime){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 =  new SimpleDateFormat("yyyy-MM");
		dateTime = dateTime+"-01";
		String yearAndMonth = "";
		try {
			Date date = sdf1.parse(dateTime);
			cal.setTime(date);
			cal.add(Calendar.MONTH, -1);
			yearAndMonth = sdf2.format(cal.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return yearAndMonth;
	}
	
    /** 
     * 方法简述：sql Date 转换为Util Date类型
     * @param date
     * @return java.util.Date 
     * @author mengbing
     * @date 2016-3-11 下午10:10:31
     */
    public static java.util.Date getSqlDateToUtilDate(java.sql.Date date){
    	 
    	 java.util.Date sqlDate=new java.util.Date(date.getTime());
    	 
		return sqlDate;
    }
    /** 
     * 方法简述：til Date 转换为Usql Date类型
     * @param date
     * @return java.sql.Date 
     * @author mengbing
     * @date 2016-3-11 下午10:10:31
     */
    public static  java.sql.Date getUtilDateToSqlDate(java.util.Date date){
    	
    	 java.sql.Date sqlDate=new java.sql.Date(date.getTime());
    	 
		return sqlDate;
    }
    
    /** 
     * 方法简述：获取一个月的天数
     * @param date
     * @autho yangyanqiu 
     */
    public static int  getMonthForNum(String date) {
		
  	  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		  calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(5, 7)) -1);

		  int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		  
		  return maxDay;
	}

    /** 
     * 方法简述：获取一段时间中的所有日期
     * @param dBegin
     * @param dEnd
     * @autho yangyanqiu
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {  
        List lDate = new ArrayList();  
        lDate.add(dBegin);  
        Calendar calBegin = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calBegin.setTime(dBegin);  
        Calendar calEnd = Calendar.getInstance();  
        // 使用给定的 Date 设置此 Calendar 的时间    
        calEnd.setTime(dEnd);  
        // 测试此日期是否在指定日期之后    
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量    
            calBegin.add(Calendar.DAY_OF_MONTH, 1);  
            lDate.add(calBegin.getTime());  
        }  
        return lDate;  
    }  
    
		   /** 
		 * 方法简述：获取前一天的时间
		 * @param date
		 * @return 
		 */
		public static String getDateString(Date date){
			   //获取毫秒数
			   long dateMilliseconds = date.getTime();
			   //获取前一天的毫秒数
			   long datelastMilliseconds = dateMilliseconds-24*60*60*1000;
			   //转化为日期
			   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		       return sdf.format(datelastMilliseconds);
		   }
		
		public static String converToString(Date date){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return df.format(date);
		}
		
		public static String converToStringDate(Date date){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(date);
		}
		
		public static String getYearMonth(Date date){
			DateFormat df = new SimpleDateFormat("yyyy-MM");
			return df.format(date);
		}
		
		public static Date converToDate(String strDate){
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = null;
			try{
				date = df.parse(strDate);
				return date;
				
			}catch(Exception e){
				System.out.println("-----------将String转化为date时出错--错误信息为---"+e.getMessage());
			}
			return null;
		}
		/** 
		 * 方法简述：获取当前时间字符串
		 * @return 
		 * @author mengbing
		 * @throws ParseException 
		 * @date 2016-7-20 下午1:10:58
		 */
		
		public static String getTime(Time time){
			DateFormat sdf = new SimpleDateFormat("hh:mm:ss");
			return sdf.format(time);
		}
		
		//yyyy－mm－dd的Date
		public static Date getDateTime(Date date) throws ParseException{
			String dateString = formatDate(date);
			return parseDateTime(dateString);
		}
		
		/**
		 * 方法简述：将一般Date转化为yyyy－mm－dd hh：mm：ss的Date
		 * @author lxr
		 * @param date 
		 * @return 返回yyyy－mm－dd hh：mm：ss的Date（用于数据库是datetime的Date）
		 * @throws ParseException
		 */
		public static Date convertToDateTime(Date date){
			String dateString = formatDateTime(date);
			Date date2 = null;
			try {
				date2 =  parseDateTime(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return date2;
		}
		
		// yyyy-mm-dd
		public static String formatDate(Date date) {
			return format.format(date);
		}
		public static Date praseDate(String date) throws java.text.ParseException{
			return format.parse(date);
		}
		// yyyy-mm-dd hh:mm:ss
		public static String formatDateTime(Date date) {
			return format2.format(date);
		}
		public static Date parseDateTime(String dateTime) throws java.text.ParseException{
			return format2.parse(dateTime);
		}
		// hh:mm:ss
		public static String formatDateHMS(Date date){
			return format3.format(date);
		}
}
