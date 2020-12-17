package com.indeed.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"json:target/cucumber-report-hello.json", "pretty"},
    features="src/test/resources/hello",
    glue="com.indeed.bdd.hello"
    )
public class RunCucumberHelloWorldTest {

}
