package foo.zongzhe.utils.common;

public class StringUtil {

    public static final String BLANK = "";

    /**
     * Check whether a string is empty.
     *
     * @param inputStr input string.
     * @return whether the input string is null or empty.
     */
    public static boolean isEmpty(String inputStr) {
        return (inputStr == null || inputStr.isEmpty());
    }
}
