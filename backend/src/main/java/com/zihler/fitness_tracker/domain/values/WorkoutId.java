package com.zihler.fitness_tracker.domain.values;

import java.util.Objects;

public class WorkoutId {
    private String gid;

    public WorkoutId(String gid) {
        this.gid = gid;
    }

    public static WorkoutId of(String gid) {
        return new WorkoutId(gid);
    }

    public String toLong() {
        return gid;
    }

    @Override
    public String toString() {
        return gid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkoutId workoutId = (WorkoutId) o;
        return Objects.equals(gid, workoutId.gid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid);
    }
}
