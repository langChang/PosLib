package com.nhsoft.poslib.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by LZ on 2019/2/26.
 */

public class LogToFileUtils {
    private static final int              SDCARD_LOG_FILE_SAVE_DAYS = 10;
    /**
     * 上下文对象
     */
    private static       Context          mContext;
    /**
     * FileLogUtils类的实例
     */
    private static       LogToFileUtils   instance;
    /**
     * 用于保存日志的文件
     */
    private static       File             logFile;
    /**
     * 日志中的时间显示格式
     */
    private static       SimpleDateFormat logSDF                    = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * 日志的最大占用空间 - 单位：字节
     * <p>
     * 注意：为了性能，没有每次写入日志时判断，故日志在写入第二次初始化之前，不会受此变量限制，所以，请注意日志工具类的初始化时间
     * <p>
     * 为了衔接上文，日志超出设定大小后不会被直接删除，而是存储一个副本，所以实际占用空间是两份日志大小
     * <p>
     * 除了第一次超出大小后存为副本外，第二次及以后再次超出大小，则会覆盖副本文件，所以日志文件最多也只有两份
     * <p>
     * 默认10M
     */
    private static final int              LOG_MAX_SIZE              =  100 * 1024 * 1024;
    /**
     * 以调用者的类名作为TAG
     */
    private static       String           tag;

    private static final String           MY_TAG     = "LogToFileUtils";
    private static       SimpleDateFormat logfile    = new SimpleDateFormat("yyyyMMdd");
    private static       String           pathLogcat = null;
    private static       int              order      = 0;


    /**
     * 初始化日志库
     *
     * @param context
     */
    public static void init(Context context) {
        Log.i(MY_TAG, "init ...");
        if (null == mContext || null == instance || null == logFile || !logFile.exists()) {
            mContext = context;
            instance = new LogToFileUtils();
            logFile = getLogFile();
            Log.i(MY_TAG, "LogFilePath is: " + logFile.getPath());
            // 获取当前日志文件大小
            long logFileSize = getFileSize(logFile);
            Log.d(MY_TAG, "Log max size is: " + Formatter.formatFileSize(context, LOG_MAX_SIZE));
            Log.i(MY_TAG, "log now size is: " + Formatter.formatFileSize(context, logFileSize));
            // 若日志文件超出了预设大小，则重置日志文件
            if (LOG_MAX_SIZE < logFileSize) {
                resetLogFile();
            }
        } else {
            Log.i(MY_TAG, "LogToFileUtils has been init ...");
            if(!getFileName().equals(logFile.getName().substring(0,8))){
                logFile = getLogFile();
            }
        }
    }

    /**
     * 写入日志文件的数据
     *
     * @param str 需要写入的数据
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void write(Object str) {

        if(null == mContext || null == instance || null == logFile || !logFile.exists()){
            init(mContext);
        }

        long logFileSize = getFileSize(logFile);
        // 若日志文件超出了预设大小，则重置日志文件
        if (LOG_MAX_SIZE < logFileSize) {
            resetLogFile();
        }
        if (str==null){
            str ="";
        }
        String logStr = getFunctionInfo() + " - " + str.toString();
        Log.i(tag, logStr);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(logFile, true));
            bw.write(logStr);
            bw.write("\r\n\n");
            bw.flush();
        } catch (Exception e) {
            Log.e(tag, "Write failure !!! " + e.toString());
        }

}


    /**
     * 重置日志文件
     * <p>
     * 若日志文件超过一定大小，则把日志改名为lastLog.txt，然后新日志继续写入日志文件
     * <p>
     * 每次仅保存一个上一份日志，日志文件最多有两份
     * <p/>
     */
    private static void resetLogFile() {
        Log.i(MY_TAG, "Reset Log File ... ");
        // 创建lastLog.txt，若存在则删除
        File lastLogFile = new File(logFile.getParent() + "/"+getFileName()+"_1.txt");
        if (lastLogFile.exists()) {
            lastLogFile.delete();
        }
        // 将日志文件重命名为 lastLog.txt
        logFile.renameTo(lastLogFile);
        // 新建日志文件
        try {
            logFile.createNewFile();
        } catch (Exception e) {
            Log.e(MY_TAG, "Create log file failure !!! " + e.toString());
        }
    }

