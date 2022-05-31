package pl.ue.oops.game.universe.utils.statistics;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Statistics {
    private final Map<TrackedParameter, Integer> stats;

    private Statistics(Map<TrackedParameter, Integer> stats) {
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

    public void add(TrackedParameter parameter, int value) {
        stats.put(parameter, get(parameter) + value);
    }

    public int get(TrackedParameter parameter) {
        return stats.getOrDefault(parameter, 0);
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
                        pair -> Integer.parseInt(pair[1])
                    )
                )
        );
    }

    public Stream<Map.Entry<TrackedParameter, Integer>> entries() {
        return stats.entrySet().stream();
    }
}
