package com.akucheruk.domain.board;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Basic board properties
 * - width of board
 * - height of board
 * - black holes count
 */

public class BoardProperties {

    @Min(value = 1, message = "Width should not be less than 1")
    @Max(value = 99, message = "Width should not be greater than 99")
    private final int width;

    @Min(value = 1, message = "Height should not be less than 1")
    @Max(value = 99, message = "Height should not be greater than 99")
    private final int height;

    @Min(value = 1, message = "Black holes count should not be less than 1")
    @Max(value = MAX_BLACK_HOLE_COUNT, message = "Width should not be greater than " + MAX_BLACK_HOLE_COUNT)
    private final int blackHolesCount;

    public final static int MAX_BLACK_HOLE_COUNT = 7840;  //max_value = max_width * max_height * max_black_hole_capacity

    public BoardProperties(int width, int height, int blackHolesCount) {
        this.width = width;
        this.height = height;
        this.blackHolesCount = blackHolesCount;
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

}
