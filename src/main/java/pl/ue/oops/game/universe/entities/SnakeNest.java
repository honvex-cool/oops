package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;

import java.util.Random;

public class SnakeNest extends AbstractActiveGridEntity {
    public SnakeNest(int row, int column, Level level) {
        super(level, row, column, "lake");
        gridPosition.set(row, column);
    }
    @Override
    public void idleBehaviour() {
        if(new Random().nextInt()%20==0){
            level.requestSpawn(new Clueless(this.gridPosition.getRow(),this.gridPosition.getColumn(),level));
        }
    }

    @Override
    public void interact(GridEntity other) {
        if(other.getClass().equals(Player.class)){
            level.hud.updateScore();
            disable();
        }
    }
}