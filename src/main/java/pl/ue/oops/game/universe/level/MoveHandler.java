package pl.ue.oops.game.universe.level;

import com.badlogic.gdx.math.Vector2;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.utils.Position;

public class MoveHandler {
    private final Level level;

    MoveHandler(Level level){
        this.level = level;
    }

    public boolean move(ActiveGridEntity entity, int rowDelta, int columnDelta){
        if(isMovePossible(entity,rowDelta,columnDelta)){
            entity.getPosition().changeGridPosition(rowDelta,columnDelta);
            return true;
        }
        return false;
    }

    boolean isMovePossible(ActiveGridEntity entity, int rowDelta, int columnDelta){
        Position newPosition = new Position(entity.getPosition().getRow()+rowDelta,entity.getPosition().getColumn()+columnDelta,level.dimensions.tileSideLength());

        if(level.dimensions.contain(newPosition) && level.getGridEntitiesAtPosition(newPosition).isEmpty())
            return true;

        return false;
    }

    public boolean moveUp(ActiveGridEntity entity) {
        return move(entity,1,0);
    }
    public boolean moveDown(ActiveGridEntity entity) {
        return move(entity,-1,0);
    }
    public boolean moveRight(ActiveGridEntity entity) {
        return move(entity,0,1);
    }
    public boolean moveLeft(ActiveGridEntity entity) {
        return move(entity,0,-1);
    }
}
