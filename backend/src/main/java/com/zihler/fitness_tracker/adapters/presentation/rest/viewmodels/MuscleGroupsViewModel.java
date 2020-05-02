package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MuscleGroupsViewModel {
    @JsonProperty("muscleGroups")
    private List<MuscleGroupViewModel> muscleGroups;

    public MuscleGroupsViewModel(List<MuscleGroupViewModel> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public MuscleGroupsViewModel() {
    }

    public List<MuscleGroupViewModel> getMuscleGroups() {
        return muscleGroups;
    }

    @Override
    public String toString() {
        return String.format("MuscleGroupsViewModel{%s}", muscleGroups);
    }
}
