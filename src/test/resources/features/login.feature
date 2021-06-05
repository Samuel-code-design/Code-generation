Feature: Login test

  Scenario: Login geeft token terug
    When Ik inlog
    Then Is de status van het request 200