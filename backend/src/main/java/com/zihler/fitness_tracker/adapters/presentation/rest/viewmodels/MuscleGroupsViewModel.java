package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class MuscleGroupsViewModel {
    @JsonProperty("muscleGroups")
    private Set<MuscleGroupNameViewModel> muscleGroups;

    public MuscleGroupsViewModel(Set<MuscleGroupNameViewModel> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public MuscleGroupsViewModel() {
    }

    public Set<MuscleGroupNameViewModel> getMuscleGroups() {
        return muscleGroups;
    }

    @Override
    public String toString() {
        return String.format("MuscleGroupsViewModel{%s}", muscleGroups);
    }
}
