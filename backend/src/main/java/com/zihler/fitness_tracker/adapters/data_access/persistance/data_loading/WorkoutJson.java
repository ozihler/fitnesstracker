package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WorkoutJson {
    @JsonProperty("workoutId")
    private String workoutId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("creationDate")
    private long creationDate;
    @JsonProperty("muscleGroups")
    private List<MuscleGroupJson> muscleGroups;

    public WorkoutJson() {
    }

    public String getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(String workoutId) {
        this.workoutId = workoutId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public List<MuscleGroupJson> getMuscleGroups() {
        return muscleGroups;
    }

    public void setMuscleGroups(List<MuscleGroupJson> muscleGroups) {
        this.muscleGroups = muscleGroups;
    }

    @Override
    public String toString() {
        return "WorkoutJson{" +
                "workoutId='" + workoutId + '\'' +
                ", title='" + title + '\'' +
                ", creationDate=" + creationDate +
                ", muscleGroups=" + muscleGroups +
                '}';
    }
}
