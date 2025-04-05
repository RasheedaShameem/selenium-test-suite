package tests;


import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import abstractTest.ParentClass;
import pageObjects.MainMenuPage;
import pageObjects.ProductCatalog;

public class MainMenuValidation extends ParentClass {
	

	@Test(dataProvider = "getUserCredentials", dataProviderClass = InventoryPageValidation.class)
public void LoginandLogout(HashMap <String,String> input ) {
	  	

		ProductCatalog prdtctgobj= login.logIntoApplication(input.get("username"), input.get("password"));
		prdtctgobj.addProductToCart("Sauce Labs Backpack");
		MainMenuPage mainMenuPage = new MainMenuPage(driver);
		mainMenuPage.clickLogoutFromMenu();
		 
		 String currentUrl = driver.getCurrentUrl();
		 boolean isLoggedOut = currentUrl.contains("saucedemo.com") && login.isLoginPageDisplayed();
		    Assert.assertTrue(isLoggedOut, "Logout failed - either login page not displayed or URL mismatch.");
 
		 }
}