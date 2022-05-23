package pl.ue.oops.game.animations.controllers;

import pl.ue.oops.game.animations.Animation;
import pl.ue.oops.game.universe.utils.GridPosition;

public interface AnimationController {
    Animation getCurrentAnimation();

    void animateMovement(GridPosition from, GridPosition to, float time);

    void animateRest(GridPosition where);

    boolean stopsTurnProgression();
}
