package pl.ue.oops.game.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import pl.ue.oops.Config;
import pl.ue.oops.game.Oops;

public abstract class GameScreen extends ScreenAdapter {
    protected final Oops game;
    protected OrthographicCamera camera;
    protected Viewport viewport; //Probably already implemented in ScreenAdapter
    public GameScreen(final Oops game) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(Config.NATIVE_WIDTH,Config.NATIVE_HEIGHT,camera); //Probably already implemented in ScreenAdapter
        camera.setToOrtho(false);
        camera.position.set(viewport.getScreenWidth()/2,viewport.getScreenHeight()/2,0);
        camera.update();
    }
}
