package org.example.Base;

import org.example.Utility.Property;
import org.example.Utility.TestListner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class Base {

    protected WebDriver driver;
    protected Property property;
    @BeforeClass
    public void initial(){
        property = new Property();
        property.LoadProperty();
        String browser = property.getProperty("browser");

       // ChromeOptions options = new ChromeOptions();
      //  options.addArguments("--incognito");

        switch (browser != null ? browser.toLowerCase() : "chrome")
        {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--incognito"); // keep your original options
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;

            case "edge":
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        TestListner.driver = driver;


    }
    @BeforeMethod
    public void navigateToBaseURL() // rename the Method name BeforeMethod-->navigateToBaseURL
    {
        driver.get(property.getProperty("Base_URL"));
    }

    @AfterClass
    public void terminate(){
        if(driver != null){
            driver.quit();
        }
    }


}
