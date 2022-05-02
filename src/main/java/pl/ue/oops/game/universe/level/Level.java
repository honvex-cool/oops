package pl.ue.oops.game.universe.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.Entities;
import pl.ue.oops.game.universe.entities.general.Entity;
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Level {
    private List<Entity> entities = new ArrayList<>();
    private final Dimensions dimensions;
    private final float tileSideLength;

    public Level(Dimensions dimensions, float tileSideLength) {
        this.dimensions = dimensions;
        this.tileSideLength = tileSideLength;
    }

    public Level add(Entity entity) {
        entities.add(entity);
        return this;
    }

    public void update(float delta, Queue<Signal> unhandledSignals) {
        if(animationsFinished() && !unhandledSignals.isEmpty())
            triggerAllReactions(unhandledSignals.poll());
        else
            stepAnimations(delta);
    }

    public void render(SpriteBatch batch) {
        for(final var entity : entities)
            Entities.render(entity, batch, tileSideLength);
    }

    private void triggerAllReactions(Signal signal) {
        System.err.println("Currently active entities: " + entities.size());
        final var nextEntities = new ArrayList<Entity>();
        for(final var entity : entities) {
            nextEntities.addAll(entity.react(signal, dimensions));
            if(entity.isActive())
                nextEntities.add(entity);
            else
                entity.dispose();
        }
        entities = nextEntities;
    }

    private void stepAnimations(float delta) {
        for(final var entity : entities) {
            entity.stepAnimation(delta);
        }
    }

    private boolean animationsFinished() {
        return entities.stream().allMatch(Entity::hasFinishedAnimation);
    }
}
