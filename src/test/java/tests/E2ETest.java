package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import abstractTest.ParentClass;
import pageObjects.CartPage;
import pageObjects.CheckoutPage1;
import pageObjects.CheckoutPage2;
import pageObjects.OrderConfimation;
import pageObjects.ProductCatalog;

import abstractTest.RetryFlakyTests;


public class E2ETest extends ParentClass {
	
  @Test(dataProvider= "getData", retryAnalyzer=RetryFlakyTests.class,groups={"E2ETest"})
  public void submitOrder(HashMap <String,String> input ) {
	  
		String firstname = "john";
		String lastname = "peter";
		String pcode = "560022";

		ProductCatalog prdtctgobj= login.logIntoApplication(input.get("username"), input.get("password"));
		prdtctgobj.addProductToCart(input.get("product"));
		CartPage cartpage = prdtctgobj.gotoCart();
		CheckoutPage1 choutpage1 =cartpage.checkout();
		choutpage1.enterPersonalInfo(firstname,lastname,pcode);
		CheckoutPage2 choutpage2 = choutpage1.continuetocheckout();		
		OrderConfimation ocp = choutpage2.finishOrder();
		Assert.assertEquals(ocp.getTextInOrderPage(),"Thank you for your order!");
		
	  
  }
  
 
  @DataProvider
  public Object[][] getData() throws IOException
  {
	  
	  /*HashMap<String,String> map1= new HashMap<String,String>();
	  map1.put("email", "standard_user");
	  map1.put("password", "secret_sauce");
	  map1.put("productName", "Sauce Labs Onesie");
	  HashMap<String,String> map2= new HashMap<String,String>();
	  map2.put("email", "standard_user");
	  map2.put("password", "secret_sauce");
	  map2.put("productName", "Sauce Labs Backpack");*/
	 // return new Object[][] { {map1},{map2}};
	  
	  List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\data\\orderData.json");
	  return new Object [][] {{data.get(0)}, {data.get(1)}};
  }
  
}
