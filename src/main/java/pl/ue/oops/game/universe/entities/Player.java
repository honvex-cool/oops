package pl.ue.oops.game.universe.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import pl.ue.oops.Config;
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
        moveTexture = new Texture(Config.TEXTURE_PATH + "greenSquare.png");
        hp = 5;
    }

    @Override
    public void idleBehaviour() {

    }

    @Override
    public void react(Signal signal) {
        if(signal != null) {
            switch(signal) {
                case REQUESTED_DOWN_MOVEMENT -> {level.moveHandler.moveDown(this);currentAnimationFrame=moveAnimationFrameLength;}
                case REQUESTED_UP_MOVEMENT -> {level.moveHandler.moveUp(this);currentAnimationFrame=moveAnimationFrameLength;}
                case REQUESTED_LEFT_MOVEMENT -> {level.moveHandler.moveLeft(this);currentAnimationFrame=moveAnimationFrameLength;}
                case REQUESTED_RIGHT_MOVEMENT -> {level.moveHandler.moveRight(this);currentAnimationFrame=moveAnimationFrameLength;}
                case REQUESTED_SPAWN -> {
                    level.requestSpawn(new Clueless(level));
                }
            }
        }
    }

    //commented part needs some work

    private final int moveAnimationFrameLength = 6;
    private int currentAnimationFrame = -1;
    private final Texture moveTexture;
    private Vector3 moveVector;

    @Override
    public void stepAnimation(float delta) {
        if(!hasFinishedAnimation()){
            if(currentAnimationFrame == 0) {
                position.setRenderPositionAsGridPosition();
                sprite.setTexture(texture);
            }
            else if(currentAnimationFrame == moveAnimationFrameLength) {
                sprite.setTexture(moveTexture);
                moveVector = position.getMoveVector().scl(1f / moveAnimationFrameLength);
            }
            else {
                position.setRenderPosition(new Vector3(position.getRenderPosition().add(moveVector)));
            }
            currentAnimationFrame--;
        }
    }

    @Override
    public boolean hasFinishedAnimation() {
        return currentAnimationFrame<0;
    }

    @Override
    public void interact(GridEntity other) {
        if(Projectile.class.isAssignableFrom(other.getClass())){
            hp-=((Projectile) other).getDamage();
            ((Projectile)other).disable();
            level.hud.updateHp(hp);
            if(hp<=0)
                Gdx.app.exit();
        }
    }
}
