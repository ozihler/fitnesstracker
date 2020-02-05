package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToCreate;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

public class WorkoutToCreateInput {
    private WorkoutToCreate request;

    public WorkoutToCreateInput(WorkoutToCreate request) {
        this.request = request;
    }

    public WorkoutTitle workoutTitle() {
        return WorkoutTitle.of(request.getTitle());
    }

}
