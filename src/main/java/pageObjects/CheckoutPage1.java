package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ParentObjectClass;

/**
 * Page Object class representing the first step of the Checkout page
 * in the Sauce Demo application. Handles user input for personal information
 * and error validation.
 */
public class CheckoutPage1 extends ParentObjectClass{

	WebDriver driver;
	
	 /**
     * Constructor to initialize the CheckoutPage1 with WebDriver instance.
     * @param driver WebDriver instance passed from the previous page
     */
	public CheckoutPage1(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(id="first-name") WebElement fname;
	@FindBy(id="last-name") WebElement lname;
	@FindBy(id="postal-code") WebElement postcalcode;
	@FindBy(xpath ="//h3[@data-test='error']") WebElement errorMsg;
	
	@FindBy(css="[value='Continue']") WebElement contbtn;
   
	 /**
     * Enters user's personal information into the checkout form.
     * @param firstname First name
     * @param lastname Last name
     * @param pcode Postal code
     */
	public void enterPersonalInfo(String firstname, String lastname, String pcode) {

    fname.sendKeys(firstname);
    lname.sendKeys(lastname);
    postcalcode.sendKeys(pcode);
		
	}
  
	  /**
     * Clicks the Continue button to proceed to the next checkout step.
     * @return CheckoutPage2 instance representing the next page
     */
	public CheckoutPage2 continuetocheckout() {
	
		contbtn.click();
		return new CheckoutPage2(driver);
	}
	
	
	  /**
     * Validates and returns the error message displayed when required fields are missing.
     * @return Error message text
     */	
	public String validateError() {
		
		contbtn.click();
		String msgText =errorMsg.getText();
		return msgText;
	}
	
		
	}

	
	
