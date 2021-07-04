package org.example;

import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.IntStream;

public class MapGenerator {

    public static char [][] generate(int enemies, int walls, int size)
    {
        int attempts = 10;
        char [][] map = null;

        while (--attempts > 0)
        {
            try {
                map = generateMap(enemies, walls, size);
                break ;
            }
            catch (Exception ignored) {
                Printer.printError("can`t create map: exception");
            }
        }
        if (attempts == 0)
        {
            Printer.printError("Can`t create map");
        }
        return map;
    }

    private static char [][] generateMap(int enemies, int walls, int size)
    {
        int attempts = size * size;
        char [][] map = new char[size][size];
        ArrayList<Coords> coords = new ArrayList<Coords>();

        setEmptyCells(map);
        setRandomCell(PropertiesHelper.playerChar, coords, map);
        setRandomCell(PropertiesHelper.goalChar, coords, map);
        IntStream.range(0, enemies)
                .forEach((i) -> setRandomCell(PropertiesHelper.enemyChar, coords, map));
        IntStream.range(0, walls)
                .forEach((i) -> setRandomCell(PropertiesHelper.wallChar, coords, map));
        return map;
    }

    private static void setEmptyCells(char [][] map) {
        char emptyChar = PropertiesHelper.emptyChar;
        for (int i = 0; i < map.length; i++)
        {
            for (int j = 0; j < map[0].length; j++)
            {
                map[i][j] = emptyChar;
            }
        }
    }

    private static void setRandomCell(char c, ArrayList<Coords> coords, char [][] map)
    {
        char emptyChar = PropertiesHelper.emptyChar;
        int randomX = getRandom(0, map.length - 1);
        int randomY = getRandom(0, map.length - 1);
        int attempts = map.length - 1;

        while (attempts-- > 0 && map[randomY][randomX] != emptyChar)
        {
            randomX = getRandom(0, map.length - 1);
            randomY = getRandom(0, map.length - 1);
        }
        if (attempts == 0)
            Printer.printError("can't place objects at map");
        coords.add(new Coords(randomX, randomY));
        map[randomY][randomX] = new Character(c);
    }



    private static int getRandom(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private static int getRandomN(int range) {
        return (int) (Math.random() * range);
    }
}
