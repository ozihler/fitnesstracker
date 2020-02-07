package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ExerciseViewModel {
    @JsonProperty("muscleGroup")
    private MuscleGroupNameViewModel muscleGroup;//todo remove this!
    @JsonProperty("name")
    private String name;
    @JsonProperty("sets")
    private List<FullSetViewModel> sets;

    public ExerciseViewModel(MuscleGroupNameViewModel muscleGroup, String name, List<FullSetViewModel> sets) {
        this.muscleGroup = muscleGroup;
        this.name = name;
        this.sets = sets;
    }

    public ExerciseViewModel() {
    }

    public String getName() {
        return name;
    }

    public List<FullSetViewModel> getSets() {
        return sets;
    }

    public MuscleGroupNameViewModel getMuscleGroup() {
        return muscleGroup;
    }

    @Override
    public String toString() {
        return String.format("ExerciseViewModel{muscleGroup=%s, name='%s', sets=%s}", muscleGroup, name, sets);
    }


}
