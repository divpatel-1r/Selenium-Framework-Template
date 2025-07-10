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

    public static String SS(WebDriver driver, String SS_name){
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/screenshots/" + SS_name + "_" + dateName + ".png";
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
