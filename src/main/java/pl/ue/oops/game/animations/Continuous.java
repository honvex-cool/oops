package pl.ue.oops.game.animations;

public interface Continuous {
    Continuous start();
    void step(float delta);

    default Continuous restart() {
        return start();
    }
}
