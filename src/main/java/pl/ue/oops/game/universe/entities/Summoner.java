package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.Clueless;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;

import java.util.Random;

public class Summoner extends AbstractActiveGridEntity {
    public Summoner(Level level, int row, int column) {
        super(level, row, column, "summoner");
    }

    @Override
    public void idleBehaviour() {
        if(new Random().nextInt() % 40 == 0) {
            var left = new Clueless(level, gridPosition.left());
            left.getAnimationController().playMoveAnimation(new GridPosition(-40, -40), left.getPosition(), 1.0f);
            var right = new Clueless(level, gridPosition.right());
            right.getAnimationController().playMoveAnimation(new GridPosition(40,40), right.getPosition(), 1.0f);
            level.requestSpawn(left).requestSpawn(right);
        }
    }

    @Override
    public void interact(GridEntity other) {
        if(other instanceof Projectile)
            disable();
    }
}
