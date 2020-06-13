package com.zihler.fitness_tracker.domain.values;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class CreationDateTime {
    private final LocalDateTime creationDateTime;

    public CreationDateTime(long instantiationTime) {
        this.creationDateTime = LocalDateTime.from(Instant.ofEpochMilli(instantiationTime).atZone(ZoneId.systemDefault()));
    }

    public static CreationDateTime of(long instantiationTime) {
        return new CreationDateTime(instantiationTime);
    }

    public LocalDateTime toLocalDateTime() {
        return creationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreationDateTime that = (CreationDateTime) o;
        return Objects.equals(creationDateTime, that.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationDateTime);
    }

    @Override
    public String toString() {
        return creationDateTime.toString();
    }
}
