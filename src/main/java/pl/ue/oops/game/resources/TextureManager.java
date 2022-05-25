package pl.ue.oops.game.resources;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import pl.ue.oops.Config;

import java.util.HashMap;
import java.util.Map;

public final class TextureManager {
    private TextureManager() {
    }

    private static final Map<String, Texture> loadedTextures = new HashMap<>();

    public static Sprite getSprite(String name) {
        return new Sprite(
            loadedTextures.computeIfAbsent(
                name,
                textureName -> new Texture(Config.TEXTURE_PATH + textureName + ".png")
            )
        );
    }
}
