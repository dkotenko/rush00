package org.example;

import org.example.exception.EnemyCantReachException;

import java.util.*;

public class ChaseLogic {

    public static int[] getNextEnemyMove(int[] enemyCoords, char[][] map,
                                         int[] playerCoords, int[][] algoMap){
        algoMap[enemyCoords[0]][enemyCoords[1]] = 0;
        algoMap[playerCoords[0]][playerCoords[1]] = 0;
//        printAlgoMap(algoMap);
        List<int[]> neighbors = getNeighbors(enemyCoords, algoMap);
//        printNeighbours(neighbors);
        // TODO: 04.07.2021 если null то враг заблочен
        wave(algoMap, enemyCoords, neighbors);
//        printAlgoMap(algoMap);
        Queue<int[]> neighborsQueue = new ArrayDeque<>(neighbors);
        int[] coords;
        while (!neighborsQueue.isEmpty()) {
            coords = neighborsQueue.poll();
            neighbors = getNeighbors(coords, algoMap);
            wave(algoMap, coords, neighbors);
            if (containsPlayer(neighbors)) {
                break;
            }
            neighborsQueue.addAll(neighbors);
        }
//        System.out.println(map.playerCoords[0] + " " + map.playerCoords[1]);
//        System.out.println(enemyCoords[0] + " " + enemyCoords[1]);
//        printAlgoMap(algoMap);
        int[] newCoords;
        if (algoMap[playerCoords[0]][playerCoords[1]] == 0) { // FIXME: 04.07.2021
            throw new EnemyCantReachException();
        } else {
            List<int[]> pathToPlayer = makePath(algoMap, enemyCoords);
//            printPath(pathToPlayer);
            newCoords = pathToPlayer.get(pathToPlayer.size() - 2);
        }
        map[enemyCoords[0]][enemyCoords[1]] = ChaseLogicProperties.EMPTY_CHAR;
        map[newCoords[0]][newCoords[1]] = PropertiesHelper.ENEMY_CHAR;
        return newCoords;
    }





    private static void printPath(List<int[]> path) {
        for (int[] coords : path) {
            System.out.printf("(%d, %d) <- ", coords[0], coords[1]);
        }
        System.out.println();
    }

    private static List<int[]> makePath(int[][] algoMap, int[] enemyCoords) {
        List<int[]> path = new LinkedList<>();
        int startValue = algoMap[map.playerCoords[0]][map.playerCoords[1]];
        int[] coords = map.playerCoords;
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
