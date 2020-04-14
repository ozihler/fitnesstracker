package com.zihler.fitness_tracker.domain.values;

import java.util.Objects;

public class WorkoutId {
    private String id;

    private WorkoutId(String id) {
        this.id = id;
    }

    public static WorkoutId of(String identifier) {
        return new WorkoutId(String.format("%s", identifier));
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
