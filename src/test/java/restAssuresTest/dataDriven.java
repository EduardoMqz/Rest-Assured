package restAssuresTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import files.ReusableMethods;

public class dataDriven {
    
    ReusableMethods configReader = new ReusableMethods("excelResource");

    @Test
    public void excelTest() throws IOException {
        FileInputStream fis = new FileInputStream(configReader.get("path"));
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase(configReader.get("sheetName"))) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();// collection of rows
                Row firstRow = rows.next();
                Iterator<Cell> cells = firstRow.cellIterator();// collection of cells
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    if (cell.getStringCellValue().equalsIgnoreCase(configReader.get("cellValue"))) {

                    }
                }
            }
        }
    }
}