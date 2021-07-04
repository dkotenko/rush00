package org.example;

import org.example.exception.EnemyCantReachException;

import java.util.*;

public class ChaseLogic {

    public static int[] getNextEnemyMove(int[] enemyCoords, char[][] map,
                                         int[] playerCoords){
        int[][] algoMap = AlgoMapHelper.makeAlgoMap(map);
        algoMap[enemyCoords[0]][enemyCoords[1]] = 0;
        algoMap[playerCoords[0]][playerCoords[1]] = 0;
        List<int[]> neighbors = getNeighbors(map, enemyCoords, algoMap);
        wave(algoMap, enemyCoords, neighbors);
        Queue<int[]> neighborsQueue = new ArrayDeque<>(neighbors);
        int[] coords;
        while (!neighborsQueue.isEmpty()) {
            coords = neighborsQueue.poll();
            neighbors = getNeighbors(map, coords, algoMap);
            wave(algoMap, coords, neighbors);
            if (containsPlayer(neighbors, playerCoords)) {
                break;
            }
            neighborsQueue.addAll(neighbors);
        }
        int[] newCoords;
        if (algoMap[playerCoords[0]][playerCoords[1]] == 0) { // FIXME: 04.07.2021
            throw new EnemyCantReachException();
        } else {
            List<int[]> pathToPlayer = makePath(algoMap, playerCoords);
            newCoords = pathToPlayer.get(pathToPlayer.size() - 2);
        }
        return newCoords;
    }


    public static List<int[]> getNeighbors(char[][] map, int[] cellCoords, int[][] algoMap) {
        List<int[]> list = new ArrayList<>();
        int x = cellCoords[0], y = cellCoords[1];
        if (ChaseLogicHelper.isEmptyOrPlayerAndInBoundsGameMap(map, x + 1, y) && algoMap[x+1][y] == 0) {
            list.add(new int[]{x + 1, y});
        }
        if (ChaseLogicHelper.isEmptyOrPlayerAndInBoundsGameMap(map, x - 1, y) && algoMap[x-1][y] == 0) {
            list.add(new int[]{x - 1, y});
        }
        if (ChaseLogicHelper.isEmptyOrPlayerAndInBoundsGameMap(map, x, y - 1) && algoMap[x][y-1] == 0) {
            list.add(new int[]{x, y - 1});
        }
        if (ChaseLogicHelper.isEmptyOrPlayerAndInBoundsGameMap(map, x, y + 1) && algoMap[x][y+1] == 0) {
            list.add(new int[]{x, y + 1});
        }
        return list;
    }


    private static void printPath(List<int[]> path) {
        for (int[] coords : path) {
            System.out.printf("(%d, %d) <- ", coords[0], coords[1]);
        }
        System.out.println();
    }

    private static List<int[]> makePath(int[][] algoMap, int[] playerCoords) {
        List<int[]> path = new LinkedList<>();
        int startValue = algoMap[playerCoords[0]][playerCoords[1]];
        int[] coords = playerCoords;
        path.add(coords);
        while (startValue != 0) {
            coords = findNeighborWithValue(algoMap, coords, --startValue);
            path.add(coords);
        }
        return path;
    }

    private static int[] findNeighborWithValue(int[][] algoMap, int[] coords, int value) {
        int x = coords[0];
        int y = coords[1];
        if (AlgoMapHelper.isInBoundsAndEqualsValue(algoMap, x + 1, y, value)) {
            return new int[]{x + 1, y};
        }
        if (AlgoMapHelper.isInBoundsAndEqualsValue(algoMap, x - 1, y, value)) {
            return new int[]{x - 1, y};
        }
        if (AlgoMapHelper.isInBoundsAndEqualsValue(algoMap, x, y - 1, value)) {
            return new int[]{x, y - 1};
        }
        if (AlgoMapHelper.isInBoundsAndEqualsValue(algoMap, x, y + 1, value)) {
            return new int[]{x, y + 1};
        }
        throw new IllegalStateException();
    }

    private static boolean containsPlayer(List<int[]> list, int[] playerCoords) {
        for (int[] coords : list) {
            if (Arrays.equals(coords, playerCoords))
                return true;
        }
        return false;
    }

    private static void wave(int[][] algoMap, int[] coords, List<int[]> neighbors) {
        int x = coords[0];
        int y = coords[1];
        for (int[] neighborCoords : neighbors) {
            algoMap[neighborCoords[0]][neighborCoords[1]] = algoMap[x][y] + 1;
        }
    }

    private static void printNeighbours(List<int[]> neighbors) {
        for (int[] coord : neighbors) {
            System.out.printf("(%d, %d), ", coord[0], coord[1]);
        }
        System.out.println();
    }

    private static void printAlgoMap(int[][] algoMap) {
        for (int i = 0; i < algoMap.length; i++) {
            for (int j = 0; j < algoMap.length; j++) {
                System.out.printf("%3d", algoMap[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
