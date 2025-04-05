package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ParentObjectClass;

public class CheckoutPage2 extends ParentObjectClass{

	WebDriver driver;
	
	public CheckoutPage2(WebDriver driver ) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
		@FindBy(name="finish") WebElement finishbtn;

		public OrderConfimation finishOrder() {
			finishbtn.click();
			return new OrderConfimation(driver);
		}
		
	}

	
	
