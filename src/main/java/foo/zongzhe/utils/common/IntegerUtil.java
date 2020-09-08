package foo.zongzhe.utils.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * For everything which will be Integer.
 */
public class IntegerUtil {

    /**
     * This is for converting LocalDateTime to Integer via custom formatting.
     * e.g. 2018-03-11 15:30:11.123 could be converted to:
     * 20180311,
     * 153011,
     * 153011123, etc.
     * @param input
     * @param pattern
     * @return
     */
    public Integer LocalDateTimeToInt(LocalDateTime input, String pattern){
        String dateStr = input.format(DateTimeFormatter.ofPattern(pattern));
        return Integer.valueOf(dateStr);
    }
}
