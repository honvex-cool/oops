package pl.ue.oops.game.universe.entities.general;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.Dimensions;
import pl.ue.oops.game.universe.utils.Position;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractGridEntity implements GridEntity{
    protected final Position position;
    protected boolean active = true;
    protected final Texture texture;
    protected final Sprite sprite;
    protected final Level level;

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void stepIdleAnimation(float delta) {
    }

    @Override
    public boolean hasFinishedAnimation() {
        return true;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    protected final void disable() {
        active = false;
    }

    public AbstractGridEntity(String texturePath, Level level) {
        texture = new Texture("src/main/resources/test_sprites/" + texturePath);
        sprite = new Sprite(texture);
        this.level = level;
        position = new Position(level.getDimensions().getTileSideLength());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
    @Override
    public Collection<ActiveGridEntity> interact(GridEntity other) {
        return Collections.emptyList();
    }
}
