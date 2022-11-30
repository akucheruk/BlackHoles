package com.akucheruk.utils;

import com.akucheruk.domain.board.BoardProperties;
import com.akucheruk.domain.cell.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class CoordinateUtils {

    public static int coordinateToIndex(Coordinate coordinate, BoardProperties boardProperties) {
        if (coordinate == null || boardProperties == null) {
            throw new IllegalArgumentException("Coordinate and boardProperties should be not null!");
        }

        return (coordinate.getY() * boardProperties.getWidth()) + (coordinate.getX());
    }

    public static Coordinate cellIndexToCoordinate(int cellIndex, BoardProperties boardProperties) {
        if (cellIndex < 0 || cellIndex > boardProperties.getWidth() * boardProperties.getHeight()) {
            throw new IllegalArgumentException(
                    "Cell index should not be negative or greater then cells count on board! Current value: " + cellIndex
            );
        }
        int x = cellIndex % boardProperties.getWidth();
        int y = cellIndex / boardProperties.getWidth();

        return new Coordinate(x, y, cellIndex);
    }

    public static List<Integer> getAdjacentIndexes(Coordinate cellCoordinate, BoardProperties boardProperties) {
        List<Integer> adjacentIndexes = new ArrayList<>();

        int maxXCoordinate = boardProperties.getWidth() - 1;
        int maxYCoordinate = boardProperties.getHeight() - 1;

        int cellIndex = cellCoordinate.getIndex();
        int xCoordinate = cellCoordinate.getX();
        int yCoordinate = cellCoordinate.getY();

        if (xCoordinate + 1 <= maxXCoordinate) {
            adjacentIndexes.add(cellIndex + 1);
        }

        if (xCoordinate - 1 >= 0) {
            adjacentIndexes.add(cellIndex - 1);
        }

        if (yCoordinate - 1 >= 0) {
            adjacentIndexes.add(cellIndex - boardProperties.getWidth());
            if (xCoordinate + 1 <= maxXCoordinate) {
                adjacentIndexes.add(cellIndex - boardProperties.getWidth() + 1);
            }
            if (xCoordinate - 1 >= 0) {
                adjacentIndexes.add(cellIndex - boardProperties.getWidth() - 1);
            }
        }

        if (yCoordinate + 1 <= maxYCoordinate) {
            adjacentIndexes.add(cellIndex + boardProperties.getWidth());
            if (xCoordinate + 1 <= maxXCoordinate) {
                adjacentIndexes.add(cellIndex + boardProperties.getWidth() + 1);
            }
            if (xCoordinate - 1 >= 0) {
                adjacentIndexes.add(cellIndex + boardProperties.getWidth() - 1);
            }
        }

        return adjacentIndexes;
    }
}
