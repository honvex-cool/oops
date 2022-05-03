package pl.ue.oops.game.universe.entities.general;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public final class Entities {
    private Entities() {
    }

    public static void render(GridEntity gridEntity, SpriteBatch batch, float sideLength) {
        final Vector3 position = gridEntity.getPosition().getRenderPosition();
        batch.draw(gridEntity.getSprite(), position.x, position.y, sideLength, sideLength);
    }
}
