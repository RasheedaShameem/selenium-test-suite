package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponents.ParentObjectClass;

public class ProductDetailPage extends ParentObjectClass  {
	
WebDriver driver;
	
	public ProductDetailPage(WebDriver driver)

	{
	    super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='inventory_details_name large_size']")WebElement productName;
    
	public String getProductName()
	{
		return productName.getText();
	}
	

	}
	

