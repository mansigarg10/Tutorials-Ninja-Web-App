package components;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resource.Base;

import java.io.IOException;

public class Listeners extends Base implements ITestListener {

    private final ThreadLocal<ExtentTest> eTest = new ThreadLocal<>();
    private final ExtentReports extent = ExtentReporter.getExtentReport();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName() + " is started");
        eTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        eTest.get().log(Status.PASS, result.getName() + " is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String filePath;
        eTest.get().fail(result.getThrowable());
        try {
            WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        eTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        eTest.get().log(Status.SKIP, result.getName() + " is skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        eTest.get().log(Status.INFO, " test is finished");
        extent.flush();
    }

}
