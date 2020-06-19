package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.exceptions.WorkoutIdNotFoundException;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class WorkoutId {
    private final String id;

    private WorkoutId(String id) {
        if (StringUtils.isBlank(id)) {
            throw new WorkoutIdNotFoundException("Workout ID cannot be empty!");
        }
        this.id = id;
    }

    public static WorkoutId of(String identifier) {
        return new WorkoutId(identifier);
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
