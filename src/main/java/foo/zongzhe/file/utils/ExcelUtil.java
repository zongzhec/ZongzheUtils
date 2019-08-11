package foo.zongzhe.file.utils;

import foo.zongzhe.common.utils.LogUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * Read from excel by rows.
     *
     * @param filePath Path of the file.
     * @param sheetNum The index number of sheet to read.
     * @throws java.io.IOException    when file is unable to parse.
     * @throws InvalidFormatException when the format is invalid.
     */
    public void readExcelValues(String filePath, int sheetNum) throws IOException, InvalidFormatException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(filePath);

        Workbook wb = new XSSFWorkbook(is);
        LogUtil.printInfo("Workbook contains sheetNum: " + wb.getNumberOfSheets());

        LogUtil.printInfo("Getting info from excel");
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        Sheet sheet = wb.getSheetAt(sheetNum);
        // Get row number and col number from the sheet
        int rows = sheet.getLastRowNum();
        int cols = 0;
        for (int i = 1; i <= rows; i++) {
            Row row = sheet.getRow(i);
            if (null != row) {
                int col = row.getLastCellNum();
                cols = Math.max(cols, col);
            }
        }
        LogUtil.printInfo(String.format("row number: %d, col number: %d", rows, cols));
        sheet.forEach(row -> {
            if (null != row) {
                row.forEach(cell -> {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    LogUtil.printInfo(cellValue);
                });
            }

        });


    }

    /**
     * Get workbook from file
     *
     * @param file the file to be parsed.
     * @throws java.io.IOException when file is unable to parse.
     */
    public static Workbook getWorkbook(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Workbook wb = null;
        InputStream is = file.getInputStream();
        // Apply different Workbook basing on different extention
        String ext = fileName.substring(fileName.indexOf('.'));
        LogUtil.printInfo("file ext is: " + ext);
        switch (ext) {
            case XLS:
                wb = new HSSFWorkbook(is);
                break;
            case XLSX:
                wb = new XSSFWorkbook(is);
                break;
            default:
                LogUtil.printError("Invalid file ext.");
        }

        return wb;
    }

}
