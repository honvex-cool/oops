package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.Collection;

public interface ActiveGridEntity extends GridEntity{
    void react(Signal signal);
    void takeTurn(Signal signal);
    void idleBehaviour();
    void stepAnimation(float delta);
}
