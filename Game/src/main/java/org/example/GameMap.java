package org.example;

import org.example.units.Enemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class GameMap {

    private char[][] map;
    private int size;

    private int[][] pathMap;
    private int pathLength;

    private List<Enemy> enemies;
    private int[] playerCoords;
    private int[] goalCoords;

    public GameMap(int enemies, int walls, int size) {
        this.size = size;
        this.map = new char[size][size];
        this.pathMap = new int[size][size];
        this.pathLength = 0;
        this.enemies = new ArrayList<>();
        generatePathMap(size);
        generateMap(enemies, walls, size);
    }

    private void generatePathMap(int size) {
        int[][] coords = new int[2][2];
        while (Arrays.equals(coords[0], coords[1])) {
            for (int i = 0; i < 2; i++)
                for (int j = 0; j < 2; j++)
                    coords[i][j] = getRandomN(size);
        }

        map[coords[0][0]][coords[0][1]] = PropertiesHelper.PLAYER_CHAR;
        this.playerCoords = coords[0].clone();
        map[coords[1][0]][coords[1][1]] = PropertiesHelper.GOAL_CHAR;
        this.goalCoords = coords[1].clone();
        if (PropertiesHelper.isDev) {
            System.out.println("Coords of player: " + Arrays.toString(playerCoords));
            System.out.println("Coords of goal: " + Arrays.toString(goalCoords));
        }
        while (true) {
            pathMap[coords[0][0]][coords[0][1]] = 1;
            pathLength++;
            if (Arrays.equals(coords[0], coords[1]))
                break;
            if (coords[0][1] == coords[1][1]
                    || getRandomN(1) == 1 && coords[0][0] != coords[1][0]) {
                if (coords[0][0] < coords[1][0])
                    coords[0][0]++;
                else
                    coords[0][0]--;
            } else {
                if (coords[0][1] < coords[1][1])
                    coords[0][1]++;
                else
                    coords[0][1]--;
            }
        }
    }

    private char [][] generateMap(int enemies, int walls, int size) {
        int toGenerate = size * size - pathLength;
        int leftEnemies = enemies;
        int leftWalls = walls;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                double sum = leftEnemies / (double) toGenerate;
                double randValue = getRandomDouble();
                if (pathMap[i][j] == 0) {
                    if (randValue < sum) {
                        map[i][j] = PropertiesHelper.ENEMY_CHAR;
                        this.enemies.add(new Enemy(new int[]{i, j}));
                        if (PropertiesHelper.isDev) {
                            System.out.println("Coords of enemy#" + (enemies - leftEnemies + 1) +
                                    ": " + Arrays.toString(new int[]{i, j}));
                        }
                        leftEnemies--;
                    } else if (randValue < (sum + leftWalls / (double) toGenerate)) {
                        map[i][j] = PropertiesHelper.WALL_CHAR;
                        leftWalls--;
                    }
                    toGenerate--;
                }
                if (map[i][j] == 0) {
                    map[i][j] = PropertiesHelper.EMPTY_CHAR;
                }
            }
        }
        if (leftEnemies > 0 || leftWalls > 0 || toGenerate > 0)
            throw new IllegalStateException("Невозможно сгенерировать карту");
        return map;
    }

    private int getRandomN(int range) {
        return (int) (Math.random() * range);
    }

    private double getRandomDouble() {
        return Math.random();
    }

    public void printMap() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Printer.getPrinter(map[i][j])
                        .print(map[i][j]);
            }
            System.out.println();
        }
    }

    public char[][] getMap() {
        return map;
    }

    public int[] getPlayerCoords() {
        return playerCoords;
    }

    public int getSize() {
        return size;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public boolean isEnemy(int x, int y) {
        return map[x][y] == PropertiesHelper.ENEMY_CHAR;
    }

    public boolean isGoal(int x, int y) {
        return map[x][y] == PropertiesHelper.GOAL_CHAR;
    }

    public boolean isWall(int x, int y) {
        return map[x][y] == PropertiesHelper.WALL_CHAR;
    }

    public void replace(int[] newCoords, int[] coords) {
        char temp = map[coords[0]][coords[1]];
        map[coords[0]][coords[1]] = map[newCoords[0]][newCoords[1]];
        map[newCoords[0]][newCoords[1]] = temp;
    }

    public void setPlayerCoords(int[] playerCoords) {
        this.playerCoords = playerCoords;
    }
}
