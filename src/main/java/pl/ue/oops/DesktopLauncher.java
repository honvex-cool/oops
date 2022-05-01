package pl.ue.oops;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import pl.ue.oops.game.Oops;

public class DesktopLauncher {
    public static void main(final String[] args) {
        new Lwjgl3Application(new Oops(), createConfiguration());
    }

    private static Lwjgl3ApplicationConfiguration createConfiguration() {
        final var configuration = new Lwjgl3ApplicationConfiguration();
        configuration.setIdleFPS(Config.FPS);
        configuration.useVsync(Config.VSYNC_ENABLED);
        configuration.setWindowedMode(Config.NATIVE_WIDTH, Config.NATIVE_HEIGHT);
        configuration.setTitle(Config.NAME);
        return configuration;
    }
}
