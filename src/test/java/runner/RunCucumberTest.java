package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = "src/test/resources/features",
        glue = "stepdefs",
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "listeners.CucumberEventListener"})
public class RunCucumberTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
