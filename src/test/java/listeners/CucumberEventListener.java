package listeners;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import io.qameta.allure.Attachment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.WebDriverManager;

import java.io.IOException;

import static java.lang.String.format;

public class CucumberEventListener implements ConcurrentEventListener {
    private static final Logger LOG = LogManager.getLogger(CucumberEventListener.class);

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestSourceRead.class, this::featureRead);
        eventPublisher.registerHandlerFor(TestCaseStarted.class, this::scenarioRead);
        eventPublisher.registerHandlerFor(TestStepStarted.class, this::stepStartedRead);
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::stepFinishedRead);
        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::scenarioFinishedRead);
    }

    private void featureRead(TestSourceRead event){
        String featureSource = event.getUri().toString();
        String featureName = featureSource.split(".*/")[1];

        LOG.info(format("Scenario: '%s' --> execution is started", featureName));
    }

    private void scenarioRead(TestCaseStarted event){
        String scenarioName = event.getTestCase().getName();
        LOG.info(format("'%s' scenario is started", scenarioName));
    }

    private void stepStartedRead(TestStepStarted event){
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
            String stepName = testStep.getStep().getText();

            LOG.info(format("Step: %s --> STARTED", stepName));
        }
    }

    private void stepFinishedRead(TestStepFinished event){
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
            String stepName = step.getStep().getText();
            Status stepResult = event.getResult().getStatus();
            LOG.info(format("Step: %s --> %s", stepName, stepResult));

            if (stepResult == Status.FAILED){
                try {
                    takeScreenShot(stepName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void scenarioFinishedRead(TestCaseFinished event){
        String scenarioName = event.getTestCase().getName();
        LOG.info(format("Scenario: '%s' --> execution is finished", scenarioName));

        Status scenarioResult = event.getResult().getStatus();

        if (scenarioResult == Status.FAILED) {
            Throwable e = event.getResult().getError();
            String errorMessage = e.getMessage();
            LOG.error(errorMessage);
            e.printStackTrace();
        }
    }

    @Attachment(value = "Failure in step {0}", type = "image/png")
    private byte[] takeScreenShot(String methodName) throws IOException {
        return ((TakesScreenshot) WebDriverManager.getInstance()).getScreenshotAs(OutputType.BYTES);
    }
}
