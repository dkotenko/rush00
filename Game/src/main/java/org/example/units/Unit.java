package org.example.units;

import com.diogonunes.jcdp.color.api.Ansi.BColor;

public abstract class Unit {
    protected int[] coords;
    private final char symbol;
    private final BColor bColor;

    public Unit(char symbol, BColor bColor) {
        this.symbol = symbol;
        this.bColor = bColor;
    }

    public int[] getCoords() {
        return coords;
    }

    public void setCoords(int[] coords) {
        this.coords = coords;
    }

    public BColor getBColor() {
        return bColor;
    }

    public char getSymbol() {
        return symbol;
    }
}
