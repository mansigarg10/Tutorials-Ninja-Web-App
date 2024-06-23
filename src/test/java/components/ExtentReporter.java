package components;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentReporter {
    public static ExtentReports getExtentReport() {
        String path = System.getProperty("user.dir") + "//reports//index";
        File file = new File(path);
        ExtentSparkReporter reporter = new ExtentSparkReporter(file);
        reporter.config().setDocumentTitle("TestResult");
        reporter.config().setReportName("AdvanceReportOfTutorialsNinja");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Created By", "Mansi Garg");
        extent.setSystemInfo("Operating System", "Windows11");
        return extent;
    }
}
