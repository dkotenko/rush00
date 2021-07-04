package org.example.units;

import com.diogonunes.jcdp.color.api.Ansi;
import org.example.*;
import org.example.exception.EnemyCantReachException;
import org.example.exception.GameOverException;

public class Enemy extends ActiveUnit {

    public Enemy(int[] coords) {
        super(PropertiesHelper.ENEMY_CHAR, Printer.determineColor(PropertiesHelper.ENEMY_COLOR));
        this.coords = coords;
    }

    @Override
    public int[] getNewCoords(GameMap map, int[] targetCoords) {
        if (checkIfPlayerNear(map)) {
            throw new GameOverException();
        }
        try {
            ChaseLogic.getNextEnemyMove(coords, map.getMap(), map.getPlayerCoords(), PreChaseLogic.makeAlgoMap(map));
        } catch (EnemyCantReachException e) {
            return getCoordsIfEnemySurrounded(map);
        }
    }

    private int[] getCoordsIfEnemySurrounded(GameMap map) {
        int x = coords[0];
        int y = coords[1];
        int[] playerCoords = map.getPlayerCoords();
        if (playerCoords[0] > x && GameMapHelper.isEmptyAndInBounds(map, x + 1, y)) {
            return new int[] {x + 1, y};
        }
        if (playerCoords[0] < x && GameMapHelper.isEmptyAndInBounds(map, x - 1, y)) {
            return new int[] {x - 1, y};
        }
        if (playerCoords[1] > y && GameMapHelper.isEmptyAndInBounds(map, x, y + 1)) {
            return new int[] {x, y + 1};
        }
        if (playerCoords[1] < y && GameMapHelper.isEmptyAndInBounds(map, x, y - 1)) {
            return new int[] {x, y - 1};
        }
        return getAnyNeighborCell(map); // если логичного хода к игроку нет ("логичный" = "по направлению к игроку")
    }

    private int[] getAnyNeighborCell(GameMap map) {
        int x = coords[0];
        int y = coords[1];
        if (GameMapHelper.isEmptyAndInBounds(map, x + 1, y)) {
            return new int[]{x + 1, y};
        }
        if (GameMapHelper.isEmptyAndInBounds(map, x - 1, y)) {
            return new int[]{x - 1, y};
        }
        if (GameMapHelper.isEmptyAndInBounds(map, x, y + 1)) {
            return new int[] {x, y+1};
        }
        if (GameMapHelper.isEmptyAndInBounds(map, x, y - 1)) {
            return new int[] {x, y-1};
        }
        return coords; // если со всех сторон окружён
    }

    private boolean checkIfPlayerNear(GameMap map) {
        int x = coords[0];
        int y = coords[1];
        return GameMapHelper.isPlayerAndInBounds(map, x + 1, y) ||
                GameMapHelper.isPlayerAndInBounds(map, x - 1, y) ||
                GameMapHelper.isPlayerAndInBounds(map, x, y - 1) ||
                GameMapHelper.isPlayerAndInBounds(map, x, y + 1);
    }
}
