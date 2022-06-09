package pl.ue.oops;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

//Config is a namespace with constants
public class Config {
    public static final int FPS = 60;
    public static final boolean VSYNC_ENABLED = true;
    public static final int NATIVE_WIDTH = 960;
    public static final int NATIVE_HEIGHT = 640;
    public static final String NAME = "Oops...";

    public static final Color CLEAR_COLOR = Color.BLACK;
    public static final int TILE_SIDE_LENGTH = 40;
    public static final int DEFAULT_ROW_COUNT=16;
    public static final int DEFAULT_COLUMN_COUNT=24;
    public static final String TEXTURE_PATH = "src/main/resources/test_sprites/";
    public static final Color MENU_BACKGROUND_COLOR = new Color(221/255f, 172/255f, 136/255f, 1.0f);
    public static final Skin DEFAULT_UI_SKIN = new Skin(new FileHandle("src/main/resources/skins/uiskin.json"));
    public static final Label.LabelStyle DEFAULT_LABEL_STYLE = new Label.LabelStyle(DEFAULT_UI_SKIN.getFont("default-font"),Color.FIREBRICK);
    public static final int ENEMIES_MINIMAL_DISTANCE_TO_PLAYER = 3;

    private Config() {
    }
}
