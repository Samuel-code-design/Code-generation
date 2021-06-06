Feature: Employee test

  Scenario: retrieve all users returns status 200
    When i retrieve all users
    Then is the status of the request 200

  Scenario: retrieve all users returns a list
    When i retrieve all users
    Then i get a list of 2 users


  Scenario: retrieve a single user returns status 200
    When i retrieve a user with id 1
    Then is the status of the request 200

  Scenario: retrieve a single user with username "JD0001"
    When i retrieve a user with id 1
    Then is de username "JD0001"





