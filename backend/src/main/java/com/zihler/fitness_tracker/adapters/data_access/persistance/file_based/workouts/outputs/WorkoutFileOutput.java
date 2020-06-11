package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.workouts.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs.MuscleGroupsFilesOutput;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.domain.entities.Workout;

public class WorkoutFileOutput {
    public Workout workout;

    private WorkoutFileOutput(Workout workout) {
        this.workout = workout;
    }

    public static WorkoutFileOutput from(Workout toStore) {
        return new WorkoutFileOutput(toStore);
    }

    public WorkoutViewModel file() {
        return WorkoutViewModel.of(
                this.workout.getWorkoutId().toString(),
                workout.getCreationDate().toMillis(),
                new MuscleGroupsFilesOutput(this.workout.getMuscleGroups()).files()
        );
    }

}
