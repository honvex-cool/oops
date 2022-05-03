package pl.ue.oops.game.universe.entities.general;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import pl.ue.oops.game.universe.control.Signal;
import pl.ue.oops.game.universe.utils.Dimensions;
import pl.ue.oops.game.universe.utils.Position;

import java.util.Collection;

public interface GridEntity extends Disposable{
    Position getPosition();
    Sprite getSprite();
    void stepIdleAnimation(float delta);
    boolean hasFinishedAnimation();
    boolean isActive();
}
