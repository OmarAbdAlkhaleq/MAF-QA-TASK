
@Regression
Feature: Get universities.

  @Smoke
  Scenario: Verify user is able to list all universities in the system.
    Given User wants to list all universities.
    When API endpoint is "Search" and Http Method is "GET"
    Then API status code should be 200.
    And Response should not be empty.

  @Smoke
  Scenario Outline: Verify user is able to list all universities by country name.
    Given User wants to list all universities.
    When Query param is "country" and value is "<value>".
    And API endpoint is "Search" and Http Method is "GET"
    Then API status code should be 200.
    And Response should not be empty.
    And Value in response "country" should equal "<value>"
    Examples:
    |value|
    |Jordan|

  @Smoke
  Scenario Outline: Verify user is able to list all universities by university name.
    Given User wants to list all universities.
    When Query param is "name" and value is "<value>".
    And API endpoint is "Search" and Http Method is "GET"
    Then API status code should be 200.
    And Response should not be empty.
    And Value in response "name" should contains "<value>"
    Examples:
      |value|
      |Princess|


  @Functional
  Scenario Outline: Verify user is able to list all universities by university name and country name.
    Given User wants to list all universities.
    When Query param is "name" and value is "<uniname>".
    And Query param is "country" and value is "<countryname>".
    And API endpoint is "Search" and Http Method is "GET"
    Then API status code should be 200.
    And Response should not be empty.
    And Value in response "country" should equal "<countryname>"
    And Value in response "name" should contains "<uniname>"
    Examples:
      |uniname|countryname|
      |Princess|Jordan|

  @Functional
  Scenario Outline: Verify that no response is returned when country name is invalid.
    Given User wants to list all universities.
    And Query param is "country" and value is "<countryname>".
    And API endpoint is "Search" and Http Method is "GET"
    Then API status code should be 200.
    And Response should be empty.
    Examples:
      |countryname|
      |invalidCountry|

  @Functional
  Scenario Outline: Verify that no response is returned when university name is invalid.
    Given User wants to list all universities.
    And Query param is "name" and value is "<uniname>".
    And API endpoint is "Search" and Http Method is "GET"
    Then API status code should be 200.
    And Response should be empty.
    Examples:
      |uniname|
      |invalidUniName|