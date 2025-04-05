package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.ParentObjectClass;

/**
 * Page Object class representing the second step of the Checkout process
 * in the Sauce Demo application. Responsible for placing the final order.
 */
public class CheckoutPage2 extends ParentObjectClass {

    WebDriver driver;

    /**
     * Constructor to initialize CheckoutPage2 with the provided WebDriver instance.
     * @param driver WebDriver instance passed from the previous page
     */
    public CheckoutPage2(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElement for the "Finish" button on the checkout page
    @FindBy(name = "finish") 
    WebElement finishbtn;

    /**
     * Clicks the Finish button to submit the order.
     * @return OrderConfimation instance representing the order confirmation page
     */
    public OrderConfimation finishOrder() {
        finishbtn.click();
        return new OrderConfimation(driver);
    }
}
