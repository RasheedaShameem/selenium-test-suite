package pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ParentObjectClass;

/**
 * Page Object class representing the Cart page in the Sauce Demo application.
 * Provides methods to proceed to checkout and retrieve product names added to the cart.
 */
public class CartPage extends ParentObjectClass {
	
	
   WebDriver driver;
   
   /**
    * Constructor to initialize the CartPage with WebDriver instance.
    * @param driver The WebDriver instance passed from test or parent class.
    */
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="checkout") WebElement checkoutbtn;
	@FindBy(css = ".inventory_item_name") List<WebElement> cartItemNames;
	

    /**
     * Clicks on the Checkout button and navigates to the CheckoutPage1.
     * @return A new instance of CheckoutPage1
     */
	public CheckoutPage1 checkout()
	{
		checkoutbtn.click();
		return new CheckoutPage1(driver);
	}
	
	 /**
     * Retrieves the names of all products currently present in the cart.
     * @return A List of product names as Strings
     */
	public List<String> getCartProductNames() {
	    return cartItemNames.stream()
	                        .map(WebElement::getText)
	                        .collect(Collectors.toList());
	}
}