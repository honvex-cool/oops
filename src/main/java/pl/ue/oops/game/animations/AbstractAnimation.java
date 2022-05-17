package pl.ue.oops.game.animations;

import pl.ue.oops.game.universe.entities.general.ActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;

public abstract class AbstractAnimation implements Animation{
    protected float length;
    protected int frameLength;
    protected int currentFrame=-1;
    protected final GridEntity entity;
    boolean paused=false;
    public AbstractAnimation(float length, int frameLength, GridEntity entity){
        this.length = length;
        this.frameLength = frameLength;
        this.entity = entity;
    }

    @Override
    public abstract void start();

    @Override
    public void stop() {
        currentFrame = -1;
    }

    @Override
    public void resume() {
        paused = false;
    }

    @Override
    public void pause() {
        paused = true;
    }

    @Override
    public boolean isActive() {
        if(paused)
            return false;
        if(currentFrame>=0)
            return true;
        return false;
    }

    @Override
    public float getLength() {
        return length;
    }

    @Override
    public int getFrameLength() {
        return frameLength;
    }

    @Override
    public abstract void step(float delta);
}
