package foo.zongzhe.utils.comon;

import foo.zongzhe.utils.common.IntegerUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class IntegerUtilTest {

    private IntegerUtil integerUtil;


    @Before
    public void before() {
        integerUtil = new IntegerUtil();
    }

    @Test
    public void loclDateTimeToIntTest() {
        LocalDateTime localDateTime = LocalDateTime.parse("2018-03-11 15:30:11.123", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        int res = integerUtil.localDateTimeToInt(localDateTime, "yyyyMMdd");
        assertEquals(20180311, res);

        int res2 = integerUtil.localDateTimeToInt(localDateTime, "HHmmss");
        assertEquals(153011, res2);

        int res3 = integerUtil.localDateTimeToInt(localDateTime, "HHmmssSSS");
        assertEquals(153011123, res3);
    }
}
