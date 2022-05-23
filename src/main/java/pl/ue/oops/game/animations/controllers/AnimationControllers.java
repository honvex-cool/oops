package pl.ue.oops.game.animations.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import pl.ue.oops.Config;
import pl.ue.oops.game.animations.sequences.SimpleSpriteSequence;
import pl.ue.oops.game.animations.sequences.SpriteSequence;

public final class AnimationControllers {
    private AnimationControllers() {
    }

    public static AnimationController create(SpriteSequence universalSpriteSequence) {
        return new SingleSpriteSequenceAnimationController(universalSpriteSequence);
    }

    public static AnimationController create(SpriteSequence movementSpriteSequence, SpriteSequence restSpriteSequence) {
        return new DoubleSpriteSequenceAnimationController(movementSpriteSequence, restSpriteSequence);
    }

    public static AnimationController singleFrame(Sprite sprite) {
        return create(new SimpleSpriteSequence(sprite));
    }

    public static AnimationController singleFrame(String spriteName) {
        return singleFrame(new Sprite(new Texture(Config.TEXTURE_PATH + spriteName + ".png")));
    }
}
