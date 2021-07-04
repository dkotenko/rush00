package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    private static Properties properties;
    private static Properties devProperties;
    private static Properties prodProperties;

    public static char enemyChar;
    public static char playerChar;
    public static char wallChar;
    public static char goalChar;
    public static char emptyChar;
    public static String enemyColor;
    public static String playerColor;
    public static String wallColor;
    public static String goalColor;
    public static String emptyColor;

    static {
        devProperties = new Properties();
        prodProperties = new Properties();
        try {
                InputStream prod = Main.class.getClassLoader()
                        .getResourceAsStream("application-production.properties");
                prodProperties.load(prod);
                InputStream dev = Main.class.getClassLoader()
                        .getResourceAsStream("application-dev.properties");
                devProperties.load(dev);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static char getPropertyCharAndCheck(String name) {
        String charString = properties.getProperty(name);
        return charString.length() != 0 ? charString.charAt(0) : ' ';
    }

    public static void setProperties(String profile) {
        if (profile.equals("dev") || profile.equals("developer"))
            properties = devProperties;
        else if (profile.equals("prod") || profile.equals("production"))
            properties = prodProperties;
        else
            Printer.printError("invalid application profile name: " + profile);
        initProperties();
    }

    private static void initProperties () {
        enemyChar = getPropertyCharAndCheck("enemy.char");
        playerChar = getPropertyCharAndCheck("player.char");
        wallChar = getPropertyCharAndCheck("wall.char");
        goalChar = getPropertyCharAndCheck("goal.char");
        emptyChar = getPropertyCharAndCheck("empty.char");
        enemyColor = properties.getProperty("enemy.color");
        playerColor = properties.getProperty("player.color");
        wallColor = properties.getProperty("wall.color");
        goalColor = properties.getProperty("goal.color");
        emptyColor = properties.getProperty("empty.color");
    }

}
