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

public class MainMenuPage extends ParentObjectClass {
	
	
   WebDriver driver;
 
	public MainMenuPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="react-burger-menu-btn") WebElement mainmenu;
	@FindBy(id ="logout_sidebar_link")WebElement logoutlink;
	@FindBy(css = ".inventory_item_name") List<WebElement> cartItemNames;
	
	public void clickmainmenu()
	{
		mainmenu.click();

	}
	public void clickLogoutFromMenu()
	{
		clickmainmenu();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(logoutlink));
		logoutlink.click();

	}
	
	
	
}