package com.yarm.task.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/7
 * Time:11:26
 * Des:
 */
public class DateUtils {
    /**
     * 格式化为字符串YYYYMM
     * @return
     */
    public static String formatStrYD(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            return dateFormat.format(dateFormat.parse(date));
        } catch (Exception e) {
            // ignore
        }
        return date;
    }

    /**
     * 格式化为字符串yyyy-MM-dd
     * @return
     */
    public static String formatStrYMD(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.format(dateFormat.parse(date));
        } catch (Exception e) {
            // ignore
        }
        return date;
    }

    /**
     * 格式化为字符串yyyy.MM.dd
     * @return
     */
    public static String formatStrYMDPoint(String date){
        if(StringUtils.isBlank(date)) return "";

        SimpleDateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        try {
            return dateFormat.format(dateFormatDate.parse(date));
        } catch (Exception e) {
            // ignore
        }
        return date;
    }

    /**
     * 格式化为日期yyyy-MM
     * @return
     */
    public static Date formatDateYM(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        try {
            String format = dateFormat.format(dateFormat.parse(date));
            return dateFormat.parse(format);
        } catch (Exception e) {
            // ignore
        }
        return null;
    }

    /**
     * 格式化为日期yyyy-MM-dd
     * @return
     */
    public static Date formatDateYMD(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String format = dateFormat.format(dateFormat.parse(date));
            return dateFormat.parse(format);
        } catch (Exception e) {
            // ignore
        }
        return null;
    }

    /**
     * 获取前一天
     * @param today
     * @return
     */
    public static String getYesTodayYMD(String today){
        if(StringUtils.isBlank(today)) return today;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = formatDateYMD(today);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.DATE, -1);
        return dateFormat.format(calendar.getTime());
    }

    public static Timestamp toTimestampYMDHMS(String str){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (StringUtils.isBlank(str)) return timestamp;
        return Timestamp.valueOf(str);
    }
}