package pl.ue.oops.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.ue.oops.Config;
import pl.ue.oops.game.Oops;
import pl.ue.oops.game.scenes.Hud;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.entities.Clueless;
import pl.ue.oops.game.universe.entities.Player;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.level.LevelLoader;
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.ArrayDeque;
import java.util.Queue;

public class LevelScreen extends GameScreen {
    private final Hud hud;
    //private final Queue<Signal> receivedSignals = new ArrayDeque<>();
    private Signal signal;
    private final Level level;

    public LevelScreen(final Oops game) {
        super(game);
        hud = new Hud(game.batch);
        level = LevelLoader.loadFromFile("src/main/resources/levels/exampleLevel.oopslvl").setHud(hud);
        //level.add(new Clueless(level.getDimensions()));
    }

    private void update() {

    }

    private Signal handleKeyInput(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            System.err.println("W pressed");
            //receivedSignals.add(Signal.REQUESTED_UP_MOVEMENT);
            return Signal.REQUESTED_UP_MOVEMENT;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            System.err.println("S pressed");
            //receivedSignals.add(Signal.REQUESTED_DOWN_MOVEMENT);
            return Signal.REQUESTED_DOWN_MOVEMENT;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            System.err.println("A pressed");
            //receivedSignals.add(Signal.REQUESTED_LEFT_MOVEMENT);
            return Signal.REQUESTED_LEFT_MOVEMENT;
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            System.err.println("D pressed");
            //receivedSignals.add(Signal.REQUESTED_RIGHT_MOVEMENT);
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
        level.update(delta, signal);
        game.batch.begin();
        level.render(game.batch);
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void show() {
        super.show();
    }
}
