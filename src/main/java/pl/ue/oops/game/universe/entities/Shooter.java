package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.animations.SimpleAnimation;
import pl.ue.oops.game.animations.movements.NoMovement;
import pl.ue.oops.game.animations.sequences.DelayedSpriteSequence;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;

import java.util.Random;

public class Shooter extends AbstractActiveGridEntity {

    boolean reload;

    public Shooter(int row, int column, Level level) {
        super(level, row, column, "venomous_snake_0_0");
        reload=false;
    }

    public Shooter(Level level) {
        this(0, 0, level);
    }

    @Override
    public void idleBehaviour() {
        //do default stuff
        reload=false;
    }
    @Override
    public void react(Signal signal){
        if(reload){
            reload=false;
            return;
        }
        switch (signal){
            case REQUESTED_DOWN_MOVEMENT -> {
                level.moveHandler.moveDown(this);
            }
            case REQUESTED_UP_MOVEMENT -> {
                level.moveHandler.moveUp(this);
            }
            case REQUESTED_LEFT_MOVEMENT -> {
                level.moveHandler.moveLeft(this);
            }
            case REQUESTED_RIGHT_MOVEMENT -> {
                level.moveHandler.moveRight(this);
            }
            case REQUESTED_DOWN_ATTACK -> {
                reload=true;
                level.requestSpawn(new Projectile(level, this.gridPosition, -2, 0, 1,this));
            }
            case REQUESTED_UP_ATTACK -> {
                reload=true;
                level.requestSpawn(new Projectile( level, this.gridPosition, 2, 0, 1,this));
            }
            case REQUESTED_LEFT_ATTACK -> {
                reload=true;
                level.requestSpawn(new Projectile( level, this.gridPosition,0, -2, 1,this));
            }
            case REQUESTED_RIGHT_ATTACK -> {
                reload=true;
                level.requestSpawn(new Projectile( level, this.gridPosition, 0, 2, 1,this));
            }
        }
    }

    @Override
    public void interact(GridEntity other) {
        if(other.getClass().equals(Player.class)){
            level.hud.updateScore();
            disable();
        } else if (other instanceof Projectile projectile && projectile.getOwner().getClass().equals(Player.class)) {
            level.hud.updateScore();
            disable();
            other.disable();
        }
    }


    @Override
    public void stepAnimation(float delta) {
        super.stepAnimation(delta);
        if(new Random().nextInt()%100==0 && animationController.getCurrentAnimation().isFinished()){

            if(new Random().nextInt()%2==0){
                animationController.playAnimation(new SimpleAnimation(new NoMovement(this.gridPosition),new DelayedSpriteSequence(false,15,
                        "venomous_snake_1",
                        "venomous_snake_2",
                        "venomous_snake_1",
                        "venomous_snake_0_0",
                        "venomous_snake_1",
                        "venomous_snake_2",
                        "venomous_snake_1",
                        "venomous_snake_0_0"
                )));
            }
            else {
                animationController.playAnimation(new SimpleAnimation(new NoMovement(this.gridPosition),new DelayedSpriteSequence(false,15,
                        "venomous_snake_0_1",
                        "venomous_snake_0_2",
                        "venomous_snake_0_3",
                        "venomous_snake_0_1",
                        "venomous_snake_0_0"
                )));
            }
        }
    }
}
