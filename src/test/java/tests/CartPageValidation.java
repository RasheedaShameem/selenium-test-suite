package tests;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import abstractTest.ParentClass;
import pageObjects.CartPage;
import pageObjects.ProductCatalog;


public class CartPageValidation extends ParentClass {
	
	/**
	 * Test Case: Add Multiple Items and Verify in Cart
	 * 
	 * Description:
	 * Logs into the application using valid credentials.
	 * Randomly selects two products from the product catalog.
	 * Adds the selected products to the cart.
	 * Navigates to the cart and verifies that the selected items are present.
	 * 
	 * Purpose:
	 * To validate that multiple products can be added to the cart
	 * and that the cart correctly reflects the added items.
	 * 
	 * Data Source:
	 * Credentials are provided via a DataProvider from InventoryPageValidation.
	 */
	@Test(dataProvider = "getUserCredentials", dataProviderClass = InventoryPageValidation.class)
	public void addMultipleItemsAndVerifyInCart(HashMap<String, String> input) {
		  // Extract login credentials from the test data
	    String username = input.get("username");
	    String password = input.get("password");
      
	    // Log in and return the ProductCatalog page object
	    ProductCatalog productCatalog = login.logIntoApplication(username, password);
	    // List of available products
	    List<String> allProducts = Arrays.asList(
	        "Sauce Labs Backpack",
	        "Sauce Labs Bike Light",
	        "Sauce Labs Bolt T-Shirt",
	        "Sauce Labs Fleece Jacket",
	        "Sauce Labs Onesie",
	        "Test.allTheThings() T-Shirt (Red)"
	    );
	     
	    // Shuffle the product list to select random products
	    Collections.shuffle(allProducts);

	    // Select first 2 random products after shuffle
	    List<String> selectedProducts = allProducts.subList(0, 2);

	    // Add selected products to the cart
	    for (String product : selectedProducts) {
	        productCatalog.addProductToCart(product);
	    }

	    // Navigate to the cart page
	    CartPage cartPage = productCatalog.gotoCart();

	    // Get list of product names currently in the cart
	    List<String> cartProductNames = cartPage.getCartProductNames();

	    // Verify that all selected products are present in the cart
	    for (String product : selectedProducts) {
	        Assert.assertTrue(cartProductNames.contains(product), "Product not found in cart: " + product);
	    }
	}
}