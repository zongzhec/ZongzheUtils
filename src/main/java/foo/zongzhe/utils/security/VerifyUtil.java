package foo.zongzhe.utils.security;

import foo.zongzhe.utils.common.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Some methods for verifying stuff
 */
public class VerifyUtil {

    public static void main(String[] args) {
        VerifyUtil.checkVerificationCode("0");
    }

    /**
     * Check verification code basing on current date.
     *
     * @param verifyCode the verify code that user enters.
     * @return whether the verify code corresponds to the given date
     */
    public static boolean checkVerificationCode(String verifyCode) {
        Date currentDay = new Date();
        return checkVerificationCodeWithGivenDate(verifyCode, currentDay);
    }

    /**
     * Check verification code basing on a given date.
     * e.g. 2020-11-25(Wed) will be broken into 20-20-11-25-3 (3 for Wed)
     * (1) get 1st, 3rd, and 5th parts: 20-11-3
     * (2) get 2nd and 4th parts 20-25, and revert: 52-02
     * (3) multiply two parts: 20113*5202 = 104627826, and check with the given code.
     *
     * @param verifyCode the verify code that user enters.
     * @param givenDate  the date which is used to generate a code and compare with the verify code.
     * @return whether the verify code corresponds to the given date
     */
    public static boolean checkVerificationCodeWithGivenDate(String verifyCode, Date givenDate) {
        // Pre-check verifyCode
        if (verifyCode == null) {
            return false;
        }

        SimpleDateFormat sdfWeekday = new SimpleDateFormat("u");
        String weekday = sdfWeekday.format(givenDate);
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYY_MM_DD);
        String dayStr = sdf.format(givenDate);

        String firstPart = dayStr.substring(0, 2) + dayStr.substring(4, 6) + weekday;
        String secondPart = dayStr.substring(2, 4) + dayStr.substring(6, 8);
        secondPart = new StringBuffer(secondPart).reverse().toString();

        // Convert to Integer and get the multi num
        try {
            Integer firstInt = Integer.parseInt(firstPart);
            Integer secondInt = Integer.parseInt(secondPart);
            Integer multiNum = firstInt * secondInt;
            String multiNumStr = String.valueOf(multiNum);
            return multiNumStr.equals(verifyCode);
        } catch (NumberFormatException e) {
            return false;
        }

    }
}
