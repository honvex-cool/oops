package pl.ue.oops.game.animations.sequences;

import com.badlogic.gdx.graphics.g2d.Sprite;
import pl.ue.oops.game.universe.utils.TextureManager;

import java.util.Arrays;

import static java.lang.Math.min;

public class SimpleSpriteSequence implements SpriteSequence {
    private final Sprite[] sprites;
    protected int spriteIndex;
    private boolean repeating;

    public SimpleSpriteSequence(boolean repeating, Sprite... sprites) {
        this.sprites = sprites;
        this.repeating = repeating;
    }

    public SimpleSpriteSequence(boolean repeating, String... spriteNames) {
        this(repeating, Arrays.stream(spriteNames).map(TextureManager::getSprite).toArray(Sprite[]::new));
    }

    public SimpleSpriteSequence(Sprite... sprites) {
        this(false, sprites);
    }

    public SimpleSpriteSequence(String... spriteNames) {
        this(false, spriteNames);
    }

    @Override
    public boolean isRepeating() {
        return repeating;
    }

    @Override
    public void setRepeating(boolean repeating) {
        this.repeating = repeating;
    }

    @Override
    public SpriteSequence start() {
        spriteIndex = 0;
        return this;
    }

    @Override
    public boolean isFinished() {
        return !repeating && spriteIndex==sprites.length;
    }

    @Override
    public void step(float delta) {
        if(spriteIndex < sprites.length)
            ++spriteIndex;
        if(spriteIndex == sprites.length && repeating)
            spriteIndex = 0;

    }

    @Override
    public Sprite getSprite() {
        return sprites[min(spriteIndex,sprites.length-1)];
    }
}
