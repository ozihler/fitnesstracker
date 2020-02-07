package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class FullWorkoutViewModel {

    @JsonProperty("gid")
    private String gid;
    @JsonProperty("creationDate")
    private long creationDate;
    @JsonProperty("title")
    private String title;

    @JsonProperty("muscleGroups")
    private Set<FullMuscleGroupViewModel> muscleGroups;

    public FullWorkoutViewModel() {
    }

    public FullWorkoutViewModel(String gid, long creationDate, String title, Set<FullMuscleGroupViewModel> muscleGroups) {
        this.gid = gid;
        this.creationDate = creationDate;
        this.title = title;
        this.muscleGroups = muscleGroups;
    }

    public String getGid() {
        return gid;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public String getTitle() {
        return title;
    }

    public Set<FullMuscleGroupViewModel> getMuscleGroups() {
        return muscleGroups;
    }


}
