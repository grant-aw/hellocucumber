package com.indeed.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"json:target/cucumber-report.json", "pretty"},
    features="src/test/resources/week",
    glue="com.indeed.bdd.week"
    )
public class RunCucumberWeekTest {

}
