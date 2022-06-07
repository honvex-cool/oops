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
            return Signal.REQUESTED_UP_MOVEMENT;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            return Signal.REQUESTED_DOWN_MOVEMENT;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            return Signal.REQUESTED_LEFT_MOVEMENT;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            return Signal.REQUESTED_RIGHT_MOVEMENT;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            return Signal.REQUESTED_PASS;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            return Signal.REQUESTED_SPAWN;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            return Signal.REQUESTED_RIGHT_ATTACK;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            return Signal.REQUESTED_UP_ATTACK;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            return Signal.REQUESTED_DOWN_ATTACK;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            return Signal.REQUESTED_LEFT_ATTACK;
        }
        return null;
    }

    @Override
    public void render(float delta) {
        if(level.animationsFinished()) {
            update();
            signal = handleKeyInput();
        }
        ScreenUtils.clear(Color.GRAY);
        currentLevelState = level.update(delta, signal);
        game.batch.begin();
        level.render(game.batch, Config.TILE_SIDE_LENGTH);
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        if(currentLevelState == LevelState.GAME_OVER){
            game.setScreen(new GameOverScreen(game, level.getPlayer().getStatistics()));
        }
        if(currentLevelState == LevelState.FINISHED_WITH_SUCCESS){
            level = level.advance(random.nextLong());
        }
    }

    @Override
    public void show() {
        super.show();
    }
}
