package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.Namable;

import java.util.ArrayList;
import java.util.List;

public class WorkoutViewModel implements Namable {

    @JsonProperty("workoutId")
    private String workoutId;
    @JsonProperty("creationDate")
    private long creationDate;
    @JsonProperty("title")
    private String title;
    @JsonProperty("muscleGroups")
    private List<MuscleGroupViewModel> muscleGroups;

    public WorkoutViewModel() {
    }

    public WorkoutViewModel(String workoutId, long creationDate, String title) {
        this(workoutId, creationDate, title, new ArrayList<>());
    }

    public WorkoutViewModel(String workoutId, long creationDate, String title, List<MuscleGroupViewModel> muscleGroups) {
        this.workoutId = workoutId;
        this.creationDate = creationDate;
        this.title = title;
        this.muscleGroups = muscleGroups;
    }

    public static WorkoutViewModel of(String workoutId, String title, long creationDate, List<MuscleGroupViewModel> muscleGroups) {
        return new WorkoutViewModel(workoutId, creationDate, title, new ArrayList<>(muscleGroups));
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
    public String name() {
        return workoutId.replace(" ", "_") + ".json";
    }

    @Override
    public String toString() {
        return "FullWorkoutViewModel{" +
                "workoutId='" + workoutId + '\'' +
                ", creationDate=" + creationDate +
                ", title='" + title + '\'' +
                ", muscleGroups=" + muscleGroups +
                '}';
    }
}
