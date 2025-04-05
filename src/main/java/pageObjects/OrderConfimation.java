package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ParentObjectClass;

public class OrderConfimation extends ParentObjectClass{

	WebDriver driver;
	
	public OrderConfimation(WebDriver driver ) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className="complete-header") WebElement confimationMsg;
	
	public String getTextInOrderPage() {
		String Msg = confimationMsg.getText();
		return Msg;
	}
		
	
	}

	
	
