package pl.ue.oops.game.universe.utils;

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
        Texture texture=null;
        try{
            texture = loadedTextures.computeIfAbsent(name, textureName -> new Texture(Config.TEXTURE_PATH + textureName + ".png"));
        }catch (Exception ignored){};
        try{
            texture = loadedTextures.computeIfAbsent(name, textureName -> new Texture("src/main/resources/textures/" + textureName + ".png"));
        }catch (Exception ignored){};
        return new Sprite(texture);
    }
}
