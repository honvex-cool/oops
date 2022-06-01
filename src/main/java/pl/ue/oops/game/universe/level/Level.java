package pl.ue.oops.game.universe.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.ue.oops.game.scenes.Hud;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.Player;
import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.utils.Dimensions;
import pl.ue.oops.game.universe.utils.GridPosition;
import pl.ue.oops.game.universe.utils.LevelState;
import pl.ue.oops.game.universe.utils.statistics.TrackedParameter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Level {
    final AIHandler aiHandler;
    public final MoveHandler moveHandler;
    final Dimensions dimensions;
    public Hud hud;
    Player player; // package private for Pathfinder
    LevelState levelState;

    List<GridEntity> groundEntities = new ArrayList<>(); //ground textures used only for rendering
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
        levelState = LevelState.ACTIVE;
    }

    //Are the functions setHud and setPlayer necessary? Maybe we can just put them in level constructor
    public Level setHud(Hud hud) {
        if(this.hud == null)
            this.hud = hud;
        else
            throw new RuntimeException("Hud already set");

        return this;
    }

    public Level setPlayerInfo(int hp) {
        if(this.player == null)
            throw new RuntimeException("No player found");
        else
            player.setPlayerInfo(hp);

        return this;
    }

    public Level substitutePlayer(Player player) {
        if(this.player != null)
            player.getPosition().set(this.player.getPosition());
        this.player = player;
        return this;
    }

    public Player getPlayer() {
        return player;
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

    public Level addGroundObject(GridEntity gridEntity) {
            groundEntities.add(gridEntity);
        return this;
    }

    public LevelState update(float delta, Signal signal) {
        if(animationsFinished() && signal != null){
            //System.err.println("Currently active entities: " + activeEntities.size());
            //System.err.println("Currently active projectiles: " + projectiles.size());
            //System.err.println("Player at " + player.getPosition().getColumn() + " " + player.getPosition().getRow());
            hud.updateTurn();
            player.takeTurn(signal);
            eraseDestroyedEntities();
            aiHandler.takeTurn();
            eraseDestroyedEntities();
            for(final var projectile: projectiles)
                projectile.takeTurn(null);
            eraseDestroyedEntities();
            if(player.isDead())
                levelState = LevelState.GAME_OVER;
        }
        else
            stepAnimations(delta);
        return levelState;
    }

    public void render(SpriteBatch batch, float tileSideLength) {
        groundEntities.stream().forEachOrdered(entity -> entity.getCurrentAnimation().render(batch, tileSideLength));
        allEntities().forEachOrdered(entity -> entity.getCurrentAnimation().render(batch, tileSideLength));
    }

    private void eraseDestroyedEntities(){
        final var nextActiveEntities = new ArrayList<ActiveGridEntity>();
        for(final var entity : activeEntities) {
            if(entity.isActive())
                nextActiveEntities.add(entity);
        }
        final var nextProjectiles = new ArrayList<Projectile>();
        for(final var entity : projectiles) {
            if(entity.isActive())
                nextProjectiles.add(entity);
        }
        activeEntities = nextActiveEntities;
        projectiles = nextProjectiles;
    }

    private void stepAnimations(float delta) {
        allEntities().forEachOrdered(gridEntity -> gridEntity.stepAnimation(delta));
    }

    public boolean animationsFinished() {
        return activeEntities.stream().allMatch(GridEntity::hasFinishedAnimation) && player.hasFinishedAnimation();
    }

    public List<GridEntity> getGridEntitiesAtPosition(GridPosition gridPosition){
        return allEntities()
            .filter(entity -> entity.getPosition().equals(gridPosition))
            .collect(Collectors.toList());
    }

    private Stream<GridEntity> allEntities() {
        return Stream.of(passiveEntities.stream(), activeEntities.stream(), projectiles.stream(),Stream.of(player))
            .flatMap(stream -> stream);
    }

    public void levelFailed(){
        levelState = LevelState.GAME_OVER;
    }
    public void levelPassed(){
        levelState = LevelState.FINISHED_WITH_SUCCESS;
    }

    public boolean allEnemiesDead(){
        return aiHandler.allEnemiesDead();
    }

    public Level advance(long seed) {
        player.getStatistics().increment(TrackedParameter.LEVELS_CLEARED);
        final var nextLevel = LevelLoader.loadFromGenerator(seed).setHud(hud);
        nextLevel.substitutePlayer(player.moveToLevel(nextLevel));
        return nextLevel;
    }
}
