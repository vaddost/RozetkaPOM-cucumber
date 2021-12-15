package listeners;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.PropertiesReader;
import utils.WebDriverManager;

import java.io.File;
import java.io.IOException;

public class TestNGEventListener implements ITestListener {
    PropertiesReader propertiesReader = new PropertiesReader();
    static Logger log = Logger.getLogger(TestNGEventListener.class);

    @Override
    public void onTestStart(ITestResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append(result.getName()).append("( ");
        if (result.getParameters().length > 0){
            for (var res : result.getParameters()){
                sb.append("'").append(res).append("' ");
            }
        }
        sb.append(") test case is STARTED");
        log.info(getTestMethodSignature(result) + " test case is STARTED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(getTestMethodSignature(result) + " test case is PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) WebDriverManager.getDriver();
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String name = getTestMethodSignature(result);
        try {
            FileHandler.copy(source, new File(propertiesReader.getScreenshotFolderPath() + "/" +
                    name + ".png"));
            log.info(name + ": screenshot is saved to " + propertiesReader.getScreenshotFolderPath());
        } catch (IOException e){
            log.error(e.getMessage());
        }
        log.error(name + " test case is FAILED");
        log.error(result.getThrowable().getMessage());
    }

    private String getTestMethodSignature(ITestResult result){
        StringBuilder sb = new StringBuilder();
        sb.append(result.getName()).append("( ");
        if (result.getParameters().length > 0){
            for (var res : result.getParameters()){
                sb.append("'").append(res).append("' ");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
