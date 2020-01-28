package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateMuscleGroupsRequest {
    @JsonProperty("muscleGroupNames")
    private String muscleGroupNames;

    public CreateMuscleGroupsRequest() {
    }

    public String getMuscleGroupNames() {
        return muscleGroupNames;
    }
}
