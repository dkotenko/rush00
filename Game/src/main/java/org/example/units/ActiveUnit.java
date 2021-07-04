package org.example.units;

import com.diogonunes.jcdp.color.api.Ansi;
import org.example.GameMap;
import org.example.MoveCommands;

import static org.example.MoveCommands.*;

public abstract class ActiveUnit extends Unit {

    public ActiveUnit(char symbol, Ansi.BColor bColor) {
        super(symbol, bColor);
    }

    public abstract int[] getNewCoords(GameMap map, int[] targetCoords);

    protected int[] getNewCoords(MoveCommands command, int[] currCoords) {
        int[] newCoords = new int[2];

        newCoords[0] = currCoords[0];
        newCoords[1] = currCoords[1];
        if (DOWN == command) {
            newCoords[0]++;
        } else if (UP == command) {
            newCoords[0]--;
        } else if (RIGHT == command) {
            newCoords[1]++;
        } else {
            newCoords[1]--;
        }
        return newCoords;
    }
}
