package org.example;

public class AlgoMapHelper {

    public static boolean isInBounds(int[][] algoMap, int x, int y) {
        return x >= 0 && y >= 0 && x < algoMap.length && y < algoMap.length;
    }

    public static boolean isInBoundsAndEqualsValue(int[][] algoMap, int x, int y, int value) {
        return isInBounds(algoMap, x, y) && algoMap[x][y] == value;
    }

}
