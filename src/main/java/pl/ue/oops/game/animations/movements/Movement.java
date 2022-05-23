package pl.ue.oops.game.animations.movements;

import com.badlogic.gdx.math.Vector2;
import pl.ue.oops.game.animations.Continuous;

public interface Movement extends Continuous {
    Vector2 getRenderPosition(float tileSideLength);
    boolean isInProgress();
}
