Feature: Transactions

  Scenario: Retrieve all transactions is status OK
    When I retrieve all transactions
    Then I get http status 200

  Scenario: Retrieve all transactions by date is status OK
    When I retrieve all transactions by date
    Then I get http status 200

  Scenario: Create new transaction is status OK
    When I post new transaction
    Then I get http status 200

  Scenario: Create null transaction is status BAD_REQUEST
    When I post null transaction
    Then I get http status 400

  Scenario: Create new transaction over transaction limit is status UNPROCESSABLE_ENTITY
    When I post new transaction over transaction limit
    Then I get http status 422

  Scenario: Create new transaction user doesnt exists is status UNPROCESSABLE_ENTITY
    When I post new transaction user doesnt exists
    Then I get http status 422

  Scenario: Create new transaction account from doesnt exists is status UNPROCESSABLE_ENTITY
    When I post new transaction account from doesnt exists
    Then I get http status 422

  Scenario: Create new transaction account to doesnt exists is status UNPROCESSABLE_ENTITY
    When I post new transaction account to doesnt exists
    Then I get http status 422

  Scenario: Create new transaction account from is saving account and is not from user is status UNPROCESSABLE_ENTITY
    When I post new transaction account from is saving account and is not from user
    Then I get http status 422

  Scenario: Create new transaction account to is saving account and is not from user is status UNPROCESSABLE_ENTITY
    When I post new transaction account to is saving account and is not from user
    Then I get http status 422

  Scenario: Create new transaction exceeds balance is status UNPROCESSABLE_ENTITY
    When I post new transaction exceeds balance
    Then I get http status 422

  Scenario: Create new transaction exceeds day limit is status UNPROCESSABLE_ENTITY
    When I post new transaction exceeds day limit
    Then I get http status 422