package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs.MuscleGroupsFullInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToUpdate;
import com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels.WorkoutViewModel;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

public class WorkoutToUpdateInput {
    private final WorkoutToUpdate request;

    public WorkoutToUpdateInput(WorkoutToUpdate request) {
        this.request = request;
    }

    public WorkoutDocument workout() {
        WorkoutViewModel workout = request.getWorkout();

        return new WorkoutDocument(
                WorkoutId.of(workout.getWorkoutId()),
                CreationDate.of(workout.getCreationDate()),
                WorkoutTitle.of(workout.getTitle()),
                new MuscleGroupsFullInput(workout.getMuscleGroups()).toDocument(),
                false
        );
    }


}
