package com.indeed.bdd.hello;

import static org.junit.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

  private static class Baby {
    boolean isEyeOpen = false;
    String sayHello() {
      return "Hello, World!";
    }
  }

  private Baby baby;

  @Given("A new baby born")
  public void a_new_baby_born() {

    baby = new Baby();
  }

  @When("The baby opens eyes")
  public void the_baby_opens_eyes() {

    baby.isEyeOpen = true;
  }

  @Then("The baby say {string}")
  public void the_baby_say(String string) {
    assertEquals(string, baby.sayHello());
  }

}
