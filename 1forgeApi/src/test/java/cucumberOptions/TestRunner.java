package cucumberOptions;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;


/*
 * TestRunner: class for executing the cucumber framework
 */

//@RunWith(Cucumber.class)
@CucumberOptions(
		features ="src/test/java/features",
		glue="stepdefinitions"
)

public class TestRunner extends AbstractTestNGCucumberTests{

}
