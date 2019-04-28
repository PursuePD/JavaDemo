package com.example.springtest.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
    static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    public static final String SQL_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PROTOCOL_TIME = "yyyyMMddHHmmss";
    private static final int HOUR_OF_DAY_START = 0;
    private static final int HOUR_OF_DAY_END = 23;

    public DateUtils() {
    }

    public static long timeStrToLong(String time, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        try {
            return simpleDateFormat.parse(time).getTime();
        } catch (ParseException var4) {
            return 0L;
        }
    }

    public static boolean isTimeSequenceLegal(String beginTime, String endTime, String format) {
        long bt = timeStrToLong(beginTime, format);
        long et = timeStrToLong(endTime, format);
        return et >= bt;
    }

    public static boolean isTimeSequenceLegal(Date beginTime, Date endTime) {
        return endTime.getTime() >= beginTime.getTime();
    }

    public static Date getStartTime(Date date, Integer type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch(type) {
            case 1:
                calendar.set(2, 0);
                calendar.set(5, 1);
                calendar.set(11, 0);
                break;
            case 2:
                calendar.set(5, 1);
                calendar.set(11, 0);
            case 3:
            case 4:
            default:
                break;
            case 5:
                calendar.set(11, 0);
        }

        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getEndTime(Date date, Integer type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch(type) {
            case 1:
                calendar.set(2, calendar.getActualMaximum(2));
                calendar.set(5, calendar.getActualMaximum(5) - 1);
                calendar.set(11, 23);
                break;
            case 2:
                calendar.set(5, calendar.getActualMaximum(5) - 1);
                calendar.set(11, 23);
                break;
            case 3:
            case 4:
            default:
                return null;
            case 5:
                calendar.set(11, 23);
        }

        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime();
    }

    public static Date getStartTimeByAdd(Date date, Integer type) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch(type) {
            case 1:
                calendar.add(1, -1);
                calendar.set(11, 0);
                break;
            case 2:
                calendar.add(2, -1);
                calendar.set(11, 0);
                break;
            case 3:
                calendar.add(3, -1);
                calendar.set(11, 0);
            case 4:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            default:
                break;
            case 5:
                calendar.add(5, -1);
                calendar.set(11, 0);
                break;
            case 11:
                calendar.set(11, 0);
        }

        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date getNumMouth(int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(2, -num);
        Date m = c.getTime();
        return m;
    }

    public static Date getNumDay(int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(5, -day);
        Date m = c.getTime();
        return m;
    }

    public static Date getCurrentWeekStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(1), cal.get(2), cal.get(5), 0, 0, 0);
        cal.set(7, 2);
        return cal.getTime();
    }

    public static Date getCurrentWeekEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentWeekStartTime());
        cal.add(7, 7);
        return cal.getTime();
    }



    public static boolean isFormatDate(String date, String regEx) {
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(date);
        return m.find();
    }



    public static String formatTime(Long ms) {
        Integer ss = 1000;
        Integer mi = ss * 60;
        Integer hh = mi * 60;
        Integer dd = hh * 24;
        Long day = ms / (long)dd;
        Long hour = (ms - day * (long)dd) / (long)hh;
        Long minute = (ms - day * (long)dd - hour * (long)hh) / (long)mi;
        Long second = (ms - day * (long)dd - hour * (long)hh - minute * (long)mi) / (long)ss;
        StringBuffer sb = new StringBuffer();
        if (day > 0L) {
            sb.append(day + "天");
        }

        if (hour > 0L) {
            sb.append(hour + "小时");
        }

        if (minute > 0L) {
            sb.append(minute + "分");
        }

        if (second > 0L) {
            sb.append(second + "秒");
        }

        return sb.toString();
    }

    public static String dateToStr(Date date, DateUtils.DateFormat format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format.val);
        return sdf.format(date);
    }

    public static String dateToStr(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static final Date addHours(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(10, hours);
        return calendar.getTime();
    }

    public static final Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, days);
        return calendar.getTime();
    }

    public static final Date addMonths(Date date, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, months);
        return calendar.getTime();
    }

    public static final Date addYears(Date date, int years) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(1, years);
        return calendar.getTime();
    }

    public static int getDaysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtils.DateFormat.CALENDAR.val);
        smdate = dateFormat.parse(dateFormat.format(smdate));
        bdate = dateFormat.parse(dateFormat.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / 86400000L;
        return Integer.parseInt(String.valueOf(betweenDays));
    }

    public static int getMonthsBetween(Date date1, Date date2, Boolean roundUP) {
        int result = 0;
        Date dateBefore = date1.before(date2) ? date1 : date2;
        Date dateAfter = date2.after(date1) ? date2 : date1;
        Date tempDate = dateBefore;

        do {
            tempDate = addMonths(tempDate, 1);
            if (roundUP != null && roundUP) {
                ++result;
            } else if (tempDate.before(dateAfter) || tempDate.compareTo(dateAfter) == 0) {
                ++result;
            }
        } while(tempDate.before(dateAfter));

        return result;
    }

    public static int getWeekOfDate(Date dt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);
        int w = calendar.get(7) - 1;
        if (w < 0) {
            w = 0;
        }

        return w;
    }

    public static Date appendCalendarAndTime(Date calendar, Date timeDate) {
        Calendar cal_one = Calendar.getInstance();
        Calendar cal_two = Calendar.getInstance();
        cal_one.setTime(calendar);
        cal_two.setTime(timeDate);
        cal_one.set(11, cal_two.get(11));
        cal_one.set(12, cal_two.get(12));
        cal_one.set(13, cal_two.get(13));
        return cal_one.getTime();
    }

    public static Date getDateTime(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(time);
    }

    public static Timestamp getTimeStampTime(String time) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new Timestamp(simpleDateFormat.parse(time).getTime());
    }

    public static Timestamp getTimeStampTime(String time, String dateFormat) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return new Timestamp(simpleDateFormat.parse(time).getTime());
    }

    public static String getStringTime(Timestamp time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(time);
    }

    public static String getStringTime(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(time);
    }

    public static Timestamp getTimeStampTime(long time) throws ParseException {
        return new Timestamp(time);
    }

    public static String getStringTime(long time, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(time);
    }

    public static Date getDateTime() {
        return new Date();
    }

    public static Timestamp getTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp string_to_timestamp(String time) {
        return Timestamp.valueOf(time);
    }

    public static enum DateFormat {
        CALENDAR("yyyy-MM-dd"),
        FOLDER("yyyyMMdd/"),
        DATE_TIME("yyyy-MM-dd HH:mm:ss");

        private final String val;

        private DateFormat(String val) {
            this.val = val;
        }

        public String getVal() {
            return this.val;
        }
    }
}
