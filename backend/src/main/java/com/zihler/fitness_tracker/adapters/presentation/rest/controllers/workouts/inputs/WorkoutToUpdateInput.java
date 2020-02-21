package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.inputs.MuscleGroupsFullInput;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullWorkoutViewModel;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToUpdate;
import com.zihler.fitness_tracker.application.outbound_ports.documents.WorkoutDocument;
import com.zihler.fitness_tracker.domain.values.CreationDate;
import com.zihler.fitness_tracker.domain.values.WorkoutId;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;

public class WorkoutToUpdateInput {
    private WorkoutToUpdate request;

    public WorkoutToUpdateInput(WorkoutToUpdate request) {
        this.request = request;
    }

    public WorkoutDocument workout() {
        FullWorkoutViewModel workout = request.getWorkout();

        return new WorkoutDocument(
                WorkoutId.of(workout.getGid()),
                CreationDate.from(workout.getCreationDate()),
                WorkoutTitle.of(workout.getTitle()),
                new MuscleGroupsFullInput(workout.getMuscleGroups()).toDocument()
        );
    }


}
