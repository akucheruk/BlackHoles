package com.akucheruk.domain;

import com.akucheruk.domain.exception.NotImplementedException;

import java.util.Collection;
import java.util.Map;

/**
 * Describes playing area with all needed structures, such as:
 * - width, height of board and count of black holes
 * - coordinates of all black holes
 * - information about each cell on the board
 */
public class Board {

    private final int width;
    private final int height;
    private final int blackHolesCount;
    private final Map<Coordinate, BlackHole> holeCoordinates; //TODO make thread-safe structure
    private final Collection<Cell> cells; //TODO make thread-safe structure

    private Board(int width, int height, int blackHolesCount) {
        this.width = width;
        this.height = height;
        this.blackHolesCount = blackHolesCount;
        this.holeCoordinates = generateBlackHoles(width, height, blackHolesCount);
        this.cells = generateCells(width, height, this.holeCoordinates);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBlackHolesCount() {
        return blackHolesCount;
    }

    //TODO: should be implemented in Part 2
    private Map<Coordinate, BlackHole> generateBlackHoles(int width, int height, int blackHolesCount) {
        throw new NotImplementedException("generateBlackHoles is not implemented yet!");
    }

    //TODO should be implemented in Part 3
    private Collection<Cell> generateCells(int width, int height, Map<Coordinate, BlackHole> holeCoordinates) {
        throw new NotImplementedException("generateCells is not implemented yet!");
    }

    public static class BoardBuilder {
        private int width;
        private int height;
        private int blackHolesCount;

        public BoardBuilder withWidth(int width) {
            this.width = width;
            return this;
        }

        public BoardBuilder withHeight(int height) {
            this.height = height;
            return this;
        }

        public BoardBuilder withBlackHolesCount(int blackHolesCount) {
            this.blackHolesCount = blackHolesCount;
            return this;
        }

        public Board build() {
            return new Board(this.width, this.height, this.blackHolesCount);
        }

    }

}
