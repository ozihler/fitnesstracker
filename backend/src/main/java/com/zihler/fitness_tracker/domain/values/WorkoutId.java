package com.zihler.fitness_tracker.domain.values;

import java.util.Objects;

public class WorkoutId {
    private String id;

    public WorkoutId(String id) {
        this.id = id;
    }

    public static WorkoutId of(String gid) {
        return new WorkoutId(gid);
    }

    @Override
    public String toString() {
        return id;
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
        return Objects.equals(id, workoutId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
