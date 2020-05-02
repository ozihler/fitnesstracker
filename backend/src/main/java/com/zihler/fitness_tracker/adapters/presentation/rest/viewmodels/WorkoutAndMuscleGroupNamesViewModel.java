package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WorkoutAndMuscleGroupNamesViewModel {
    @JsonProperty("workoutId")
    private String workoutId;
    @JsonProperty("creationDate")
    private long creationDate;
    @JsonProperty("title")
    private String title;
    @JsonProperty("muscleGroups")
    private List<MuscleGroupViewModel> muscleGroups;

    public WorkoutAndMuscleGroupNamesViewModel(String workoutId, long creationDate, String title, List<MuscleGroupViewModel> muscleGroups) {
        this.workoutId = workoutId;
        this.creationDate = creationDate;
        this.title = title;
        this.muscleGroups = muscleGroups;
    }

    public WorkoutAndMuscleGroupNamesViewModel() {
    }

    public String getWorkoutId() {
        return workoutId;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public String getTitle() {
        return title;
    }

    public List<MuscleGroupViewModel> getMuscleGroups() {
        return muscleGroups;
    }

    @Override
    public String toString() {
        return String.format("WorkoutViewModel{workoutId=%s, creationDate=%d, title='%s', muscleGroups=%s}", workoutId, creationDate, title, muscleGroups);
    }


}
