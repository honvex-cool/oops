package pl.ue.oops.game.animations;

public interface Animation {
    void start();
    void stop();
    void resume();
    void pause();
    boolean isActive();
    float getLength();
    int getFrameLength();
    void step(float delta);
}
