Feature: Login test

  Scenario: Login geeft status ok terug
    When User inlogt
    Then Is de status request 200

  Scenario: Login met verkeerde credentials geeft status unprocessable Entity
    When User inlogt met verkeerde credentials
    Then Is de status request 422

  Scenario: Login met locked account geeft status unprocessable Entity
    When User inlogt met locked account
    Then Is de status request 422
