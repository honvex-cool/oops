package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;

import java.util.Random;

public class Clueless extends AbstractActiveGridEntity {

    public Clueless(int row, int column, Level level) {
        super("redSquare.png",level);
        position.setGridPosition(row, column);
    }

    public Clueless(Level level) {
        this(0, 0, level);
    }

    @Override
    public void idleBehaviour() {
        //do default stuff
        System.err.println("Clueless at " + position.getRow() + ", " + position.getColumn() + "...");
        if(new Random().nextBoolean()) {
            level.moveHandler.moveUp(this);
        }
        else {
            level.moveHandler.moveRight(this);
        }
        if(new Random().nextInt()%7==0)
            level.requestSpawn(new Projectile("noEntrySign.png",level,0,-1,1),this.position);
    }
    @Override
    public boolean hasFinishedAnimation() {
        return true;
    }

    @Override
    public void interact(GridEntity other) {
        if(other.getClass().equals(Player.class)){
            disable();
        }
    }
}
