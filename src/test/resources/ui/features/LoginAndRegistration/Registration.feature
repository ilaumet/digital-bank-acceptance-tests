@Registration

Feature: Digital Bank Registration Page

  Background:
    Given The user with "alia@gmail.com" is not in DB
    And User navigates to Digital Bank signup page

@Test
  Scenario: Positive Case. As a user, I want to create Digital Bank account
    When User creates account with  following fields
      |title | firstName | lastName   | gender | dob        |ssn          |email          |password | address     | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone |termsCheckMark|
      | Mr.  | Jack      | Test       | M      | 12/12/1990 | 333-44-4454 |alia@gmail.com |Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591008 | 543254323   | 123567321 |true          |
    Then User should be displayed with the message "Success Registration Successful. Please Login."
    Then the following user info should be saved in the db
      |title | firstName | lastName   | gender | dob        |ssn          |email          |password | address     | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone |accountNonExpired|accountNonLocked|credentialsNonExpired|enabled|
      | Mr.  | Jack      | Test       | M      | 12/12/1990 | 333-44-4454 |alia@gmail.com |Tester123 | 12 Main st | City     | CA     | 99921      | US      | 2146591008 | 543254323   | 123567321 |true             |  true          |true                 | true  |

    @NegativeRegistrationCases
Scenario Outline: Negative Test Cases. As a Digital Bank Admin I want to make sure users can not register without providing all valid data

  Given User navigates to Digital Bank signup page
  When User creates account with  following fields
    |title  | firstName | lastName  | gender | dob       |ssn   |email   |password  | address    | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone | termsCheckMark|
    |<title>|<firstName>| <lastName>|<gender>|   <dob>   |<ssn> |<email> |<password>| <address>  |<locality>|<region>|<postalCode>|<country>| <homePhone>|<mobilePhone>|<workPhone>|<termsCheckMark>|
  Then User should see the "<fieldWithError>" required field error message "<errorMessage>"

  Examples:
    |title | firstName | lastName   | gender | dob        |ssn |email  |password | address     | locality | region | postalCode | country | homePhone  | mobilePhone | workPhone |termsCheckMark |fieldWithError | errorMessage                        |
    |      |           |            |        |            |    |       |         |             |          |        |            |         |            |             |           |               |     title     |  Выберите один из пунктов списка. |
    |  Mr. |           |            |        |            |    |       |         |             |          |        |            |         |            |             |           |               |     firstName |  Заполните это поле. |
    |  Mr. |    Jack   |            |        |            |    |       |         |             |          |        |            |         |            |             |           |               |     lastName  |                      |

