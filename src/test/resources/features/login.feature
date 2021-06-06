Feature: Login test

  Scenario: Login geeft status ok terug
    When Ik inlog
    Then Is de status van het request 200