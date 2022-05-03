package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.Collection;

public interface ActiveGridEntity extends GridEntity{
    Collection<ActiveGridEntity> react(Signal signal);
    Collection<ActiveGridEntity> takeTurn(Signal signal);
    public Collection<ActiveGridEntity> idleBehaviour();
    void stepAnimation(float delta);
    Collection<ActiveGridEntity> interact(GridEntity other);
}
