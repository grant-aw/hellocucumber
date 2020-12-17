package com.indeed.bdd.week;

import java.time.DayOfWeek;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

import static org.junit.Assert.*;

public class StepDefinitions {

  private DayOfWeek today;
  private String answer;

  @Given("today is Sunday")
  public void today_is_sunday() {

    today = DayOfWeek.SUNDAY;
  }

  @Given("today is Friday")
  public void today_is_friday() {

    today = DayOfWeek.FRIDAY;
  }

  @Given("today is {string}")
  public void today_is(String string) {

    try {
      today = DayOfWeek.valueOf(string.toUpperCase());
    } catch (java.lang.IllegalArgumentException e) {
      today = null;
    }
  }

  @When("I ask whether it's Friday yet")
  public void i_ask_whether_it_s_friday_yet() {

    if (today != null)
      if (today == DayOfWeek.FRIDAY)
        answer = "Yepp";
      else
        answer = "Nope";
    else
      answer = "Nope";
  }


  @Then("I should be told {string}")
  public void i_should_be_told(String string) {
      assertEquals(string, answer);
  }


}
