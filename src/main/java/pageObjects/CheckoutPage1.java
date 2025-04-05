package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ParentObjectClass;

public class CheckoutPage1 extends ParentObjectClass{

	WebDriver driver;
	
	public CheckoutPage1(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(id="first-name") WebElement fname;
	@FindBy(id="last-name") WebElement lname;
	@FindBy(id="postal-code") WebElement postcalcode;
	@FindBy(xpath ="//h3[@data-test='error']") WebElement errorMsg;
	
	@FindBy(css="[value='Continue']") WebElement cntinuebtn;

	public void enterPersonalInfo(String firstname, String lastname, String pcode) {

    fname.sendKeys(firstname);
    lname.sendKeys(lastname);
    postcalcode.sendKeys(pcode);
		
	}

	public CheckoutPage2 continuetocheckout() {
	
		cntinuebtn.click();
		return new CheckoutPage2(driver);
	}
	
	public String validateError() {
		
		cntinuebtn.click();
		String msgText =errorMsg.getText();
		return msgText;
	}
	
		
	}

	
	
