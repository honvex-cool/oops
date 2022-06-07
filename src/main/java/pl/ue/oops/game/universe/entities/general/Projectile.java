package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.animations.controllers.AnimationControllers;
import pl.ue.oops.game.animations.controllers.DoubleSpriteSequenceAnimationController;
import pl.ue.oops.game.animations.controllers.SingleSpriteSequenceAnimationController;
import pl.ue.oops.game.animations.sequences.SimpleSpriteSequence;
import pl.ue.oops.game.universe.entities.Player;
import pl.ue.oops.game.universe.level.Level;
import pl.ue.oops.game.universe.utils.GridPosition;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Projectile extends AbstractActiveGridEntity {
    private final int damage;
    private final int rowDelta;
    private final int columnDelta;

    int partRowDelta=0;
    int partColumnDelta=0;

    public Object getOwner() {
        return owner;
    }

    private final Object owner;

    public Projectile(String spriteName, Level level, GridPosition gridPosition, int rowDelta, int columnDelta, int damage) {
        this(spriteName,level,gridPosition,rowDelta,columnDelta,damage,new Object());
    }

    public Projectile(String spriteName, Level level, GridPosition gridPosition, int rowDelta, int columnDelta, int damage, Object owner) {
        super(level, gridPosition, spriteName);
        this.rowDelta = rowDelta;
        this.columnDelta = columnDelta;
        this.damage = damage;
        this.owner = owner;
        try{partRowDelta = rowDelta/abs(rowDelta);}catch (Exception ignored){}
        try{partColumnDelta = columnDelta/abs(columnDelta);}catch (Exception ignored){}

        if(owner.getClass().equals(Player.class)){
            if(partRowDelta==1)
                animationController = AnimationControllers.create(new SimpleSpriteSequence("bullet_move_0_0","bullet_move_0_1"),new SimpleSpriteSequence("bullet_0"));
            if(partRowDelta==-1)
                animationController = AnimationControllers.create(new SimpleSpriteSequence("bullet_move_2_0","bullet_move_2_1"),new SimpleSpriteSequence("bullet_2"));
            if(partColumnDelta==1)
                animationController = AnimationControllers.create(new SimpleSpriteSequence("bullet_move_1_0","bullet_move_1_1"),new SimpleSpriteSequence("bullet_1"));
            if(partColumnDelta==-1)
                animationController = AnimationControllers.create(new SimpleSpriteSequence("bullet_move_3_0","bullet_move_3_1"),new SimpleSpriteSequence("bullet_3"));
        }
    }

    @Override
    public void idleBehaviour() {
        animationController.playMoveAnimation(gridPosition,gridPosition.shifted(rowDelta,columnDelta),0.15f);
        for(int i = 0; i < max(abs(rowDelta), abs(columnDelta)); i++) {
            if (active)
                level.moveHandler.move(this, partRowDelta, partColumnDelta);
        }
        if(!level.getDimensions().contain(gridPosition))
            disable();
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public void interact(GridEntity other) {
        if(!(other instanceof Projectile) && other.getPosition().getPreviousGridPosition().equals(getPosition().shifted(partRowDelta,partColumnDelta))){
            other.interact(this);
        }
    }
}
