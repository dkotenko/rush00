package org.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

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
        Main main = new Main();
        JCommander jCommander = new JCommander(main);
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            Printer.printError("The following options are required: [--profile], [--enemiesCo" +
                    "unt], [--size], [--wallsCount]");
        }

        checkArgs();
        PropertiesHelper.setProperties(profile);
        if (main.enemiesCount + main.wallsCount + 2 > main.size * main.size)
            throw new IllegalArgumentException();
        Map map = new Map(main.size, main.wallsCount, main.enemiesCount);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            map.printMap();
            String input = getInput(scanner);
            while (!isValidInput(input, scanner)) {
                input = getInput(scanner);
            }
            //boolean isEnd = doMove();
            //if (isEnd)
            //    break;
        }
        //printResult();
    }

    private static String getInput(Scanner scanner) {
        String input = null;
        try {
            input = scanner.nextLine();
        }  catch (NoSuchElementException e) {
            Printer.printError("no line to parse");
        } catch (IllegalStateException e) {
            Printer.printError("scanner object is closed");
        }
        return input;
    }

    private static boolean isValidInput(String input, Scanner scanner) {
        if (input.equals("9"))
            System.exit(1);
        if (!(input.equals("a") || input.equals("w") ||
                input.equals("s") || input.equals("d"))) {
            System.out.println("invalid move command. Use W-A-S-D");
            return false;
        }
        if (PropertiesHelper.isDev) {
            System.out.println("Enter '8' to confirm your move");
            String confirm = getInput(scanner);
            if (!confirm.equals("8")) {
                System.out.println("Move has not been confirmed");
                return false;
            }
        }
        return true;
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
        if (enemiesCount + wallsCount + 2 > (size * size) / 2)
            Printer.printError("invalid parameters set: not enough space for gameplay");
    }
}
