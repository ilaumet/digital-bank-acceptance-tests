Feature: Creating a new checking account

  Scenario: Create a standard individual checking account
    Given the user logged in as "elona@gmail.com" "Elonamuska1!"
    When the user creates a new checking account with the following data
      |checkingAccountType | accountOwnership | accountName                 | initialDepositAmount |
      |Standard Checking   | Individual       | Elona Muska Second Checking | 100000.00            |
    Then the user should see "Successfully created new Standard Checking account named Elona Muska Second Checking"
    And the user should see newly added account card
      |accountName                 | accountType      | ownership  | accountNumber  | interestRate | balance    |
      |Elona Muska Second Checking | Standard Checking| Individual | 486133894      |  0.0%        | $100000.00 |
    And the user should see the following transactions
      | date             | category | description               | amount     | balance     |
      | 2024-01-02 07:09 | Income   | 845325114 (DPT) - Deposit | $100000.00 | $100000.00  |