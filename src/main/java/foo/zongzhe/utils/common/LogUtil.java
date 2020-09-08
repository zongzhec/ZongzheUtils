package foo.zongzhe.utils.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {

    public static final String DOTS = "*********************************************";
    private static final String INFO = "INFO";
    private static final String ERROR = "ERROR";
    private static final String DEBUG = "DEBUG";


    public static void log(String msg, String logType) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYY_MM_DD_HH_MM_SS_WITH_HYPHEN);
        String dateStr = sdf.format(new Date());
        System.out.println(String.join(" ", dateStr, logType, msg));
    }

    public static void logInfo(String msg) {
        log(msg, INFO);
    }

    public static void logDebug(String msg) {
        log(msg, DEBUG);
    }

    public static void logError(String msg) {
        log(msg, ERROR);
    }

    public static void logNextLine() {
        System.out.println();
    }
}
