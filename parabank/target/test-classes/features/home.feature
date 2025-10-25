@smoke @login @commons
Feature: Common Login Functionality
  As a user of the Loan Application Portal
  I want to be able to log in with valid credentials
  So that I can access role-specific features securely

  Background:
    Given the login page is displayed

  @positive @role_based
  Scenario Outline: Successful login for different user roles
    When the user enters username "<username>" and password "<password>"
    And clicks on the login button
    Then the user should be redirected to the "<dashboard>" dashboard page
    And the welcome message for "<role>" should be displayed

    Examples:
      | username       | password  | role          | dashboard      |
      | loanofficer1   | Pass@123  | Loan Officer  | loan_officer   |
      | underwriter1   | Pass@123  | Underwriter   | underwriter    |
      | manager1       | Pass@123  | Manager       | manager        |
      | admin1         | Pass@123  | Admin         | admin          |

  @negative @invalid_credentials
  Scenario Outline: Login attempt with invalid credentials
    When the user enters username "<username>" and password "<password>"
    And clicks on the login button
    Then the error message "Invalid username or password." should be displayed
    And the user should remain on the login page

    Examples:
      | username       | password   |
      | invalidUser    | Pass@123   |
      | loanofficer1   | wrongPass  |
      | ""             | ""         |

  @negative @locked_account
  Scenario: Login attempt with locked user account
    Given the user account "lockedUser" is locked
    When the user enters username "lockedUser" and password "Pass@123"
    And clicks on the login button
    Then the error message "Your account is locked. Please contact support." should be displayed
    And the user should remain on the login page

  @negative @password_expired
  Scenario: Login attempt with expired password
    Given the user account "expiredUser" has expired password
    When the user enters username "expiredUser" and password "Pass@123"
    And clicks on the login button
    Then the user should be redirected to the password reset page
    And a message "Your password has expired. Please reset your password." should be displayed
