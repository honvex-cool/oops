package pl.ue.oops.game.animations.movements;

import pl.ue.oops.game.universe.utils.GridPosition;

public class NoMovement extends StraightLineOneWayMovement {
    public NoMovement(GridPosition gridPosition) {
        super(gridPosition, gridPosition, 0);
    }
}
