package pl.ue.oops.game.universe.entities;

import com.badlogic.gdx.Gdx;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;

public class Player extends AbstractActiveGridEntity {
    private int hp;
    public Player(int row, int column, Level level) {
        super(level, row, column, "blueSquare", "greenSquare");
        hp = 5;
    }

    @Override
    public void idleBehaviour() {
    }

    @Override
    public void react(Signal signal) {
        if(signal != null) {
            switch(signal) {
                case REQUESTED_DOWN_MOVEMENT -> {level.moveHandler.moveDown(this);}
                case REQUESTED_UP_MOVEMENT -> {level.moveHandler.moveUp(this);}
                case REQUESTED_LEFT_MOVEMENT -> {level.moveHandler.moveLeft(this);}
                case REQUESTED_RIGHT_MOVEMENT -> {level.moveHandler.moveRight(this);}
                case REQUESTED_DOWN_ATTACK -> {
                    level.requestSpawn(new Projectile("noEntrySign",level,this.gridPosition,-1,0,2, this));
                }
                case REQUESTED_UP_ATTACK -> {
                    level.requestSpawn(new Projectile("noEntrySign",level,this.gridPosition,1,0,2, this));
                }
                case REQUESTED_LEFT_ATTACK -> {
                    level.requestSpawn(new Projectile("noEntrySign",level,this.gridPosition, 0,-1,2,this));
                }
                case REQUESTED_RIGHT_ATTACK -> {
                    level.requestSpawn(new Projectile("noEntrySign",level,this.gridPosition, 0,1,2, this));
                }
                case REQUESTED_SPAWN -> {
                    level.requestSpawn(new Clueless(level));
                }
            }
        }
    }

    @Override
    public void interact(GridEntity other) {
        if(other instanceof final Projectile projectile && !((Projectile) other).player){
            hp = Math.max(hp - projectile.getDamage(), 0);
            projectile.disable();
            level.hud.updateHp(hp);
            if(hp<=0)
                Gdx.app.exit();
        }
    }

    public void hurt(int damage){
        System.err.println("HURTING!!!");
        hp = Math.max(hp - damage, 0);
        level.hud.updateHp(hp);
        if(hp <= 0) // temporary
            Gdx.app.exit();
    }
}
