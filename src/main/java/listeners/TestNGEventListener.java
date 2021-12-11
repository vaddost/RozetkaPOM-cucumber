package listeners;

import org.apache.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGEventListener implements ITestListener {
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
        log.info(sb.toString());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getName() + " test case is PASSED");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error(result.getName() + " test case is FAILED");
        log.error(result.getThrowable().getMessage());
    }
}
