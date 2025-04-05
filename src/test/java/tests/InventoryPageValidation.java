package tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import abstractTest.ParentClass;
import pageObjects.ProductCatalog;
import pageObjects.ProductDetailPage;



public class InventoryPageValidation extends ParentClass {
	
   
@Test(dataProvider = "getUserCredentials")
  public void VerifyItemCount(HashMap<String, String> input) {
	 
	  String username = input.get("username");
	   String password = input.get("password");
	   ProductCatalog productCatalog= login.logIntoApplication(username, password);	   
	   List<WebElement> items = productCatalog.getProductList();
	   int actualCount = items.size();
	   int expectedCount = 6;
	   Assert.assertEquals(actualCount, expectedCount, "6 Inventory items not displayed");	   
}

@Test(dataProvider = "getUserCredentials")
public void sortProductsByLowToHighPrice(HashMap<String, String> input) {

	  String username = input.get("username");
	   String password = input.get("password");
	   ProductCatalog productCatalog= login.logIntoApplication(username, password);	 
	   Map<String, List<Double>> prices = productCatalog.sortProductsByPrice("Price (low to high)");
	    Assert.assertEquals(prices.get("actual"), prices.get("expected"));	  

}
@Test(dataProvider = "getUserCredentials")
public void sortProductsByHighToLowPrice(HashMap<String, String> input) {

	  String username = input.get("username");
	   String password = input.get("password");
	   ProductCatalog productCatalog= login.logIntoApplication(username, password);	 
	   Map<String, List<Double>> prices = productCatalog.sortProductsByPrice("Price (high to low)");  
	    Assert.assertEquals(prices.get("actual"), prices.get("expected"));	   

}
 

@Test(dataProvider = "getData" ,dataProviderClass = E2ETest.class)
public void verifyProductDetailsNavigation(HashMap <String,String> input ) {
	
	String productNamefromFile = input.get("product");
	ProductCatalog productCatalog= login.logIntoApplication(input.get("username"), input.get("password"));
	ProductDetailPage productdetail = productCatalog.clickProductLink(productNamefromFile);
	String productName = productdetail.getProductName();
	 if (productName.equals(productNamefromFile)) {
		  System.out.println("Test Passed: Product detail page displayed correctly for '" + productNamefromFile + "'");
	    } else {
	        System.out.println("Test Failed: Expected '" + productNamefromFile + "' but found '" +productName + "'");
	    }
	 
	
} 

@Test(dataProvider = "getData" ,dataProviderClass = E2ETest.class)
public void addSingleItemtoCart(HashMap <String,String> input ) {
	 
	  String username = input.get("username");
	   String password = input.get("password");
	   ProductCatalog productCatalog= login.logIntoApplication(username, password);	   
	   productCatalog.addProductToCart(input.get("product"));
	   String badgevalue =productCatalog.returnCartBadgeVaue();
	   Assert.assertEquals(badgevalue, "1", "Cart badge value is not as expected!");
}

@Test(dataProvider = "getData" ,dataProviderClass = E2ETest.class)
public void addandRemoveProductfromCart(HashMap <String,String> input ) {
	 
	  String username = input.get("username");
	   String password = input.get("password");
	   String productName = input.get("product");
	   ProductCatalog productCatalog= login.logIntoApplication(username, password);	   
	   productCatalog.addProductToCart(productName);
	   String badgevalue =productCatalog.returnCartBadgeVaue();
	   Assert.assertEquals(badgevalue, "1", "Cart badge value is not as expected!");
	   productCatalog.removeProductFromCart(productName);
	   Assert.assertTrue(productCatalog.isCartBadgeGone(), "Cart badge should disappear after removing item.");
	   
}
	  @DataProvider
	  public Object[][] getUserCredentials() throws IOException
	  {  
		  List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\userCredentials.json");
		  return new Object[][] { { data.get(0) } };
		  
		  
	  }
}
 