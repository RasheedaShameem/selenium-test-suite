package abstractTest;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;


import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;

import pageObjects.LoginPage;

public class ParentClass {
	
	public WebDriver driver;
	public LoginPage login;

	
  @Test
  public WebDriver initializeDriver() throws IOException {
	  
	  
	  Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);

		String browserName = (System.getProperty("browser")) != null ? System.getProperty("browser")
				: prop.getProperty("browser");
		// String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions option = new ChromeOptions();
			  option.addArguments("-disable-notifications-");
			
			WebDriverManager.chromedriver().setup();

			if (browserName.contains("headless")) {
				option.addArguments("headless");
			}

			driver = new ChromeDriver(option);
			driver.manage().window().setSize(new Dimension(1440,900));   //to run in full screen in headless mode

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		return driver;
	}
	  
  public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// convert json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		//String to HashMap
		ObjectMapper mapper = new ObjectMapper();
		// convert to list of hashmap
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {

				});
		return data;

	}
  
  public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
  {
	  TakesScreenshot ts = (TakesScreenshot)driver;
	 File source =  ts.getScreenshotAs(OutputType.FILE);
	
	 File file = new File (System.getProperty("user.dir")+ "//reports//"+ testCaseName + ".png");
	 
    FileUtils.copyFile(source, file);
      return System.getProperty("user.dir")+ "//reports//"+ testCaseName + ".png";  //returns Path where output is stored
	  
  }
  
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() throws IOException {
	  

	  driver = initializeDriver();
	  login = new LoginPage(driver);
	   login.goTo();
     
   
	  }
	  
  
   @AfterMethod(alwaysRun=true)
 public void closeWindow() {
		driver.close();
	}

}
