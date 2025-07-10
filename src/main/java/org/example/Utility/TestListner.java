package org.example.Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListner implements ITestListener {

    ExtentSparkReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;
    String SSpath = "screenshots/testName.png";

    public static WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Functional Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        Property prop = new Property();
        prop.LoadProperty();
        String testers = prop.getProperty("testers"); // example: "Div Patel,Sunil Yadav,Kalpesh Ahir"
        extent.setSystemInfo("Testers", testers);
        System.out.println(" Testers configured in report: " + testers);
     //   extent.setSystemInfo("Tester", "Div Patel");
    }
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        Property prop = new Property();
        prop.LoadProperty();
        String author = prop.getProperty(result.getMethod().getMethodName() + ".author");

        if(author != null && !author.isEmpty()){
            test.assignAuthor(author);
            System.out.println("Assigned author: " + author + " for test: " + result.getMethod().getMethodName());
        } else {
            System.out.println("No author found for test: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
        String screenshotPath = Screenshot.SS(driver, result.getMethod().getMethodName());
        // Convert to relative path from the project root (HTML report is in test-output/)
        String relativePath = "../screenshots/" + new File(screenshotPath).getName();

        try {
            test.addScreenCaptureFromPath(relativePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.log(Status.FAIL, result.getThrowable());
        String screenshotPath = Screenshot.SS(driver, result.getMethod().getMethodName());
        // Convert to relative path from the project root (HTML report is in test-output/)
        String relativePath = "../screenshots/" + new File(screenshotPath).getName();

        try {
            test.addScreenCaptureFromPath(relativePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
