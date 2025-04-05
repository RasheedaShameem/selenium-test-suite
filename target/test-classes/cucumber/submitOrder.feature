#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

@tag
Feature: Purchase an item from Ecommerce website

Background: 
Given I landed on Saucelabs website

@Regression
 Scenario: E2E test
   Given Logged in with "standard_user" and "secret_sauce"
    When I add the product "Sauce Labs Onesie" from cart
    And Checkout "Sauce Labs Onesie" and submit the order
    Then verify  "Thank you for your order!" message is displayed on ConfirmationPage
    
 
#  Scenario Outline: E2E test
   # Given Logged in with <username> and <password>
  #  When I add the product <productName> from cart
  #  And Checkout <productName> and submit the order
 #   Then verify  "Thank you for your order!" message is displayed on ConfirmationPage

   # Examples: 
   #   |username                 |password				|productName				|
  #    |performance_glitch_user  |secret_sauce		|Sauce Labs Onesie	|
   #   |standard_user            |secret_sauce		|Sauce Labs Backpack|
