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
    private final Label ammoLabel;


    public Hud(SpriteBatch spriteBatch){
        viewport = new FitViewport(Config.NATIVE_WIDTH,Config.NATIVE_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        score = 0;
        scoreLabel = new Label(String.format("%03d",score),Config.DEFAULT_UI_SKIN);
        scoreNameLabel = new Label(String.format("SCORE"),Config.DEFAULT_UI_SKIN);
        turn = 0;
        turnLabel = new Label(String.format("%03d",turn),Config.DEFAULT_LABEL_STYLE);
        turnNameLabel = new Label(String.format("TURN"),Config.DEFAULT_LABEL_STYLE);
        hpLabel = new Label("5",Config.DEFAULT_LABEL_STYLE);
        hpNameLabel = new Label(String.format("HP"),Config.DEFAULT_LABEL_STYLE);
        ammoLabel = new Label("10",Config.DEFAULT_LABEL_STYLE);
        final var ammoNameLabel = new Label("AMMO",Config.DEFAULT_LABEL_STYLE);


        Table table = new Table();
        table.setFillParent(true);
        table.top();
        table.add(scoreNameLabel).expandX();
        table.add(hpNameLabel).expandX();
        table.add(ammoNameLabel).expandX();
        table.add(turnNameLabel).expandX();
        table.row();
        table.add(scoreLabel).expandX();
        table.add(hpLabel).expandX();
        table.add(ammoLabel).expandX();
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

    public void updateAmmo(int ammo) {
        if(ammo == 0)
            ammoLabel.setText("EMPTY!");
        else
            ammoLabel.setText(ammo);
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}
