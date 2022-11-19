package my.iprice.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import my.iprice.qa.base.TestBase;

public class TestUtil extends TestBase {

//	To Take the screenshot 
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(srcFile, new File(currentDir + "/screenshots" + System.currentTimeMillis() + ".png"));
	}
//	To Scroll to view the element
	public static void scrollToView(WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		
	}
	
//	To Enter details in a Text box
	public void enterText(WebElement element,String text) {
		element.sendKeys(text);		
	}

//To click on  Webelemnt
	public void clickOnElement(WebElement element) {
		element.click();
	}
	
//	To Read Datd from excel
	public String[][] getExcelData(String fileName, String sheetName) throws IOException {

		String[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(0);

			int noOfRows = sheet.getPhysicalNumberOfRows();
			int noOfColumn = row.getLastCellNum();
			Cell cell;
			data = new String[noOfRows - 1][noOfColumn];

			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfColumn; j++) {
					row = sheet.getRow(i);
					cell = row.getCell(j);
					data[i - 1][j] = cell.getStringCellValue();

				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return data;

	}
	


}
