package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.level.Level;

import java.util.Random;

public class SUS extends AbstractActiveGridEntity {
    public SUS(String texturePath, Level level) {
        super(texturePath, level);
    }
    public SUS(Level level) {
        this("sus.png", level);
    }

    @Override
    public void idleBehaviour() {
        if(new Random().nextInt() % 7==0)
            disable();
        position.setRenderPositionAsGridPosition();
    }
}
