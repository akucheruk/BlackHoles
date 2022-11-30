package com.akucheruk.domain;

import java.util.Collection;

/**
 * Cell represents cell on the Board with it adjacent cells, blackHoles and own status
 */
public class Cell {

    private final Coordinate coordinate;
    private final BlackHole blackHole;
    private final int adjacentBlackHolesCount;
    private final Collection<Cell> adjacentCells;

    private boolean isHide;

    public Cell(Coordinate coordinate,
                BlackHole blackHole,
                int adjacentBlackHolesCount,
                Collection<Cell> adjacentCells) {
        this.coordinate = coordinate;
        this.blackHole = blackHole;
        this.adjacentBlackHolesCount = adjacentBlackHolesCount;
        this.adjacentCells = adjacentCells;
        this.isHide = true;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public BlackHole getBlackHole() {
        return blackHole;
    }

    public int getAdjacentBlackHolesCount() {
        return adjacentBlackHolesCount;
    }

    public Collection<Cell> getAdjacentCells() {
        return adjacentCells;
    }

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public static class CellBuilder {
        private Coordinate coordinate;
        private BlackHole blackHole;
        private int adjacentBlackHolesCount;
        private Collection<Cell> adjacentCells;

        public CellBuilder withCoordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public CellBuilder withBlackHole(BlackHole blackHole) {
            this.blackHole = blackHole;
            return this;
        }

        public CellBuilder withAdjacentBlackHolesCount(int adjacentBlackHolesCount) {
            this.adjacentBlackHolesCount = adjacentBlackHolesCount;
            return this;
        }

        public CellBuilder withAdjacentCells(Collection<Cell> adjacentCells) {
            this.adjacentCells = adjacentCells;
            return this;
        }

        public Cell build() {
            return new Cell(this.coordinate, this.blackHole, this.adjacentBlackHolesCount, this.adjacentCells);
        }

    }
}
