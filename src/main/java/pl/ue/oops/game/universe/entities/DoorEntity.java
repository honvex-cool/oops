package pl.ue.oops.game.universe.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import org.lwjgl.system.CallbackI;
import pl.ue.oops.game.animations.SimpleAnimation;
import pl.ue.oops.game.animations.movements.NoMovement;
import pl.ue.oops.game.animations.sequences.DelayedSpriteSequence;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.AbstractGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.level.AIHandler;
import pl.ue.oops.game.universe.level.Level;

public class DoorEntity extends AbstractActiveGridEntity {
    private boolean open;
    public DoorEntity(Level level, int row, int column){
        super(level, row, column, "portal_inactive_0");
        open=false;
    }

    @Override
    public void interact(GridEntity other) {
        if(open && other.getClass().equals(Player.class)){
            level.levelPassed();
        }
    }

    @Override
    public void stepAnimation(float delta) {
        super.stepAnimation(delta);
        if(open && getCurrentAnimation().isFinished()){
            animationController.playAnimation(new SimpleAnimation(new NoMovement(this.gridPosition), new DelayedSpriteSequence(true,15,
                    "portal_active_0",
                    "portal_active_1",
                    "portal_active_2"
            )));
        }
    }

    @Override
    public void idleBehaviour() {
        if(!open && level.allEnemiesDead()){
            open=true;
            animationController.playAnimation(
                    new SimpleAnimation(new NoMovement(this.gridPosition),
                        new DelayedSpriteSequence(false,10,
                                "portal_activation_0",
                                "portal_activation_1",
                                "portal_activation_2",
                                "portal_activation_3",
                                "portal_activation_4",
                                "portal_activation_5",
                                "portal_activation_6",
                                "portal_activation_7")
                        )
                    );

        }
    }
}
