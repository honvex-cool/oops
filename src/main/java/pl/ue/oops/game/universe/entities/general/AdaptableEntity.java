package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.Collection;
import java.util.Collections;

public abstract class AdaptableEntity extends AbstractEntity {
    @Override
    public Collection<Entity> react(Signal signal, Dimensions gridDimensions) {
        adapt(signal, gridDimensions);
        return Collections.emptyList();
    }

    public abstract void adapt(Signal signal, Dimensions gridDimensions);
}
