package pl.ue.oops.game.universe.entities;

import com.badlogic.gdx.Gdx;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;
import pl.ue.oops.game.universe.utils.statistics.Statistics;
import pl.ue.oops.game.universe.utils.statistics.TrackedParameter;

public class Player extends AbstractActiveGridEntity {
    private int hp;
    private boolean dead = false;
    private final Statistics statistics = new Statistics();

    public Player(int row, int column, Level level) {
        super(level, row, column, "blueSquare", "greenSquare");
        hp = 5;
    }

    public void setPlayerInfo(int hp){
        this.hp = hp;
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
                    level.requestSpawn(new Projectile("noEntrySign",level,this.gridPosition,-2,0,2, this));
                    statistics.increment(TrackedParameter.PROJECTILES_FIRED);
                }
                case REQUESTED_UP_ATTACK -> {
                    level.requestSpawn(new Projectile("noEntrySign",level,this.gridPosition,2,0,2, this));
                    statistics.increment(TrackedParameter.PROJECTILES_FIRED);
                }
                case REQUESTED_LEFT_ATTACK -> {
                    level.requestSpawn(new Projectile("noEntrySign",level,this.gridPosition, 0,-2,2,this));
                    statistics.increment(TrackedParameter.PROJECTILES_FIRED);
                }
                case REQUESTED_RIGHT_ATTACK -> {
                    level.requestSpawn(new Projectile("noEntrySign",level,this.gridPosition, 0,2,2, this));
                    statistics.increment(TrackedParameter.PROJECTILES_FIRED);
                }
                case REQUESTED_SPAWN -> {
                    level.requestSpawn(new Clueless(level));
                }
            }
            statistics.increment(TrackedParameter.TURNS_SURVIVED);
        }
    }

    @Override
    public void interact(GridEntity other) {
        if(other instanceof final Projectile projectile && !projectile.getOwner().getClass().equals(Player.class)){
            hurt(projectile.getDamage());
            projectile.disable();
        }
    }

    public void hurt(int damage){
        System.err.println("HURTING!!!");
        statistics.increment(TrackedParameter.HITS_TAKEN);
        statistics.add(TrackedParameter.DAMAGE_TAKEN, damage);
        hp = Math.max(hp - damage, 0);
        level.hud.updateHp(hp);
        if(hp <= 0)
            die();
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void die() {
        dead = true;
    }

    public void revive() {
        dead = false;
    }

    public boolean isDead() {
        return dead;
    }

    public Player moveToLevel(Level nextLevel) {
        level = nextLevel;
        return this;
    }
}
