package org.example;

import org.example.exception.GameOverException;
import org.example.exception.HitWallException;
import org.example.exception.NotInBoundsException;
import org.example.units.ActiveUnit;
import org.example.units.Enemy;
import org.example.units.Player;

import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Game {
    private GameMap map;
    private ActiveUnitsRing activeUnitsRing;
    private Scanner scanner;

    public Game(GameMap map, ActiveUnitsRing activeUnitsRing) {
        this.map = map;
        this.activeUnitsRing = activeUnitsRing;
        this.scanner = new Scanner(System.in);
    }

    public void play() {
        if (PropertiesHelper.isDev) {
            playDev();
        } else {
            playProduction();
        }
    }

    private void playDev() {
        int turnNumber = 1;
        while (true) {
            System.out.printf("Turn #%d%n", turnNumber);
            map.printMap();
            printDelimiter();
            for (ActiveUnit activeUnit : activeUnitsRing.getActiveUnits()) {
                int[] coords = activeUnit.getCoords();
                int[] newCoords = doNextMove(activeUnit);
                map.printMap();
                if (activeUnit instanceof Enemy) {
                    System.out.println("Enemy step from " + Arrays.toString(coords) +
                            " to " + Arrays.toString(newCoords));
                    while (true) {
                        System.out.println("Enter '8' to confirm your move.");
                        if ("8".equals(scanner.nextLine().trim())) {
                            break;
                        }
                    }
                } else if (activeUnit instanceof Player) {
                    map.setPlayerCoords(newCoords);
                }
                System.out.println();
            }
            turnNumber++;
        }
    }

    public void playProduction() {
        while (true) {
            try {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
            map.printMap();
            for (ActiveUnit activeUnit : activeUnitsRing.getActiveUnits()) {
                doNextMove(activeUnit);
            }
        }
    }

    private int[] doNextMove(ActiveUnit activeUnit) {
        int[] newCoords;

        while (true) {
            try {
                newCoords = activeUnit.getNewCoords(map);
                break;
            } catch (HitWallException | NotInBoundsException e) {
                System.out.println(e.getMessage() + " Try again.");
            }
        }
        if (activeUnit instanceof Enemy && Arrays.equals(newCoords, map.getPlayerCoords())) {
            throw new GameOverException("Enemy hit player " + Arrays.toString(activeUnit.getCoords()) +
                    "->" + Arrays.toString(newCoords) + ".");
        }
        map.replace(newCoords, activeUnit.getCoords());
        activeUnit.setCoords(newCoords);
        return newCoords;
    }

    private void printDelimiter() {
        for (int i = 0; i < map.getSize(); i++) {
            System.out.print('-');
        }
        System.out.println();
    }

}
