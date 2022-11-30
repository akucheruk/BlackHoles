package com.akucheruk.domain.board;

import com.akucheruk.domain.cell.Cell;
import java.util.Map;

/**
 * Describes playing area with all needed structures, such as:
 * - boardProperties (width, height of board and count of black holes)
 * - coordinates of all cells and black holes
 */
public class Board {

    private final BoardProperties boardProperties;
    private final Map<Integer, Cell> cellIndexMap;

    public Board(BoardProperties boardProperties, Map<Integer, Cell> cellIndexMap) {
        this.boardProperties = boardProperties;
        this.cellIndexMap = cellIndexMap;
    }

    public BoardProperties getBoardProperties() {
        return boardProperties;
    }

    public Map<Integer, Cell> getCellIndexMap() {
        return cellIndexMap;
    }
}
