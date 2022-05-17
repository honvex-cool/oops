package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.animations.Animation;
import pl.ue.oops.game.animations.MoveAnimation;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.Dimensions;
import pl.ue.oops.game.universe.utils.Position;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractActiveGridEntity extends AbstractGridEntity implements ActiveGridEntity {

    protected MoveAnimation moveAnimation;
    public AbstractActiveGridEntity(String texturePath, Level level) {
        super(texturePath,level);
        moveAnimation = new MoveAnimation(0,0,this,null,null);//WE NEED SPRITE HANDLER!!!!
        currentAnimation = idleAnimation;
        currentAnimation.start();
    }

    @Override
    public void takeTurn(Signal signal) {
        if(signal == null)
            idleBehaviour();
        else
            react(signal);
    }
    public abstract void idleBehaviour();
    @Override
    public void react(Signal signal) {
        idleBehaviour();
    }
    @Override
    public boolean hasFinishedAnimation() {
        return !currentAnimation.isActive();
    }
    @Override
    public MoveAnimation getMoveAnimation(){
        return moveAnimation;
    }
}
