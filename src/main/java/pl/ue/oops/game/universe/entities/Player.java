package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.Entity;
import pl.ue.oops.game.universe.entities.general.TexturedEntity;
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Player extends TexturedEntity {
    public Player(int x, int y) {
        super("blueSquare.png");
        getPosition().set(x, y);
    }

    @Override
    public Collection<Entity> react(Signal signal, Dimensions gridDimensions) {
        if(signal != null) {
            switch(signal) {
                case REQUESTED_DOWN_MOVEMENT -> getPosition().moveDown();
                case REQUESTED_UP_MOVEMENT -> getPosition().moveUp();
                case REQUESTED_LEFT_MOVEMENT -> getPosition().moveLeft();
                case REQUESTED_RIGHT_MOVEMENT -> getPosition().moveRight();
                case REQUESTED_SPAWN -> {
                    return List.of(new Clueless());
                }
            }
        }
        return Collections.emptyList();
    }
}
