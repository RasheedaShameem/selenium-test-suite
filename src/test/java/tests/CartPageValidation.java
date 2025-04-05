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
	

	@Test(dataProvider = "getUserCredentials", dataProviderClass = InventoryPageValidation.class)
	public void addMultipleItemsAndVerifyInCart(HashMap<String, String> input) {
	    String username = input.get("username");
	    String password = input.get("password");

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
	     
	    	    //add 2 products randomly
	    Collections.shuffle(allProducts);
	    List<String> selectedProducts = allProducts.subList(0, 2);
	    
	    // Add them to cart
	    for (String product : selectedProducts) {
	        productCatalog.addProductToCart(product);
	    }
	    
	    CartPage cartPage = productCatalog.gotoCart();
	    List<String> cartProductNames = cartPage.getCartProductNames();
	  
	    for (String product : selectedProducts) {
	        Assert.assertTrue(cartProductNames.contains(product), "Product not found in cart: " + product);
	    }
	}

	    

}
 