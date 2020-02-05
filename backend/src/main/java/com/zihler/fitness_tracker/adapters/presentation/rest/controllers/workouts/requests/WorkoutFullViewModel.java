package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class WorkoutFullViewModel {

    @JsonProperty("gid")
    private long gid;
    @JsonProperty("creationDate")
    private long creationDate;
    @JsonProperty("title")
    private String title;

    public WorkoutFullViewModel() {
    }

    public WorkoutFullViewModel(long gid, long creationDate, String title, Set<MuscleGroupFullViewModel> muscleGroups) {
        this.gid = gid;
        this.creationDate = creationDate;
        this.title = title;
        this.muscleGroups = muscleGroups;
    }

    public long getGid() {
        return gid;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public String getTitle() {
        return title;
    }

    public Set<MuscleGroupFullViewModel> getMuscleGroups() {
        return muscleGroups;
    }

    @JsonProperty("muscleGroups")
    private Set<MuscleGroupFullViewModel> muscleGroups;


}
