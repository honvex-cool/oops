package pl.ue.oops.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.ue.oops.game.Oops;
public class MainMenuScreen implements Screen {

    private final Sprite play_button_active;
    private final Sprite exit_button_active;
    private final Sprite settings_button_active;
    private final Sprite play_button_inactive;
    private final Sprite exit_button_inactive;
    private final Sprite settings_button_inactive;
    Oops game;
    public MainMenuScreen(final Oops game) {
        this.game=game;
        play_button_inactive = new Sprite(new Texture("src/main/resources/test_sprites/blueSquare.png"));
        exit_button_inactive = new Sprite(new Texture("src/main/resources/test_sprites/redSquare.png"));
        settings_button_inactive = new Sprite(new Texture("src/main/resources/test_sprites/greenSquare.png"));
        play_button_active = new Sprite(new Texture("src/main/resources/test_sprites/greenSquare.png"));
        exit_button_active = new Sprite(new Texture("src/main/resources/test_sprites/greenSquare.png"));
        settings_button_active = new Sprite(new Texture("src/main/resources/test_sprites/greenSquare.png"));

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.CHARTREUSE);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();

        //game.batch.draw(play_button_active, 100,400,200,200);
        game.batch.draw(settings_button_active, 100,100,200,200);
        //game.batch.draw(exit_button_active, 400,400,200,200);

        if(Gdx.input.getX()>=100 &&Gdx.input.getX()<=300 && Gdx.input.getY()>=50 && Gdx.input.getY()<=250 ){

            if(Gdx.input.isTouched()) {
                game.setScreen(new LevelScreen(game));
            }
            else {
                game.batch.draw(play_button_active, 100,400,200,200);
            }
        }
        else {
            game.batch.draw(play_button_inactive, 100,400,200,200);
        }
        if(Gdx.input.getX()>=400 &&Gdx.input.getX()<=600 && Gdx.input.getY()>=50 && Gdx.input.getY()<=250 ){
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
            else {
                game.batch.draw(exit_button_active, 400,400,200,200);
            }
        }
        else {
            game.batch.draw(exit_button_inactive, 400,400,200,200);
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
