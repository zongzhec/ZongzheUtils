package foo.zongzhe.utils.file;

import foo.zongzhe.utils.common.LogUtil;
import foo.zongzhe.utils.common.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ExcelUtil {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    /**
     * Read from excel by rows.
     *
     * @param filePath Path of the file.
     * @param sheetNum The index number of sheet to read.
     * @throws java.io.IOException when file is unable to parse.
     */
    public static String[][] readExcelValues(String filePath, int sheetNum) throws IOException {

        Workbook wb = getWorkbook(filePath);
        LogUtil.logInfo("Workbook contains sheetNum: " + wb.getNumberOfSheets());

        LogUtil.logInfo("Getting info from excel");
        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();
        Sheet sheet = wb.getSheetAt(sheetNum);
        // Get row number and col number from the sheet
        int rows = sheet.getLastRowNum() + 1; // rows starts with 0
        int cols = 0;
        for (int i = 0; i < rows; i++) {
            Row row = sheet.getRow(i);
            if (null != row) {
                int col = row.getLastCellNum();
                cols = Math.max(cols, col);
            }
        }
        LogUtil.logInfo(String.format("row number: %d, col number: %d", rows, cols));
        // Initialize return values.
        String[][] contents = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                contents[i][j] = StringUtil.BLANK;
            }
        }
        for (int i = 0; i < rows; i++) {
            Row row = sheet.getRow(i);
            if (null != row) {
                for (int j = 0; j < cols; j++) {
                    String cellValue = dataFormatter.formatCellValue(row.getCell(j));
                    if (!StringUtil.isEmpty(cellValue)) contents[i][j] = cellValue;
                }
            }
        }
        return contents;
    }

    /**
     * Get workbook from file
     *
     * @param filePath the file to be parsed.
     * @throws java.io.IOException when file is unable to parse.
     */
    public static Workbook getWorkbook(String filePath) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(filePath);
        if (is == null) {
            is = new FileInputStream(filePath);
        }

        Workbook wb = null;
//        InputStream is = file.getInputStream();
        // Apply different Workbook basing on different extention
        String ext = filePath.substring(filePath.indexOf('.') + 1);
        LogUtil.logInfo("file ext is: " + ext);

        // Try best to decode the file magic
        try {
            wb = new HSSFWorkbook(is);
        } catch (OfficeXmlFileException e1) {
            is = new FileInputStream(filePath);
            try {
                wb = new XSSFWorkbook(is);
            } catch (IOException e2) {
                throw new IOException(e2);
            }
        } finally {
            is.close();
        }
        return wb;
    }

    public static void writeToExcel(String filePath, String[] excelTitle, String[][] contents, String sheetName)
            throws IOException {
        LogUtil.logInfo("Writing into Excel via " + filePath);
        Workbook workbook = getWorkbook(filePath);
        // Create Sheet
        Sheet sheet = workbook.createSheet(sheetName);
        int rowIndex = 0;

        // Write row title
        Row titleRow = sheet.createRow(rowIndex);
        for (int i = 0; i < excelTitle.length; i++) {
            titleRow.createCell(i).setCellValue(excelTitle[i]);
        }
        rowIndex++;

        // Write contents
        for (int i = 0; i < contents.length; i++) {
            int colIndex = 0;
            String[] contentRow = contents[i];
            Row row = sheet.createRow(rowIndex);
            for (int j = 0; j < contentRow.length; j++) {
                row.createCell(colIndex).setCellValue(contentRow[j]);
                colIndex++;
            }
            rowIndex++;
        }

        FileOutputStream fos = new FileOutputStream(filePath);
        workbook.write(fos);
        fos.close();
        LogUtil.logInfo("Write Excel completed.");
    }

}
