package pl.ue.oops.game.universe.entities.general;

import pl.ue.oops.game.universe.control.Signal;

public interface ActiveGridEntity extends GridEntity{
    void react(Signal signal);
    void takeTurn(Signal signal);
    void idleBehaviour();
}
