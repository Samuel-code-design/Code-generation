Feature: Accounts

  Scenario: Retrieve all accounts is status OK
    When I retrieve all accounts
    Then I get http status 200

  Scenario: Getting one account
    When I retrieve account with iban "NL02INHO0123456789"
    Then I get http status 200

  Scenario: Creating an account
    When I post an account
    Then I get http status 201
  
  Scenario: Updating an account
    When I update an account
    Then I get http status 200

  Scenario: getting a list of accounts
    When I get accounts for userId 1
    Then I get a list of 2 accounts