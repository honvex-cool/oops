package pl.ue.oops.game.universe.utils;

import java.util.Objects;

public class GridPosition {
    private int row;
    private int column;

    private int prevRow;
    private int prevColumn;

    public GridPosition() {
        this(0, 0);
    }

    public GridPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public GridPosition(GridPosition other) {
        this(other.getRow(), other.getColumn());
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

    public void setPrev(int prevRow, int prevColumn) {
        this.prevRow = prevRow;
        this.prevColumn = prevColumn;
    }

    public GridPosition getPreviousGridPosition(){
        return new GridPosition(prevRow,prevColumn);
    }

    public void set(final GridPosition gridPosition) {
        setRow(gridPosition.getRow());
        setColumn(gridPosition.getColumn());
    }

    public GridPosition shifted(int rowDelta, int columnDelta) {
        return new GridPosition(getRow() + rowDelta, getColumn() + columnDelta);
    }

    @Override
    public String toString() {
        return "Position(" + getRow() + ", " + getColumn() + ")";
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof final GridPosition gridPosition))
            return false;
        return (row == gridPosition.getRow() && column == gridPosition.getColumn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRow(), getColumn());
    }
}
