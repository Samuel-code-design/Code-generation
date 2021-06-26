Feature: Employee test

  Scenario: retrieve all users returns status 200
    When i retrieve all users
    Then is the status of the request 200

  Scenario: retrieve all users returns a list
    When i retrieve all users
    Then i get a list of 4 users

  Scenario: retrieve a single user returns status 200
    When i retrieve a user with id 1
    Then is the status of the request 200

  Scenario: retrieve a single user with username "bank"
    When i retrieve a user with id 1
    Then is de username "bank"

  Scenario: create a user returns status 201
    When i create a user
    Then is the status of the request 201

  Scenario: create a user with existing username returns status 422
    When i create a user with existing username
    Then is the status of the request 422

  Scenario: lock a user with id 2 returns status 200
    When i lock the user with id 2
    Then is the status of the request 200

  Scenario: lock a user that is locked returns status 422
    When i lock user 2 that is already locked
    Then is the status of the request 422

  Scenario: lock a user with id 1 returns status 422
    When i lock the user with id 1
    Then is the status of the request 422

  Scenario: Updating a user returns status 200
    When i update a user
    Then is the status of the request 200

  Scenario: Updating a user with existing username returns status 422
    When i update a user with existing username
    Then is the status of the request 422










