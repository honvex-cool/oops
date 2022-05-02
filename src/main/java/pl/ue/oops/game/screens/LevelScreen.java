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
import pl.ue.oops.game.universe.utils.Dimensions;

import java.util.ArrayDeque;
import java.util.Queue;

public class LevelScreen extends GameScreen {
    private final Hud hud;
    private final Queue<Signal> receivedSignals = new ArrayDeque<>();
    private final Level level = new Level(
        new Dimensions(
            Config.NATIVE_HEIGHT / Config.TILE_SIDE_LENGTH,
            Config.NATIVE_WIDTH / Config.TILE_SIDE_LENGTH
        ),
        Config.TILE_SIDE_LENGTH
    );

    public LevelScreen(final Oops game) {
        super(game);
        hud = new Hud(game.batch);
        level.add(new Player(0, 0)).add(new Clueless());
    }

    private void update() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            System.err.println("W pressed");
            receivedSignals.add(Signal.REQUESTED_UP_MOVEMENT);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            System.err.println("S pressed");
            receivedSignals.add(Signal.REQUESTED_DOWN_MOVEMENT);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            System.err.println("A pressed");
            receivedSignals.add(Signal.REQUESTED_LEFT_MOVEMENT);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            System.err.println("D pressed");
            receivedSignals.add(Signal.REQUESTED_RIGHT_MOVEMENT);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            System.err.println("[Space] pressed");
            receivedSignals.add(Signal.REQUESTED_SPAWN);
        }
    }

    @Override
    public void render(float delta) {
        update();
        ScreenUtils.clear(Color.CHARTREUSE);
        level.update(delta, receivedSignals);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        game.batch.begin();
        level.render(game.batch);
        game.batch.end();
    }

    @Override
    public void show() {
        super.show();
    }
}
