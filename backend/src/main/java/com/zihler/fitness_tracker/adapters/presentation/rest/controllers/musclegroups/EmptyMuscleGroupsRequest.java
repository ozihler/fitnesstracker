package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

public class EmptyMuscleGroupsRequest extends RuntimeException {
    public EmptyMuscleGroupsRequest() {
        super("Muscle group string was empty!");
    }
}
