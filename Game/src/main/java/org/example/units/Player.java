package org.example.units;

import org.example.GameMap;
import org.example.MoveCommands;
import org.example.Printer;
import org.example.PropertiesHelper;
import org.example.exception.GameOverException;
import org.example.exception.GameWinException;
import org.example.exception.HitWallException;
import org.example.exception.NotInBoundsException;

import java.util.Scanner;

public class Player extends ActiveUnit {

    private final Scanner scanner;


    public Player(int[] coords) {
        super(PropertiesHelper.PLAYER_CHAR, Printer.determineColor(PropertiesHelper.PLAYER_COLOR));
        this.coords = coords;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public int[] getNewCoords(GameMap map) {
        int[] newCoords;

        MoveCommands moveCommand = getMoveCommand();
        if (MoveCommands.GIVE_UP.equals(moveCommand)) {
            throw new GameOverException("Player gave up.");
        }
        if (!isInBounds(moveCommand, map.getSize())) {
            throw new NotInBoundsException("Not in bounds.");
        }
        newCoords = getNewCoords(moveCommand);
        if (map.isGoal(newCoords[0], newCoords[1])) {
            throw new GameWinException();
        } else if (map.isEnemy(newCoords[0], newCoords[1])) {
            throw new GameOverException("You hit the enemy.");
        } else if (map.isWall(newCoords[0], newCoords[1])) {
           throw new HitWallException("You hit the wall.");
        }
        return newCoords;
    }

    private MoveCommands getMoveCommand() {
        System.out.println("Use 1(LEFT), 2(UP), 3(RIGHT), 4(DOWN), 9(GIVE UP)");
        try {
            String number = scanner.nextLine().trim();
            MoveCommands moveCommand = MoveCommands.getCommand(number);
            if (moveCommand == null) {
                throw new IllegalArgumentException("Invalid move command");
            } else {
                return moveCommand;
            }
        } catch (IllegalArgumentException e) {
            System.out.print("Invalid move command.");
            return getMoveCommand();
        }
    }
}
