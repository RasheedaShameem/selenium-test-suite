package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abstractTest.ParentClass;
import pageObjects.ProductCatalog;
import pageObjects.ProductDetailPage;

public class InventoryPageValidation extends ParentClass {

    /**
     * Test to verify that 6 inventory items are displayed after login
     */
    @Test(dataProvider = "getUserCredentials")
    public void VerifyItemCount(HashMap<String, String> input) {
        ProductCatalog productCatalog = login.logIntoApplication(input.get("username"), input.get("password"));

        // Fetch all products listed on the inventory page
        List<WebElement> items = productCatalog.getProductList();

        // Verify the total number of items displayed
        int actualCount = items.size();
        int expectedCount = 6;
        Assert.assertEquals(actualCount, expectedCount, "6 Inventory items not displayed");
    }

    /**
     * Test to verify sorting products from Low to High price
     */
    @Test(dataProvider = "getUserCredentials")
    public void sortProductsByLowToHighPrice(HashMap<String, String> input) {
        ProductCatalog productCatalog = login.logIntoApplication(input.get("username"), input.get("password"));

        // Perform and validate sorting by Low to High price
        Map<String, List<Double>> prices = productCatalog.sortProductsByPrice("Price (low to high)");
        Assert.assertEquals(prices.get("actual"), prices.get("expected"));
    }

    /**
     * Test to verify sorting products from High to Low price
     */
    @Test(dataProvider = "getUserCredentials")
    public void sortProductsByHighToLowPrice(HashMap<String, String> input) {
        ProductCatalog productCatalog = login.logIntoApplication(input.get("username"), input.get("password"));

        // Perform and validate sorting by High to Low price
        Map<String, List<Double>> prices = productCatalog.sortProductsByPrice("Price (high to low)");
        Assert.assertEquals(prices.get("actual"), prices.get("expected"));
    }

    /**
     * Test to validate navigation to product detail page on clicking a product title
     */
    @Test(dataProvider = "getData", dataProviderClass = E2ETest.class)
    public void verifyProductDetailsNavigation(HashMap<String, String> input) {
        String productNameFromFile = input.get("product");

        // Login and click on a product title
        ProductCatalog productCatalog = login.logIntoApplication(input.get("username"), input.get("password"));
        ProductDetailPage productDetail = productCatalog.clickProductLink(productNameFromFile);

        // Validate the correct product detail page is displayed
        String actualProductName = productDetail.getProductName();
        if (actualProductName.equals(productNameFromFile)) {
            System.out.println("Test Passed: Product detail page displayed correctly for '" + productNameFromFile + "'");
        } else {
            System.out.println("Test Failed: Expected '" + productNameFromFile + "' but found '" + actualProductName + "'");
        }
    }

    /**
     * Test to add a single item to cart and validate the cart badge
     */
    @Test(dataProvider = "getData", dataProviderClass = E2ETest.class)
    public void addSingleItemtoCart(HashMap<String, String> input) {
        ProductCatalog productCatalog = login.logIntoApplication(input.get("username"), input.get("password"));

        // Add one product and verify the cart badge shows '1'
        productCatalog.addProductToCart(input.get("product"));
        String badgeValue = productCatalog.returnCartBadgeVaue();
        Assert.assertEquals(badgeValue, "1", "Cart badge value is not as expected!");
    }

    /**
     * Test to add and then remove a product from cart, validating the cart badge disappears
     */
    @Test(dataProvider = "getData", dataProviderClass = E2ETest.class)
    public void addandRemoveProductfromCart(HashMap<String, String> input) {
        ProductCatalog productCatalog = login.logIntoApplication(input.get("username"), input.get("password"));

        // Add a product to the cart
        productCatalog.addProductToCart(input.get("product"));
        String badgeValue = productCatalog.returnCartBadgeVaue();
        Assert.assertEquals(badgeValue, "1", "Cart badge value is not as expected!");

        // Remove the same product from the cart
        productCatalog.removeProductFromCart(input.get("product"));
        Assert.assertTrue(productCatalog.isCartBadgeGone(), "Cart badge should disappear after removing item.");
    }

    /**
     * Data provider method to supply login credentials from JSON file
     */
    @DataProvider
    public Object[][] getUserCredentials() throws IOException {
        // Load credentials from JSON file
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
                + "\\src\\test\\java\\data\\userCredentials.json");

        return new Object[][] { { data.get(0) } };
    }
}