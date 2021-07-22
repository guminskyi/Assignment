package Runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"html:target/cucumber", "json:target/cucumber.json", "pretty"},
		features="src/test/resources/features",
		glue="steps",
		tags="@4",
		dryRun=false
)
public class CukesRunner {

}
