Feature: Register test

  Scenario: Registeren als customer geeft status 201
    When Ik registreer
    Then Is de status van het request 201



