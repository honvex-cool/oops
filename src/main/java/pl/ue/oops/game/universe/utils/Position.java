package pl.ue.oops.game.universe.utils;

import java.util.Objects;

public class Position {
    private static final int DEFAULT_ROW = 0;
    private static final int DEFAULT_COLUMN = 0;
    private int row;
    private int column;

    public Position() {
        this(DEFAULT_ROW, DEFAULT_COLUMN);
    }

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void set(int row, int column) {
        setRow(row);
        setColumn(column);
    }

    public void moveUp() {
        setRow(getRow() + 1);
    }

    public void moveDown() {
        setRow(getRow() - 1);
    }

    public void moveRight() {
        setColumn(getColumn() + 1);
    }

    public void moveLeft() {
        setColumn(getColumn() - 1);
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof final Position position))
            return false;
        return row == position.getRow() && column == position.getColumn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getColumn());
    }
}
