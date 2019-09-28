package foo.zongzhe.comon.utils;

import foo.zongzhe.common.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(JUnit4.class)
public class DateUtilTest {

    @Test
    public void isWeekdayTest() {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYY_MM_DD_WITH_HYPHEN);
        String dateStr = "2019-01-06";
        try {
            Date date = sdf.parse(dateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            assertFalse(DateUtil.isWeekday(c));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateStr = "2019-01-07";
        try {
            Date date = sdf.parse(dateStr);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            assertTrue(DateUtil.isWeekday(c));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
