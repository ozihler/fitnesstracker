package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.presentation.rest.presenters.musclegroups.MuscleGroupViewModel;

import java.util.Set;

public class WorkoutViewModel {
    @JsonProperty("gid")
    private long gid;
    @JsonProperty("creationDate")
    private long creationDate;
    @JsonProperty("title")
    private String title;
    @JsonProperty("muscleGroups")
    private Set<MuscleGroupViewModel> muscleGroups;

    public WorkoutViewModel(long gid, long creationDate, String title, Set<MuscleGroupViewModel> muscleGroups) {
        this.gid = gid;
        this.creationDate = creationDate;
        this.title = title;
        this.muscleGroups = muscleGroups;
    }

    public WorkoutViewModel() {
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

    public Set<MuscleGroupViewModel> getMuscleGroups() {
        return muscleGroups;
    }

    @Override
    public String toString() {
        return String.format("WorkoutViewModel{gid=%d, creationDate=%d, title='%s', muscleGroups=%s}", gid, creationDate, title, muscleGroups);
    }
}
