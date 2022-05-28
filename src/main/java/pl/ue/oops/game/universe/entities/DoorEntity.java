package pl.ue.oops.game.universe.entities;

import org.lwjgl.system.CallbackI;
import pl.ue.oops.game.universe.entities.general.AbstractGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.level.AIHandler;
import pl.ue.oops.game.universe.level.Level;

public class DoorEntity extends AbstractGridEntity {
    public DoorEntity(Level level, int row, int column){
        super(level, row, column, "lake");
    }

    @Override
    public void interact(GridEntity other) {
        if(other.getClass().equals(Player.class)&& level.allEnemiesDead()){
            level.levelPassed();
        }
    }

    @Override
    public void stepAnimation(float delta) {
        if(level.allEnemiesDead()){
            //Change sprite to open door, but I have no idea how to use new animationController system
        }
    }
}
