package pl.ue.oops.game.universe.entities;

import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.AbstractGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.level.Level;

public class MedKit extends AbstractGridEntity {
    private static final int HEAL = 1;

    public MedKit(int row, int column, Level level) {
        super(level, row, column, "medkit");
    }

    @Override
    public void interact(GridEntity other) {
        if(other instanceof final Player player) {
            player.heal(HEAL);
            disable();
        }
    }



}
