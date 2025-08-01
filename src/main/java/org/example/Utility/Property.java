package org.example.Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Property {
/*
    * Property is a utility class for loading and retrieving properties from a properties file.
    * It loads the properties from "initials.properties" located in the "src/test/resources" directory.
    * The properties can be accessed using the getProperty method with the property key as an argument.
 */
    Properties prop;

    public void LoadProperty(){// Method to load properties from the properties file
         prop = new Properties();

        try {// Load the properties file from the specified path
            prop.load(Files.newInputStream(Paths.get("src/test/resources/initials.properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String string){
        return prop.getProperty(string);// Method to retrieve a property value by its key
    }
}
