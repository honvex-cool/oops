package pl.ue.oops.game.universe.level;

import pl.ue.oops.game.universe.entities.SUS;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.utils.GridPosition;
import pl.ue.oops.game.universe.entities.Shooter;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;

import java.util.Random;

public class AIHandler {
    private final Level level;
    Pathfinder pathfinder;
    public AIHandler(Level level){
        this.level = level;
        this.pathfinder= new Pathfinder(level);
    }

    public void takeTurn(){
        try{
            pathfinder.findPathMelee();
        }
        catch(Exception e){
            System.out.println("sraka" + e);
        }
        for(final var entity :level.getListOfActiveEntities()) //some decision-making and forcing objects to take turns(with some suggestions in Signals
        {
            entity.takeTurn(pathfinder.possible.get(entity));
        }
        spawnSUS(500);
    }

    private void spawnSUS(int probability){
        if(new Random().nextInt()%probability==0){
            int tr = new Random().nextInt()%level.getDimensions().rowCount();
            tr += level.getDimensions().rowCount();
            tr %= level.getDimensions().rowCount();
            int tc = new Random().nextInt()%level.getDimensions().columnCount();
            tc += level.getDimensions().columnCount();
            tr %= level.getDimensions().columnCount();
            var sus = new SUS(level, new GridPosition(tr, tc));
            if(level.getGridEntitiesAtPosition(sus.getPosition()).isEmpty())
                level.requestSpawn(sus);
        }
    }

    public boolean allEnemiesDead(){
        return level.getListOfActiveEntities().size()==1;
    }
}
