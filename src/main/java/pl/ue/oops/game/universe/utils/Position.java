package pl.ue.oops.game.universe.utils;

import com.badlogic.gdx.math.Vector3;
import pl.ue.oops.game.universe.level.Level;

import java.util.Objects;

public class Position {
    private static final int DEFAULT_ROW = 0;
    private static final int DEFAULT_COLUMN = 0;
    private int row;
    private int column;

    private final float tileSideLength;

    private Vector3 renderPosition;

    public Position() {
        this(1);
    }

    public Position(int row, int column,float tileSideLength) {
        this.row = row;
        this.column = column;
        this.tileSideLength = tileSideLength;
        renderPosition = new Vector3(column*tileSideLength,row*tileSideLength,0);
    }

    public Position(float tileSideLength) {
        this(DEFAULT_ROW,DEFAULT_COLUMN,tileSideLength);
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
    public Vector3 getRenderPosition() {
        return new Vector3(renderPosition);
    }
    public Vector3 getGridPosition() {
        return new Vector3(column*tileSideLength,row*tileSideLength,0);
    }

    public Vector3 getMoveVector(){
        Vector3 temp = new Vector3(getGridPosition());
        temp.add(getRenderPosition().scl(-1));
        return temp;
    }
    public void set(int row, int column) {
        setRow(row);
        setColumn(column);
    }
    public void setRenderPosition(Vector3 renderPosition) {
        this.renderPosition = renderPosition;
    }
    public void setRenderPositionAsGridPosition(){
        setRenderPosition(getGridPosition());
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
