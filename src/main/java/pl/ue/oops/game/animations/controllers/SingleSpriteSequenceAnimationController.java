package pl.ue.oops.game.animations.controllers;

import pl.ue.oops.game.animations.sequences.SpriteSequence;

public class SingleSpriteSequenceAnimationController extends AbstractAnimationController {
    private final SpriteSequence movementSpriteSequence;

    public SingleSpriteSequenceAnimationController(SpriteSequence movementSpriteSequence) {
        this.movementSpriteSequence = movementSpriteSequence;
    }

    @Override
    protected SpriteSequence getMovementSpriteSequence() {
        return movementSpriteSequence;
    }

    @Override
    protected SpriteSequence getRestSpriteSequence() {
        return movementSpriteSequence;
    }
}
