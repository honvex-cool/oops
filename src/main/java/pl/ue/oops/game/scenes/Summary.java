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
import pl.ue.oops.game.universe.utils.statistics.Statistics;
import pl.ue.oops.game.universe.utils.statistics.TrackedParameter;

import java.util.Arrays;

public class Summary implements Disposable {
    public Stage stage;

    public Summary(SpriteBatch spriteBatch, Statistics statistics) {
        Viewport viewport = new FitViewport(Config.NATIVE_WIDTH, Config.NATIVE_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);

        Table table = new Table();
        table.setFillParent(true);
        table.top();
        table.add(new Label("GAME OVER! :(", new Label.LabelStyle(new BitmapFont(), Color.BLACK))).expandX().row();
        Arrays.stream(TrackedParameter.values())
            .forEachOrdered(
                parameter -> table.add(
                    new Label(
                        TrackedParameter.formatted(parameter, statistics.get(parameter)),
                        new Label.LabelStyle(new BitmapFont(), Color.BLACK)
                    )
                ).expandX().row()
            );

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
