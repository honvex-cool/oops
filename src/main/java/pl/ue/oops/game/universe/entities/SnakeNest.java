package pl.ue.oops.game.universe.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import pl.ue.oops.game.universe.entities.general.AbstractActiveGridEntity;
import pl.ue.oops.game.universe.entities.general.GridEntity;
import pl.ue.oops.game.universe.entities.general.Projectile;
import pl.ue.oops.game.universe.level.Level;

import java.util.Random;

public class SnakeNest extends AbstractActiveGridEntity {

    private Sound sound;
    public SnakeNest(int row, int column, Level level) {
        super(level, row, column, "snake_nest");
        gridPosition.set(row, column);
        sound = Gdx.audio.newSound(new FileHandle("src/main/resources/music/Sssss_final.mp3"));
    }
    @Override
    public void idleBehaviour() {
        if(new Random().nextInt()%20==0){
            sound.play(1f);
            level.requestSpawn(new Clueless(this.gridPosition.getRow(),this.gridPosition.getColumn(),level));
        }
    }

    @Override
    public void interact(GridEntity other) {
        if(other.getClass().equals(Player.class)){
            level.hud.updateScore();
            sound.dispose();
            disable();
        }
    }
}