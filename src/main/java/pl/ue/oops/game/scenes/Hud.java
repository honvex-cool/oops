package pl.ue.oops.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import pl.ue.oops.Config;

import javax.swing.text.TabableView;

public class Hud {
    public Stage stage;
    private Viewport viewport;
    private Integer score;
    private Label scoreLabel;

    public Hud(SpriteBatch spriteBatch){
        viewport = new FitViewport(Config.NATIVE_WIDTH,Config.NATIVE_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        score = 0;
        scoreLabel = new Label(String.format("%03d",score),new Label.LabelStyle(new BitmapFont(), Color.FIREBRICK));

        Table table = new Table();
        table.setFillParent(true);
        table.top();
        table.left();
        table.add(scoreLabel);

        stage.addActor(table);
    }

}
