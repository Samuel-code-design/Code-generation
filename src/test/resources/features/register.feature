Feature: Register test

  Scenario: Registeren als customer geeft status 201
    When User registreert
    Then Is de status van het request 201

  Scenario: Registeren als customer met username die al bestaat
    When User registreert met username die al bestaat
    Then Is de status van het request 422

  Scenario: Registeren als customer met email die al bestaat
    When User registreert met email die al bestaat
    Then Is de status van het request 422

  Scenario: Registeren als customer met wachtwoord die te kort is
    When User registreert met wachtwoord die te kort is
    Then Is de status van het request 422

