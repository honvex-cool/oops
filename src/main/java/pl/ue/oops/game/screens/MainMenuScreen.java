package pl.ue.oops.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import pl.ue.oops.Config;
import pl.ue.oops.game.Oops;

import java.security.Key;

public class MainMenuScreen extends GameScreen {

    private Sprite play_button;
    public MainMenuScreen(final Oops game) {
        super(game);
        play_button = new Sprite(new Texture(this.game.test_sprites.child("redSquare.png")));
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
