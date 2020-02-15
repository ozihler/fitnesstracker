package com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.Namable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FullWorkoutViewModel implements Namable {

    @JsonProperty("gid")
    private String gid; // todo rename to workoutid
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

    public static FullWorkoutViewModel of(String workoutId, String title, long creationDate, List<FullMuscleGroupViewModel> muscleGroups) {
        return new FullWorkoutViewModel(workoutId, creationDate, title, new HashSet<>(muscleGroups));
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


    @Override
    public String name() {
        return gid.replace(" ", "_") + ".json";
    }
}
