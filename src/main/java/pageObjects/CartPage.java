package pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ParentObjectClass;

public class CartPage extends ParentObjectClass {
	
	
   WebDriver driver;
 
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="checkout") WebElement checkoutbtn;
	@FindBy(css = ".inventory_item_name") List<WebElement> cartItemNames;
	
	public CheckoutPage1 checkout()
	{
		checkoutbtn.click();
		return new CheckoutPage1(driver);
	}
	
	
	public List<String> getCartProductNames() {
	    return cartItemNames.stream()
	                        .map(WebElement::getText)
	                        .collect(Collectors.toList());
	}
}