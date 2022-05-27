package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;

public class LakeEntity extends AbstractGridEntity {
    public LakeEntity(Level level, int row, int column,String spriteName){
        super(level, row, column, spriteName);
    }
    public LakeEntity(Level level, int row, int column){
        super(level, row, column, "lake");
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
