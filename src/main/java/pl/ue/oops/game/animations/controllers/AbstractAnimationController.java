package pl.ue.oops.game.animations.controllers;

import pl.ue.oops.Config;
import pl.ue.oops.game.animations.Animation;
import pl.ue.oops.game.animations.SimpleAnimation;
import pl.ue.oops.game.animations.movements.NoMovement;
import pl.ue.oops.game.animations.movements.StraightLineOneWayMovement;
import pl.ue.oops.game.animations.sequences.SpriteSequence;
import pl.ue.oops.game.universe.utils.GridPosition;

public abstract class AbstractAnimationController implements AnimationController {
    private Animation currentAnimation;
    private boolean isMoveAnimation;

    @Override
    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    @Override
    public void animateMovement(GridPosition from, GridPosition to, float time) {
        final var movement = new StraightLineOneWayMovement(from, to, (int)Math.ceil(time * Config.FPS));
        currentAnimation = new SimpleAnimation(movement, getMovementSpriteSequence());
        isMoveAnimation = true;
    }

    @Override
    public void animateRest(GridPosition where) {
        currentAnimation = new SimpleAnimation(new NoMovement(where), getRestSpriteSequence());
        isMoveAnimation = false;
    }

    @Override
    public boolean stopsTurnProgression() {
        return isMoveAnimation;
    }

    protected abstract SpriteSequence getMovementSpriteSequence();

    protected abstract SpriteSequence getRestSpriteSequence();
}
