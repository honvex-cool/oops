package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Clueless extends AbstractActiveGridEntity {

    public Clueless(Dimensions gridDimensions) {
        super("redSquare.png",gridDimensions);
    }
    @Override
    public Collection<ActiveGridEntity> idleBehaviour() {
        //do default stuff
        if(new Random().nextBoolean())
            position.moveUp();
        else
            position.moveRight();
        if(!gridDimensions.contain(position)) {
            System.err.println("Disabling...");
            disable();
        }
        return Collections.emptyList();
    }
    @Override
    public boolean hasFinishedAnimation() {
        return true;
    }
}
