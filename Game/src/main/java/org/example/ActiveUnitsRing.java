package org.example;

import org.example.units.ActiveUnit;
import org.example.units.Enemy;
import org.example.units.Player;

import java.util.ArrayList;
import java.util.List;

public class ActiveUnitsRing {
    private List<ActiveUnit> activeUnits;
    private Player player;

    public ActiveUnitsRing(List<Enemy> enemies, Player player) {
        activeUnits = new ArrayList<>();
        this.player = player;
        activeUnits.add(player);
        activeUnits.addAll(enemies);
    }

    public List<ActiveUnit> getActiveUnits() {
        return activeUnits;
    }

    public Player getPlayer() {
        return player;
    }
}
