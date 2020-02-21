package com.zihler.fitness_tracker.domain.values;

import java.time.*;

public class CreationDate {
    private static ZoneId zone = Clock.systemDefaultZone().getZone();

    private final LocalDate creationDate;

    public CreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public static CreationDate from(LocalDate creationDate) {
        return new CreationDate(creationDate);
    }

    public static CreationDate from(long creationDateInMillis) {
        return from(LocalDate.from(Instant.ofEpochMilli(creationDateInMillis).atZone(zone)));
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public long toMillis() {
        return getCreationDate().atStartOfDay(zone).toInstant().toEpochMilli();
    }

    @Override
    public String toString() {
        return creationDate.toString();
    }
}
