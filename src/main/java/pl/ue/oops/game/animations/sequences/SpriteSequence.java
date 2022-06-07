package pl.ue.oops.game.animations.sequences;

import com.badlogic.gdx.graphics.g2d.Sprite;
import pl.ue.oops.game.animations.Continuous;

public interface SpriteSequence extends Continuous {
    Sprite getSprite();
    boolean isRepeating();
    boolean isFinished();
    void setRepeating(boolean repeating);
}
