package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class WorkoutAndMuscleGroupNamesViewModel {
    @JsonProperty("gid")
    private String gid;
    @JsonProperty("creationDate")
    private long creationDate;
    @JsonProperty("title")
    private String title;
    @JsonProperty("muscleGroups")
    private Set<MuscleGroupNameViewModel> muscleGroups;

    public WorkoutAndMuscleGroupNamesViewModel(String gid, long creationDate, String title, Set<MuscleGroupNameViewModel> muscleGroups) {
        this.gid = gid;
        this.creationDate = creationDate;
        this.title = title;
        this.muscleGroups = muscleGroups;
    }

    public WorkoutAndMuscleGroupNamesViewModel() {
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

    public Set<MuscleGroupNameViewModel> getMuscleGroups() {
        return muscleGroups;
    }

    @Override
    public String toString() {
        return String.format("WorkoutViewModel{gid=%d, creationDate=%d, title='%s', muscleGroups=%s}", gid, creationDate, title, muscleGroups);
    }


}
