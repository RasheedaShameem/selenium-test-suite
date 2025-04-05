package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ParentObjectClass;

/**
 * Page Object class representing the Order Confirmation page of the Sauce Demo application.
 * Contains methods to retrieve the order confirmation message.
 */
public class OrderConfimation extends ParentObjectClass {

    WebDriver driver;

    /**
     * Constructor to initialize the OrderConfimation page with the provided WebDriver instance.
     * @param driver WebDriver instance passed from the test class
     */
    public OrderConfimation(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElement for the order confirmation message displayed after the order is completed
    @FindBy(className = "complete-header") WebElement confimationMsg;

    /**
     * Retrieves the text of the order confirmation message.
     * @return The text of the order confirmation message
     */
    public String getTextInOrderPage() {
        String Msg = confimationMsg.getText();
        return Msg;
    }
}
