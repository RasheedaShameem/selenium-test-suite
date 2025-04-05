package tests;


import java.util.HashMap;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import abstractTest.ParentClass;
import pageObjects.CartPage;
import pageObjects.CheckoutPage1;
import pageObjects.ProductCatalog;


public class Errorvalidation extends ParentClass {

/**
 * This class contains negative test cases to validate error scenarios 
 * such as login failures, performance issues, and validation checks 
 * during checkout on the Sauce Demo application.
 */
 
 

    /**
     * Test Case: Locked Out User
     * 
     * Description:
     * Attempts login with a locked-out user.
     * Verifies the correct error message is displayed.
     */
  @Test(groups={"Errorhandling"})
  public void lockedUser() {
	 
	    login.logIntoApplication("locked_out_user", "secret_sauce");
		Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.",login.ErrorMessage());
		
	  
}
  /**
   * Test Case: Performance Glitch User
   * 
   * Description:
   * Logs in using the "performance_glitch_user" and attempts to load a product.
   * Verifies that the product is displayed, indicating page loads correctly.
   */
  @Test (groups={"Errorhandling"})
  public void performanceGlitch() {
	 
		ProductCatalog productCatalog = login.logIntoApplication("performance_glitch_user", "secret_sauce");
		WebElement product = productCatalog.getProductByName("Sauce Labs Onesie");
		if(product.isDisplayed())
		{
			Assert.assertTrue(true);
		}
		else {
		
		Assert.assertTrue(false);
		}
}  
  

  /**
   * Test Case: Invalid Login
   * 
   * Description:
   * Attempts login with invalid credentials.
   * Verifies that the application displays the appropriate error message.
   */
  
  @Test(groups={"Errorhandling"})
  public void InvalidLogin() {
	 
	    login.logIntoApplication("test", "test");
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",login.ErrorMessage());		
	  
}
	
  /**
   * Test Case: Checkout with Missing First Name
   * 
   * Description:
   * Logs in with a valid user.
   * Adds a product to the cart, proceeds to checkout, and leaves the first name blank.
   * Validates that the correct error message is shown for the missing required field.
   */
	@Test(dataProvider = "getUserCredentials", dataProviderClass = InventoryPageValidation.class , groups={"Errorhandling"})
public void checkoutWithMissingFirstName(HashMap <String,String> input ) {
	  	

		ProductCatalog prdtctgobj= login.logIntoApplication(input.get("username"), input.get("password"));
		prdtctgobj.addProductToCart("Sauce Labs Backpack");
		CartPage cartpage = prdtctgobj.gotoCart();
		CheckoutPage1 choutpage1 =cartpage.checkout();
		choutpage1.enterPersonalInfo("","test","56522");
		String errorMsg = choutpage1.validateError();		
		Assert.assertEquals(errorMsg,"Error: First Name is required");
		  
}
   
  
  
}
