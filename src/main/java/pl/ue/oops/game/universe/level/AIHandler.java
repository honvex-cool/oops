package pl.ue.oops.game.universe.level;

import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class AIHandler {
    private final Level level;

    public AIHandler(Level level){
        this.level = level;
    }

    public void takeTurn(){
        for(final var entity :level.activeEntities) //some decision-making and forcing objects to take turns(with some suggestions in Signals
            entity.takeTurn(null);
    }
}
