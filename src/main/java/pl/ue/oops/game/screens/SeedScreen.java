package pl.ue.oops.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import pl.ue.oops.game.Oops;

import java.util.Random;

public class SeedScreen implements Screen {
    private Oops game;
    private Stage stage;
    private TextButton back;
    private TextButton seed_b;
    private TextField seed;
    private Skin skin;
    private FitViewport viewport;
    private long se;
    SeedScreen(final Oops game){
        this.game=game;
        game.camera.setToOrtho(false, 1440, 900);
        this.viewport = new FitViewport(1440, 900, game.camera);

        game.camera.update();

        stage=new Stage();
        stage.setViewport(viewport);
        skin = new Skin(new FileHandle("src/main/resources/skins/uiskin.json"));

        seed_b =new TextButton("Seed!", skin);
        seed_b.setPosition(200, 100);
        seed_b.setSize(100, 50);
        stage.addActor(seed_b);
        seed_b.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                seedSent();
            }

        });


        se=new Random().nextLong();
        seed = new TextField(""+se, skin);
        seed.setPosition(200, 200);
        seed.setSize(500, 50);
        stage.addActor(seed);

        back = new TextButton("Back", skin);
        back.setPosition(800, 100);
        back.setSize(100, 50);
        stage.addActor(back);
        back.addListener(new ClickListener(){
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button){
                game.setScreen(new MainMenuScreen(game));
            }

        });


        Gdx.input.setInputProcessor(stage);

    }
    private void seedSent(){
        try{
            game.setScreen(new LevelScreen (game, Long.parseLong(seed.getText())));
        }catch(Exception ignore){
            System.out.println("Random seed:" + se);
            game.setScreen(new LevelScreen (game, se));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.CHARTREUSE);

        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
