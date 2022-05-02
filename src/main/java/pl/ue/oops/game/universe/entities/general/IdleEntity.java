package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.utils.Dimensions;

public abstract class IdleEntity extends AdaptableEntity {
    @Override
    public void adapt(Signal signal, Dimensions gridDimensions) {
    }
}
