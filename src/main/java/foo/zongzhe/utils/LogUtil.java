package foo.zongzhe.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {

    public static final String DOTS = "*********************************************";

    public void info(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYY_MM_DD_HH_MM_SS_WITH_HYPHEN);
        String dateStr = sdf.format(new Date());
        System.out.println(dateStr + " " + msg);
    }
}
