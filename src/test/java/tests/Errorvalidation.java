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
	
   
  @Test(groups={"Errorhandling"})
  public void lockedUser() {
	 
	    login.logIntoApplication("locked_out_user", "secret_sauce");
		Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.",login.ErrorMessage());
		
	  
}
    
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
  @Test(groups={"Errorhandling"})
  public void InvalidLogin() {
	 
	    login.logIntoApplication("test", "test");
		Assert.assertEquals("Epic sadface: Username and password do not match any user in this service",login.ErrorMessage());		
	  
}
	
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
