package pl.ue.oops.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import pl.ue.oops.Config;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Integer score;
    private Label scoreLabel;
    private final Label scoreNameLabel;
    private Integer turn;
    private Label turnLabel;
    private final Label turnNameLabel;
    private final Label hpLabel;
    private final Label hpNameLabel;


    public Hud(SpriteBatch spriteBatch){
        viewport = new FitViewport(Config.NATIVE_WIDTH,Config.NATIVE_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        score = 0;
        scoreLabel = new Label(String.format("%03d",score),new Label.LabelStyle(new BitmapFont(), Color.FIREBRICK));
        scoreNameLabel = new Label(String.format("SCORE"),new Label.LabelStyle(new BitmapFont(), Color.FIREBRICK));
        turn = 0;
        turnLabel = new Label(String.format("%03d",turn),new Label.LabelStyle(new BitmapFont(), Color.FIREBRICK));
        turnNameLabel = new Label(String.format("TURN"),new Label.LabelStyle(new BitmapFont(), Color.FIREBRICK));
        hpLabel = new Label("5",new Label.LabelStyle(new BitmapFont(), Color.FIREBRICK));
        hpNameLabel = new Label(String.format("HP"),new Label.LabelStyle(new BitmapFont(), Color.FIREBRICK));


        Table table = new Table();
        table.setFillParent(true);
        table.top();
        table.add(scoreNameLabel).expandX();
        table.add(hpNameLabel).expandX();
        table.add(turnNameLabel).expandX();
        table.row();
        table.add(scoreLabel).expandX();
        table.add(hpLabel).expandX();
        table.add(turnLabel).expandX();

        stage.addActor(table);
    }

    public void updateTurn(){
        turn++;
        turnLabel.setText(turn);
    }
    public void updateScore(){
        score++;
        scoreLabel.setText(score);
    }

    public void updateHp(int hp){
        hpLabel.setText(hp);
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
