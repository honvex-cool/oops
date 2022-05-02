package pl.ue.oops.game.universe.entities.general;

import com.badlogic.gdx.utils.Disposable;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.utils.Dimensions;
import pl.ue.oops.game.universe.utils.Position;

import java.util.Collection;

public interface Entity extends Disposable, Visible {
    Position getPosition();
    Collection<Entity> react(Signal signal, Dimensions gridDimensions);
    boolean isActive();

    Collection<Entity> interact(Entity other);
}
