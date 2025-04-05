package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponents.ParentObjectClass;

/**
 * Page Object class representing the Product Detail page of the Sauce Demo application.
 * Provides methods to interact with the details of a specific product.
 */
public class ProductDetailPage extends ParentObjectClass {

    WebDriver driver;

    /**
     * Constructor to initialize the ProductDetailPage with the provided WebDriver instance.
     * @param driver WebDriver instance passed from the test class
     */
    public ProductDetailPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElement for the product name displayed on the product detail page
    @FindBy(xpath = "//div[@class='inventory_details_name large_size']") WebElement productName;

    /**
     * Retrieves the name of the product displayed on the product detail page.
     * @return The name of the product as a String
     */
    public String getProductName() {
        return productName.getText();
    }
}
