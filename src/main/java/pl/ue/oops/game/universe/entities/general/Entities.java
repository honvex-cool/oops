package pl.ue.oops.game.universe.entities.general;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public final class Entities {
    private Entities() {
    }

    public static void render(Entity entity, SpriteBatch batch, float sideLength) {
        final var position = entity.getPosition();
        final var x = position.getColumn() * sideLength;
        final var y = position.getRow() * sideLength;
        batch.draw(entity.getSprite(), x, y, sideLength, sideLength);
    }
}
