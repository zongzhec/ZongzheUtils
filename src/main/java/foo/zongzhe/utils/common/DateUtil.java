package foo.zongzhe.utils.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static final String YYYY_MM_DD_WITH_HYPHEN = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS_WITH_HYPHEN = "yyyy-MM-dd HH:mm:ss";

    public static Boolean isWeekday(Calendar calendarDate) {
        return !(calendarDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                calendarDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
    }

    public static Boolean isWeekday(Date date) {
        Calendar cDate = Calendar.getInstance();
        cDate.setTime(date);
        return isWeekday(cDate);
    }

    public static Boolean isWeekday(String dateStr) {
        DateFormat df = new SimpleDateFormat(YYYY_MM_DD_WITH_HYPHEN);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            LogUtil.logError("Unable to parse date: " + dateStr);
            e.printStackTrace();
        }
        Calendar cDate = Calendar.getInstance();
        cDate.setTime(date);
        return isWeekday(cDate);
    }

    public static Boolean isBusday(Calendar cDate, List<Calendar> holidayList) {
        return isWeekday(cDate) && !holidayList.contains(cDate);
    }

    public static Date getPrevDate(Calendar cDate) {
        cDate.add(Calendar.DAY_OF_MONTH, 1);
        return cDate.getTime();
    }

    public static Date getPrevDate(String dateStr) {
        DateFormat df = new SimpleDateFormat(YYYY_MM_DD_WITH_HYPHEN);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            LogUtil.logError("Unable to parse date: " + dateStr);
            e.printStackTrace();
        }
        Calendar cDate = Calendar.getInstance();
        cDate.setTime(date);
        cDate.add(Calendar.DAY_OF_MONTH, 1);
        return cDate.getTime();
    }

    public static Calendar getNextBusDay(Calendar cDate, List<Calendar> holidayList) {
        cDate.add(Calendar.DAY_OF_MONTH, 1);
        while (!isWeekday(cDate)) {
            cDate.add(Calendar.DAY_OF_MONTH, 1);
        }
        return cDate;
    }

    /**
     * Convert date to String with given format pattern
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * Convert String to Calendar with given format pattern
     */
    public static Calendar stringToCalendar(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * Copy a calendar object
     */
    public static Calendar CopyCalendar(Calendar inputCalendar) {
        Calendar outputCalendar = Calendar.getInstance();
        outputCalendar.setTime(inputCalendar.getTime());
        return outputCalendar;
    }

    public static int getBusDaysWithinPeriod(Calendar startC, Calendar endC, List<Calendar> holidayList) {
        Calendar c = CopyCalendar(startC);
        int days = (isBusday(startC, holidayList)) ? 1 : 0;
        while (!c.equals(endC)) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            if (isBusday(c, holidayList)) {
                days++;
            }
        }
        return days;
    }

    public static Calendar getNextBusDate (Calendar calendarDate, List<Calendar> holidayList) {
        calendarDate.add(Calendar.DAY_OF_MONTH, 1);
        while (!isBusday(calendarDate, holidayList)){
            calendarDate.add(Calendar.DAY_OF_MONTH, 1);
        }
        return calendarDate;
    }

}