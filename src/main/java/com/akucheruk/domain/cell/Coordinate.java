package com.akucheruk.domain.cell;

import java.util.Objects;

/**
 * Cell or BlackHole Coordinate on the Board
 * x - horizontal coordinate
 * y - vertical coordinate
 * index - index of cell in the board
 */
public class Coordinate {

    private final int x;
    private final int y;
    private final int index;

    public Coordinate(int x, int y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y && index == that.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, index);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                ", index=" + index +
                '}';
    }
}
