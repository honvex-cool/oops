package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;

public abstract class AbstractActiveGridEntity extends AbstractGridEntity implements ActiveGridEntity {
    public AbstractActiveGridEntity(Level level, GridPosition gridPosition, String spriteName) {
        super(level, gridPosition, spriteName);
    }

    public AbstractActiveGridEntity(Level level, GridPosition gridPosition, String normalSpriteName, String moveSpriteName) {
        super(level, gridPosition, normalSpriteName, moveSpriteName);
    }

    public AbstractActiveGridEntity(Level level, int row, int column, String spriteName) {
        super(level, row, column, spriteName);
    }

    public AbstractActiveGridEntity(Level level, int row, int column, String idleSpriteName, String moveSpriteName) {
        super(level, row, column, idleSpriteName, moveSpriteName);
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
        return !getCurrentAnimation().isInMotion();
    }
}
