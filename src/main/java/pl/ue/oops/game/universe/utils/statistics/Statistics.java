package pl.ue.oops.game.universe.utils.statistics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Statistics {
    private final Map<TrackedParameter, Long> stats;

    private Statistics(Map<TrackedParameter, Long> stats) {
        this.stats = stats;
    }

    public Statistics() {
        this(new HashMap<>());
    }

    public void increment(TrackedParameter parameter) {
        add(parameter, 1);
    }

    public void decrement(TrackedParameter parameter) {
        add(parameter, -1);
    }

    public void add(TrackedParameter parameter, long value) {
        stats.put(parameter, get(parameter) + value);
    }

    public void setSeed(long value){
        stats.put(TrackedParameter.CURRENT_SEED,value);
    }

    public long get(TrackedParameter parameter) {
        return stats.getOrDefault(parameter, 0l);
    }

    public String toKeyValueRepresentation() {
        return entries()
            .map(entry -> entry.getKey().toString() + " " + entry.getValue().toString())
            .collect(Collectors.joining("\n"));
    }

    public static Statistics fromKeyValueRepresentation(String representation) {
        return new Statistics(
            Arrays.stream(representation.split("\n"))
                .map(singleEntry -> singleEntry.split(" "))
                .collect(
                    Collectors.toMap(
                        pair -> TrackedParameter.valueOf(pair[0]),
                        pair -> Long.parseLong(pair[1])
                    )
                )
        );
    }

    public Stream<Map.Entry<TrackedParameter, Long>> entries() {
        return stats.entrySet().stream();
    }
}
