package com.backbase.tests;

import com.backbase.pages.Pages;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

// Example with tags
//@CucumberOptions(plugin = {"pretty", "html:target/cucumber-report.html"},
//        features = {"src/test/resources/com/backbase"},
//        glue = {"com.backbase.stepdefinitions"},
//        tags = "@create")

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-report.html"},
        features = {"src/test/resources/com/backbase"},
        glue = {"com.backbase.stepdefinitions"})
public class TestRunner {

    @AfterClass
    public static void cleanUp() {
        System.out.println("Cleaning Up");
        Pages.cleanUp();
    }

}
