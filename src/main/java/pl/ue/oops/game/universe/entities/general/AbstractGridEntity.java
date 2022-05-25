package pl.ue.oops.game.universe.entities.general;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import pl.ue.oops.Config;
import pl.ue.oops.game.animations.controllers.AnimationController;
import pl.ue.oops.game.animations.controllers.AnimationControllers;
import pl.ue.oops.game.animations.sequences.DelayedSpriteSequence;
import pl.ue.oops.game.animations.sequences.SimpleSpriteSequence;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;

public abstract class AbstractGridEntity implements GridEntity {
    protected final GridPosition gridPosition;
    protected boolean active = true;
    protected final Level level;
    protected AnimationController animationController;

    public AbstractGridEntity(Level level, GridPosition gridPosition, String spriteName) {
        this(level, gridPosition, AnimationControllers.singleFrame(spriteName));
    }

    public AbstractGridEntity(Level level, GridPosition gridPosition, String normalSpriteName, String moveSpriteName) {
        this(level, gridPosition, createAnimationController(normalSpriteName, moveSpriteName));
    }

    public AbstractGridEntity(Level level, GridPosition gridPosition, AnimationController animationController) {
        this.level = level;
        this.animationController = animationController;
        this.gridPosition = gridPosition;
        this.animationController.playIdleAnimation(this.gridPosition);
    }

    private static AnimationController createAnimationController(String normalSpriteName, String moveSpriteName) {
        Sprite normalSprite = new Sprite(new Texture(Config.TEXTURE_PATH + normalSpriteName + ".png"));
        Sprite moveSprite = new Sprite(new Texture(Config.TEXTURE_PATH + moveSpriteName + ".png"));
        return AnimationControllers.create(new SimpleSpriteSequence(moveSprite), new DelayedSpriteSequence(3, moveSprite, normalSprite));
    }

    @Override
    public GridPosition getPosition() {
        return gridPosition;
    }

    @Override
    public boolean hasFinishedAnimation() {
        return true;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public final void disable() {
        active = false;
    }

    @Override
    public void interact(GridEntity other) {
    }

    @Override
    public void stepAnimation(float delta) {
        if(getAnimationController().stopsTurnProgression() && !getCurrentAnimation().isInMotion())
            getAnimationController().playIdleAnimation(getPosition());
        else
            getCurrentAnimation().step(delta);
    }

    @Override
    public AnimationController getAnimationController() {
        return animationController;
    }
}
