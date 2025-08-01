package org.example.Base.BrowserConfig;
import org.example.Utility.Property;
import org.example.Utility.TestListner;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Initialization {

    public static Property property;// Property object to load configuration properties
    public static WebDriver driver;// WebDriver instance to control the browser\

    /**
     * Initializes the WebDriver based on the browser specified in the properties file.
     * Loads the properties and sets up the WebDriver with appropriate options.
     */

    // This method is called to initialize the WebDriver and load properties.
    // It reads the browser type from the properties file and sets up the WebDriver accordingly.
    public static void initial(){
        property = new Property();
        property.LoadProperty();
        String browser = property.getProperty("browser");

        switch (browser != null ? browser.toLowerCase() : "chrome")
        {
            case "chrome":// Default to Chrome if no browser is specified or if the specified browser is Chrome
                ChromeOptions chromeOptions = new ChromeOptions();// Create ChromeOptions to customize ChromeDriver behavior
                chromeOptions.addArguments("--incognito"); // keep your original options
                chromeOptions.addArguments("--start-maximized");// Start Chrome in maximized mode
                chromeOptions.addArguments("--disable-notifications"); // Disable notifications
                chromeOptions.addArguments("--disable-popup-blocking"); // Disable popup blocking
                driver = new ChromeDriver(chromeOptions);// Initialize ChromeDriver with the specified options
                break;

            case "firefox":// If the specified browser is Firefox
                driver = new FirefoxDriver();// Initialize FirefoxDriver
                driver.manage().window().maximize();// Maximize the Firefox window
                break;

            case "edge":// If the specified browser is Edge
                driver = new EdgeDriver();// Initialize EdgeDriver
                driver.manage().window().maximize();// Maximize the Edge window
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);// If the specified browser is not supported, throw an exception

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));// Set an implicit wait of 5 seconds for the WebDriver to wait for elements to be present before throwing an exception
        TestListner.driver = driver;// Set the driver in TestListener to allow access to the WebDriver instance in test listeners


    }
}
