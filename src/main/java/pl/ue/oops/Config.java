package pl.ue.oops;

import com.badlogic.gdx.graphics.Color;

//Config is a namespace with constants
public class Config {
    public static final int FPS = 60;
    public static final boolean VSYNC_ENABLED = true;
    public static final int NATIVE_WIDTH = 960;
    public static final int NATIVE_HEIGHT = 640;
    public static final String NAME = "Oops...";

    public static final Color CLEAR_COLOR = Color.BLACK;
    public static final int TILE_SIDE_LENGTH = 40;
    public static final String TEXTURE_PATH = "src/main/resources/test_sprites/";

    private Config() {
    }
}
