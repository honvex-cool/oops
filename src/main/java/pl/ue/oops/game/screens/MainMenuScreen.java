package pl.ue.oops.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.ue.oops.game.Oops;

import java.security.Key;

public class MainMenuScreen extends GameScreen {

    private final Sprite play_button;
    public MainMenuScreen(final Oops game) {
        super(game);
        play_button = new Sprite(new Texture("src/main/resources/test_sprites/redSquare.png"));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.CHARTREUSE);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.batch.draw(play_button,0,0,200,200);
        game.batch.end();

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            game.camera.rotate(2);
            game.camera.update();
        }

    }
}
