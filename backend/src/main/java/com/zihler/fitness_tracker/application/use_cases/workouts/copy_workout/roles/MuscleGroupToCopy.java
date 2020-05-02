package com.zihler.fitness_tracker.application.use_cases.workouts.copy_workout.roles;

import com.zihler.fitness_tracker.domain.values.MuscleGroup;
import com.zihler.fitness_tracker.domain.values.Name;

public class MuscleGroupToCopy {
    private MuscleGroup muscleGroup;

    public MuscleGroupToCopy(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public MuscleGroup copy() {
        return   MuscleGroup.of(
                Name.of(muscleGroup.getName().toString()),
                new ExercisesToCopy(muscleGroup.getExercises()).copy()
        );
    }
}
