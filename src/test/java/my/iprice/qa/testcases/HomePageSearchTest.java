package my.iprice.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import my.iprice.qa.base.TestBase;
import my.iprice.qa.pages.HomePage;
import my.iprice.qa.pages.LaptopPage;
import my.iprice.qa.util.TestUtil;

/*SCENARIO#3 Users are able to search for an item 
Navigate to the homepage
- Search for “iPhone 14”
- Validate that the search results returned matches the search criteria*/
public class HomePageSearchTest extends TestBase {

	HomePage homePage;
	TestUtil testUtil;

	public HomePageSearchTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		homePage = new HomePage();

	}

	@Test(priority = 1)
//	Search for “iPhone 14”
	public void searchProduct() {

		testUtil.enterText(homePage.Search, "iPhone 14");
		testUtil.clickOnElement(homePage.SearchBtn);
		String searchProduct = homePage.searchProductName.getText();
		Assert.assertEquals(searchProduct, "iPhone 14", "Search Resutls Displayed");

	}

//	
	public void verifyResults() {

		if (homePage.verifyResultDisplayed("iPhone 14") != true) {
			Assert.fail("Search Result Ddisplayed Incorrectly");

		}

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
