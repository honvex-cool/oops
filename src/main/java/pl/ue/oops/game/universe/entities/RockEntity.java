package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.level.Level;

public class RockEntity extends AbstractGridEntity {
    public RockEntity(Level level, int row, int column){
        this(level, row, column, "rock");
    }
    public RockEntity(Level level, int row, int column,String spriteName){
        super(level, row, column, spriteName);
    }
    @Override
    public void interact(GridEntity other) {
        other.disable();
    }
}
