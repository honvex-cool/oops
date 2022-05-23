package pl.ue.oops.game.animations.sequences;

import com.badlogic.gdx.graphics.g2d.Sprite;
import pl.ue.oops.game.animations.Continuous;

public interface SpriteSequence extends Continuous {
    Sprite getSprite();
    boolean isRepeating();
    void setRepeating(boolean repeating);
}
