Feature: Edit Profile
  As a user of the ResS system
  I want to edit my profile
  So that i can alter my username, email or password
  
@ignore
  Scenario: Edit generator profile
    Given I am at the the edit profile page as a generator
    When Fill the generators username field with "cantina"
    And the generators password field with "12345"
    And the generators email field with "cantina@ufpe.br"
    And I confirm the generators edition
    Then I see at the account settings page my new generator settings
@ignore
  Scenario: Edit collector profile
    Given I am at the the edit profile page as a collector
    When Fill the collectors username field with "newadmin"
    And the collectors password field with "12345"
    And the collectors email field with "newadmin@ufpe.br"
    And I confirm the collectors edition
    Then I see at the account settings page my new collector settings

  Scenario: User profile changes
    Given Exists an user with the username "ru" in the system
    When I change the username to "cantina"
    And the password to "12345"
    And the email to "cantina@ufpe.br"
    Then The user has new account settings