    /**
     * 获取文件大小
     *
     * @param file 文件
     * @return
     */
    private static long getFileSize(File file) {
        long size = 0;
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                size = fis.available();
            } catch (Exception e) {
                Log.e(MY_TAG, e.toString());
            }
        }
        return size;
    }

    /**
     * 获取APP日志文件
     *
     * @return APP日志文件
     */
    public static File getLogFile() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            pathLogcat = mContext.getExternalFilesDir("NHLog").getPath() + "/";
        } else {
            pathLogcat = mContext.getFilesDir().getPath() + "/NHLog/";
        }
        File file = new File(pathLogcat);
        if (!file.exists()) {
            file.mkdirs();
        } else {
            delFile(order);
        }
        File logFile = new File(file.getPath() + "/" + getFileName() + ".txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (Exception e) {
                Log.e(MY_TAG, "Create log file failure !!! " + e.toString());
            }
        }
        return logFile;
    }


    /**
     *     * 获取两个日期之间的间隔天数
     *     * @return
     *     
     */


    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);

    }


    /**
     * 获取当前函数的信息
     *
     * @return 当前函数的信息
     */
    private static String getFunctionInfo() {
        StackTraceElement[] sts = Thread.currentThread().getStackTrace();
        if (sts == null) {
            return null;
        }
        for (StackTraceElement st : sts) {
            if (st.isNativeMethod()) {
                continue;
            }
            if (st.getClassName().equals(Thread.class.getName())) {
                continue;
            }
            if (st.getClassName().equals(instance.getClass().getName())) {
                continue;
            }
            tag = st.getFileName();
            return "[" + logSDF.format(new Date()) + " " + st.getClassName() + " " + st
                    .getMethodName() + " Line:" + st.getLineNumber() + "]";
        }
        return null;
    }

    /**
     * 两个时间相差多少天
     */
    public long dateDiff(String startTime, String endTime, String format) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime()
                    - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min
                    + "分钟" + sec + "秒。");
            if (day >= 1) {
                return day;
            } else {
                if (day == 0) {
                    return 1;
                } else {
                    return 0;
                }

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;

    }


    public static void delFile(int order) {
        try {
            String needDelFiel = logfile.format(getDateBefore(order));
            needDelFiel = needDelFiel.substring(0, 8);
            int needDelTime = Integer.parseInt(needDelFiel);

            File dirFile = new File(pathLogcat);
            if (dirFile.exists() || dirFile.isDirectory()) {
                File[] files = dirFile.listFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        String fileName = files[i] + "";
                        fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
                        fileName = fileName.substring(0, 8);
                        int filetime = Integer.parseInt(fileName);
                        if (filetime <= needDelTime) {
                            files[i].delete();
                        }
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();

        }
    }


    private static Date getDateBefore(int order) {
        Date nowtime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowtime);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - SDCARD_LOG_FILE_SAVE_DAYS + order);
        order++;
        if (order == SDCARD_LOG_FILE_SAVE_DAYS) {
            order = 0;
        }
        return now.getTime();
    }


    //起止时间
    public static String getoldTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = format.format(new Date(System.currentTimeMillis()));
        return date;
    }

    //结束时间
    public static Date currentTime() {
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        Date enddata = new Date(System.currentTimeMillis());
        return enddata;
    }

    public static String getDateEN() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = format1.format(new Date(System.currentTimeMillis()));
        return date1;
    }


    public static String getFileName() {
        String date = logfile.format(new Date(System.currentTimeMillis()));
        return date;
    }

}
