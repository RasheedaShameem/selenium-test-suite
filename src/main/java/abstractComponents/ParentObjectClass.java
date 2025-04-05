package abstractComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageObjects.CartPage;

public class ParentObjectClass {
	
	WebDriver driver;
	
	public ParentObjectClass(WebDriver driver) {
		
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className="shopping_cart_link") WebElement carticon;

	public CartPage gotoCart()
	{
		
		carticon.click();
		return new CartPage(driver);
		
	}

}
