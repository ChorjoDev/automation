@update
Feature: Update Computers

  As a user of the computer database system
  I want to be able to update a computer in the database
  So that records can remain up to date and corrected

  # TODO - Test boundary dates - would need to find the boundaries of the dates from requirements

  # Due to the invalid date validation then dates that
  # are not possible will have a knock on effect to the dates
  # being converted to a display date as they aren't valid dates

  Background:
    Given I am on the homepage of the computer database application

  # This below tests are flaky - I didn't want to amend this to
  # satisfy the curernt behaviour. The reason it is flaky
  # is the database accepts duplicate records. I feel
  # that is not correct, but if so the test would need to
  # be refactored
  Scenario: Update the name of a computer in the database
    And I select a record at random from the database
    And I edit the "name" field
    When I click the "save" button
    Then the "name" field has been updated

  Scenario: Update the introduced date of a computer in the database
    And I select a record at random from the database
    And I edit the "introduced" field
    When I click the "save" button
    Then the "introduced" field has been updated

  Scenario: Update the discontinued date of a computer in the database
    And I select a record at random from the database
    And I edit the "discontinued" field
    When I click the "save" button
    Then the "discontinued" field has been updated

  Scenario: Update the manufacturer of a computer in the database
    And I select a record at random from the database
    And I edit the "manufacturer" field
    When I click the "save" button
    Then the "manufacturer" field has been updated

  Scenario: Update a record in the database with empty name
    And I select a record at random from the database
    And I edit the "name" field with ""
    When I click the "save" button
    Then the validation error appears on the "computer name" field

  # Due to the formatting issues of the data fields then it would be
  # pointless duplicating these tests from the create page.
  Scenario Outline: Update a record in the database with an invalid date
    And I select a record at random from the database
    And I edit the "<field name>" field with "<value>"
    When I click the "save" button
    Then the validation error appears on the "<field name>" field
    Examples:
      | field name   | value    |
      | introduced   | AA-0A-AA |
      | discontinued | 2000-A-A |
