package pl.ue.oops.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.ue.oops.Config;
import pl.ue.oops.game.screens.LevelScreen;
import pl.ue.oops.game.screens.MainMenuScreen;

public class Oops extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    public OrthographicCamera camera;
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.update();
        setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        ScreenUtils.clear(Config.CLEAR_COLOR);
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
