package pl.ue.oops.game.universe.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import pl.ue.oops.Config;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Player extends AbstractActiveGridEntity {
    public Player(int x, int y, Level level) {
        super("blueSquare.png",level);
        getPosition().setGridPosition(x, y);
        getPosition().setRenderPositionAsGridPosition();
        moveTexture = new Texture(Config.TEXTURE_PATH + "greenSquare.png");
    }

    @Override
    public Collection<ActiveGridEntity> idleBehaviour() {
        return Collections.emptyList();
    }

    @Override
    public Collection<ActiveGridEntity> react(Signal signal) {
        if(signal != null) {
            System.err.println("Player at " + position.getRow() + ", " + position.getColumn() + "...");
            switch(signal) {
                case REQUESTED_DOWN_MOVEMENT -> {level.moveHandler.moveDown(this);currentAnimationFrame=moveAnimationFrameLength;}
                case REQUESTED_UP_MOVEMENT -> {level.moveHandler.moveUp(this);currentAnimationFrame=moveAnimationFrameLength;}
                case REQUESTED_LEFT_MOVEMENT -> {level.moveHandler.moveLeft(this);currentAnimationFrame=moveAnimationFrameLength;}
                case REQUESTED_RIGHT_MOVEMENT -> {level.moveHandler.moveRight(this);currentAnimationFrame=moveAnimationFrameLength;}
                case REQUESTED_SPAWN -> {
                    return List.of(new Clueless(level));
                }
            }
        }
        return Collections.emptyList();
    }

    //commented part needs some work

    private final int moveAnimationFrameLength = 6;
    private int currentAnimationFrame = -1;
    private final Texture moveTexture;
    private Vector3 moveVector;

    @Override
    public void stepAnimation(float delta) {
        if(!hasFinishedAnimation()){
            if(currentAnimationFrame == 0) {
                position.setRenderPositionAsGridPosition();
                sprite.setTexture(texture);
            }
            else if(currentAnimationFrame == moveAnimationFrameLength) {
                sprite.setTexture(moveTexture);
                moveVector = position.getMoveVector().scl(1f / moveAnimationFrameLength);
            }
            else {
                position.setRenderPosition(new Vector3(position.getRenderPosition().add(moveVector)));
            }
            currentAnimationFrame--;
        }
    }

    @Override
    public boolean hasFinishedAnimation() {
        return currentAnimationFrame<0;
    }
}
