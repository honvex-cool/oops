package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;

public class Shooter extends AbstractActiveGridEntity {

    boolean reload;

    public Shooter(int row, int column, Level level) {
        super(level, row, column, "greenSquare");
        reload=false;
    }

    public Shooter(Level level) {
        this(0, 0, level);
    }

    @Override
    public void idleBehaviour() {
        //do default stuff
        if(reload){
            reload=false;
            return;
        }


        level.requestSpawn(new Projectile("noEntrySign",level,this.gridPosition,0,-1,1));
    }
    @Override
    public void react(Signal signal){
        switch (signal){
            case REQUESTED_DOWN_MOVEMENT -> {
                level.moveHandler.moveDown(this);
            }
            case REQUESTED_UP_MOVEMENT -> {
                level.moveHandler.moveUp(this);
            }
            case REQUESTED_LEFT_MOVEMENT -> {
                level.moveHandler.moveLeft(this);
            }
            case REQUESTED_RIGHT_MOVEMENT -> {
                level.moveHandler.moveRight(this);
            }
            case REQUESTED_DOWN_ATTACK -> {
                level.requestSpawn(new Projectile("noEntrySign", level, this.gridPosition, -1, 0, 1));
            }
            case REQUESTED_UP_ATTACK -> {
                level.requestSpawn(new Projectile("noEntrySign", level, this.gridPosition, 1, 0, 1));
            }
            case REQUESTED_LEFT_ATTACK -> {
                level.requestSpawn(new Projectile("noEntrySign", level, this.gridPosition,0, -1, 1));
            }
            case REQUESTED_RIGHT_ATTACK -> {
                level.requestSpawn(new Projectile("noEntrySign", level, this.gridPosition, 0, 1, 1));
            }
        }
    }

    @Override
    public void interact(GridEntity other) {
        if(other.getClass().equals(Player.class)){
            level.hud.updateScore();
            disable();
        }
    }
}
