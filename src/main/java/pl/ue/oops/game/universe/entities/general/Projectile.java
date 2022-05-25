package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.entities.Player;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Projectile extends AbstractActiveGridEntity {
    private final int damage;
    private final int rowDelta;
    private final int columnDelta;
    public boolean player;
    private boolean setup=false;

    public Projectile(String spriteName, Level level, GridPosition gridPosition, int rowDelta, int columnDelta, int damage) {
        super(level, new GridPosition(gridPosition), spriteName);
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
        this.damage = damage;
        player=false;
    }
    public Projectile(String spriteName, Level level, GridPosition gridPosition, int rowDelta, int columnDelta, int damage, Player p) {
        super(level, new GridPosition(gridPosition), spriteName);
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
        this.damage = damage;
        player=true;
    }

    @Override
    public void idleBehaviour() {
        if(setup){
            var a=level.getGridEntitiesAtPosition(this.getPosition());
            for(var i : a){
                if(i instanceof ActiveGridEntity){
                    i.interact(this);
                    break;
                }
            }
        }
        setup=true;
        for(int i = 0; i < max(abs(rowDelta), abs(columnDelta)); i++)
            level.moveHandler.move(this, rowDelta, columnDelta); //here move should be 1 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(!level.getDimensions().contain(gridPosition))
            disable();
    }

    public int getDamage() {
        return damage;
    }
}
