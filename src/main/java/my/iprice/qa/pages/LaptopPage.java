package my.iprice.qa.pages;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ByAll;

import my.iprice.qa.base.TestBase;

public class LaptopPage extends TestBase {

	@FindBy(xpath = "//*[@id=\"body\"]/div/div[1]/div[4]/amp-carousel")
	public WebElement BrandFilter;

	@FindBy(xpath = "//a[@data-vars-cgt='click_brand_carousel_label']")
	public java.util.List<WebElement> allOptions;
	
	@FindBy(xpath="//*[@id=\"clear_filters\"]")
	public WebElement searchResultText;
	

//	Intializing the page
	public LaptopPage() {
		PageFactory.initElements(driver, this);

	}

	public String getPageTitle() {

		String pageTitle = driver.getTitle();
		return pageTitle;

	}

	public void clickOnBrandName(String name) {
		for(int i=0;i<allOptions.size();i++) {
			if(allOptions.get(i).getText().equals(name) ) {
				allOptions.get(i).click();
				break;
			}
		}
		

	}

}
