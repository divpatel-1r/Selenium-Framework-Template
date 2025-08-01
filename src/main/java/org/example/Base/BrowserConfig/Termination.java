package org.example.Base.BrowserConfig;

import static org.example.Base.BrowserConfig.Initialization.driver;

public class Termination {

    /**
     * Terminates the WebDriver session by quitting the browser and setting the driver to null.
     * This method should be called after all tests are completed to ensure proper cleanup.
     */
    public static void term(){
        if(driver != null){ // Check if the driver is not null to avoid NullPointerException
            driver.quit(); // Close the browser and terminate the WebDriver session
            driver = null; // Set the driver to null to avoid memory leaks
        }
    }
}
