package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;

import java.util.Random;

public class SUS extends AbstractActiveGridEntity {
    public SUS(Level level, GridPosition gridPosition) {
        super(level, gridPosition, "sus");
    }

    @Override
    public void idleBehaviour() {
        if(new Random().nextInt() % 7==0)
            disable();
    }
}
