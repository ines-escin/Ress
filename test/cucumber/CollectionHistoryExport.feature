Feature: Collection History Export
  As a user of the ResS system
  I want to use the collection history
  So that i can export it to Excel and download it

  Scenario: Excel export file
    Given the history collection is in the system
    When I export the history collection in an excel file
    Then A csv file is generated with the history collection




