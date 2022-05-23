package pl.ue.oops.game.animations.movements;

import com.badlogic.gdx.math.Vector2;
import pl.ue.oops.game.universe.utils.GridPosition;

public class StraightLineOneWayMovement extends OneWayMovement {
    private final float stepValue;
    private float displacement = 0.0f;

    public StraightLineOneWayMovement(GridPosition startingGridPosition, GridPosition targetGridPosition, int frameLength) {
        super(startingGridPosition, targetGridPosition, frameLength);
        System.err.println(frameLength);
        stepValue = this.frameLength == 0.0f ? 0.0f : 1.0f / this.frameLength;
    }

    @Override
    public void step(float delta) {
        super.step(delta);
        displacement += stepValue;
    }

    @Override
    public Vector2 getRenderPosition(float tileSideLength) {
        return new Vector2(normalizedTotalDisplacement).scl(tileSideLength * displacement);
    }
}
