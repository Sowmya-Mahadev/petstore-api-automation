package api.utilities;

	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import org.apache.poi.ss.usermodel.*;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class XLUtility {

	    String path;
	    FileInputStream fis;
	    FileOutputStream fos;
	    Workbook workbook;
	    Sheet sheet;

	    public XLUtility(String path) {
	        this.path = path;
	    }

	    // ✅ Get Row Count
	    public int getRowCount(String sheetName) throws IOException {
	        fis = new FileInputStream(path);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheet(sheetName);
	        int rowCount = sheet.getLastRowNum();
	        workbook.close();
	        fis.close();
	        return rowCount;
	    }

	    // ✅ Get Cell Count (columns)
	    public int getCellCount(String sheetName, int rowNum) throws IOException {
	        fis = new FileInputStream(path);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheet(sheetName);
	        Row row = sheet.getRow(rowNum);
	        int cellCount = row.getLastCellNum();
	        workbook.close();
	        fis.close();
	        return cellCount;
	    }

	   
	    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {

	        FileInputStream fi = new FileInputStream(path);
	        Workbook workbook = new XSSFWorkbook(fi);
	        Sheet sheet = workbook.getSheet(sheetName);

	        Row row = sheet.getRow(rowNum);
	        Cell cell = row.getCell(colNum);

	        DataFormatter formatter = new DataFormatter();
	        String data;

	        try {
	            // Handles all types (String, Numeric, Date, Boolean)
	            data = formatter.formatCellValue(cell);
	        } catch (Exception e) {
	            data = "";
	        }

	        workbook.close();
	        fi.close();

	        return data;
	    }

	    // ✅ Set Cell Data using Column Name
	    public void setCellData(int sheetIndex, int rowNum, String colName, String value) throws IOException {
	        fis = new FileInputStream(path);
	        workbook = new XSSFWorkbook(fis);
	        sheet = workbook.getSheetAt(sheetIndex);

	        Row headerRow = sheet.getRow(0);
	        int colIndex = -1;

	        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
	            if (headerRow.getCell(i).getStringCellValue().equalsIgnoreCase(colName)) {
	                colIndex = i;
	                break;
	            }
	        }

	        Row row = sheet.getRow(rowNum);
	        if (row == null) {
	            row = sheet.createRow(rowNum);
	        }

	        Cell cell = row.getCell(colIndex);
	        if (cell == null) {
	            cell = row.createCell(colIndex);
	        }

	        cell.setCellValue(value);

	        fos = new FileOutputStream(path);
	        workbook.write(fos);

	        workbook.close();
	        fis.close();
	        fos.close();
	    }
	}	
	
	
	
	

