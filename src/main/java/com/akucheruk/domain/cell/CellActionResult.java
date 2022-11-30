package com.akucheruk.domain.cell;

/**
 * Result of action with cell
 */
public class CellActionResult {

    private final ActionResult actionResult;
    private final CellProperties cellProperties;
    private final Integer blackHoleCount;

    public CellActionResult(ActionResult actionResult, CellProperties cellProperties, Integer blackHoleCount) {
        this.actionResult = actionResult;
        this.cellProperties = cellProperties;
        this.blackHoleCount = blackHoleCount;
    }

    public ActionResult getActionResult() {
        return actionResult;
    }

    public CellProperties getCellProperties() {
        return cellProperties;
    }

    public Integer getBlackHoleCount() {
        return blackHoleCount;
    }

    @Override
    public String toString() {
        return "CellActionResult{" +
                "actionResult=" + actionResult +
                ", cellProperties=" + cellProperties +
                ", blackHoleCount=" + blackHoleCount +
                '}';
    }
}
