package pl.ue.oops.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.ue.oops.Config;
import pl.ue.oops.game.Oops;
import pl.ue.oops.game.universe.utils.TextureManager;

import java.awt.*;

public class MainMenuScreen implements Screen {

    private final Sprite play_button_active;
    private final Sprite exit_button_active;
    private final Sprite settings_button_active;
    private final Sprite play_button_inactive;
    private final Sprite exit_button_inactive;
    private final Sprite settings_button_inactive;
    private final Sprite music_playing_active;
    private final Sprite music_not_playing_active;
    private final Sprite music_playing_inactive;
    private final Sprite music_not_playing_inactive;
    private final Rectangle play;
    private final Rectangle settings;
    private final Rectangle exit;
    private final Rectangle music;

    Oops game;
    public MainMenuScreen(final Oops game) {
        this.game=game;
        System.err.println("gsgs");
        play_button_inactive = TextureManager.getSprite("start_inactive");
        exit_button_inactive = TextureManager.getSprite("exit_inactive");
        settings_button_inactive = TextureManager.getSprite("settings_inactive");
        play_button_active = TextureManager.getSprite("start_active");
        exit_button_active = TextureManager.getSprite("exit_active");
        settings_button_active = TextureManager.getSprite("settings_active");
        music_playing_active = TextureManager.getSprite("music_active_1");
        music_playing_inactive = TextureManager.getSprite("music_active_0");
        music_not_playing_active = TextureManager.getSprite("music_inactive_1");
        music_not_playing_inactive = TextureManager.getSprite("music_inactive_0");
        game.camera.setToOrtho(false, 1440, 900);
        game.camera.update();
        play=new Rectangle(352, 350, 256, 256);
        exit=new Rectangle(832, 350, 256, 256);
        settings=new Rectangle(352, 47, 256, 256);
        music=new Rectangle(832, 47, 256, 256);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(Config.MENU_BACKGROUND_COLOR);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.camera.update();
        game.batch.begin();

        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        game.camera.unproject(touchPos);
        //System.out.println(touchPos);

        if(play.contains(touchPos.x, touchPos.y)){
            game.batch.draw(play_button_active, play.x, play.y);
            if(Gdx.input.justTouched()) {
                game.setScreen(new SeedScreen(game));
            }
        }else{
            game.batch.draw(play_button_inactive, play.x, play.y);
        }
        if(exit.contains(touchPos.x, touchPos.y)){
            game.batch.draw(exit_button_active, exit.x, exit.y);
            if(Gdx.input.justTouched()) Gdx.app.exit();
        }else{
            game.batch.draw(exit_button_inactive, exit.x, exit.y);
        }
        if(settings.contains(touchPos.x, touchPos.y)){
            game.batch.draw(settings_button_active, settings.x, settings.y);
            if(Gdx.input.justTouched()){
                game.setScreen(new SettingsScreen(game));
            }
        }else{
            game.batch.draw(settings_button_inactive, settings.x, settings.y);
        }
        if(game.music.isPlaying()){//triggers many times will fix in future
            if(music.contains(touchPos.x, touchPos.y)){
                game.batch.draw(music_playing_active, music.x, music.y);
                if(Gdx.input.justTouched()){
                    game.music.stop();
                    System.out.println("music stop");
                }
            }else{
                game.batch.draw(music_playing_inactive, music.x, music.y);
            }
        }
        else{//triggers many times will fix in future //FIXED :)
            if(music.contains(touchPos.x, touchPos.y)){
                game.batch.draw(music_not_playing_active, music.x, music.y);
                if(Gdx.input.justTouched()) {
                    game.music.play();
                    System.out.println("music start");
                }
            }else{
                game.batch.draw(music_not_playing_inactive, music.x, music.y);
            }
        }
        game.batch.end();

    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
