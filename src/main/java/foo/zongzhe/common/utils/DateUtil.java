package foo.zongzhe.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static final String YYYY_MM_DD_WITH_HYPHEN = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS_WITH_HYPHEN = "yyyy-MM-dd HH:mm:ss";

    public Boolean isWeekday(Calendar calendarDate) {
        return (calendarDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY ||
                calendarDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY);
    }

    public Boolean isWeekday(Date date) {
        Calendar cDate = Calendar.getInstance();
        cDate.setTime(date);
        return isWeekday(cDate);
    }

    public Boolean isWeekday(String dateStr) {
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

    public Date getPrevDate(Calendar cDate) {
        cDate.add(Calendar.DAY_OF_MONTH, 1);
        return cDate.getTime();
    }

    public Date getPrevDate(String dateStr) {
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

}
