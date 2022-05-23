package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;

public class LakeEntity extends AbstractGridEntity {
    public LakeEntity(Level level, GridPosition gridPosition){
        super(level, gridPosition, "lake");
    }
    @Override
    public void interact(GridEntity other){
        if(!(other instanceof Projectile)) { // each no-projectile object is drowning
            if(other instanceof Player player)
                player.hurt(3);
            else
                other.disable();
        }
    }
}
