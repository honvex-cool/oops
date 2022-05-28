package pl.ue.oops.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.ue.oops.Config;
import pl.ue.oops.game.Oops;
import pl.ue.oops.game.scenes.Hud;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.level.LevelLoader;
import pl.ue.oops.game.universe.utils.LevelState;

import java.util.Random;

public class LevelScreen extends GameScreen {
    private final Hud hud;
    private Signal signal;
    private Level level;

    private LevelState currentLevelState;
    private final Random random;

    public LevelScreen(final Oops game,long seed) {
        super(game);
        hud = new Hud(game.batch);
        //level = LevelLoader.loadFromFile("src/main/resources/levels/exampleLevel.oopslvl").setHud(hud);
        random = new Random(seed);
        level = LevelLoader.loadFromGenerator(random.nextLong()).setHud(hud);
        //level.add(new Clueless(level.getDimensions()));
    }

    private void update() {

    }

    private Signal handleKeyInput(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            System.err.println("W pressed");
            return Signal.REQUESTED_UP_MOVEMENT;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            System.err.println("S pressed");
            return Signal.REQUESTED_DOWN_MOVEMENT;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            System.err.println("A pressed");
            return Signal.REQUESTED_LEFT_MOVEMENT;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            System.err.println("D pressed");
            return Signal.REQUESTED_RIGHT_MOVEMENT;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            System.err.println("[Space] pressed");
            return Signal.REQUESTED_PASS;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            System.err.println("[Enter] pressed");
            return Signal.REQUESTED_SPAWN;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            System.err.println("[Right] pressed");
            return Signal.REQUESTED_RIGHT_ATTACK;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            System.err.println("[Up] pressed");
            return Signal.REQUESTED_UP_ATTACK;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            System.err.println("[Down] pressed");
            return Signal.REQUESTED_DOWN_ATTACK;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            System.err.println("[Left] pressed");
            return Signal.REQUESTED_LEFT_ATTACK;
        }
        return null;
    }

    @Override
    public void render(float delta) {
        if(level.animationsFinished())
            signal = handleKeyInput();
        ScreenUtils.clear(Color.GRAY);
        currentLevelState = level.update(delta, signal);
        game.batch.begin();
        level.render(game.batch, Config.TILE_SIDE_LENGTH);
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        if(currentLevelState == LevelState.GAME_OVER){
            Gdx.app.exit();
        }
        if(currentLevelState == LevelState.FINISHED_WITH_SUCCESS){
            level = LevelLoader.loadFromGenerator(random.nextLong()).setHud(hud).setPlayerInfo(level.getPlayerHp());
        }
    }

    @Override
    public void show() {
        super.show();
    }
}
