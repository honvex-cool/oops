package pl.ue.oops.game.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.utils.Position;

public class MoveAnimation extends AbstractAnimation{
    private Vector3 moveVector;
    private Texture baseTexture;
    private Texture moveTexture;

    public MoveAnimation(float length, int frameLength, ActiveGridEntity entity, Texture baseTexture,Texture moveTexture){
        super(length,frameLength,entity);
        this.baseTexture = baseTexture;
        this.moveTexture = moveTexture;
    }
    @Override
    public void start() {
        entity.getCurrentAnimation().pause();
        entity.setCurrentAnimation(this);
        currentFrame = 0;
        moveVector = entity.getPosition().getMoveVector().scl(1f / frameLength); //we can use this vector to check move direction in if clauses below
        if(entity.getPosition().getGridPosition().x < entity.getPosition().getRenderPosition().x && moveTexture != null /*&& moveLeftTexture != null*/){
            //entity.getSprite().setTexture(moveLeftTexture);
            entity.getSprite().setTexture(moveTexture);
        }
        else if(entity.getPosition().getGridPosition().x > entity.getPosition().getRenderPosition().x && moveTexture != null/*&& moveRightTexture != null*/){
            //entity.getSprite().setTexture(moveRightTexture);
            entity.getSprite().setTexture(moveTexture);
        }
        else if(entity.getPosition().getGridPosition().y < entity.getPosition().getRenderPosition().y && moveTexture != null/*&& moveDownTexture != null*/){
            //entity.getSprite().setTexture(moveDownTexture);
            entity.getSprite().setTexture(moveTexture);
        }
        else if(entity.getPosition().getGridPosition().y > entity.getPosition().getRenderPosition().y && moveTexture != null/*&& moveUPTexture != null*/){
            //entity.getSprite().setTexture(moveUPTexture);
            entity.getSprite().setTexture(moveTexture);
        }
    }

    @Override
    public void step(float delta) {
        if(paused)
            return;
        if(currentFrame == frameLength){
            entity.getPosition().setRenderPositionAsGridPosition();
            //entity.getSprite().setTexture(basicTexture);
            if(baseTexture != null)
                entity.getSprite().setTexture(baseTexture);
            currentFrame = -1;
            entity.setCurrentAnimation(entity.getIdleAnimation());
            entity.getCurrentAnimation().start();
        }
        else {
            entity.getPosition().setRenderPosition(new Vector3(entity.getPosition().getRenderPosition().add(moveVector)));
            currentFrame++;
        }
    }
}
