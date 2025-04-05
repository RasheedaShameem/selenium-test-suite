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

/**
 * Page Object class representing the Product Catalog page of the Sauce Demo application.
 * Provides methods to interact with product listings, sorting, and shopping cart functionalities.
 */
public class ProductCatalog extends ParentObjectClass {

    WebDriver driver;

    /**
     * Constructor to initialize the ProductCatalog page with the provided WebDriver instance.
     * @param driver WebDriver instance passed from the test class
     */
    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // WebElements for interacting with the product catalog page
    @FindBy(css = ".inventory_item") List<WebElement> listOfItems;
    @FindBy(css = ".product_sort_container") WebElement sortDropdown;
    @FindBy(css = ".inventory_item_price") List<WebElement> priceOfItems;
    @FindBy(className = "shopping_cart_badge") WebElement cartbadge;
    @FindBy(className = "shopping_cart_badge") List<WebElement> badges;

    // Locators for adding/removing items to/from the cart
    By addToCart = By.cssSelector(".btn");
    By productToAdd = By.cssSelector(".inventory_item_name");
    By removeBtn = By.xpath(".//button[contains(text(),'Remove')]");
    WebElement item;

    /**
     * Retrieves the list of all product items displayed on the page.
     * @return List of WebElements representing products
     */
    public List<WebElement> getProductList() {
        return listOfItems;
    }

    /**
     * Retrieves a product by its name.
     * @param productName The name of the product to search for
     * @return WebElement representing the product
     */
    public WebElement getProductByName(String productName) {
        item = getProductList().stream()
            .filter(s -> s.findElement(productToAdd).getText().contains(productName))
            .findFirst().orElse(null);
        return item;
    }

    /**
     * Adds a specified product to the shopping cart.
     * @param productName The name of the product to add
     */
    public void addProductToCart(String productName) {
        getProductByName(productName).findElement(addToCart).click();
    }

    /**
     * Clicks on a product link and navigates to the product detail page.
     * @param productName The name of the product to click
     * @return A ProductDetailPage object
     */
    public ProductDetailPage clickProductLink(String productName) {
        WebElement productCard = getProductByName(productName);
        productCard.findElement(productToAdd).click();
        return new ProductDetailPage(driver);
    }

    /**
     * Sorts the products by price in either ascending or descending order.
     * @param sortOrder The sorting order, either "Price (low to high)" or "Price (high to low)"
     * @return A map containing the actual and expected prices after sorting
     */
    public Map<String, List<Double>> sortProductsByPrice(String sortOrder) {
        // Select the sorting option from the dropdown
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(sortOrder);

        // Collect the actual prices from the product list
        List<Double> actualPrices = new ArrayList<>();
        for (WebElement priceOfItem : priceOfItems) {
            String priceText = priceOfItem.getText().replace("$", "").trim();
            actualPrices.add(Double.parseDouble(priceText));
        }

        // Sort the prices according to the specified order
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        if (sortOrder.equalsIgnoreCase("Price (low to high)")) {
            Collections.sort(expectedPrices);
        } else if (sortOrder.equalsIgnoreCase("Price (high to low)")) {
            expectedPrices.sort(Collections.reverseOrder());
        } else {
            throw new IllegalArgumentException("Invalid sort option provided: " + sortOrder);
        }

        // Prepare the result map
        Map<String, List<Double>> result = new HashMap<>();
        result.put("actual", actualPrices);
        result.put("expected", expectedPrices);
        return result;
    }

    /**
     * Retrieves the value displayed on the shopping cart badge (number of items in the cart).
     * @return The number of items in the cart
     */
    public String returnCartBadgeVaue() {
        return cartbadge.getText();
    }

    /**
     * Removes a specified product from the shopping cart.
     * @param productName The name of the product to remove
     */
    public void removeProductFromCart(String productName) {
        WebElement productCard = getProductByName(productName);
        productCard.findElement(removeBtn).click();
    }

    /**
     * Checks if the cart badge is empty (i.e., no items in the cart).
     * @return true if the cart badge is empty, false otherwise
     */
    public boolean isCartBadgeGone() {
        return badges.isEmpty(); // returns true if no badge found
    }
}
