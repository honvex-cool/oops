package pl.ue.oops.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.ue.oops.game.Oops;
import pl.ue.oops.game.scenes.Hud;
import pl.ue.oops.game.sprites.Player;

import java.util.concurrent.ForkJoinWorkerThread;

public class LevelScreen extends GameScreen {

    private Sprite sprite;
    private Hud hud;
    private World world;
    private Box2DDebugRenderer b2DDR;
    private Player player;

    public LevelScreen(final Oops game) {
        super(game);
        hud = new Hud(game.batch);
        sprite = new Sprite(new Texture(game.test_sprites.child("greenSquare.png")));
        player = new Player(0,0);
        player.setNewVelocity(3);
        //world = new World(new Vector2(0,0),true);
        //b2DDR = new Box2DDebugRenderer();
    }






    private void update(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            Gdx.app.exit();
        if(Gdx.input.isKeyPressed(Input.Keys.W))
            player.move(new Vector3(0,1,0));
        if(Gdx.input.isKeyPressed(Input.Keys.S))
            player.move(new Vector3(0,-1,0));
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            player.move(new Vector3(-1,0,0));
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            player.move(new Vector3(1,0,0));
    }



    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.CHARTREUSE);
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(player.getTexture(),player.getPosition().x,player.getPosition().y);
        game.batch.end();
        update(delta);
    }

    @Override
    public void show() {
        super.show();
    }
}
