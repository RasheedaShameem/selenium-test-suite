# Sauce Demo Automation Test Suite
This is an automation test suite built using **Selenium WebDriver** to validate key functionalities of the **Sauce Demo** website.

### Test Scenarios
1. **Login and Logout Flow**
2. **CartPageValidation**  
   - addMultipleItemsAndVerifyInCart
3. **E2ETest**  
   - submitOrder
4. **Error Validation**  
   - lockedUser  
   - performanceGlitch  
   - InvalidLogin  
   - checkoutWithMissingFirstName
5. **Inventory Page Validation**  
   - VerifyItemCount  
   - sortProductsByLowToHighPrice  
   - sortProductsByHighToLowPrice  
   - verifyProductDetailsNavigation  
   - addandRemoveProductfromCart


## Getting Started

### Prerequisites
- **Java 8 or later**
- **Maven** (for dependencies management)
- **Selenium WebDriver**

### Installation

1. Clone the repository to your local machine:
   git clone https://github.com/RasheedaShameem/selenium-test-suite.git

2. Ensure that you have Maven installed on your machine. If not, download and install it from the official Maven website.
   Install the required dependencies by running:
   ** mvn clean install**

3. Running the Tests
   To run all the tests, use the following Maven command:
  ** mvn test**
   This will trigger the test suite and execute all the test cases.

4. Reports and Screenshots on Failure
Reports and screenshots are automatically generated and saved in the output folder.
The report provides detailed information about test execution, including pass/fail status and error logs.
Screenshots are captured on test failures and saved in the output folder to help with debugging.
The output folder typically contains:
HTML reports
Screenshots (for failed tests)


