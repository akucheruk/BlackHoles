package com.akucheruk.domain.cell;

import java.util.Optional;

/**
 * Common interface for cells with common methods
 */
public interface Cell {
    /**
     * @return CellProperties - properties of cell
     */
    CellProperties getProperties();

    /**
     * @return Optional<CellAdjacentProperties> - information about surround cells. Black holes does not have such info
     */
    Optional<CellAdjacentProperties> getAdjacentProperties();

    /**
     * @return boolean value about is cell black hole or not
     */
    boolean isBlackHole();
}
