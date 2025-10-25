@feature @login @auth
Feature: Login Functionality

  @smoke @positive @validLogin @ui @fast
  Scenario Outline: Valid login with correct credentials
    Given user is on the login page
    When user enters username "<username>" and password "<password>"
    And clicks on the login button
    Then user should land on "<expectedPageTitle>"

    Examples: 
      | username   | password | expectedPageTitle |
      | abcdef     | ABCdef   | Accounts Overview |
      | testuser01 | Pass@123 | Accounts Overview |
