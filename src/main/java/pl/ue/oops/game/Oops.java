package pl.ue.oops.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.lwjgl3.audio.Mp3;
import com.badlogic.gdx.files.FileHandle;
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
    public Music music;
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1440, 900);
        camera.update();
        music = Gdx.audio.newMusic(new FileHandle("src/main/resources/music/music(SFW).mp3"));
        music.setLooping(true);
        music.setVolume(0.4f);
        music.play();
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
        music.dispose();
    }
}
