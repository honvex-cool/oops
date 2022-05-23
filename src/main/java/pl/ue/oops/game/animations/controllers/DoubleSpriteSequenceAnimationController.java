package pl.ue.oops.game.animations.controllers;

import pl.ue.oops.game.animations.sequences.SpriteSequence;

public class DoubleSpriteSequenceAnimationController extends SingleSpriteSequenceAnimationController {
    private final SpriteSequence restSpriteSequence;

    public DoubleSpriteSequenceAnimationController(SpriteSequence movementSpriteSequence, SpriteSequence restSpriteSequence) {
        super(movementSpriteSequence);
        this.restSpriteSequence = restSpriteSequence;
    }

    @Override
    protected SpriteSequence getRestSpriteSequence() {
        return restSpriteSequence;
    }
}
