Feature: Login Authentication
  As a user of the ResS system
  I want to log in my account
  So that i can use the system as a generator or collector

  Scenario: Log in failed
    Given I am at the HomePage
    When I go to the login page
    And Enter the username "ru"
    And The password "12345" at the correct fields
    Then I can not log in


  #testes não estão funcionando por incompatibilidade do spring security com o grails

  Scenario: Log in as a generator
    Given I am at the HomePage
    When I go to the login page
    And Enter the username "ru"
    And The password "pass" at the correct fields
    Then I log in as a generator
@ignore
  Scenario: Log in as a collector
    Given I am at the HomePage
    When I go to the login page
    And Enter the username "admin"
    And The password "pass" at the correct fields
    Then I log in as a collector


