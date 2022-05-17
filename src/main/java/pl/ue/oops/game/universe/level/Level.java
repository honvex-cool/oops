package pl.ue.oops.game.universe.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.ue.oops.game.scenes.Hud;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.Player;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.Entities;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.utils.Dimensions;
import pl.ue.oops.game.universe.utils.Position;

import java.util.ArrayList;
import java.util.List;

public class Level {
    final AIHandler aiHandler;
    public final MoveHandler moveHandler;
    final Dimensions dimensions;
    public Hud hud;
    private Player player;
    List<GridEntity> passiveEntities = new ArrayList<>(); //package private for AIHandler to use
    List<ActiveGridEntity> activeEntities = new ArrayList<>(); //package private for AIHandler to use
    List<Projectile> projectiles = new ArrayList<>(); //package private for AIHandler to use

    public Dimensions getDimensions() {
        return dimensions;
    }

    public Level(Dimensions dimensions) {
        this.dimensions = dimensions;
        aiHandler = new AIHandler(this);
        moveHandler = new MoveHandler(this);
    }

    //Are the functions setHud and setPlayer necessary? Maybe we can just put them in level constructor
    public Level setHud(Hud hud) {
        if(this.hud == null)
            this.hud = hud;
        else
            throw new RuntimeException("Hud already set");

        return this;
    }

    public Level setPlayer(Player player) {
        if(this.player == null)
            this.player = player;
        else
            throw new RuntimeException("Player already set");
        return this;
    }
    public Level requestSpawn(GridEntity gridEntity) {
        if(Projectile.class.isAssignableFrom(gridEntity.getClass()))
            projectiles.add((Projectile)gridEntity);
        else if(ActiveGridEntity.class.isAssignableFrom(gridEntity.getClass()))
            activeEntities.add((ActiveGridEntity)gridEntity);
        else
            passiveEntities.add(gridEntity);
        return this;
    }

    public Level requestSpawn(GridEntity gridEntity, Position position){
        gridEntity.getPosition().setGridPosition(position.getRow(),position.getColumn());
        gridEntity.getPosition().setRenderPositionAsGridPosition();
        requestSpawn(gridEntity);
        return this;
    }

    public void update(float delta, /*Queue<Signal> unhandledSignals*/Signal signal) {
        if(animationsFinished() && /*!unhandledSignals.isEmpty()*/ signal != null){
            System.err.println("Currently active entities: " + activeEntities.size());
            System.err.println("Currently active projectiles: " + projectiles.size());
            System.err.println("Player at " + player.getPosition().getColumn() + " " + player.getPosition().getRow());
            hud.updateTurn();
            player.takeTurn(signal);
            eraseDestroyedEntities();
            aiHandler.takeTurn();
            eraseDestroyedEntities();
            for(final var projectile: projectiles)
                projectile.takeTurn(null);
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
        for(final var entity : projectiles)
            Entities.render(entity, batch, dimensions.getTileSideLength());
    }

    private void eraseDestroyedEntities(){
        final var nextActiveEntities = new ArrayList<ActiveGridEntity>();
        for(final var entity : activeEntities) {
            if(entity.isActive())
                nextActiveEntities.add(entity);
            else {
                entity.dispose();
                hud.updateScore();
            }
        }

        final var nextProjectiles = new ArrayList<Projectile>();
        for(final var entity : projectiles) {
            if(entity.isActive())
                nextProjectiles.add(entity);
            else {
                entity.dispose();
            }
        }
        activeEntities = nextActiveEntities;
        projectiles = nextProjectiles;
    }

    /*private void triggerAllReactions(Signal signal) {
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
    }*/

    private void stepAnimations(float delta) {
        player.stepAnimation(delta);
        for(final var entity : activeEntities) {
            entity.stepAnimation(delta);
        }
        for(final var entity : projectiles) {
            entity.stepAnimation(delta);
        }
    }

    public boolean animationsFinished() {
        return (activeEntities.stream().allMatch(GridEntity::hasFinishedAnimation) && player.hasFinishedAnimation());
    }

    public List<GridEntity> getGridEntitiesAtPosition(Position position){
        var list = new ArrayList<GridEntity>();
        for (final var entity: passiveEntities) {
            if(entity.getPosition().equals(position)) {
                list.add(entity);
            }
        }
        for (final var entity: activeEntities) {
            if(entity.getPosition().equals(position)) {
                list.add(entity);
            }
        }
        if(player.getPosition().equals(position))
            list.add(player);
        return list;
    }
}
