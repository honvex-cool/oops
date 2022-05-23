package pl.ue.oops.game.animations;

public interface Continuous {
    void start();
    void step(float delta);

    default void restart() {
        start();
    }
}
