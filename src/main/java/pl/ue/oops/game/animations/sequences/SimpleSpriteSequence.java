package pl.ue.oops.game.animations.sequences;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class SimpleSpriteSequence implements SpriteSequence {
    private final Sprite[] sprites;
    protected int spriteIndex;
    private boolean repeating;

    SimpleSpriteSequence(boolean repeating, Sprite... sprites) {
        this.sprites = sprites;
        this.repeating = repeating;
    }

    public SimpleSpriteSequence(Sprite... sprites) {
        this(true, sprites);
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
    public void step(float delta) {
        if(++spriteIndex == sprites.length)
            spriteIndex = isRepeating() ? 0 : sprites.length - 1;
    }

    @Override
    public Sprite getSprite() {
        return sprites[spriteIndex];
    }
}
