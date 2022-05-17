package pl.ue.oops.game.universe.entities.general;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import pl.ue.oops.game.animations.Animation;
import pl.ue.oops.game.animations.IdleAnimation;
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
    protected IdleAnimation idleAnimation;
    protected Animation currentAnimation;
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public boolean hasFinishedAnimation() {
        return true;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    public final void disable() {
        active = false;
    }

    public AbstractGridEntity(String texturePath, Level level) {
        texture = new Texture("src/main/resources/test_sprites/" + texturePath);
        sprite = new Sprite(texture);
        this.level = level;
        position = new Position(level.getDimensions().getTileSideLength());
        idleAnimation = new IdleAnimation(0,0,this,null,null);
        currentAnimation = idleAnimation;
        currentAnimation.start();
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
    @Override
    public void interact(GridEntity other) { }
    @Override
    public IdleAnimation getIdleAnimation(){
        return idleAnimation;
    }
    @Override
    public void stepAnimation(float delta){
        currentAnimation.step(delta);
    }
    @Override
    public void setCurrentAnimation(Animation animation){
         currentAnimation = animation;
    }
    @Override
    public Animation getCurrentAnimation(){
        return currentAnimation;
    }
}
