package pl.ue.oops.game.universe.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.ue.oops.game.scenes.Hud;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.Player;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.Entities;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Level {
    private final Player player;
    private final AIHandler aiHandler;
    private final Hud hud;
    List<ActiveGridEntity> activeEntities = new ArrayList<>(); //package private for AIHandler to use
    List<GridEntity> passiveEntities = new ArrayList<>(); //package private for AIHandler to use

    public Dimensions getDimensions() {
        return dimensions;
    }

    private final Dimensions dimensions;

    public Level(Dimensions dimensions,Hud hud) {
        this.dimensions = dimensions;
        this.hud = hud;
        player = new Player(0,0,dimensions);
        aiHandler = new AIHandler(this);
    }

    public Level add(ActiveGridEntity activeGridEntity) {
        activeEntities.add(activeGridEntity);
        return this;
    }
    public Level add(GridEntity gridEntity) {
        passiveEntities.add(gridEntity);
        return this;
    }

    public void update(float delta, /*Queue<Signal> unhandledSignals*/Signal signal) {
        if(animationsFinished() && /*!unhandledSignals.isEmpty()*/ signal != null){
            hud.updateTurn();
            activeEntities.addAll(player.takeTurn(signal));
            eraseDestroyedEntities();
            activeEntities.addAll(aiHandler.takeTurn());
            eraseDestroyedEntities();
        }
        else
            stepAnimations(delta);
    }

    public void render(SpriteBatch batch) {
            Entities.render(player,batch,dimensions.getTileSideLength());
        for(final var entity : activeEntities)
            Entities.render(entity, batch, dimensions.getTileSideLength());
        for(final var entity : passiveEntities)
            Entities.render(entity, batch, dimensions.getTileSideLength());
    }

    private void eraseDestroyedEntities(){
        System.err.println("Currently active entities: " + activeEntities.size());
        final var nextEntities = new ArrayList<ActiveGridEntity>();
        for(final var entity : activeEntities) {
            if(entity.isActive())
                nextEntities.add(entity);
            else {
                entity.dispose();
                hud.updateScore();
            }
        }
        activeEntities = nextEntities;
    }

    private void triggerAllReactions(Signal signal) {
        System.err.println("Currently active entities: " + activeEntities.size());
        final var nextEntities = new ArrayList<ActiveGridEntity>();
        for(final var entity : activeEntities) {
            nextEntities.addAll(entity.react(signal));
            if(entity.isActive())
                nextEntities.add(entity);
            else
                entity.dispose();
        }
        activeEntities = nextEntities;
    }

    private void stepAnimations(float delta) {
        for(final var entity : activeEntities) {
            entity.stepAnimation(delta);
        }
        player.stepAnimation(delta);
    }

    public boolean animationsFinished() {
        return (activeEntities.stream().allMatch(GridEntity::hasFinishedAnimation) && player.hasFinishedAnimation());
    }
}
