package pl.ue.oops.game.universe.utils.statistics;

public enum TrackedParameter {
    TURNS_SURVIVED,
    PROJECTILES_FIRED,
    ;

    public static String formatted(TrackedParameter parameter, int value) {
        return value + " " + (value == 1 ? parameter.singular() : parameter.plural());
    }

    public String singular() {
        final var plural = plural();
        return plural.substring(0, plural.length() - 1);
    }

    public String plural() {
        return name().replace('_', ' ');
    }
}
