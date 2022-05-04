package pl.ue.oops.game.universe.entities;

import pl.ue.oops.Config;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.utils.Dimensions;
import pl.ue.oops.game.universe.utils.Position;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Clueless extends AbstractActiveGridEntity {

    public Clueless(int row, int column, Dimensions gridDimensions) {
        super("redSquare.png",gridDimensions);
        position.set(row, column);
    }

    public Clueless(Dimensions dimensions) {
        this(0, 0, dimensions);
    }

    @Override
    public Collection<ActiveGridEntity> idleBehaviour() {
        //do default stuff
        System.err.println("Clueless at " + position.getRow() + ", " + position.getColumn() + "...");
        if(new Random().nextBoolean())
            position.moveUp();
        else
            position.moveRight();
        if(!gridDimensions.contain(position)) {
            disable();
        }
        return Collections.emptyList();
    }
    @Override
    public boolean hasFinishedAnimation() {
        return true;
    }
}
