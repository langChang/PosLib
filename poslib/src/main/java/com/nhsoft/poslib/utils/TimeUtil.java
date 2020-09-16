package com.nhsoft.poslib.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtil {
    public static final String LONG_DATE_FORMAT = "yyyyMMdd";
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TWO = "MM-dd HH:mm";
    public static final String FORMAT_THREE = "yyyy-MM-dd";
    public static final String FORMAT_FORE = "hhmmss";

    private static TimeUtil instance;

    public static TimeUtil getInstance() {
        if (instance == null) {
            instance = new TimeUtil();
        }
        return instance;
    }

    /**
     * @throws ParseException
     */

    public static long  getSubTime(String t1,String t2) {

        return  60 * 365 * 24 * 60;

//        try {
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date d1=sdf.parse(t1);
//            Date d2=sdf.parse(t2);
//            return daysBetween(d1,d2);
//        }catch (Exception e){
//            return 60 * 30 * 24 * 60;
//        }

//        System.out.println(daysBetween(d1,d2));

//        System.out.println(daysBetween("2012-09-08 10:10:10","2012-09-15 00:00:00"));

    }

    /**

     * 计算两个日期之间相差的天数

     * @param smdate 较小的时间

     * @param bdate 较大的时间

     * @return 相差天数

     * @throws ParseException

     */

    public static long daysBetween(Date smdate,Date bdate) throws ParseException

    {

        Calendar cal = Calendar.getInstance();

        cal.setTime(smdate);

        long time1 = cal.getTimeInMillis();

        cal.setTime(bdate);

        long time2 = cal.getTimeInMillis();

        long between_days=(time2-time1);

        return between_days/1000;

    }

    /**

     *字符串的日期格式的计算

     */

    public static int daysBetween(String smdate,String bdate) throws ParseException{

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();

        cal.setTime(sdf.parse(smdate));

        long time1 = cal.getTimeInMillis();

        cal.setTime(sdf.parse(bdate));

        long time2 = cal.getTimeInMillis();

        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));

    }




    public static String dealDate(String oldDateStr) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = df.parse(oldDateStr);
        SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        Date date1 = df1.parse(date.toString());
        date1.setTime(date1.getTime() + 1000*3600*8);
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df2.format(date1);
    }

    /*
     * 将时间转换为时间戳
     */
    public long dateToStamp(String s)  {
//        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
//        res = String.valueOf(ts);
        return ts;
    }


    public long dateToStamp2(String s)  {
//        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime();
//        res = String.valueOf(ts);
        return ts;
    }

    /**
     * n天后，取正值：
     * n天前，取负值：
     *
     * @param n
     * @return
     */
    public String getNDaysDate(String oldDate, int n) {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf2.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);
        String n_days_after = sdf2.format(calendar.getTime());
        return n_days_after;
    }

    public String getNDays(String oldDate, int n) {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf2.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);
        String n_days_after = sdf2.format(calendar.getTime());
        n_days_after=n_days_after.substring(5);
        n_days_after=n_days_after.replace("-","月");
        return n_days_after;
    }



    /*
     * 将时间戳转换为时间
     */
    public String stampToDate(long time) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(time);
        res = simpleDateFormat.format(date);
        return res;
    }


    /**
     * 获取时分秒
     * @param time
     * @return
     */
    public static String getTimeByhsm(long time) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_FORE);
        Date date = new Date(time);
        res = simpleDateFormat.format(date);
        return res;
    }

    public String getStringTime(String dataTime) {
        return dataTime.trim().replace("-", "").replace(":", "").replace(" ", "");
    }

    /**
     * 获取当前时间的指定格式
     *
     * @param format
     * @return
     */
    public String getCurrDateSelfType(String format) {
        return dateToString(new Date(), format);
    }


    public static String convertToNewFormat(String dateStr) throws ParseException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        sdf.setTimeZone(utc);
        Date convertedDate = sdf.parse(dateStr);
        long finalTime = convertedDate.getTime() + 8 * 60 * 60 * 1000;
        convertedDate.setTime(finalTime);
        return convertedDate.toString();
    }

    /**
     * 把日期转换为字符串
     *
     * @param date
     * @return
     */
    public String dateToString(Date date, String format) {
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            result = formater.format(date);
        } catch (Exception e) {
            // log.error(e);
        }
        return result;
    }

    public String dateToString(String strDate, String strFormat) {
        Date date1 = null;
        DateFormat df2 = null;
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = df.parse(strDate);
            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            date1 = df1.parse(date.toString());
            df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } catch (ParseException e) {

            e.printStackTrace();
        }

        return df2.format(date1);

    }

    //获取当前时间
    public Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        Date currentTime_2 = null;
        try {
            currentTime_2 = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return currentTime_2;
    }

    //获取当前时间
    public String getNowDateString() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    //获取当前时间
    public static String getNowDateString(String type) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        String dateString = formatter.format(currentTime);
        return dateString;
    }


    //获取当前周的第一天：
    public String getWeekStartDate(String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();
        return sdf.format(date);
    }

    //获取当前周最后一天：
    public String getWeekEndDate(String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Calendar cal = Calendar.getInstance();

        try {
            Date date = new Date();
            cal.setTime(date);
            cal.set(Calendar.DAY_OF_WEEK, 1);
            cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 7);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sdf.format(cal.getTime());
    }

    /**
     * 获取当前月第一天：
     *
     * @throws ParseException
     */
    public String getFirstOfMonth(String type) {

        SimpleDateFormat format = new SimpleDateFormat(type);
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(cale.getTime());
    }

    //获取当前月最后一天：
    public static String getCurrentMonthLastDay(String type) {
        SimpleDateFormat format = new SimpleDateFormat(type);
        Calendar cale = Calendar.getInstance();
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(cale.getTime());
    }


    //获取当前时间
    public String getLaterDate(String dateString, int delayDay) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_ONE);
        Date date = formatter.parse(dateString, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.DATE, delayDay);
        Date newDate = calendar.getTime();
        return formatter.format(newDate);
    }


    //获取昨天时间
    public String getYesterdayDate() {
        String str = "";
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);//把日期往前减少一天，若想把日期向后推一天则将负数改为正数
        date = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        str = formatter.format(date);
        return str;
    }

    /**
     * 今天23点59分59秒的毫秒数
     * @return
     */
    public long getToNet(){
        long current=System.currentTimeMillis();//当前时间毫秒数
        long zero=current/(1000*3600*24)*(1000*3600*24)- TimeZone.getDefault().getRawOffset();//今天零点零分零秒的毫秒数
        long twelve=zero+24*60*60*1000-1;//今天23点59分59秒的毫秒数
        return twelve;
    }


    public Date getDateByString(String dayString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            return formatter.parse(dayString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //是否为今天的日期
    public static boolean isContainTodayDate(String dayString) {
        String nowDateString = getNowDateString(FORMAT_THREE);
        if (dayString != null && dayString.contains(nowDateString.substring(nowDateString.length() - 2))) {
            return true;
        } else {
            return false;
        }
    }


    public Date getPriceDateByString(String dayString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_THREE);
            return formatter.parse(dayString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据日期string 类型 获取Date类型
     *
     * @param dayString
     * @return
     */
    public static Date getPolicMomotionDateString(String dayString) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            return formatter.parse(dayString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean isActionEffectiveDate(Date nowTime, String startTime, String endTime) {
        try {
            Date starDate = null;
            if (startTime.length() > 10) {
                startTime.substring(0, 11);
            }
            starDate = new SimpleDateFormat(FORMAT_THREE).parse(startTime);

            Date endDate = null;
            if (endTime.length() > 10) {
                endTime.substring(0, 11);
            }
            endDate = new SimpleDateFormat(FORMAT_THREE).parse(endTime);

            long nowTimeTime = nowTime.getTime() / 1000 * 1000;





            Calendar date = Calendar.getInstance();
            date.setTime(nowTime);

            Calendar begin = Calendar.getInstance();
            begin.setTime(starDate);

            Calendar end = Calendar.getInstance();
            end.setTime(endDate);

            if(begin.after(end)){
                return false;
            }

            if (nowTimeTime == starDate.getTime()
                    || nowTimeTime == endDate.getTime()) {
                return true;
            }

            if (date.after(begin) && date.before(end)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }

    }


    public static boolean isaboveNowDate(Date nowTime, String startTime) {
        try {
            Date starDate = new SimpleDateFormat(FORMAT_ONE).parse(startTime);
            if (nowTime.getTime() == starDate.getTime()) {
                return true;
            }

            Calendar date = Calendar.getInstance();
            date.setTime(nowTime);

            Calendar begin = Calendar.getInstance();
            begin.setTime(starDate);

            if (date.after(begin)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }

    }

    /**
     * 获取当天零点字符串时间
     * @return
     */
    public static String getTodayZeroString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zeroTime = calendar.getTime();

        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_ONE);
        return formatter.format(zeroTime);
    }

    /**
     * 获取当天零点字符串Date
     * @return
     */
    public static Date getTodayZeroDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date zeroTime = calendar.getTime();
        return zeroTime;
    }

    public static boolean isaboveLastDate(String lasttime, String endtime) {
        try {
            Date lastDate = new SimpleDateFormat(FORMAT_ONE).parse(lasttime);
            Date endDate = new SimpleDateFormat(FORMAT_ONE).parse(endtime);
            if (endDate.getTime() == endDate.getTime()) {
                return true;
            }

            Calendar date = Calendar.getInstance();
            date.setTime(lastDate);

            Calendar begin = Calendar.getInstance();
            begin.setTime(endDate);

            if (date.after(begin)) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            return false;
        }

    }

    public static Date initDateByDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    //输入日期，返回周几
    public static boolean judgeYear(int year) {
        if (year % 400 == 0)
            return true;
        else if ((year % 4 == 0) && (year % 100 != 0))
            return true;
        else
            return false;
    }

    public static String getWeekOfDate(int year, int month, int day) {
        int week = -1;
        int[] monthDay = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        boolean isLeapYear = false;
        int sumDay = 0;
        if (year >= 1900) {
            for (int i = 1900; i < year; i++) {
                if (judgeYear(i))
                    sumDay += 366;
                else
                    sumDay += 365;
            }
            for (int i = 0; i < month - 1; i++) {
                sumDay += monthDay[i];
            }
            if (month > 2) {
                isLeapYear = judgeYear(year);
                if (isLeapYear)
                    sumDay++;
            }
            sumDay += day;

            week = sumDay % 7;
        }
        return convert(week);
    }

    public static String convert(int week) {
        String[] weekName = { "周天", "周一", "周二", "周三", "周四", "周五", "周六" };
        return weekName[week];
    }


}
