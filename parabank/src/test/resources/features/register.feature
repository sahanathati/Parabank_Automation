@feature @registration @ui
Feature: User Registration Functionality

@smoke @positive @newUser @fast
Scenario: User registers with valid details from data table
  Given user is on the registration page
  When user fills the registration form with:
    | firstName | John       |
    | lastName  | Doe        |
    | address   | 123 Main St|
    | city      | New York   |
    | state     | NY         |
    | zipCode   | 10001      |
    | phone     | 1234567890 |
    | ssn       | 987-65-4321|
    | username  | testuser01 |
    | password  | Pass@123   |
  And submits the registration form
  Then user should see a confirmation message "Your account was created successfully. You are now logged in."
