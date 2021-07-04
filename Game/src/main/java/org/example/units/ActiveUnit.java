package org.example.units;

import com.diogonunes.jcdp.color.api.Ansi;
import org.example.GameMap;
import org.example.MoveCommands;

import java.util.Arrays;

import static org.example.MoveCommands.*;

public abstract class ActiveUnit extends Unit {

    public ActiveUnit(char symbol, Ansi.BColor bColor) {
        super(symbol, bColor);
    }

    public abstract int[] getNewCoords(GameMap map);

    protected int[] getNewCoords(MoveCommands command) {
        int[] newCoords = new int[2];

        newCoords[0] = coords[0];
        newCoords[1] = coords[1];
        if (DOWN.equals(command)) {
            newCoords[0]++;
        } else if (UP.equals(command)) {
            newCoords[0]--;
        } else if (RIGHT.equals(command)) {
            newCoords[1]++;
        } else if (LEFT.equals(command)) {
            newCoords[1]--;
        } else {
            throw new IllegalArgumentException();
        }
        return newCoords;
    }

    public boolean isInBounds(MoveCommands moveCommand, int mapSize) {
        return !(LEFT.equals(moveCommand) && this.coords[1] == 0 ||
                UP.equals(moveCommand) && this.coords[0] == 0 ||
                RIGHT.equals(moveCommand) && this.coords[1] == mapSize - 1 ||
                DOWN.equals(moveCommand) && this.coords[0] == mapSize - 1);
    }
}
