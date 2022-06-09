package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.animations.controllers.AnimationControllers;
import pl.ue.oops.game.animations.movements.NoMovement;
import pl.ue.oops.game.animations.sequences.DelayedSpriteSequence;
import pl.ue.oops.game.animations.sequences.SimpleSpriteSequence;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.statistics.Statistics;
import pl.ue.oops.game.universe.utils.statistics.TrackedParameter;

public class Player extends AbstractActiveGridEntity {
    private static final int PROJECTILE_SPEED = 2;
    private static final int PROJECTILE_DAMAGE = 2;
    private static final int INITIAL_HP = 5;
    private static final int INITIAL_AMMO = 10;
    private int hp = INITIAL_HP;
    private int ammo = INITIAL_AMMO;
    private boolean dead = false;
    private final Statistics statistics = new Statistics();

    public Player(int row, int column, Level level) {
        super(level, row, column, "eyer_0_0");
        animationController = AnimationControllers.create(new SimpleSpriteSequence("eyer_move_1","eyer_move_2","eyer_move_1"),new SimpleSpriteSequence("eyer_0_0"));
        animationController.playIdleAnimation(this.gridPosition);
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
                case REQUESTED_DOWN_MOVEMENT -> level.moveHandler.moveDown(this);
                case REQUESTED_UP_MOVEMENT -> level.moveHandler.moveUp(this);
                case REQUESTED_LEFT_MOVEMENT -> level.moveHandler.moveLeft(this);
                case REQUESTED_RIGHT_MOVEMENT -> level.moveHandler.moveRight(this);
                case REQUESTED_DOWN_ATTACK -> fireProjectile(-1, 0);
                case REQUESTED_UP_ATTACK -> fireProjectile(1, 0);
                case REQUESTED_LEFT_ATTACK -> fireProjectile(0, -1);
                case REQUESTED_RIGHT_ATTACK -> fireProjectile(0, 1);
                case REQUESTED_SPAWN -> level.requestSpawn(new Clueless(level));
            }
            statistics.increment(TrackedParameter.TURNS_SURVIVED);
        }
    }

    private void fireProjectile(int rowDelta, int columnDelta) {
        if(ammo == 0)
            return;
        level.requestSpawn(
            new Projectile(
                level,
                this.gridPosition,
                rowDelta * PROJECTILE_SPEED,
                columnDelta * PROJECTILE_SPEED,
                PROJECTILE_DAMAGE,
                this
            )
        );
        statistics.increment(TrackedParameter.PROJECTILES_FIRED);
        level.hud.updateAmmo(--ammo);
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
        statistics.add(TrackedParameter.HP_POINTS_LOST, damage);
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

    public void heal(int heal) {
        hp += heal;
        level.hud.updateHp(hp);
        statistics.add(TrackedParameter.HP_POINTS_GAINED, heal);
    }

    public void refillAmmo(int quantity) {
        level.hud.updateAmmo(ammo += quantity);
        statistics.increment(TrackedParameter.MAGS_COLLECTED);
        statistics.add(TrackedParameter.BULLETS_COLLECTED, quantity);
    }
}
