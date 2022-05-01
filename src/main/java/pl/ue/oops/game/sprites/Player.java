package pl.ue.oops.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class Player {
    private Texture texture;
    private Vector3 position;
    private float velocity;
    public Player(int x, int y) {
        texture = new Texture("src/main/resources/test_sprites/blueSquare.png");
        position = new Vector3(x, y, 0);
        velocity = 1;
    }

    public void setNewVelocity(float velocity){
        this.velocity = velocity;
    }
    public void move(Vector3 vector){
        position.add(vector.scl(velocity));
    }
    public Vector3 getPosition(){
        return position;
    }
    public Texture getTexture() {
        return texture;
    }
}
