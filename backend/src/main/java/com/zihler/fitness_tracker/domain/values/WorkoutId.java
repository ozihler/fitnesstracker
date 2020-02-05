package com.zihler.fitness_tracker.domain.values;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class WorkoutId {
    private long gid;

    public WorkoutId(long gid) {
        this.gid = gid;
    }

    public static WorkoutId random() {
        return new WorkoutId(ThreadLocalRandom.current().nextLong(0L, Long.MAX_VALUE));
    }

    public static WorkoutId of(long gid) {
        return new WorkoutId(gid);
    }

    public long toLong() {
        return gid;
    }

    @Override
    public String toString() {
        return String.valueOf(gid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkoutId workoutId1 = (WorkoutId) o;
        return gid == workoutId1.gid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid);
    }
}
