package pl.ue.oops.game.universe.entities.general;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class TexturedEntity extends AbstractEntity {
    private final Texture texture;
    private final Sprite sprite;

    public TexturedEntity(String texturePath) {
        texture = new Texture("src/main/resources/test_sprites/" + texturePath);
        sprite = new Sprite(texture);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
