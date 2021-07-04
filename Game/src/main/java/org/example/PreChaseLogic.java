package org.example;

import java.util.ArrayList;
import java.util.List;

public class PreChaseLogic {

    public static int[][] makeAlgoMap(GameMap map) {
        int size = map.getSize();
        int[][] algoMap = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (GameMapHelper.isEmpty(map, i, j))
                    algoMap[i][j] = -1;
                else
                    algoMap[i][j] = 0;
            }
        }
        return algoMap;
    }

    public static List<int[]> getNeighbors(GameMap map, int[] cellCoords, int[][] algoMap) {
        List<int[]> list = new ArrayList<>();
        int x = cellCoords[0], y = cellCoords[1];
        if (GameMapHelper.isEmptyOrPlayerAndInBounds(map, x + 1, y) && algoMap[x+1][y] == 0) {
            list.add(new int[]{x + 1, y});
        }
        if (GameMapHelper.isEmptyOrPlayerAndInBounds(map, x - 1, y) && algoMap[x-1][y] == 0) {
            list.add(new int[]{x - 1, y});
        }
        if (GameMapHelper.isEmptyOrPlayerAndInBounds(map, x, y - 1) && algoMap[x][y-1] == 0) {
            list.add(new int[]{x, y - 1});
        }
        if (GameMapHelper.isEmptyOrPlayerAndInBounds(map, x, y + 1) && algoMap[x][y+1] == 0) {
            list.add(new int[]{x, y + 1});
        }
        return list;
    }
}
