package pl.ue.oops.game.animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pl.ue.oops.game.animations.movements.Movement;
import pl.ue.oops.game.animations.sequences.SpriteSequence;

public class SimpleAnimation implements Animation {
    protected Movement movement;
    protected SpriteSequence spriteSequence;

    public SimpleAnimation(Movement movement, SpriteSequence spriteSequence) {
        this.movement = movement;
        this.spriteSequence = spriteSequence;
    }

    @Override
    public void start() {
        movement.start();
        spriteSequence.start();
    }

    @Override
    public void step(float delta) {
        movement.step(delta);
        spriteSequence.step(delta);
    }

    @Override
    public boolean isInMotion() {
        return movement.isInProgress();
    }

    @Override
    public void render(SpriteBatch batch, float tileSideLength) {
        final var renderPosition = movement.getRenderPosition(tileSideLength);
        batch.draw(spriteSequence.getSprite(), renderPosition.x, renderPosition.y, tileSideLength, tileSideLength);
    }
}
