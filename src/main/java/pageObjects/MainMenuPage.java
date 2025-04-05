package pageObjects;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponents.ParentObjectClass;

/**
 * Page Object class representing the Main Menu page of the Sauce Demo application.
 * Contains methods for interacting with the main menu and logging out.
 */
public class MainMenuPage extends ParentObjectClass {

    WebDriver driver;

    /**
     * Constructor to initialize MainMenuPage with the provided WebDriver instance.
     * @param driver WebDriver instance passed from the test class
     */
    public MainMenuPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElements for the Main Menu page
    @FindBy(id = "react-burger-menu-btn") WebElement mainmenu;  // Main menu button
    @FindBy(id = "logout_sidebar_link") WebElement logoutlink; // Logout link in the menu
    @FindBy(css = ".inventory_item_name") List<WebElement> cartItemNames; // List of items in the cart

    /**
     * Clicks the main menu button to open the menu.
     */
    public void clickmainmenu() {
        mainmenu.click();
    }

    /**
     * Clicks the logout link in the main menu to log out of the application.
     * It waits until the logout link is clickable before clicking it.
     */
    public void clickLogoutFromMenu() {
        clickmainmenu(); // Opens the main menu

        // Waits for the logout link to be clickable (timeout 10 seconds)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(logoutlink));
        logoutlink.click(); // Clicks the logout link
    }
}
