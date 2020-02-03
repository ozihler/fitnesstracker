package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests;

public class EmptyMuscleGroupsRequest extends RuntimeException {
    public EmptyMuscleGroupsRequest() {
        super("Muscle group string was empty!");
    }
}
