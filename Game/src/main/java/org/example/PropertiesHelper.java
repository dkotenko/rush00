package org.example;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {

    private static Properties properties;
    private static Properties devProperties;
    private static Properties prodProperties;
    public static boolean isDev;

    public static char ENEMY_CHAR;
    public static char PLAYER_CHAR;
    public static char WALL_CHAR;
    public static char GOAL_CHAR;
    public static char EMPTY_CHAR;
    public static String ENEMY_COLOR;
    public static String PLAYER_COLOR;
    public static String WALL_COLOR;
    public static String GOAL_COLOR;
    public static String EMPTY_COLOR;

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
        if (profile.equals("dev") || profile.equals("developer")) {
            properties = devProperties;
            isDev = true;
        } else if (profile.equals("prod") || profile.equals("production")) {
            properties = prodProperties;
            isDev = false;
        } else {
            Printer.printError("invalid application profile name: " + profile);
        }
        initProperties();
    }

    private static void initProperties () {
        ENEMY_CHAR = getPropertyCharAndCheck("enemy.char");
        PLAYER_CHAR = getPropertyCharAndCheck("player.char");
        WALL_CHAR = getPropertyCharAndCheck("wall.char");
        GOAL_CHAR = getPropertyCharAndCheck("goal.char");
        EMPTY_CHAR = getPropertyCharAndCheck("empty.char");
        ENEMY_COLOR = properties.getProperty("enemy.color");
        PLAYER_COLOR = properties.getProperty("player.color");
        WALL_COLOR = properties.getProperty("wall.color");
        GOAL_COLOR = properties.getProperty("goal.color");
        EMPTY_COLOR = properties.getProperty("empty.color");
    }

}
