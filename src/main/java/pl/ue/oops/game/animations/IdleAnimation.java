package pl.ue.oops.game.animations;

import com.badlogic.gdx.graphics.Texture;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;

public class IdleAnimation extends AbstractAnimation {
    private Texture baseTexture;
    private Texture idleAnimationTexture;
    public IdleAnimation(float timeBetweenChanges, int framesBetweenChanges, GridEntity entity, Texture baseTexture, Texture idleAnimationTexture) {
        super(timeBetweenChanges, framesBetweenChanges,entity);
        this.baseTexture = baseTexture;
        this.idleAnimationTexture = idleAnimationTexture;
    }

    @Override
    public void start() {
        paused = false;
        currentFrame = 0;
        //entity.getSprite().setTexture(defaultTexture);
        if(baseTexture != null)
            entity.getSprite().setTexture(baseTexture);
    }

    @Override
    public boolean isActive(){
        return false;
    }

    @Override
    public void step(float delta) {
        if(paused)
            return;
        if(currentFrame == frameLength){
            //entity.getSprite().setTexture(idleAnimationTexture);
            if(idleAnimationTexture != null)
                entity.getSprite().setTexture(idleAnimationTexture);
        }
        else if(currentFrame >= 2*frameLength){
            //entity.getSprite().setTexture(defaultTexture);
            if(baseTexture != null)
                entity.getSprite().setTexture(baseTexture);
            currentFrame = -1;
        }
        currentFrame++;
    }
}
