package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.musclegroups.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateMuscleGroupsRequest {
    @JsonProperty("muscleGroupNames")
    private String muscleGroupNames;

    public CreateMuscleGroupsRequest() {
    }

    public String getMuscleGroupNames() {
        return muscleGroupNames;
    }

    public void setMuscleGroupNames(String muscleGroupNames) {
        this.muscleGroupNames = muscleGroupNames;
    }
}
