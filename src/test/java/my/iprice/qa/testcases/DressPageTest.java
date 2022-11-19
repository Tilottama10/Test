package my.iprice.qa.testcases;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;
import my.iprice.qa.base.TestBase;
import my.iprice.qa.pages.DressPage;
import my.iprice.qa.pages.HomePage;
import my.iprice.qa.pages.LaptopPage;
import my.iprice.qa.util.TestUtil;

//Scenario 2**: Users are able to sort results under dresses by price in descending order
public class DressPageTest extends TestBase {

	HomePage homePage;
	TestUtil testUtil;
	DressPage dressPage;

	public DressPageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		homePage = new HomePage();
		dressPage = new DressPage();

	}

//Navigate to the `/clothing/dresses` page
	@Test(dataProvider = "sectionName", priority = 1)
	public void navigateToDressPage(String section) throws Exception {
		homePage.scrollToSection(section);
		dressPage = homePage.clickOnDress("Dresses");
		String currPageTitle = homePage.getPageTitle();
		org.testng.Assert.assertEquals(currPageTitle, "Best Dresses Price in Malaysia 2022 | Shop Dresses Online");

	}

//Click on Price sorting until the indicator indicates that the list is sorted by price in descending order
	@Test(priority = 2)
	public void sortDescPrice() {
		try {
			dressPage.selectSorting("Price");
			if (dressPage.verifySortingOrder() == false) {
				dressPage.selectSorting("Price");
			}
		} catch (Exception e) {
			e.getMessage();
		}

	}
	
//	Validate that the results are sorted in descending order of Price
	public void verifyResultInDescOrder() {
		try {
			ArrayList<Float> priceList=new ArrayList<Float>();
			for(int i=0;i<dressPage.price.size();i++) {
				priceList.add(Float.parseFloat(dressPage.price.get(i).getText()));
				System.out.println(dressPage.price.get(i).getText());
			}
			if(!dressPage.descndingCheck(priceList)) {
				org.testng.Assert.fail("Not In Descending Order");
			}
		} catch (NumberFormatException e) {		
			e.printStackTrace();
		}
	}

	/*
	 * @AfterTest public void takeScreenShots() throws IOException {
	 * testUtil.takeScreenshotAtEndOfTest(); }
	 */

	@DataProvider(name = "sectionName")
	public Object[][] excelDataprovider() throws IOException {

		Object[][] arrObj = testUtil.getExcelData(
				System.getProperty("user.dir") + "\\src\\main\\java\\my\\iprice\\qa\\resources\\Brand_TestData.xlsx",
				"Sheet2");
		return arrObj;
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
