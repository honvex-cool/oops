package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.level.Level;

public class TemporaryGroundEntity extends AbstractGridEntity{
    public TemporaryGroundEntity(Level level, int row, int column,String spriteName){
            super(level, row, column, spriteName);
    }
    public TemporaryGroundEntity(Level level, int row, int column){
        super(level, row, column, "grass_0");
    }
}
