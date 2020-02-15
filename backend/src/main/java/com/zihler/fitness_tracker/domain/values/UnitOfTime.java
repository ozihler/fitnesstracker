package com.zihler.fitness_tracker.domain.values;

public enum UnitOfTime {
    SECONDS("s");

    private String shortname;

    UnitOfTime(String shortname) {
        this.shortname = shortname;
    }

    public static UnitOfTime fromShortName(String waitingTimeUnit) {
        switch (waitingTimeUnit) {
            case "s":
            default:
                return SECONDS;
        }
    }

    public String shortname() {
        return shortname;
    }
}
