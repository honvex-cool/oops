package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.Entity;
import pl.ue.oops.game.universe.entities.general.TexturedEntity;
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Clueless extends TexturedEntity {

    public Clueless() {
        super("redSquare.png");
    }

    @Override
    public Collection<Entity> react(Signal signal, Dimensions gridDimensions) {
        if(new Random().nextBoolean())
            getPosition().moveUp();
        else
            getPosition().moveRight();
        if(!gridDimensions.contain(getPosition())) {
            System.err.println("Disabling...");
            disable();
        }
        return Collections.emptyList();
    }

    @Override
    public void stepAnimation(float delta) {
    }

    @Override
    public boolean hasFinishedAnimation() {
        return true;
    }
}
