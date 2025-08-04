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
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListner implements ITestListener {
/*
    TestListner is a utility class that implements ITestListener from TestNG to generate Extent Reports.
    It captures test execution events such as start, success, failure, and skip, and logs them in an HTML report.
    The report includes screenshots for passed and failed tests, and it can assign authors based on properties defined in a properties file.
 */
    ExtentSparkReporter htmlReporter;
    ExtentReports extent;
    ExtentTest test;
//    String SSpath = "screenshots/testName.png";

    public static WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());// Create a timestamp for the screenshot filename
        String dateFolder = new SimpleDateFormat("yyyyMMdd").format(new Date());// Create a folder with the current date
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-outcome/"+dateFolder+"/Report"+dateName+".html");// Specify the path for the HTML report

        htmlReporter.config().setDocumentTitle("Automation Report");// Set the title of the report
        htmlReporter.config().setReportName("Functional Test Report");// Set the name of the report
        htmlReporter.config().setTheme(Theme.STANDARD);// Set the theme of the report to STANDARD
        extent = new ExtentReports();// Create an instance of ExtentReports
        extent.attachReporter(htmlReporter);// Attach the HTML reporter to the ExtentReports instance
        Property prop = new Property();
        prop.LoadProperty();
        String testers = prop.getProperty("testers"); // example: "Div Patel,Sunil Yadav,Kalpesh Ahir"
        extent.setSystemInfo("Testers", testers);
        System.out.println(" Testers configured in report: " + testers);
     //   extent.setSystemInfo("Tester", "Div Patel");
    }
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());// Create a new test in the report with the name of the test method
        Property prop = new Property();
        prop.LoadProperty();
        String author = prop.getProperty(result.getMethod().getMethodName() + ".author");// Retrieve the author from properties file using the test method name as key

        if(author != null && !author.isEmpty()){
            test.assignAuthor(author);
            System.out.println("Assigned author: " + author + " for test: " + result.getMethod().getMethodName());// Log the assigned author for the test
        } else {
            System.out.println("No author found for test: " + result.getMethod().getMethodName());// Log if no author is found for the test
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());// Log the success of the test
        String screenshotPath = Screenshot.SS(driver, result.getMethod().getMethodName());// Capture a screenshot for the passed test
        // Convert to relative path from the project root (HTML report is in test-output/)
//        String relativePath = "../screenshots/" + new File(screenshotPath).getName();// Get the relative path of the screenshot
        String relativePath = new File(screenshotPath).getName();// Get the relative path of the screenshot

        try {
            test.addScreenCaptureFromPath(relativePath);// Add the screenshot to the report
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());// Log the failure of the test
        test.log(Status.FAIL, result.getThrowable());// Log the exception that caused the failure
        String screenshotPath = Screenshot.SS(driver, result.getMethod().getMethodName());// Capture a screenshot for the failed test
        // Convert to relative path from the project root (HTML report is in test-output/)
//        String relativePath = "../screenshots/" + new File(screenshotPath).getName();// Get the relative path of the screenshot
        String relativePath = new File(screenshotPath).getName();// Get the relative path of the screenshot

        try {
            test.addScreenCaptureFromPath(relativePath);// Add the screenshot to the report
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());// Log the skip of the test
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();// Flush the ExtentReports instance to write the report to disk
    }
}
