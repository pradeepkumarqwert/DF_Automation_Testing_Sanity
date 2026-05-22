package Supporting_Classes.Listeners;

import Reporting.ExtentManager;
import Utils.DriverManager;
import com.aventstack.extentreports.*;
import org.openqa.selenium.*;
import org.testng.*;

import java.io.File;

public class Listeners_Class implements ITestListener {

    ExtentReports extent = ExtentManager.getInstance();
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        WebDriver driver = DriverManager.getDriver();

        try {
            String path = captureScreenshot(driver, result.getMethod().getMethodName());
            test.get().addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    public String captureScreenshot(WebDriver driver, String name) throws Exception {

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = System.getProperty("user.dir") + "/screenshots/" + name + "_" + System.currentTimeMillis() + ".png";

        java.nio.file.Files.copy(src.toPath(), new File(path).toPath());

        return path;
    }
}