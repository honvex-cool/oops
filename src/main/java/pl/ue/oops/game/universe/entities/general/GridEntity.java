package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.animations.Animation;
import pl.ue.oops.game.animations.controllers.AnimationController;
import pl.ue.oops.game.universe.utils.GridPosition;

public interface GridEntity {
    GridPosition getPosition();

    void stepAnimation(float delta);

    boolean hasFinishedAnimation();

    boolean isActive();

    void interact(GridEntity other);

    AnimationController getAnimationController();

    void disable();

    default Animation getCurrentAnimation() {
        return getAnimationController().getCurrentAnimation();
    }
}
