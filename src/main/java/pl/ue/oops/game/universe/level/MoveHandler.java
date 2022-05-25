package pl.ue.oops.game.universe.level;

import pl.ue.oops.game.universe.entities.Clueless;
import pl.ue.oops.game.universe.entities.Player;
import pl.ue.oops.game.universe.entities.RockEntity;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.utils.GridPosition;

public class MoveHandler {
    private final Level level;

    MoveHandler(Level level){
        this.level = level;
    }

    public boolean move(ActiveGridEntity entity, int rowDelta, int columnDelta){
        if(isMovePossible(entity,rowDelta,columnDelta)){
            final var destination = entity.getPosition().shifted(rowDelta, columnDelta);
            entity.getAnimationController().playMoveAnimation(entity.getPosition(), destination, 0.1f);
            final var interruptedEntities = level.getGridEntitiesAtPosition(entity.getPosition());
            for (final var interruptedEntity: interruptedEntities) {
                if(interruptedEntity != entity)
                    interruptedEntity.interact(entity);
            }
            entity.getPosition().set(destination);
            System.err.println("Entity " + entity + " at position " + entity.getPosition());
            return true;
        }
        return false;
    }

    boolean isMovePossible(ActiveGridEntity entity, int rowDelta, int columnDelta){ //That's a very dirty function - we should clean it later
        GridPosition newGridPosition = new GridPosition(entity.getPosition().getRow()+rowDelta,entity.getPosition().getColumn()+columnDelta);
        if(entity instanceof Projectile) //Projectiles may go out of the map bounds             //they are getting destroyed then (in Projectile.idleBehaviour())
            return true;
        if(!level.dimensions.contain(newGridPosition))//And nothing else can
            return false;
        var list = level.getGridEntitiesAtPosition(newGridPosition);
        if(entity.getClass().equals(Player.class)){ //player can't enter rocks
            for (var x:list)
                if(x instanceof RockEntity)
                    return false;
            return true;
        }
        //Here it gets even worse
        //We need some kind of checker class with function boolean canEnter(ActiveGridEntity entering,GridEntity entered);
        for (var x:list){
            if(x.getClass().equals(RockEntity.class) || x.getClass().equals(Player.class) || x.getClass().equals(Clueless.class))
                return false;
        }
        return true;
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
