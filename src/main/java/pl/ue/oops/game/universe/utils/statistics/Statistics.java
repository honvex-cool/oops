package pl.ue.oops.game.universe.utils.statistics;

import java.util.HashMap;
import java.util.Map;

public class Statistics {
    private Map<TrackedParameter, Integer> stats = new HashMap<>();

    public void increment(TrackedParameter parameter) {
        add(parameter, 1);
    }

    public void decrement(TrackedParameter parameter) {
        add(parameter, -1);
    }

    public void add(TrackedParameter parameter, int value) {
        if(!stats.containsKey(parameter))
            stats.put(parameter, 0);
        stats.put(parameter, stats.get(parameter) + value);
    }
}
