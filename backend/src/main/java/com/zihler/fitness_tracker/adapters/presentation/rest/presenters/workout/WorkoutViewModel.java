package com.zihler.fitness_tracker.adapters.presentation.rest.presenters.workout;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkoutViewModel {
    @JsonProperty("id")
    private final long id;
    @JsonProperty("creationDate")
    private final long creationDate;
    @JsonProperty("title")
    private final String title;

    public WorkoutViewModel(long id, long creationDate, String title) {
        this.id = id;
        this.creationDate = creationDate;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public String getTitle() {
        return title;
    }
}
