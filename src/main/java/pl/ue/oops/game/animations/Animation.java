package pl.ue.oops.game.animations;

public interface Animation {
    public void start();
    public void stop();
    public void resume();
    public void pause();
    public boolean isActive();
    public float getLength();
    public int getFrameLength();
    public void step(float delta);
}
