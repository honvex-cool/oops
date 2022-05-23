package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;

public class RockEntity extends AbstractGridEntity {
    public RockEntity(Level level, GridPosition gridPosition){
        super(level, gridPosition, "rock");
    }
    @Override
    public void interact(GridEntity other) {
        other.disable();
    }
}
