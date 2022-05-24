package pl.ue.oops.game.animations.movements;

import com.badlogic.gdx.math.Vector2;
import pl.ue.oops.game.universe.utils.GridPosition;

public abstract class OneWayMovement implements Movement {
    protected final GridPosition startingGridPosition;
    protected final GridPosition targetGridPosition;
    protected final Vector2 normalizedTotalDisplacement;
    protected final int frameLength;

    public OneWayMovement(GridPosition startingGridPosition, GridPosition targetGridPosition, int frameLength) {
        this.startingGridPosition = startingGridPosition;
        this.targetGridPosition = targetGridPosition;
        this.frameLength = frameLength;
        normalizedTotalDisplacement = new Vector2(
            targetGridPosition.getColumn() - startingGridPosition.getColumn(),
            targetGridPosition.getRow() - startingGridPosition.getRow()
        );
    }

    private int currentFrame;

    @Override
    public void start() {
        currentFrame = 0;
    }

    @Override
    public boolean isInProgress() {
        return currentFrame < frameLength;
    }

    @Override
    public void step(float delta) {
        if(isInProgress())
            ++currentFrame;
    }
}
