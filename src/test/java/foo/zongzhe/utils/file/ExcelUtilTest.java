package foo.zongzhe.utils.file;

import foo.zongzhe.utils.common.LogUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import java.io.IOException;

public class ExcelUtilTest {

    static ExcelUtil excelUtil = new ExcelUtil();

    @Test
    public void readExcelValuesTest() throws IOException, InvalidFormatException {
        ExcelUtil excelUtil = new ExcelUtil();
        String[][] contents =  excelUtil.readExcelValues("ExcelTest.xlsx", 0);
        for (String[] content : contents) {
            for (String s : content) {
                System.out.print(s + " | ");
            }
            LogUtil.logNextLine();
        }
    }

    @Test
    public void readWrongExcelValuesTest() throws IOException, InvalidFormatException {
        ExcelUtil excelUtil = new ExcelUtil();
        String[][] contents =  excelUtil.readExcelValues("Excel07withWrongName.xls", 0);
        for (String[] content : contents) {
            for (String s : content) {
                System.out.print(s + " | ");
            }
            LogUtil.logNextLine();
        }
    }
}
