package com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles;

import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;

public class MuscleGroupToCopy {
    private final MuscleGroup muscleGroup;

    public MuscleGroupToCopy(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public MuscleGroup copy() {
        return new MuscleGroup(Name.of(muscleGroup.getName().toString()), new ExercisesToCopy(muscleGroup.getExercises()).copy());
    }
}
