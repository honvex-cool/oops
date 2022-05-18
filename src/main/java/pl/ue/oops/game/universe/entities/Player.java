package pl.ue.oops.game.universe.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import pl.ue.oops.Config;
import pl.ue.oops.game.animations.IdleAnimation;
import pl.ue.oops.game.animations.MoveAnimation;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;

public class Player extends AbstractActiveGridEntity {
    private int hp;
    public Player(int x, int y, Level level) {
        super("blueSquare.png",level);
        getPosition().setGridPosition(x, y);
        getPosition().setRenderPositionAsGridPosition();
        Texture moveTexture = new Texture(Config.TEXTURE_PATH + "greenSquare.png");
        moveAnimation = new MoveAnimation(0,7,this,texture, moveTexture);
        idleAnimation = new IdleAnimation(0,10,this,texture, moveTexture);
        currentAnimation.start();
        hp = 5;
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
                case REQUESTED_SPAWN -> level.requestSpawn(new Clueless(level));
            }
        }
    }

    @Override
    public void interact(GridEntity other) {
        if(other instanceof final Projectile projectile){
            hp -= projectile.getDamage();
            projectile.disable();
            level.hud.updateHp(hp);
            if(hp<=0)
                Gdx.app.exit();
        }
    }

    public void hurt(int damage){
        hp = Math.max(hp - damage, 0);
        level.hud.updateHp(hp);
        if(hp <= 0) // temporary
            Gdx.app.exit();
    }
}
