Feature: Testing adding new expenses
  Scenario: Adding new expense
    Given that I am on the main page
    When I press on add new expense
    Then the form to add expense is visible
    And title textbox is on the left of amount text box
    And date textbox is below title textbox
    When I enter "Test" as title
    And I enter 12 as amount
    And I enter today as date
    And press on add expense
    Then expense "Test" is visible on overview