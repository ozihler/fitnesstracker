package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WorkoutAndMuscleGroupNamesViewModel {
    @JsonProperty("gid")
    private String gid;
    @JsonProperty("creationDate")
    private long creationDate;
    @JsonProperty("title")
    private String title;
    @JsonProperty("muscleGroups")
    private List<MuscleGroupNameViewModel> muscleGroups;

    public WorkoutAndMuscleGroupNamesViewModel(String gid, long creationDate, String title, List<MuscleGroupNameViewModel> muscleGroups) {
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

    public List<MuscleGroupNameViewModel> getMuscleGroups() {
        return muscleGroups;
    }

    @Override
    public String toString() {
        return String.format("WorkoutViewModel{gid=%s, creationDate=%d, title='%s', muscleGroups=%s}", gid, creationDate, title, muscleGroups);
    }


}
