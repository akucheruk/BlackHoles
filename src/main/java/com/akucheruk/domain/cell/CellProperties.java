package com.akucheruk.domain.cell;

import java.util.Objects;

/**
 * Describes basic cell properties
 */
public class CellProperties {

    private final Coordinate coordinate;
    private boolean isHide;
    private boolean isMarked;

    public CellProperties(Coordinate coordinate) {
        this.coordinate = coordinate;
        this.isHide = true;
        this.isMarked = false;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellProperties that = (CellProperties) o;
        return coordinate.equals(that.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }

    @Override
    public String toString() {
        return "CellProperties{" +
                "coordinate=" + coordinate +
                ", isHide=" + isHide +
                ", isMarked=" + isMarked +
                '}';
    }
}
