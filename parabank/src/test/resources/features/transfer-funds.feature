@feature @transferfunds
Feature: Transfer Funds

  Background:
    Given user is logged into Parabank with username "testuser01" and password "Pass@123"
    And the user has at least two accounts

  Scenario: Transfer funds between user's accounts
    When the user transfers "$500" between their accounts
    Then a transfer confirmation message should be displayed
   