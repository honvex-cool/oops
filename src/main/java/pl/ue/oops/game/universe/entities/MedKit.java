package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.animations.controllers.AnimationController;
import pl.ue.oops.game.animations.controllers.AnimationControllers;
import pl.ue.oops.game.animations.sequences.DelayedSpriteSequence;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.level.Level;

public class MedKit extends AbstractActiveGridEntity {
    private static final int HEAL = 1;

    public MedKit(int row, int column, Level level) {
        super(
                level,
                row,
                column,
                AnimationControllers.create(
                        new DelayedSpriteSequence(
                                14,
                                "medpack_0","medpack_1","medpack_2","medpack_1"
                        )
                )
        );
    }

    @Override
    public void interact(GridEntity other) {
        if(other instanceof final Player player) {
            player.heal(HEAL);
            disable();
        }
    }

    @Override
    public void idleBehaviour() {
    }
}
