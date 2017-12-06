package com.yidusoft.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 时间戳与时间格式互转
 * @author Administrator
 *
 */
public class TimeStampDate {


    /**
     * 时间戳转换成日期格式字符串
     * @param seconds 精确到秒的字符串
     * @param
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

    /**
     * String时间戳 转Date
     */
    public static Date strToDate(String strDate,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date= null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    /**
     * String 转Date
     */
    public static Date getDate(String strDate,String format) {
        String strDates = timeStamp2Date(strDate, format);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date= null;
        try {
            date = sdf.parse(strDates);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }

    public static Date getDate(Date date,String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date dates= null;
        try {
            String strDate=sdf.format(date);
            dates = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  dates;
    }

    /**
     * 日期格式字符串转换成时间戳
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 时间转字符串
     */
    public static  String dateToStr(Date date,String format){
        String datetime="";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
              datetime= sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return  datetime;
    }


    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }

    /**
     * 创建日历
     *
     */
    public static Calendar getCalendar(Date date,String format){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }
    /**
     *  Calendar转化Date
     *
     */
    public static Date calendarToDate(Calendar Calendar,String format){
        Date date=null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String dateStr = sdf.format(Calendar.getTime());
            date=sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /***
     * 两个时间相隔多少天
     * @param createDate
     * @param date
     */
     public static Integer timeBeApart(Date createDate,Date date){
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

         try {
             Date smdate = sdf.parse(sdf.format(createDate));
             Date bdate=sdf.parse(sdf.format(date));
             Calendar cal = Calendar.getInstance();
             cal.setTime(smdate);
             long time1 = cal.getTimeInMillis();
             cal.setTime(bdate);
             long time2 = cal.getTimeInMillis();
             long between_days=(time2-time1)/(1000*3600*24);
             Integer times=Integer.valueOf(String.valueOf(between_days));
             return  times;
         } catch (ParseException e) {
             e.printStackTrace();
         }
       return 0;

     }
    //  输出结果：
    //  timeStamp=1417792627  
    //  date=2014-12-05 23:17:07  
    //  1417792627  
    public static void main(String[] args) {
     /*   String timeStamp = "1503319104";
        System.out.println("timeStamp="+timeStamp);

        String date = timeStamp2Date(timeStamp, "yyyy-MM-dd HH:mm:ss");
        System.out.println("date="+date);

        String timeStamp2 = date2TimeStamp(date, "yyyy-MM-dd HH:mm:ss");
        System.out.println(timeStamp2);

         Date date1=getDate(date,"yyyy-MM-dd HH:mm:ss");
        System.out.println(date1);

        Calendar c = Calendar.getInstance();
        c.setTime(date1);
        c.add(Calendar.DAY_OF_MONTH,1);

        System.out.println(calendarToDate(c,"yyyy-MM-dd HH:mm:ss"));

        Calendar cc = Calendar.getInstance();
        cc.setTime(date1);
        cc.add(Calendar.DATE,1);//
        System.out.println("加一天"+calendarToDate(cc,"yyyy-MM-dd HH:mm:ss"));

         int i= timeBeApart(date1,date1);
         System.out.println(i);*/
        getDate("2017-12-11 00:00:00","yyyy-MM-dd HH:mm:ss");
        System.out.println(getDate("2017-12-11 00:00:00","yyyy-MM-dd HH:mm:ss"));
    }
}
