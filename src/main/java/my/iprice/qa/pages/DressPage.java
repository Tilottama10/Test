package my.iprice.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import my.iprice.qa.base.TestBase;

public class DressPage extends TestBase {

	@FindBy(xpath = "//a[@data-vars-cgt=\"click_sort_order_label\"]")
	public List<WebElement> SortOption;

	@FindBy(xpath = "//i[contains(@class,'sprite-icons cI ar')]")
	public List<WebElement> sortIcon;
	
	@FindBy(xpath="//div[contains(@class,'uT ellipsis-1']")
	public List<WebElement> price;

//	Initializing the DressPage
	public DressPage() {
		PageFactory.initElements(driver, this);
	}

	public void selectSorting(String sortBy) {
		for (int j = 0; j < SortOption.size(); j++) {

			if (SortOption.get(j).getAttribute("data-vars-lb").equals(sortBy)) {
				
				SortOption.get(j).click();
				break;
			}
		}
	}

	public boolean verifySortingOrder()
	{
		boolean flag=false;
		for(int i=0;i<sortIcon.size();i++)
		{
			String sortingOrder= sortIcon.get(i).getAttribute("class");
			if(sortingOrder.contains("i-descending-green-active")) 
			{
				 flag=true;
			}else 
			{
				flag=false;
			}
		}
		return flag;
	}
	
	public boolean descndingCheck(ArrayList<Float> data) {
		for(int i=0;i<data.size()-1;i++) {
			if(data.get(i)<data.get(i+1)) {
				return false;				
			}
		}
		return true;
	}
}
		
	
	
