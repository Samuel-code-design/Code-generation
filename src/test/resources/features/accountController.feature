Feature: Accounts

  Scenario: Retrieve all accounts is status OK
    When I retrieve all accounts
    Then (Account) I get http status 200

  Scenario: Getting one account
    When I retrieve account with iban "NL02INHO0123456789"
    Then (Account) I get http status 200

  Scenario: Creating an account
    When I post an account
    Then (Account) I get http status 201
  
  Scenario: Updating an account
    When I update an account with iban "NL02INHO0123456789"
    Then (Account) I get http status 200

  Scenario: Getting a list of accounts
    When I get accounts for userId 3
    Then I get a list of 4 accounts

  Scenario: Locking an account
    When I lock an account with iban "NL02INHO0123456789"
    Then (Account) I get http status 200

  Scenario: Getting account with wrong iban
    When I retrieve account with iban "NL02INHO0123456123"
    Then (Account) I get http status 404

  Scenario: Update account with wrong iban
    When I update an account with iban "NL02INHO0123456123"
    Then (Account) I get http status 404

  Scenario: Getting a list of accounts
    When I get accounts for userId 10
    Then (Account) I get http status 404

  Scenario: Locking an account with wrong iban
    When I lock an account with iban "NL02INHO0123456123"
    Then (Account) I get http status 404