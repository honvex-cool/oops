package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.utils.Position;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractEntity implements Entity {
    private final Position position = new Position();
    private boolean active = true;

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    protected final void disable() {
        active = false;
    }

    @Override
    public void dispose() {
    }

    @Override
    public Collection<Entity> interact(Entity other) {
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
