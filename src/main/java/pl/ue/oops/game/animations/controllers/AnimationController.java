package pl.ue.oops.game.animations.controllers;

import pl.ue.oops.game.animations.Animation;
import pl.ue.oops.game.universe.utils.GridPosition;

public interface AnimationController {
    Animation getCurrentAnimation();

    Animation playMoveAnimation(GridPosition from, GridPosition to, float time);

    Animation playIdleAnimation(GridPosition where);

    Animation playAnimation(Animation animation, boolean stopsProgression);

    default Animation playAnimation(Animation animation) {
        return playAnimation(animation, false);
    }

    boolean stopsTurnProgression();
}
