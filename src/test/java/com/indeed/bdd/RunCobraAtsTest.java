package com.indeed.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"json:target/cucumber-report-cobraats.json", "pretty"},
    features="src/test/resources/cobra-ats",
    glue="com.indeed.bdd.cobraats"
    )
public class RunCobraAtsTest {

}
