package pl.ue.oops.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.ue.oops.Config;
import pl.ue.oops.game.screens.LevelScreen;

public class Oops extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    public FileHandle test_sprites;

    public OrthographicCamera camera;
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        test_sprites = new FileHandle("src/main/resources/test_sprites");
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        camera.position.set(new Vector3(0,0,0));
        camera.update();
        setScreen(new LevelScreen(this));
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
