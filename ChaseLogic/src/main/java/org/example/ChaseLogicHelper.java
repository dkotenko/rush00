package org.example;

public class ChaseLogicHelper {

    public static boolean isEmptyGameMap(char[][] map, int x, int y) {
        return map[x][y] == ChaseLogicProperties.EMPTY_CHAR;
    }

    public static boolean isPlayerGameMap(char[][] map, int x, int y) {
        return map[x][y] == ChaseLogicProperties.PLAYER_CHAR;
    }

    public static boolean isInBoundsGameMap(char[][] map, int x, int y) {
        return x >= 0 && y >= 0 && x < map.length && y < map.length;
    }

    public static boolean isEmptyOrPlayerAndInBoundsGameMap(char[][] map, int x, int y) {
        return isInBoundsGameMap(map, x, y) &&
                (isEmptyGameMap(map, x, y) || isPlayerGameMap(map, x, y));
    }

}
