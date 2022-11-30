package com.akucheruk;
import com.akucheruk.domain.cell.CellActionResult;
import com.akucheruk.domain.board.Board;
import com.akucheruk.domain.board.BoardProperties;
import com.akucheruk.domain.cell.Cell;
import com.akucheruk.domain.generator.CellGenerator;
import com.akucheruk.domain.validator.BoardPropertiesValidator;
import com.akucheruk.handler.BoardActionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Start the Black Hole game!!!");

        //Define board width, height and black hole count
        BoardProperties boardProperties = new BoardProperties(3, 3, 1);

        //Create validator for boardProperties and preform validation
        BoardPropertiesValidator validator = new BoardPropertiesValidator();
        Set<ConstraintViolation<BoardProperties>> violations = validator.validate(boardProperties);
        if (!violations.isEmpty()) {
            StringJoiner violationJoiner = new StringJoiner(" \n");
            violations.forEach(violation -> violationJoiner.add(violation.getMessage()));
            throw new ValidationException("Board properties is not valid: \n" + violationJoiner);
        }

        //More complex validation. Check capacity of black holes for current board
        validator.validateBlackHoleCapacity(boardProperties);

        //Generate cells and black holes randomly
        CellGenerator cellGenerator = new CellGenerator(boardProperties);
        Map<Integer, Cell> cellMap = cellGenerator.generateCells();

        //At least create the board which contains all needed properties
        Board board = new Board(boardProperties, cellMap);

        //Create handler for performing actions with cells
        BoardActionHandler handler = new BoardActionHandler(board);
        List<CellActionResult> actionResults = handler.openCell(3);

        System.out.println("Finish!");
    }
}
