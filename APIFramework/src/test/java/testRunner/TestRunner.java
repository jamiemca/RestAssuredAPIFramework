package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/featureFiles",
		glue = {"stepDefinitions"},
		tags = ("@jm"),
		plugin = {"pretty",
				"html:target/reports/test-report",
				"json:target/results/cucumber.json",
				"junit:target/results/cucumber.xml"
		},
		monochrome = true,
		dryRun = false,
		stepNotifications = true
		)


public class TestRunner {

}
