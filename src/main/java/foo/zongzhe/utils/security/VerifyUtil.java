package foo.zongzhe.utils.security;

/**
 * Some methods for verifying stuff
 */
public class VerifyUtil {

    /**
     * Check verification code basing on current date.
     * e.g. 2020-11-25(Wed) will be broken into 20-20-11-25-3 (3 for Wed)
     * (1) get 1st, 3rd, and 5th parts: 20-11-3
     * (2) get 2nd and 4th parts 20-25, and revert: 52-02
     * (3) multiply two parts: 20113*5202 = 104627826, and check with the given code.
     * @param verifyCode
     * @return
     */
    public static boolean checkVerificationCode(Integer verifyCode){

    }
}
