package org.example.Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Property {

    Properties prop;

    public void LoadProperty(){
         prop = new Properties();

        try {
            prop.load(Files.newInputStream(Paths.get("src/test/resources/initials.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String string){
        return prop.getProperty(string);
    }
}
