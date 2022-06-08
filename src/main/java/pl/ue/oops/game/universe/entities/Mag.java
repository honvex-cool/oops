package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.animations.controllers.AnimationControllers;
import pl.ue.oops.game.animations.sequences.DelayedSpriteSequence;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.AbstractGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.level.Level;

public class Mag extends AbstractGridEntity {
    private static final int CAPACITY = 3;

    public Mag(int row, int column, Level level) {
        super(
            level,
            row,
            column,
            AnimationControllers.create(
                new DelayedSpriteSequence(
                    14,
                    "ammo_0", "ammo_1", "ammo_2", "ammo_1"
                )
            )
        );
    }

    @Override
    public void interact(GridEntity other) {
        if(other instanceof final Player player) {
            player.refillAmmo(CAPACITY);
            disable();
        }
    }
}
