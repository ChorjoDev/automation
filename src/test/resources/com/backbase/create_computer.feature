@create
Feature: Create Computers

  As a user of the computer database system
  I want to be able to add a computer to the database
  So that I can keep a record of the computer

  Background:
    Given I am on the homepage of the computer database application

  @test
  Scenario: Create a computer
    And I have a computer to add to the database
    And I click the "add a new computer" button
    And I complete all the fields to create a new computer
    When I click the "create this computer" button
    Then the computer created banner is present
    And the number of records has been "increased"
    And I am able to find the record in the database

  # Expecting this test to Fail as the app doesnt have validation and doesnt on submission
  @defect
  Scenario: Attempt to create a computer with maximum amount of characters
    And I have a computer to add that has a name length of 15000 characters
    And I click the "add a new computer" button
    And I complete all the fields to create a new computer
    When I click the "create this computer" button
    Then the validation error appears on the "computer name" field

  Scenario: Attempt to create a computer with a blank name
    And I have a computer to add with a blank name
    And I click the "add a new computer" button
    And I complete all the fields to create a new computer
    When I click the "create this computer" button
    Then the validation error appears on the "computer name" field

  @defect # Expected failures due to broken validation on the date fields
  Scenario Outline: Attempt to create a computer with an introduced date that doesnt follow yyyy-MM-dd format
    And I have a computer to add to the database
    And I click the "add a new computer" button
    And I enter the "<date>" into the "<field>" field
    When I click the "create this computer" button
    Then the validation error appears on the "<field>" field

    Examples: # Would auto generate the dates or randomly select from file
      | date        | field      |
      | 9999-13-32  | introduced |
      | 2021-02-30  | introduced |
      | 1999-00-01  | introduced |
      | 3000-00-00  | introduced |
      | 99999-01-01 | introduced |
      | 99-01-01    | introduced |
      | 01-01-01    | introduced |
      | 00000-01-01 | introduced |
      | 2000-1-1    | introduced |
      | 2000-011-01 | introduced |
      | 2000-11-011 | introduced |

  # Duplicated this test for a different field as it can be run in parallel
  # Expected failures due to broken validation on the date fields
  @defect
  Scenario Outline: Attempt to create a computer with an discontinued date that doesnt follow yyyy-MM-dd format
    And I have a computer to add to the database
    And I click the "add a new computer" button
    And I enter the "<date>" into the "<field>" field
    When I click the "create this computer" button
    Then the validation error appears on the "<field>" field

    Examples:
      | date         | field        |
      | 0000-13-32   | discontinued |
      | 0000-00-01   | discontinued |
      | 3000-00-00   | discontinued |
      | 12345-01-01  | discontinued |
      | 99-01-01     | discontinued |
      | 000000-01-01 | discontinued |
      | 2000-1-1     | discontinued |
      | 2000-011-01  | discontinued |
      | 2000-11-011  | discontinued |
      | 01-01-01     | discontinued |

  Scenario: Click cancel on create new computer page
    And I have a computer to add to the database
    And I click the "add a new computer" button
    And I complete all the fields to create a new computer
    When I click the "cancel" button
    Then I am taken back to the homepage

  # Duplicated this test for a different field as it can be run in parallel
  Scenario: Click back button on create new computer page
    And I have a computer to add to the database
    And I click the "add a new computer" button
    And I complete all the fields to create a new computer
    When I click the "back" button
    Then I am taken back to the homepage