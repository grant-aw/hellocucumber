#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Cobra-ats Overview

  Scenario Outline: There is at least one application!
    Given "<user>" login to cobra-ats hosted at "<test-server>" with "<password>"
    When there is jobs under him/her
    Then at least there is one application

    Examples:
      | user | password | test-server |
      | tardisone@easternbay.cn | Eastbay007 | indeed.grantsunny.com |


  Scenario Outline: Build-in job has one default application
    Given "<user>" login to cobra-ats hosted at "<test-server>" with "<password>"
    When there is job titled "Editor"
    Then there is one application for this job
    And this applicant named "IDR HCS"

    Examples:
      | user | password | test-server |
      | tardisone@easternbay.cn | Eastbay007 | indeed.grantsunny.com |
