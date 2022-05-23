package pl.ue.oops.game.universe.utils;

public record Dimensions(int rowCount, int columnCount) {
    public boolean contain(GridPosition gridPosition) {
        final var row = gridPosition.getRow();
        final var column = gridPosition.getColumn();
        return row >= 0 && row < rowCount() && column >= 0 && column < columnCount();
    }
}
