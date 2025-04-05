package pageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage  {

	WebDriver driver;
	
	public LoginPage(WebDriver driver)

	{
		//super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="user-name") WebElement Username;
	@FindBy(id="password") WebElement pwd;
	@FindBy(name="login-button") WebElement login;
	@FindBy(css="[data-test=\"error\"]") WebElement msg;
	@FindBy(xpath ="//div[@class='login_logo']" ) WebElement homepageTitle;
			
	

	public ProductCatalog logIntoApplication(String username, String password)
	
	{
		Username.sendKeys(username);
		pwd.sendKeys(password);
		login.click();
		return new ProductCatalog(driver);

	}


	public void goTo() {
		
		 driver.get("https://www.saucedemo.com/");
		// TODO Auto-generated method stub
		
	}
	public String ErrorMessage()
	{
		return msg.getText();		
		
	}
  
	
	public boolean isLoginPageDisplayed()
	{  
		if(homepageTitle.getText().equalsIgnoreCase("Swag Labs"))		
		return true;
		else
			return false;
		
		
	}
	
}


