package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    private static Properties properties;

    static {
        properties = new Properties();
        try {
            try (InputStream is = Main.class.getClassLoader().getResourceAsStream("application-production.properties")) {
                properties.load(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyByKey(String name) {
        return properties.getProperty(name);
    }

}
