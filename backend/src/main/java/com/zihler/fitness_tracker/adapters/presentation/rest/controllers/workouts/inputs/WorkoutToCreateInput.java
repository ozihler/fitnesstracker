package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.inputs;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.WorkoutToCreateRequest;
import com.zihler.fitness_tracker.domain.values.WorkoutTitle;

public class WorkoutToCreateInput {
    private WorkoutToCreateRequest request;

    public WorkoutToCreateInput(WorkoutToCreateRequest request) {
        this.request = request;
    }

    public WorkoutTitle workoutTitle() {
        return WorkoutTitle.from(request.getTitle());
    }

}
