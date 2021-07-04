package org.example;

public class AlgoMapHelper {

    public static boolean isInBounds(int[][] algoMap, int x, int y) {
        return x >= 0 && y >= 0 && x < algoMap.length && y < algoMap.length;
    }

    public static boolean isInBoundsAndEqualsValue(int[][] algoMap, int x, int y, int value) {
        return isInBounds(algoMap, x, y) && algoMap[x][y] == value;
    }

    public static int[][] makeAlgoMap(char[][] map) {
        int size = map.length;
        int[][] algoMap = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (ChaseLogicHelper.isEmptyGameMap(map, i, j))
                    algoMap[i][j] = 0;
                else
                    algoMap[i][j] = -1;
            }
        }
        return algoMap;
    }


}
