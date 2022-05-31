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

    public Object getOwner() {
        return owner;
    }

    private final Object owner;

    public Projectile(String spriteName, Level level, GridPosition gridPosition, int rowDelta, int columnDelta, int damage) {
        super(level, new GridPosition(gridPosition), spriteName);
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
        this.damage = damage;
        this.owner = new Object();
    }

    public Projectile(String spriteName, Level level, GridPosition gridPosition, int rowDelta, int columnDelta, int damage, Object owner) {
        super(level, gridPosition, spriteName);
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
        this.damage = damage;
        this.owner = owner;
    }

    @Override
    public void idleBehaviour() {
        for(int i = 0; i < max(abs(rowDelta), abs(columnDelta)); i++)
            level.moveHandler.move(this, rowDelta, columnDelta); //here move should be 1 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        if(!level.getDimensions().contain(gridPosition))
            disable();
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void interact(GridEntity other) {
        if(!(other instanceof Projectile) && other.getPosition().getPreviousGridPosition().equals(getPosition().shifted(rowDelta,columnDelta))){
            other.interact(this);
        }
    }
}
