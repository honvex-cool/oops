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
    private Skin skin;
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
        skin = new Skin(new FileHandle("src/main/resources/skins/uiskin.json"));

        back = new TextButton("Back", skin);
        back.setPosition(800, 100);
        back.setSize(100, 50);
        stage.addActor(back);
        back.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new MainMenuScreen(game));
            }

        });


        sliderm = new Slider(0, 100, 0.1f, false, skin);
        sliderm.setSize(400, 100);
        sliderm.setValue(game.music.getVolume() * 100f);
        sliderm.setPosition(200, 20);
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
        text.draw(game.batch, "Music Volume", 200,100);
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
