package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.utils.Dimensions;
import pl.ue.oops.game.universe.utils.Position;

import java.util.Collection;
import java.util.Collections;

public abstract class AbstractActiveGridEntity extends AbstractGridEntity implements ActiveGridEntity {

    public AbstractActiveGridEntity(String texturePath,Dimensions gridDimensions) {
        super(texturePath,gridDimensions);
    }

    @Override
    public Collection<ActiveGridEntity> takeTurn(Signal signal) {
        if(signal == null)
            return idleBehaviour();
        return react(signal);
    }
    public abstract Collection<ActiveGridEntity> idleBehaviour();
    @Override
    public Collection<ActiveGridEntity> react(Signal signal) {
        return idleBehaviour();
    }

    @Override
    public Collection<ActiveGridEntity> interact(GridEntity other) {
        return Collections.emptyList();
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
