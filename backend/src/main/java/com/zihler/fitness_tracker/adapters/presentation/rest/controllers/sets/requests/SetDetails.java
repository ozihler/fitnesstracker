package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetDetails {

    @JsonProperty("setDetails")
    private String setDetails;

    public SetDetails() {
    }

    public String getSetDetails() {
        return setDetails;
    }

    @Override
    public String toString() {
        return setDetails;
    }
}
