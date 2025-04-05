package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object class representing the Login page of the Sauce Demo application.
 * Contains methods for logging into the application and retrieving error messages.
 */
public class LoginPage {

    WebDriver driver;

    /**
     * Constructor to initialize LoginPage with the provided WebDriver instance.
     * @param driver WebDriver instance passed from the test class
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElements for the Login page
    @FindBy(id = "user-name") WebElement Username;   // Username field
    @FindBy(id = "password") WebElement pwd;         // Password field
    @FindBy(name = "login-button") WebElement login;  // Login button
    @FindBy(css = "[data-test=\"error\"]") WebElement msg; // Error message (if any)
    @FindBy(xpath = "//div[@class='login_logo']") WebElement homepageTitle; // Page logo

    /**
     * Logs into the application with the provided username and password.
     * @param username The username to login with
     * @param password The password to login with
     * @return ProductCatalog instance after successful login
     */
    public ProductCatalog logIntoApplication(String username, String password) {
        Username.sendKeys(username);
        pwd.sendKeys(password);
        login.click();
        return new ProductCatalog(driver);
    }

    /**
     * Navigates to the Sauce Demo website.
     */
    public void goTo() {
        driver.get("https://www.saucedemo.com/");
    }

    /**
     * Returns the error message displayed on the login page.
     * @return The error message text
     */
    public String ErrorMessage() {
        return msg.getText();
    }

    /**
     * Verifies if the login page is displayed by checking the homepage title.
     * @return True if the login page is displayed, otherwise false
     */
    public boolean isLoginPageDisplayed() {
        return homepageTitle.getText().equalsIgnoreCase("Swag Labs");
    }
}
