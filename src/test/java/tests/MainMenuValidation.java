package tests;


import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import abstractTest.ParentClass;
import pageObjects.MainMenuPage;
import pageObjects.ProductCatalog;

public class MainMenuValidation extends ParentClass {
	
	
	 // Test method for verifying login and logout functionality using data-driven approach
	@Test(dataProvider = "getUserCredentials", dataProviderClass = InventoryPageValidation.class)
public void LoginandLogout(HashMap <String,String> input ) {
	  	
	     // Step 1: Log into the application using the provided credentials
		ProductCatalog productcatalog= login.logIntoApplication(input.get("username"), input.get("password"));
	
        // Step 2: Add a product to cart to simulate a user action post-login
		productcatalog.addProductToCart("Sauce Labs Backpack");

        // Step 3: Initialize the MainMenuPage to interact with the side menu
        MainMenuPage mainMenuPage = new MainMenuPage(driver);

        // Step 4: Click on the logout option from the side menu
        mainMenuPage.clickLogoutFromMenu();

        // Step 5: Verify if the user is redirected to the login page after logout
        String currentUrl = driver.getCurrentUrl();
        boolean isLoggedOut = currentUrl.contains("saucedemo.com") && login.isLoginPageDisplayed();

        // Step 6: Assert that the logout was successful
        Assert.assertTrue(isLoggedOut, "Logout failed - either login page not displayed or URL mismatch.");
    }
}