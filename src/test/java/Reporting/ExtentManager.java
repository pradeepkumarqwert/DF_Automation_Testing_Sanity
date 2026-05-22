package Reporting;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {
    private static ExtentReports extent;
    public static ExtentReports getInstance() {

        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
            reporter.config().setReportName("Automation Test Results");
            reporter.config().setDocumentTitle("Execution Report");
            extent = new ExtentReports();
            extent.attachReporter(reporter);

            extent.setSystemInfo("Environment", "DeviceFarm.Test");
            extent.setSystemInfo("Tester", "Pradeep Kumar");
            extent.setSystemInfo("Test_Lead", "Pratap");
            extent.setSystemInfo("Scheduled_Time", fetchDataAndTime());
        }
        return extent;
    }


    public static String fetchDataAndTime() {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        System.out.println(formattedDateTime);
        return formattedDateTime;
    }
}