package org.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Parameters(separators = "=")
public class Main {
    @Parameter(names="--enemiesCount", required = true)
    static int enemiesCount;
    @Parameter(names="--wallsCount", required = true)
    static int wallsCount;
    @Parameter(names="--size", required = true)
    static int size;
    @Parameter(names="--profile", required = true)
    static String profile;


    public static void main(String[] args) {
        JCommander jCommander = new JCommander(new Main());
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            Printer.printError("The following options are required: [--profile], [--enemiesCo" +
                    "unt], [--size], [--wallsCount]");
        }

        checkArgs();
        PropertiesHelper.setProperties(profile);

        GameMap map = new GameMap(size, wallsCount, enemiesCount);
        map.printMap();
        System.out.printf("--enemiesCount=%d --wallsCount=%d --size=%d ==profile=%s%n",
                enemiesCount, wallsCount, size, profile);
    }

    private static void checkArgs() {
        if (enemiesCount < 1) {
            Printer.printError("enemies number must be positive");
        }
        if (size < 2) {
            Printer.printError("size must be at least 2");
        }
        if (wallsCount < 0) {
            Printer.printError("walls number can`t be negative");
        }
        if (!(profile.equals("dev") ||
                profile.equals("developer") ||
                profile.equals("prod") ||
                profile.equals("production")))
            Printer.printError("invalid application profile name: " + profile);
        if (enemiesCount + wallsCount + 2 > size * size)
            Printer.printError("invalid parameters set: not enough space for gameplay");
    }
}
