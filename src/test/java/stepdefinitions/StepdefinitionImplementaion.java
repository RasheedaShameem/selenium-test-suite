package stepdefinitions;

import java.io.IOException;

import org.testng.Assert;

import abstractTest.ParentClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.CartPage;
import pageObjects.CheckoutPage1;
import pageObjects.CheckoutPage2;
import pageObjects.OrderConfimation;
import pageObjects.ProductCatalog;

public class StepdefinitionImplementaion extends ParentClass{
	
	public ProductCatalog prdtctgobj;
	public 	CartPage cartpage;
	public CheckoutPage1 choutpage1;
	public 	OrderConfimation ocp;
	String firstname = "john";
	String lastname = "peter";
	String pcode = "560022";
	
	@Given("I landed on Saucelabs website")
	public void I_landed_on_Saucelabs_website() throws IOException
	{
		
		beforeMethod();
		
	}
	
	 @Given("Logged in with {string} and {string}")
	   public void Logged_in_with_username_and_password(String Username, String password)
	   {
		   System.out.println(Username+""+password);
		   prdtctgobj= login.logIntoApplication(Username, password);
		   
		   
	   }
	 

/*   @Given("^Logged in with(.+) and (.+)$")
   public void Logged_in_with_username_and_password(String Username, String password)
   {
	   System.out.println(Username+""+password);
	   prdtctgobj= login.logIntoApplication(Username, password);
	   
	   
   }
   
   */
	
   @When("I add the product {string} from cart")
   public void I_add_the_product_from_cart(String productName)
   {
		prdtctgobj.addProductToCart(productName);
	  cartpage = prdtctgobj.gotoCart();
	   
   }
   
   @When("Checkout {string} and submit the order")
   public void Checkout_productName_and_submit_the_order(String productName)
   {
	     choutpage1 =cartpage.checkout();
	   choutpage1.enterPersonalInfo(firstname,lastname,pcode);
		CheckoutPage2 choutpage2 = choutpage1.continuetocheckout();		
	     ocp = choutpage2.finishOrder();
		
	   
   }
   
   @Then("verify  {string} message is displayed on ConfirmationPage")
   public void verify_Than_you_message_is_displayed_on_ConfirmationPage(String msg)
   {
	   Assert.assertEquals(ocp.getTextInOrderPage(),msg);
	   driver.close();
		
   }
}
