package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.Namable;

import java.util.ArrayList;
import java.util.List;

public class FullWorkoutViewModel implements Namable {

    @JsonProperty("gid")
    private String workoutId;
    @JsonProperty("creationDate")
    private long creationDate;
    @JsonProperty("title")
    private String title;
    @JsonProperty("muscleGroups")
    private List<FullMuscleGroupViewModel> muscleGroups;

    public FullWorkoutViewModel() {
    }

    public FullWorkoutViewModel(String workoutId, long creationDate, String title, List<FullMuscleGroupViewModel> muscleGroups) {
        this.workoutId = workoutId;
        this.creationDate = creationDate;
        this.title = title;
        this.muscleGroups = muscleGroups;
    }

    public static FullWorkoutViewModel of(String workoutId, String title, long creationDate, List<FullMuscleGroupViewModel> muscleGroups) {
        return new FullWorkoutViewModel(workoutId, creationDate, title, new ArrayList<>(muscleGroups));
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

    public List<FullMuscleGroupViewModel> getMuscleGroups() {
        return muscleGroups;
    }


    @Override
    public String name() {
        return workoutId.replace(" ", "_") + ".json";
    }

    @Override
    public String toString() {
        return "FullWorkoutViewModel{" +
                "gid='" + workoutId + '\'' +
                ", creationDate=" + creationDate +
                ", title='" + title + '\'' +
                ", muscleGroups=" + muscleGroups +
                '}';
    }
}
