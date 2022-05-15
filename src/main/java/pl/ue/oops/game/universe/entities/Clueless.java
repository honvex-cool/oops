package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.Collection;
import java.util.Collections;
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
    public Collection<ActiveGridEntity> idleBehaviour() {
        //do default stuff
        System.err.println("Clueless at " + position.getRow() + ", " + position.getColumn() + "...");
        if(new Random().nextBoolean())
            level.moveHandler.moveUp(this);
        else
            level.moveHandler.moveRight(this);
        if(!level.getDimensions().contain(position)) {
            disable();
        }
        return Collections.emptyList();
    }
    @Override
    public boolean hasFinishedAnimation() {
        return true;
    }
}
