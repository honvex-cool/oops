package pl.ue.oops.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.ue.oops.game.Oops;

import java.awt.*;

public class MainMenuScreen implements Screen {

    private final Sprite play_button_active;
    private final Sprite exit_button_active;
    private final Sprite settings_button_active;
    private final Sprite play_button_inactive;
    private final Sprite exit_button_inactive;
    private final Sprite settings_button_inactive;
    private final Sprite music_active;
    private final Sprite music_inactive;
    private final Rectangle play;
    private final Rectangle settings;
    private final Rectangle exit;
    private final Rectangle music;

    Oops game;
    public MainMenuScreen(final Oops game) {
        this.game=game;
        play_button_inactive = new Sprite(new Texture("src/main/resources/test_sprites/start_inactive.png"));
        exit_button_inactive = new Sprite(new Texture("src/main/resources/test_sprites/exit_inactive.png"));
        settings_button_inactive = new Sprite(new Texture("src/main/resources/test_sprites/settings_inactive.png"));
        play_button_active = new Sprite(new Texture("src/main/resources/test_sprites/start_active.png"));
        exit_button_active = new Sprite(new Texture("src/main/resources/test_sprites/exit_active.png"));
        settings_button_active = new Sprite(new Texture("src/main/resources/test_sprites/settings_active.png"));
        music_inactive = new Sprite(new Texture("src/main/resources/test_sprites/music_inactive.png"));
        music_active = new Sprite(new Texture("src/main/resources/test_sprites/music_active.png"));
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
    public void render(float delta) {//1440 x 900

        ScreenUtils.clear(Color.CHARTREUSE);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.camera.update();
        game.batch.begin();

        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        game.camera.unproject(touchPos);
        //System.out.println(touchPos);

        if(play.contains(touchPos.x, touchPos.y)){
            game.batch.draw(play_button_active, play.x, play.y);
            if(Gdx.input.isTouched()) {
                game.setScreen(new SeedScreen(game));
            }
        }else{
            game.batch.draw(play_button_inactive, play.x, play.y);
        }
        if(exit.contains(touchPos.x, touchPos.y)){
            game.batch.draw(exit_button_active, exit.x, exit.y);
            if(Gdx.input.isTouched()) Gdx.app.exit();
        }else{
            game.batch.draw(exit_button_inactive, exit.x, exit.y);
        }
        if(settings.contains(touchPos.x, touchPos.y)){
            game.batch.draw(settings_button_active, settings.x, settings.y);
        }else{
            game.batch.draw(settings_button_inactive, settings.x, settings.y);
        }
        if(game.music.isPlaying()){//triggers many times will fix in future
            if(music.contains(touchPos.x, touchPos.y)){
                game.batch.draw(music_active, music.x, music.y);
                if(Gdx.input.isTouched()){
                    game.music.stop();
                    System.out.println("music stop");
                }
            }else{
                game.batch.draw(music_inactive, music.x, music.y);
            }
        }
        else{//triggers many times will fix in future
            if(music.contains(touchPos.x, touchPos.y)){
                game.batch.draw(music_inactive, music.x, music.y);
                if(Gdx.input.isTouched()) {
                    game.music.play();
                    System.out.println("music start");
                }
            }else{
                game.batch.draw(music_active, music.x, music.y);
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
