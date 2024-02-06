package CommonUtils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtils {

	public String getDataFromExcel(String sheetname,int rownum,int cellnum) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream("src\\test\\resources\\LeadModule.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String value=wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
		return value;
	}
}
