package pl.ue.oops.game.universe.entities.general;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Visible {
    Sprite getSprite();
    void stepAnimation(float delta);
    boolean hasFinishedAnimation();
}
