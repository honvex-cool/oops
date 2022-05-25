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
    private boolean stopsProgression;

    @Override
    public Animation getCurrentAnimation() {
        return currentAnimation;
    }

    @Override
    public Animation playMoveAnimation(GridPosition from, GridPosition to, float time) {
        final var movement = new StraightLineOneWayMovement(from, to, (int)Math.ceil(time * Config.FPS));
        return playAnimation(new SimpleAnimation(movement, getMovementSpriteSequence()), true);
    }

    @Override
    public Animation playIdleAnimation(GridPosition where) {
        return playAnimation(new SimpleAnimation(new NoMovement(where), getRestSpriteSequence()), false);
    }

    @Override
    public Animation playAnimation(Animation animation, boolean stopsProgression) {
        final var interrupted = currentAnimation;
        this.stopsProgression = stopsProgression;
        currentAnimation = animation;
        return interrupted;
    }

    @Override
    public boolean stopsTurnProgression() {
        return stopsProgression;
    }

    protected abstract SpriteSequence getMovementSpriteSequence();

    protected abstract SpriteSequence getRestSpriteSequence();
}
