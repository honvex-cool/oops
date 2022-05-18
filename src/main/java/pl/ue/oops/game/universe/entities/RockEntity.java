package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.level.Level;

public class RockEntity extends AbstractGridEntity {
    public RockEntity(String texturePath, Level level) {
        super(texturePath, level);
    }
    public RockEntity(Level level){
        this("rock.png",level);
    }
    @Override
    public void interact(GridEntity other) {
        other.disable();
    }
}
