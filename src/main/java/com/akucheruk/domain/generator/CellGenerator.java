package com.akucheruk.domain.generator;

import com.akucheruk.domain.board.BoardProperties;
import com.akucheruk.domain.cell.Cell;
import com.akucheruk.domain.cell.Coordinate;
import com.akucheruk.domain.cell.CellImpl;
import com.akucheruk.domain.cell.CellAdjacentProperties;
import com.akucheruk.domain.cell.CellProperties;
import com.akucheruk.domain.exception.BlackHoleRuntimeException;
import com.akucheruk.utils.CoordinateUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Common class for generation cells and black holes on the board
 */
public class CellGenerator {

    private final BoardProperties boardProperties;

    public CellGenerator(BoardProperties boardProperties) {
        this.boardProperties = boardProperties;
    }

    public Map<Integer, Cell> generateCells() {
        int totalCellsCount = boardProperties.getWidth() * boardProperties.getHeight();

        List<CellProperties> cellProperties = Stream.iterate(0, i -> i + 1)
                .limit(totalCellsCount)
                .map(cellIndex -> CoordinateUtils.cellIndexToCoordinate(cellIndex, boardProperties))
                .map(CellProperties::new)
                .collect(Collectors.toList());

        Set<CellProperties> blackHoleCellProperties = generateBlackHolesCellProperties();

        Map<Integer, Cell> cellIndexMap = generateCellIndexMap(cellProperties, blackHoleCellProperties);
        cellIndexMap.values().forEach(cell -> addAdjacentProperties(cell, cellIndexMap));

        return cellIndexMap;
    }

    private Set<CellProperties> generateBlackHolesCellProperties() {
        return new Random().ints(0, boardProperties.getWidth() * boardProperties.getHeight() - 1)
                .distinct()
                .limit(boardProperties.getBlackHolesCount())
                .mapToObj(index -> CoordinateUtils.cellIndexToCoordinate(index, boardProperties))
                .map(CellProperties::new)
                .collect(Collectors.toSet());
    }

    private Map<Integer, Cell> generateCellIndexMap(List<CellProperties> allCellProperties, Set<CellProperties> blackHoleCells) {
        return allCellProperties.stream()
                .map(cellProp -> makeCell(cellProp, blackHoleCells))
                .collect(Collectors.toMap(
                        key -> key.getProperties().getCoordinate().getIndex(),
                        Function.identity()));
    }

    private Cell makeCell(CellProperties cellProp, Set<CellProperties> blackHoleCells) {
        return blackHoleCells.contains(cellProp)
                ? CellImpl.createBlackHole(cellProp)
                : CellImpl.createTypicalCell(cellProp);
    }

    private void addAdjacentProperties(Cell currentCell, Map<Integer, Cell> indexCellMap) {
        if (!currentCell.isBlackHole()) {
            Coordinate cellCoordinate = currentCell.getProperties().getCoordinate();

            List<Integer> adjacentIndexes = CoordinateUtils.getAdjacentIndexes(cellCoordinate, boardProperties);
            adjacentIndexes.forEach(cellIndex -> {
                Optional<CellAdjacentProperties> cellAdjacentPropertiesOptional = currentCell.getAdjacentProperties();
                if (cellAdjacentPropertiesOptional.isEmpty()) {
                    throw new BlackHoleRuntimeException("AdjacentProperties is not exist for typical cell!");
                }

                System.out.println(cellIndex);
                cellAdjacentPropertiesOptional.get().addAdjacentCell(indexCellMap.get(cellIndex));
            });
        }
    }

}
