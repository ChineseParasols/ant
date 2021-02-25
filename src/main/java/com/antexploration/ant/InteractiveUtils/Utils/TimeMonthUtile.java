package com.antexploration.ant.InteractiveUtils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 时间处理工具类
 */
public class TimeMonthUtile {

    /**
     * 获取本月的第一天
     *
     * @return
     */
    public static String MonthOne() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal_1 = Calendar.getInstance();
        cal_1.set(Calendar.DAY_OF_MONTH, 1);
        String MonthOne = sdf.format(cal_1.getTime());
        return MonthOne;
    }

    /**
     * 获取今日日志
     *
     * @return
     */
    public static String ToDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        return today;
    }

    /**
     * 计算两个日期相差多少天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(String date1, String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse1 = sdf.parse(date1);
        Date parse2 = sdf.parse(date2);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(parse1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(parse2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        int number = 0;
        if (year1 != year2) {//不同一年

            int timeDistance = 0;
            if (year1 < year2) {
                for (int i = year1; i < year2; i++) {
                    timeDistance += IfYear(year1);
                }
                number = timeDistance + (day2 - day1);
                number = number - (number * 2);
            } else {
                for (int i = year2; i < year1; i++) {
                    timeDistance += IfYear(year2);
                }
                number = timeDistance + (day1 - day2);
            }
            return number;
        } else {//同一年
            return number = (day2 - day1) - (day2 - day1) * 2;
        }
    }

    /**
     * 判断次年份是否为 闰年
     *
     * @param year 年份
     * @return
     */
    public static int IfYear(int year) {
        int timeDistance = 0;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {//闰年
            timeDistance += 366;
        } else {//不是闰年
            timeDistance += 365;
        }
        return timeDistance;
    }

    /**
     * 时间后移
     *
     * @param date 时间
     * @return
     */
    public static String TheDayMoveback (String date,int number) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 将日期向后延长一天
        Date dateTime = sdf.parse(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);
        calendar.add(calendar.DATE, number);// 把日期往后增加一天.整数往后推,负数往前移动
        dateTime = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        String endTime = sdf.format(dateTime);
        return endTime;
    }

    /**
     *  时间前移
     *
     * @param date 时间
     * @return
     */
    public static String TheDayMoveForward (String date,int number) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 将日期向后延长一天
        Date dateTime = sdf.parse(date);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateTime);
        calendar.add(calendar.DATE, -number);// 把日期往后增加一天.整数往后推,负数往前移动
        dateTime = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        String endTime = sdf.format(dateTime);
        return endTime;
    }



    public static void main(String[] args) throws ParseException {
        int i = differentDays("2020-12-10", "2020-12-01");
        System.out.println(i);

        String s = TheDayMoveback("2020-12-10", 2);
        System.out.println(s);

        String s1 = TheDayMoveForward("2020-12-10", 2);
        System.out.println(s1);

    }
}
