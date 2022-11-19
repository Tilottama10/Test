package my.iprice.qa.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import my.iprice.qa.base.TestBase;
import my.iprice.qa.pages.HomePage;
import my.iprice.qa.pages.LaptopPage;
import my.iprice.qa.util.TestUtil;


//SCENARIO : Users are able to filter for an item by brand under the Computing > Laptop section
public class HomePageTest extends TestBase {

	HomePage homePage;
	TestUtil testUtil;
	LaptopPage laptopPage;

	public HomePageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		homePage = new HomePage();
		laptopPage = new LaptopPage();

	}

//Navigate to the `/computing/laptops` page
	@Test(priority = 1)
	public void navigateToLaptopPage() {
		laptopPage = homePage.clickOnLaptop();

	}

//	Select the brand value to be Dell
	@Test(priority = 2)
	public void verifyLaptopPageDisplayed() {

		assertEquals(laptopPage.getPageTitle(), "Latest Laptops Price in Malaysia | Harga Murah November, 2022");

	}

	@Test(dataProvider = "brand", priority = 3)
	public void selectTheBrand(String brandName) {
		
		laptopPage.clickOnBrandName(brandName);

	}
//	Validate that the results returned from page 1 matches the selected brand
	@Test(priority = 4)
	public void verifyResult() {
		String resultPageTitle= laptopPage.getPageTitle();
		Assert.assertEquals(resultPageTitle, "Compare Latest Dell Laptops Price in Malaysia | Harga November, 2022");
		Assert.assertTrue(laptopPage.searchResultText.isDisplayed());
	}
@AfterTest
public void takeScreenShots() throws IOException {
	testUtil.takeScreenshotAtEndOfTest();
}
	@DataProvider(name = "brand")
	public Object[][] excelDataprovider() throws IOException {

		Object[][] arrObj = testUtil.getExcelData(
				System.getProperty("user.dir") + "\\src\\main\\java\\my\\iprice\\qa\\resources\\Brand_TestData.xlsx",
				"Sheet1");
		return arrObj;
	}
	

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
