package org.example.Base;
import org.example.Base.BrowserConfig.Initialization;
import org.example.Base.BrowserConfig.Termination;
import org.example.Utility.Property;
import org.example.Utility.TestListner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
// Base.java
public class Base {

    protected WebDriver driver;
    protected Property property;
    Initialization initialization;
    /*
    Initialize the browser and properties before any test methods are run
     */
    @BeforeClass
    public void setUp() {
        Initialization.initial();// Initialize the browser and properties
        this.driver = Initialization.driver;// Get the driver from Initialization
        this.property = Initialization.property;// Get the property from Initialization
        TestListner.driver = driver; // Set the driver in TestListener
    }

    /*
        Navigate to Base URL
     */
    @BeforeMethod
    public void navigateToBaseURL() // rename the Method name BeforeMethod-->navigateToBaseURL
    {
        driver.get(property.getProperty("Base_URL"));// Navigate to the base URL defined in properties
    }

    /*
    Terminate the driver after all tests are done
     */
    @AfterClass
    public void terminate()// rename the Method name AfterClass-->terminate
    {
        Termination.term();// Terminate the WebDriver session
    }


}
