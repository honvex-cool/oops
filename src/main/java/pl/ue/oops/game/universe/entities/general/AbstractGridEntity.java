package pl.ue.oops.game.universe.entities.general;

import com.badlogic.gdx.graphics.g2d.Sprite;
import pl.ue.oops.game.animations.controllers.AnimationController;
import pl.ue.oops.game.animations.controllers.AnimationControllers;
import pl.ue.oops.game.animations.sequences.DelayedSpriteSequence;
import pl.ue.oops.game.animations.sequences.SimpleSpriteSequence;
import pl.ue.oops.game.resources.TextureManager;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;

public abstract class AbstractGridEntity implements GridEntity {
    protected final GridPosition gridPosition;
    protected boolean active = true;
    protected final Level level;
    protected AnimationController animationController;

    public AbstractGridEntity(Level level, int row, int column, AnimationController controller) {
        this.level = level;
        this.animationController = controller;
        this.gridPosition = new GridPosition(row, column);
        this.animationController.playIdleAnimation(this.gridPosition);
    }

    public AbstractGridEntity(Level level, int row, int column, String spriteName) {
        this(level, row, column, AnimationControllers.singleFrame(spriteName));
    }

    public AbstractGridEntity(Level level, int row, int column, String idleSpriteName, String moveSpriteName) {
        this(level, row, column, createAnimationController(idleSpriteName, moveSpriteName));
    }

    public AbstractGridEntity(Level level, GridPosition gridPosition, String spriteName) {
        this(level, gridPosition, AnimationControllers.singleFrame(spriteName));
    }

    public AbstractGridEntity(Level level, GridPosition gridPosition, String normalSpriteName, String moveSpriteName) {
        this(level, gridPosition, createAnimationController(normalSpriteName, moveSpriteName));
    }

    public AbstractGridEntity(Level level, GridPosition gridPosition, AnimationController animationController) {
        this(level, gridPosition.getRow(), gridPosition.getColumn(), animationController);
    }

    private static AnimationController createAnimationController(String idleSpriteName, String moveSpriteName) {
        Sprite idleSprite = new Sprite(TextureManager.getSprite(idleSpriteName));
        Sprite moveSprite = new Sprite(TextureManager.getSprite(moveSpriteName));
        return AnimationControllers.create(new SimpleSpriteSequence(moveSprite), new DelayedSpriteSequence(3, moveSprite, idleSprite));
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
