package pl.ue.oops.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.ue.oops.game.Oops;
import pl.ue.oops.game.scenes.Summary;
import pl.ue.oops.game.universe.utils.statistics.Statistics;

public class GameOverScreen extends GameScreen {

    private final Summary summary;

    public GameOverScreen(Oops game, Statistics statistics) {
        super(game);
        summary = new Summary(game.batch, statistics);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.CORAL);
        summary.stage.draw();
    }
}
