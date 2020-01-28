package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class MuscleGroupsViewModel {
    @JsonProperty("muscleGroups")
    private Set<MuscleGroupViewModel> muscleGroups;

    public MuscleGroupsViewModel(Set<MuscleGroupViewModel> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    public MuscleGroupsViewModel() {
    }

    public Set<MuscleGroupViewModel> getMuscleGroups() {
        return muscleGroups;
    }
}
