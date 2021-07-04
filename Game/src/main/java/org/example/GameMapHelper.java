package org.example;

public class GameMapHelper {

    public static boolean isEmpty(GameMap map, int x, int y) {
        return map.getMap()[x][y] == PropertiesHelper.EMPTY_CHAR;
    }

    public static boolean isPlayer(GameMap map, int x, int y) {
        return map.getMap()[x][y] == PropertiesHelper.PLAYER_CHAR;
    }

    public static boolean isCoordInBounds(GameMap map, int x, int y) {
        return x >= 0 && y >= 0 && x < map.getSize() && y < map.getSize();
    }

    public static boolean isEmptyAndInBounds(GameMap map, int x, int y) {
        return isCoordInBounds(map, x, y) && isEmpty(map, x, y);
    }

    public static boolean isEmptyOrPlayer(GameMap map, int x, int y) {
        return isEmpty(map, x, y) || isPlayer(map, x, y);
    }

    public static boolean isEmptyOrPlayerAndInBounds(GameMap map, int x, int y) {
        return isCoordInBounds(map, x, y) && isEmptyOrPlayer(map, x , y);
    }

    public static boolean isPlayerAndInBounds(GameMap map, int x, int y) {
        return isCoordInBounds(map, x, y) && isPlayer(map, x, y);
    }


}
