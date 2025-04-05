package pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import abstractComponents.ParentObjectClass;

public class ProductCatalog extends ParentObjectClass  {
	
WebDriver driver;
	
	public ProductCatalog(WebDriver driver)

	{
	    super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".inventory_item") List<WebElement> listOfItems;
	@FindBy(css=".product_sort_container") WebElement sortDropdown;
	@FindBy(css=".inventory_item_price") List<WebElement> priceOfItems;
	@FindBy(className="shopping_cart_badge") WebElement cartbadge;
	@FindBy(className="shopping_cart_badge")List<WebElement> badges;
	By addToCart = By.cssSelector(".btn");
	By productToAdd = By.cssSelector(".inventory_item_name");
	By removeBtn = By.xpath(".//button[contains(text(),'Remove')]");
	WebElement item;
	
		
	public List<WebElement> getProductList()
	{		
		return listOfItems;
		
	}
	public WebElement getProductByName(String productName)
	{		
	      item =	getProductList().stream()
				.filter(s->s.findElement(productToAdd).getText().contains(productName))
				.findFirst().orElse(null);
     return item;
		
	}
	
	
	public void addProductToCart(String string) {
				
		getProductByName(string).findElement(addToCart).click();
			
	}
	public ProductDetailPage clickProductLink(String productName) {
		
		  WebElement productCard = getProductByName(productName);		    
		  productCard.findElement(productToAdd).click();
		    return new ProductDetailPage(driver);

			
	}
	
	public Map<String, List<Double>> sortProductsByPrice(String sortOrder) {
	    // Select sort option from dropdown
	    Select select = new Select(sortDropdown);
	    select.selectByVisibleText(sortOrder); 
	 
	    // Collect actual prices from UI
	    List<Double> actualPrices = new ArrayList<>();
	    for (WebElement priceOfItem : priceOfItems) {
	        String priceText = priceOfItem.getText().replace("$", "").trim();
	        actualPrices.add(Double.parseDouble(priceText));
	    }

	    // Sort expected prices based on the selected order
	    List<Double> expectedPrices = new ArrayList<>(actualPrices);
	    if (sortOrder.equalsIgnoreCase("Price (low to high)")) {
	        Collections.sort(expectedPrices);
	    } else if (sortOrder.equalsIgnoreCase("Price (high to low)")) {
	        expectedPrices.sort(Collections.reverseOrder());
	    } else {
	        throw new IllegalArgumentException("Invalid sort option provided: " + sortOrder);
	    }

	    // Prepare result map
	    Map<String, List<Double>> result = new HashMap<>();
	    result.put("actual", actualPrices);
	    result.put("expected", expectedPrices);
	    return result;
	}
  
   
	
	public String returnCartBadgeVaue()
	{
		return (cartbadge.getText());		
		
	}
	
	
	public void removeProductFromCart(String productName)
	{
		 WebElement productCard = getProductByName(productName);		
		 productCard.findElement(removeBtn).click();
	}
	
	
	public boolean isCartBadgeGone() {
	    return badges.isEmpty(); // returns true if no badge found
	}
	
	
	}
	

