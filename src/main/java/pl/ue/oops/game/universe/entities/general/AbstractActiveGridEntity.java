package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.Dimensions;
import pl.ue.oops.game.universe.utils.Position;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractActiveGridEntity extends AbstractGridEntity implements ActiveGridEntity {

    public AbstractActiveGridEntity(String texturePath, Level level) {
        super(texturePath,level);
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
    public void stepAnimation(float delta) {
        position.setRenderPositionAsGridPosition();
    }

    @Override
    public boolean hasFinishedAnimation() {
        return true;
    }
}
