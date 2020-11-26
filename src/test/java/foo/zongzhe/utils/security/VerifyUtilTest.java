package foo.zongzhe.utils.security;

import foo.zongzhe.utils.common.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class VerifyUtilTest {

    static Date date;

    @Before
    public void initialize() {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYY_MM_DD_WITH_HYPHEN);
        try {
            date = sdf.parse("2020-11-26");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkVerificationCodeWithGivenDateShouldReturnTrueOnValidCode() {
        assertTrue(VerifyUtil.checkVerificationCodeWithGivenDate("124747028", date));

    }

    @Test
    public void checkVerificationCodeWithGivenDateShouldReturnFalseOnInValidCode() {
        assertFalse(VerifyUtil.checkVerificationCodeWithGivenDate("00", date));
    }

    @Test
    public void checkVerificationCodeWithGivenDateShouldReturnFalseOnEmptyCode() {
        assertFalse(VerifyUtil.checkVerificationCodeWithGivenDate("", date));
    }

    @Test
    public void checkVerificationCodeWithGivenDateShouldReturnFalseOnBlankCode() {
        assertFalse(VerifyUtil.checkVerificationCodeWithGivenDate("    ", date));
    }
}
