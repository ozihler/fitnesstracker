package com.zihler.fitness_tracker.domain.values;

import java.util.Objects;

public class MuscleGroupName {
    private Name muscleGroupName;

    private MuscleGroupName(String muscleGroupName) {
        this.muscleGroupName = Name.of(muscleGroupName);
    }

    public static MuscleGroupName of(String muscleGroupName) {
        return new MuscleGroupName(muscleGroupName);
    }

    @Override
    public String toString() {
        return muscleGroupName.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MuscleGroupName that = (MuscleGroupName) o;
        return Objects.equals(muscleGroupName, that.muscleGroupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(muscleGroupName);
    }
}
