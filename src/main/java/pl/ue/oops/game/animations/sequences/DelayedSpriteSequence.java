package pl.ue.oops.game.animations.sequences;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class DelayedSpriteSequence extends SimpleSpriteSequence {
    private final int framesBetweenChanges;
    private int delay;

    public DelayedSpriteSequence(boolean repeating, int framesBetweenChanges, Sprite... sprites) {
        super(repeating, sprites);
        this.framesBetweenChanges = framesBetweenChanges;
    }

    public DelayedSpriteSequence(int framesBetweenChanges, Sprite... sprites) {
        this(true, framesBetweenChanges, sprites);
    }

    @Override
    public void start() {
        super.start();
        delay = 0;
    }

    @Override
    public void step(float delta) {
        if(delay++ == framesBetweenChanges) {
            super.step(delta);
            delay = 0;
        }
    }
}
