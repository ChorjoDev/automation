@delete
Feature: Delete Computers

  As a user of the computer database system
  I want to be able to delete a computer in the database
  So that records can be removed

  # For these tests I would like to check database backend to confirm removal of the record

  Background:
    Given I am on the homepage of the computer database application

  Scenario: Delete a computer from the database
    And I select a record at random from the database
    When I click the "delete" button
    Then the computer delete banner is present
    And the number of records has been "decreased"
    And the record has been removed from the database

  Scenario: Delete a computer from the database and refresh page
    And I select a record at random from the database
    And I click the "delete" button
    When I refresh the page
    And the record has been removed from the database