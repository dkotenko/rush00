package org.example;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

import java.util.Arrays;
import java.util.Locale;

public class Printer {
    static ColoredPrinter enemyPrinter;
    static ColoredPrinter emptyPrinter;
    static ColoredPrinter playerPrinter;
    static ColoredPrinter goalPrinter;
    static ColoredPrinter wallPrinter;

    static {
        enemyPrinter = Printer.initPrinter(PropertiesHelper.enemyColor);
        emptyPrinter = Printer.initPrinter(PropertiesHelper.emptyColor);
        playerPrinter = Printer.initPrinter(PropertiesHelper.playerColor);
        goalPrinter = Printer.initPrinter(PropertiesHelper.goalColor);
        wallPrinter = Printer.initPrinter(PropertiesHelper.wallColor);
    }

    public static void printError(String message)
    {
        System.err.println("Error: " + message);
        System.exit(0);
    }


    public static ColoredPrinter getPrinter(char c) {
        if (c == PropertiesHelper.emptyChar) {
            return emptyPrinter;
        }
        else if (c == PropertiesHelper.playerChar) {
            return playerPrinter;
        }
        else if (c == PropertiesHelper.enemyChar) {
            return enemyPrinter;
        }
        else if (c == PropertiesHelper.wallChar) {
            return wallPrinter;
        }
        else if (c == PropertiesHelper.goalChar) {
            return goalPrinter;
        }
        Printer.printError("invalid char at map: " + c);
        return null;
    }

    private static ColoredPrinter initPrinter(String color) {
        color = color.toLowerCase(Locale.ROOT);
        ColoredPrinter cp = null;

        switch (color) {
            case "black":
                cp = new ColoredPrinter.Builder(1, false)
                        .foreground(Ansi.FColor.WHITE).background(Ansi.BColor.BLACK)
                        .build();
                break ;
            case "green":
                cp = new ColoredPrinter.Builder(1, false)
                        .foreground(Ansi.FColor.BLACK).background(Ansi.BColor.GREEN)
                        .build();
                break ;
            case "red":
                cp = new ColoredPrinter.Builder(1, false)
                        .foreground(Ansi.FColor.BLACK).background(Ansi.BColor.RED)
                        .build();
                break ;
            case "yellow":
                cp = new ColoredPrinter.Builder(1, false)
                        .foreground(Ansi.FColor.BLACK).background(Ansi.BColor.YELLOW)
                        .build();
                break ;
            case "blue":
                cp = new ColoredPrinter.Builder(1, false)
                        .foreground(Ansi.FColor.BLACK).background(Ansi.BColor.BLUE)
                        .build();
                break ;
            case "magenta":
                cp = new ColoredPrinter.Builder(1, false)
                        .foreground(Ansi.FColor.BLACK).background(Ansi.BColor.MAGENTA)
                        .build();
                break ;
            case "cyan":
                cp = new ColoredPrinter.Builder(1, false)
                        .foreground(Ansi.FColor.BLACK).background(Ansi.BColor.CYAN)
                        .build();
                break ;
            case "white":
                cp = new ColoredPrinter.Builder(1, false)
                        .foreground(Ansi.FColor.BLACK).background(Ansi.BColor.WHITE)
                        .build();
                break ;
            default:
                System.err.println("Error: invalid color '" + color + "'");
                System.exit(-1);
        }
        return cp;
    }

}
