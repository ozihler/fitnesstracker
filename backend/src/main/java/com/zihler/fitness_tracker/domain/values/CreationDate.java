package com.zihler.fitness_tracker.domain.values;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

public class CreationDate {
    private static final ZoneId zone = Clock.systemDefaultZone().getZone();

    private final LocalDate creationDate;

    public CreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public static CreationDate of(LocalDate creationDate) {
        return new CreationDate(creationDate);
    }

    public static CreationDate of(long creationDateInMillis) {
        return of(LocalDate.from(Instant.ofEpochMilli(creationDateInMillis).atZone(zone)));
    }

    public static CreationDate now() {
        return of(LocalDate.now());
    }

    public LocalDate toLocalDate() {
        return creationDate;
    }

    public long toMillis() {
        return toLocalDate().atStartOfDay(zone).toInstant().toEpochMilli();
    }

    @Override
    public String toString() {
        return creationDate.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreationDate that = (CreationDate) o;
        return Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDate);
    }
}
