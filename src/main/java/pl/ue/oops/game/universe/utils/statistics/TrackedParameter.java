package pl.ue.oops.game.universe.utils.statistics;

public enum TrackedParameter {
    TURNS_SURVIVED,
    PROJECTILES_FIRED,
    LEVELS_CLEARED,
    HITS_TAKEN,
    HP_POINTS_LOST,
    HP_POINTS_GAINED,
    ;

    public static String formatted(TrackedParameter parameter, int value) {
        return value + " " + (value == 1 ? parameter.singular() : parameter.plural());
    }

    public String singular() {
        final var words = plural().split(" ");
        if(words.length >= 2) {
            final String noun = words[words.length - 2];
            words[words.length - 2] = noun.substring(0, noun.length() - 1);
        }
        return String.join(" ", words);
    }

    public String plural() {
        return name().replace('_', ' ');
    }
}
