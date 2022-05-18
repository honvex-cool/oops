package pl.ue.oops.game.universe.entities;

import org.lwjgl.system.CallbackI;
import pl.ue.oops.game.universe.entities.general.AbstractGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;

public class LakeEntity extends AbstractGridEntity {
    public LakeEntity(String texturePath, Level level) {
        super(texturePath, level);
    }
    public LakeEntity(Level level){
        this("lake.png",level);
    }
    @Override
    public void interact(GridEntity other){
        if(!(Projectile.class).isAssignableFrom(other.getClass())){ //each no-projectile object is drowning
            if(other.getClass().equals(Player.class))
                ((Player)other).hurt(3);
            else
                other.disable();
        }
    }
}
