Feature: Add to cart feature

  Scenario: Select any product and add it to cart
    Given I am on the Home Page
    When I fill search field with Printed Summer Dress
    And I click on loop button
    And I click the product with index 0
    And I click the add to cart button
    And I click Proceed to checkout button
    Then The product with the name Printed Summer Dress is in the cart

