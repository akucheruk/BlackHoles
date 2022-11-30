package com.akucheruk.domain.cell;

import java.util.ArrayList;
import java.util.Collection;

/**
 * CellAdjacentProperties contains info about adjacent black holes count and
 * surround cells
 */
public class CellAdjacentProperties {

    private int blackHolesCount;
    private final Collection<Cell> cells;

    public CellAdjacentProperties() {
        this.cells = new ArrayList<>();
    }

    public void addAdjacentCell(Cell cell) {
        cells.add(cell);
        if (cell.isBlackHole()) {
            blackHolesCount++;
        }
    }

    public int getBlackHolesCount() {
        return blackHolesCount;
    }

    public Collection<Cell> getCells() {
        return cells;
    }
}
