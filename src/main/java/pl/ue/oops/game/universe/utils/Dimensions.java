package pl.ue.oops.game.universe.utils;

public record Dimensions(int rowCount, int columnCount) {
    public boolean contain(Position position) {
        final var row = position.getRow();
        final var column = position.getColumn();
        return row >= 0 && row < rowCount() && column >= 0 && column < columnCount();
    }
}
