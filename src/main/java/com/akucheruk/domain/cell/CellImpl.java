package com.akucheruk.domain.cell;

import java.util.Objects;
import java.util.Optional;

/**
 * CellImpl implementation of cell on the Board with cell properties,
 * information about surround cells
 */
public class CellImpl implements Cell {

    private final CellProperties properties;
    private final boolean isBlackHole;

    private Optional<CellAdjacentProperties> adjacentProperties;

    public CellImpl(CellProperties properties,
                    boolean isBlackHole,
                    Optional<CellAdjacentProperties> adjacentPropertiesOpt) {
        this.properties = properties;
        this.isBlackHole = isBlackHole;
        this.adjacentProperties = adjacentPropertiesOpt;
    }

    public static CellImpl createBlackHole(CellProperties properties) {
        return new CellImpl(properties, true, Optional.empty());
    }

    public static CellImpl createTypicalCell(CellProperties properties) {
        return new CellImpl(properties, false, Optional.of(new CellAdjacentProperties()));
    }

    @Override
    public CellProperties getProperties() {
        return this.properties;
    }

    @Override
    public Optional<CellAdjacentProperties> getAdjacentProperties() {
        return adjacentProperties;
    }

    @Override
    public boolean isBlackHole() {
        return this.isBlackHole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellImpl that = (CellImpl) o;
        return properties.equals(that.properties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(properties);
    }
}
