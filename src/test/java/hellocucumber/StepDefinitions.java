package hellocucumber;

import java.time.DayOfWeek;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;

public class StepDefinitions {

  private DayOfWeek today;
  private String answer = "Nope";

  @Given("today is Sunday")
  public void today_is_sunday() {
    today = DayOfWeek.SUNDAY;
  }

  @Given("today is Friday")
  public void today_is_friday() {
    today = DayOfWeek.FRIDAY;
  }

  @Given("today is {string}")
  public void today_is(String todayAsString) {
    try {
      today = DayOfWeek.valueOf(todayAsString.toUpperCase());
    } catch (IllegalArgumentException e) {
      today = null;
    }
  }

  @When("I ask whether it's Friday yet")
  public void i_ask_whether_it_s_friday_yet() {

    if (today != null) {
      if (today == DayOfWeek.FRIDAY)
        answer = "Yepp";
      else
        answer = "Nope";
    } else
      answer = "Nope";

  }

  @Then("I should be told {string}")
  public void i_should_be_told(String expected) {
    assertEquals(expected, answer);

  }

}
