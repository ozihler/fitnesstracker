package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkoutInOverviewViewModel {
    @JsonProperty("gid")
    private String gid;
    @JsonProperty("creationDate")
    private long creationDate;
    @JsonProperty("title")
    private String title;

    public WorkoutInOverviewViewModel(String gid, long creationDate, String title) {
        this.gid = gid;
        this.creationDate = creationDate;
        this.title = title;
    }

    public WorkoutInOverviewViewModel() {
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
}
