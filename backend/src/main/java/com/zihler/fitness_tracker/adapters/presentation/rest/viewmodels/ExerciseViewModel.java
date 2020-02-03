package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupViewModel;

import java.util.List;

public class ExerciseViewModel {
    @JsonProperty("muscleGroup")
    private MuscleGroupViewModel muscleGroup;
    @JsonProperty("name")
    private String name;
    @JsonProperty("sets")
    private List<SetViewModel> sets;

    public ExerciseViewModel(MuscleGroupViewModel muscleGroup, String name, List<SetViewModel> sets) {
        this.muscleGroup = muscleGroup;
        this.name = name;
        this.sets = sets;
    }

    public ExerciseViewModel() {
    }

    public String getName() {
        return name;
    }

    public List<SetViewModel> getSets() {
        return sets;
    }

    public MuscleGroupViewModel getMuscleGroup() {
        return muscleGroup;
    }

    @Override
    public String toString() {
        return String.format("ExerciseViewModel{muscleGroup=%s, name='%s', sets=%s}", muscleGroup, name, sets);
    }
}
