package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.level.Level;

public class RockEntity extends AbstractGridEntity {
    public RockEntity(Level level, int row, int column){
        super(level, row, column, "rock");
    }
    @Override
    public void interact(GridEntity other) {
        other.disable();
    }
}
