package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.level.Level;

import java.util.Collection;
import java.util.Collections;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Projectile extends AbstractActiveGridEntity{
    private final int damage;
    private final int rowDelta;
    private final int columnDelta;
    public Projectile(String texturePath, Level level, int rowDelta, int columnDelta, int damage){
        super(texturePath, level);
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
        this.damage = damage;
    }
    @Override
    public void idleBehaviour() {
        for(int i=0;i<max(abs(rowDelta),abs(columnDelta));i++)
            level.moveHandler.move(this,rowDelta,columnDelta); //here move should be 1 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(!level.getDimensions().contain(position))
            disable();
    }

    public int getDamage(){
        return damage;
    }
}
