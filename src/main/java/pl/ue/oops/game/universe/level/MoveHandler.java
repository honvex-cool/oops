package pl.ue.oops.game.universe.level;

import com.badlogic.gdx.math.Vector2;
import pl.ue.oops.game.universe.entities.Player;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.utils.Position;

public class MoveHandler {
    private final Level level;

    MoveHandler(Level level){
        this.level = level;
    }

    public boolean move(ActiveGridEntity entity, int rowDelta, int columnDelta){
        if(isMovePossible(entity,rowDelta,columnDelta)){
            entity.getPosition().changeGridPosition(rowDelta,columnDelta);
            final var interruptedEntities = level.getGridEntitiesAtPosition(entity.getPosition());
            for (final var interruptedEntity: interruptedEntities) {
                if(interruptedEntity != entity)
                    interruptedEntity.interact(entity);
            }
            return true;
        }
        return false;
    }

    boolean isMovePossible(ActiveGridEntity entity, int rowDelta, int columnDelta){
        Position newPosition = new Position(entity.getPosition().getRow()+rowDelta,entity.getPosition().getColumn()+columnDelta,level.dimensions.tileSideLength());
        if(Projectile.class.isAssignableFrom(entity.getClass())) //Projectiles may go out of the map bounds             //they are getting destroyed then (in Projectile.idleBehaviour())
            return true;
        if(!level.dimensions.contain(newPosition))//And nothing else can
            return false;
        if(entity.getClass().equals(Player.class) || level.getGridEntitiesAtPosition(newPosition).isEmpty()) //Player may move onto objects to destroy them and Clueless can't, this is just some test behaviour, it will be changed
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
