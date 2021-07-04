package org.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import org.example.exception.GameOverException;
import org.example.exception.GameWinException;
import org.example.units.Player;

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
        JCommander jCommander = new JCommander(new Main());
        try {
            jCommander.parse(args);
        } catch (ParameterException e) {
            Printer.printError("The following options are required: [--profile], [--enemiesCo" +
                    "unt], [--size], [--wallsCount]");
        }
        checkArgs();
        PropertiesHelper.setProperties(profile);
        chaseLogicPropertiesInit();
        try {
            GameMap map = new GameMap(enemiesCount, wallsCount, size);
            ActiveUnitsRing activeUnitsRing = new ActiveUnitsRing(map.getEnemies(), new Player(map.getPlayerCoords()));
            Game game = new Game(map, activeUnitsRing);
            game.play();
        } catch (GameOverException e) {
            System.out.println(e.getMessage() + " Game over!");
        } catch (GameWinException e) {
            System.out.println("You won!");
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
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

    private static void chaseLogicPropertiesInit() {
        ChaseLogicProperties.EMPTY_CHAR = PropertiesHelper.EMPTY_CHAR;
        ChaseLogicProperties.PLAYER_CHAR = PropertiesHelper.PLAYER_CHAR;
    }
}
