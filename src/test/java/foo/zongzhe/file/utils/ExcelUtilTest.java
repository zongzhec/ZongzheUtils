package foo.zongzhe.file.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ExcelUtilTest {

    static ExcelUtil excelUtil = new ExcelUtil();

    @Test
    public void readExcelValuesTest() throws IOException, InvalidFormatException {
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.readExcelValues("ExcelTest.xlsx", 0);
    }
}
