@feature @accounts @overview
Feature: Account Services Functionality

  Background:
    Given user is on the login page
    When user enters username "abcdef" and password "ABCdef"
    And clicks on the login button

  @smoke @overview @ui
  Scenario: User views account overview page
    Then account overview section should be displayed
    And account summary table should contain at least one account