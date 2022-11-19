package my.iprice.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import my.iprice.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//*[@type=\"carousel\"]/div/div[1]/div[1]/div/a[1]")
	WebElement CompSection;

	@FindBy(xpath = "//*[@type=\"carousel\"]/div/div[1]/div[1]/div/a[4]")
	WebElement Laptop;

	@FindBy(xpath = "//div[@role=\"listitem\"]/a/amp-img")
	public List<WebElement> ListItem;

	@FindBy(xpath = "//div[@class=\"tc ra c4 c5\"]/a")
	public List<WebElement> allOptions;
	
	@FindBy(id="term-desktop")
	public WebElement Search;
	
	@FindBy(id="search-btn")
	public WebElement SearchBtn;
	
	@FindBy(xpath="//*[@id=\"breadcrumb\"]/div[1]/div/h1")
	public WebElement searchProductName;
	
	@FindBy(xpath="//*[@id=\"product-list\"]/div/a")
	public List<WebElement> ProductList;
	
	

//	Initializing the Homepage
	public HomePage() {
		PageFactory.initElements(driver, this);

	}

	public String getPageTitle() {
		String title = driver.getTitle();
		return title;
	}

	public boolean verifyComputing() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].scrollIntoView(true);", CompSection);
		return CompSection.isDisplayed();

	}

	public void scrollToSection(String sectionName) {
		for (int i = 0; i < ListItem.size(); i++) {
			if (ListItem.get(i).getAttribute("alt").equals(sectionName)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView(true);", ListItem.get(i));
				ListItem.get(i).isDisplayed();
			}
		}

	}

	public DressPage clickOnDress(String productName) {
		for (int i = 0; i < allOptions.size(); i++) {
			if (allOptions.get(i).getText().equals(productName)) {
				allOptions.get(i).click();

			}
		}
		return new DressPage();
	}

	public boolean verifyResultDisplayed(String searchTxt) {
		for(int i=0;i<ProductList.size();i++) {
			if(ProductList.get(i).getAttribute("data-vars-lb").contains(searchTxt)) {
				return true;				
			}
		}
		return false;
	}

	public LaptopPage clickOnLaptop() {
		Laptop.click();
		return new LaptopPage();

	}

}
