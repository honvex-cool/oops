package pl.ue.oops.game.animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Animation extends Continuous {
    void render(SpriteBatch batch, float tileSideLength);
    Animation start();
    boolean isFinished();
    boolean isInMotion();
}
