package org.example.Utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
/*
    * Screenshot is a utility class for capturing screenshots in Selenium WebDriver.
    * It provides a method to take a screenshot and save it with a timestamp in the filename.
    * The screenshots are saved in the "screenshots" directory under the current working directory.
 */
    public static String SS(WebDriver driver, String SS_name){
        String dateName = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss").format(new Date());// Create a timestamp for the screenshot filename
        String dateFolder = new SimpleDateFormat("yyyy-MM-dd").format(new Date());// Create a folder with the current date
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/Test-OutCome/"+dateFolder+"/screenshots/" + SS_name + "_" + dateName + ".png";
        File finalDestination = new File(destination);


        try {
            Files.createDirectories(finalDestination.getParentFile().toPath());
            Files.copy(source.toPath(), finalDestination.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
}
