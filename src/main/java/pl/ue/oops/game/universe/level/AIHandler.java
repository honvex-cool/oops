package pl.ue.oops.game.universe.level;

import pl.ue.oops.game.universe.entities.SUS;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.utils.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class AIHandler {
    private final Level level;

    public AIHandler(Level level){
        this.level = level;
    }

    public void takeTurn(){
        for(final var entity :level.activeEntities) //some decision-making and forcing objects to take turns(with some suggestions in Signals
            entity.takeTurn(null);
        spawnSUS(50);
    }

    private void spawnSUS(int probability){
        if(new Random().nextInt()%probability==0){
            int tr = new Random().nextInt()%level.getDimensions().rowCount();
            tr += level.getDimensions().rowCount();
            tr %= level.getDimensions().rowCount();
            int tc = new Random().nextInt()%level.getDimensions().columnCount();
            tc += level.getDimensions().columnCount();
            tr %= level.getDimensions().columnCount();
            var sus = new SUS(level);
            sus.getPosition().setGridPosition(tr,tc);
            if(level.getGridEntitiesAtPosition(sus.getPosition()).isEmpty());
            level.requestSpawn(sus);
        }
    }
}
