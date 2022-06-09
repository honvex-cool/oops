package pl.ue.oops.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import pl.ue.oops.Config;
import pl.ue.oops.game.Oops;

public class SettingsScreen implements Screen {
    private Oops game;
    private Stage stage;
    private TextButton back;
    private FitViewport viewport;
    private Slider sliderm;
    private BitmapFont text;
    private Slider sliderd;
    SettingsScreen(final Oops game) {
        this.game = game;
        game.camera.setToOrtho(false, 1440, 900);
        this.viewport = new FitViewport(1440, 900, game.camera);

        game.camera.update();

        stage = new Stage();
        stage.setViewport(viewport);

        back = new TextButton("Back", Config.DEFAULT_UI_SKIN);
        back.setPosition(1020, 75);
        back.setSize(100, 50);
        stage.addActor(back);
        back.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
            }

        });


        sliderm = new Slider(0, 100, 0.1f, false, Config.DEFAULT_UI_SKIN);
        sliderm.setSize(800, 100);
        sliderm.setValue(game.music.getVolume() * 100f);
        sliderm.setPosition(320, 145);
        sliderm.addListener((EventListener) e -> {
            boolean sliderUpdating = false;
            if (!sliderUpdating && sliderm.isDragging()) {
                game.music.setVolume((sliderm.getValue() / 100f));
            }
            return sliderUpdating;
        });
        stage.addActor(sliderm);


        text=new BitmapFont();


        Gdx.input.setInputProcessor(stage);

    }



    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Config.MENU_BACKGROUND_COLOR);

        stage.act(v);
        stage.draw();
        game.batch.begin();
        text.draw(game.batch, "Music Volume", 100,200);
        game.batch.draw(Config.LOGO, 444, 600,512,256);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        stage.getViewport().update(width, height);
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
        text.dispose();
    }
}
