package com.akucheruk.handler;

import com.akucheruk.domain.cell.ActionResult;
import com.akucheruk.domain.cell.CellActionResult;
import com.akucheruk.domain.cell.Coordinate;
import com.akucheruk.domain.board.Board;
import com.akucheruk.domain.board.BoardProperties;
import com.akucheruk.domain.cell.Cell;
import com.akucheruk.domain.cell.CellAdjacentProperties;
import com.akucheruk.domain.cell.CellProperties;
import com.akucheruk.domain.exception.BlackHoleRuntimeException;
import com.akucheruk.domain.exception.InvalidInputArgument;
import com.akucheruk.domain.exception.NotImplementedException;
import com.akucheruk.utils.CoordinateUtils;

import java.util.*;

/**
 * Class provides functionality for interaction with the board. Such as:
 * - open cell
 * - mark cell
 */
public class BoardActionHandler {

    private final Board board;
    private final int maxCellIndex;

    public BoardActionHandler(Board board) {
        this.board = board;
        BoardProperties boardProperties = this.board.getBoardProperties();
        this.maxCellIndex = boardProperties.getWidth() * boardProperties.getHeight();
    }

    public CellActionResult markCell(int cellIndex) {
        throw new NotImplementedException("Mark cell functionality will be added in next version!");
    }

    public List<CellActionResult> openCell(int cellIndex) {
        validateCellIndexRange(cellIndex);
        Cell cell = getCellByIndex(cellIndex)
                .orElseThrow(() -> new BlackHoleRuntimeException(String.format("Cell by index: %s is null!", cellIndex)));

        if (!cell.getProperties().isHide()) {
            return Collections.singletonList(
                    new CellActionResult(
                            ActionResult.ALREADY_OPENED,
                            cell.getProperties(),
                            null
                    )
            );
        }

        if (cell.isBlackHole()) {
            return Collections.singletonList(
                    new CellActionResult(
                            ActionResult.BLOWN_UP,
                            cell.getProperties(),
                            null
                    )
            );
        }

        return openCurrentCell(cell);
    }

    private void validateCellIndexRange(int cellIndex) {
        if (cellIndex < 0 || cellIndex > maxCellIndex) {
            throw new InvalidInputArgument(
                    String.format("Cell index is should be in range 0 to %s. Current value: %s",
                            maxCellIndex,
                            cellIndex)
            );
        }
    }

    private Optional<Cell> getCellByIndex(int cellIndex) {
        Map<Integer, Cell> cellMap = board.getCellIndexMap();
        return Optional.ofNullable(cellMap.get(cellIndex));
    }

    private List<CellActionResult> openCurrentCell(Cell currentCell) {
        CellProperties cellProperties = currentCell.getProperties();
        Coordinate cellCoordinate = cellProperties.getCoordinate();

        CellAdjacentProperties cellAdjacentProperties = currentCell.getAdjacentProperties()
                .orElseThrow(() -> new BlackHoleRuntimeException(
                        String.format("Cell by index: %s doesn't have adjacentProperties!", cellCoordinate.getIndex())));

        List<CellActionResult> results = new ArrayList<>();
        currentCell.getProperties().setHide(false);
        results.add(new CellActionResult(ActionResult.OPENED, cellProperties, cellAdjacentProperties.getBlackHolesCount()));

        if (cellAdjacentProperties.getBlackHolesCount() == 0) {
            results.addAll(updateSurroundCells(cellCoordinate.getIndex()));
        }

        return results;
    }

    private List<CellActionResult> updateSurroundCells(int currentCellIndex) {
        BoardProperties boardProperties = board.getBoardProperties();
        Coordinate currentCellCoordinate = CoordinateUtils.cellIndexToCoordinate(currentCellIndex, boardProperties);

        return updateSurroundCells(currentCellCoordinate.getIndex(), new ArrayList<>());
    }

    private List<CellActionResult> updateSurroundCells(int currentCellIndex, List<CellActionResult> cellActionResults) {
        BoardProperties boardProperties = board.getBoardProperties();

        Coordinate currentCellCoordinate = CoordinateUtils.cellIndexToCoordinate(currentCellIndex, boardProperties);
        List<Integer> adjacentIndexes = CoordinateUtils.getAdjacentIndexes(currentCellCoordinate, boardProperties);

        adjacentIndexes.forEach(cellIndex -> {
            Cell cell = board.getCellIndexMap().get(cellIndex);
            openSurroundCell(cell).ifPresent(cellActionResult -> {
                cellActionResults.add(cellActionResult);
                if (cellActionResult.getBlackHoleCount() == 0) {
                    updateSurroundCells(cell.getProperties().getCoordinate().getIndex(), cellActionResults);
                }
            });
        });

        return cellActionResults;
    }

    private Optional<CellActionResult> openSurroundCell(Cell cell) {
        if (!cell.isBlackHole() && cell.getProperties().isHide()) {
            CellAdjacentProperties cellAdjacentProperties = cell.getAdjacentProperties()
                    .orElseThrow(() -> new BlackHoleRuntimeException(
                            String.format("Cell by index: %s doesn't have adjacentProperties!", cell.getProperties().getCoordinate().getIndex())
                    ));
            cell.getProperties().setHide(false);
            return Optional.of(
                    new CellActionResult(
                            ActionResult.OPENED,
                            cell.getProperties(),
                            cellAdjacentProperties.getBlackHolesCount()
                    )
            );
        } else {
            return Optional.empty();
        }
    }
}
