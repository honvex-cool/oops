package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.animations.MoveAnimation;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;
public class Shooter extends AbstractActiveGridEntity {

    boolean reload;

    public Shooter(int row, int column, Level level) {
        super("greenSquare.png",level);
        position.setGridPosition(row, column);
        position.setRenderPositionAsGridPosition();
        moveAnimation = new MoveAnimation(0,7,this,texture,texture);
        reload=false;
    }

    public Shooter(Level level) {
        this(0, 0, level);
    }

    @Override
    public void idleBehaviour() {
        //do default stuff
        System.err.println("Shooter at " + position.getRow() + ", " + position.getColumn() + "...");
        if(reload){
            reload=false;
            return;
        }


        level.requestSpawn(new Projectile("noEntrySign.png",level,0,-1,1),this.position);
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
                level.requestSpawn(new Projectile("noEntrySign.png", level, -1, 0, 1), this.position);
            }
            case REQUESTED_UP_ATTACK -> {
                level.requestSpawn(new Projectile("noEntrySign.png", level, 1, 0, 1), this.position);
            }
            case REQUESTED_LEFT_ATTACK -> {
                level.requestSpawn(new Projectile("noEntrySign.png", level, 0, -1, 1), this.position);
            }
            case REQUESTED_RIGHT_ATTACK -> {
                level.requestSpawn(new Projectile("noEntrySign.png", level, 0, 1, 1), this.position);
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
