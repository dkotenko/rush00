package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    private static Properties properties;

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
        properties = new Properties();
        try {
            try (InputStream is = Main.class.getClassLoader().getResourceAsStream("application-production.properties")) {
                properties.load(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private static char getPropertyCharAndCheck(String name) {
        String charString = properties.getProperty(name);
        return charString.length() != 0 ? charString.charAt(0) : ' ';
    }

}
