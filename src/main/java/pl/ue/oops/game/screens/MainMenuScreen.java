package pl.ue.oops.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.ue.oops.game.Oops;

public class MainMenuScreen implements Screen {
    boolean settings, selection;



    private final Sprite play_button_active;
    private final Sprite exit_button_active;
    private final Sprite settings_button_active;
    private final Sprite play_button_inactive;
    private final Sprite exit_button_inactive;
    private final Sprite settings_button_inactive;
    private final Sprite music_active;
    private final Sprite music_inactive;


    float def_w, def_h;

    private float getX(float x){
        return x*Gdx.graphics.getWidth()/def_w;
    }
    private float getY(float x){
        return x*Gdx.graphics.getHeight()/def_h;
    }
    Oops game;
    public MainMenuScreen(final Oops game) {
        def_w= Gdx.graphics.getWidth();
        def_h= Gdx.graphics.getHeight();
        this.game=game;
        play_button_inactive = new Sprite(new Texture("src/main/resources/test_sprites/start_inactive.png"));
        exit_button_inactive = new Sprite(new Texture("src/main/resources/test_sprites/exit_inactive.png"));
        settings_button_inactive = new Sprite(new Texture("src/main/resources/test_sprites/settings_inactive.png"));
        play_button_active = new Sprite(new Texture("src/main/resources/test_sprites/start_active.png"));
        exit_button_active = new Sprite(new Texture("src/main/resources/test_sprites/exit_active.png"));
        settings_button_active = new Sprite(new Texture("src/main/resources/test_sprites/settings_active.png"));
        music_inactive = new Sprite(new Texture("src/main/resources/test_sprites/music_inactive.png"));
        music_active = new Sprite(new Texture("src/main/resources/test_sprites/music_active.png"));
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


        //System.out.println("   "+ Gdx.input.getY() +"  "+def_h + "    "+ getY((def_h/3f)) );
        ScreenUtils.clear(Color.CHARTREUSE);
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        //game.batch.draw(play_button_active, 100,400,200,200);
        game.batch.draw(settings_button_inactive, (def_w/3f)-128f ,(def_h/3f)- 160f,256f,256f);
        //game.batch.draw(exit_button_active, 400,400,200,200);

        if    ( Gdx.input.getX()>=getX((def_w/3f)-100f) &&Gdx.input.getX()<=getX((def_w/3f)+100f)
                && Gdx.input.getY()>=getY((def_h/3f)-128f) && Gdx.input.getY()<=getY((def_h/3f)+64f) ){

            if(Gdx.input.isTouched()) {
                game.setScreen(new LevelScreen(game));
            }
            else {
                game.batch.draw(play_button_active, (def_w/3f)-128f,(def_h/3f)+128f,256f,256f);
            }
        }
        else {
            game.batch.draw(play_button_inactive, (def_w/3)-128,(def_h/3)+128,256,256);
        }
        if(     Gdx.input.getX()>=getX((2f*def_w/3f)-100f) &&Gdx.input.getX()<=getX((2f*def_w/3f)+100f) &&
                Gdx.input.getY()>=getY((def_h/3f)-128f) && Gdx.input.getY()<=getY((def_h/3f)+64f) ){
            if(Gdx.input.isTouched()) {
                Gdx.app.exit();
            }
            else {
                game.batch.draw(exit_button_active, (2*def_w/3)-128,(def_h/3)+128,256,256);
            }
        }
        else {
            game.batch.draw(exit_button_inactive, (2*def_w/3)-128,(def_h/3)+128,256,256);
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
