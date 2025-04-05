package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abstractTest.ParentClass;
import abstractTest.RetryFlakyTests;
import pageObjects.CartPage;
import pageObjects.CheckoutPage1;
import pageObjects.CheckoutPage2;
import pageObjects.OrderConfimation;
import pageObjects.ProductCatalog;

public class E2ETest extends ParentClass {

    /**
     * End-to-End Test Case: Submits an order from login to order confirmation.
     * - Logs into the application
     * - Adds a product to the cart
     * - Proceeds through the checkout process
     * - Verifies the final confirmation message
     *
     * Retry is enabled via RetryFlakyTests for flaky test handling.
     */
    @Test(dataProvider = "getData", retryAnalyzer = RetryFlakyTests.class, groups = { "E2ETest" })
    public void submitOrder(HashMap<String, String> input) {
        // Personal information for checkout
        String firstname = "john";
        String lastname = "peter";
        String pcode = "560022";

        // Step 1: Login
        ProductCatalog productCatalog = login.logIntoApplication(input.get("username"), input.get("password"));

        // Step 2: Add product to cart
        productCatalog.addProductToCart(input.get("product"));

        // Step 3: Navigate to cart and initiate checkout
        CartPage cartPage = productCatalog.gotoCart();
        CheckoutPage1 checkoutPage1 = cartPage.checkout();

        // Step 4: Fill in user information and continue
        checkoutPage1.enterPersonalInfo(firstname, lastname, pcode);
        CheckoutPage2 checkoutPage2 = checkoutPage1.continuetocheckout();

        // Step 5: Finish order and verify confirmation
        OrderConfimation confirmationPage = checkoutPage2.finishOrder();
        Assert.assertEquals(confirmationPage.getTextInOrderPage(), "Thank you for your order!");
    }

    /**
     * Data Provider: Supplies test data from a JSON file (orderData.json)
     * Each map contains username, password, and product name.
     */
    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(
            System.getProperty("user.dir") + "\\src\\test\\java\\data\\orderData.json"
        );

        return new Object[][] { { data.get(0) }, { data.get(1) } };
    }
}
