package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts;

import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.JsonReadWritableFile;
import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.musclegroups.MuscleGroupFile;

import java.util.List;

public class WorkoutFile implements JsonReadWritableFile {
    private String workoutId;
    private String title;
    private long creationDate;
    private List<MuscleGroupFile> muscleGroups;

    private WorkoutFile(String workoutId, String title, long creationDate, List<MuscleGroupFile> muscleGroups) {
        this.workoutId = workoutId;
        this.title = title;
        this.creationDate = creationDate;
        this.muscleGroups = muscleGroups;
    }

    public static WorkoutFile of(String workoutId, String title, long creationDate, List<MuscleGroupFile> muscleGroups) {
        return new WorkoutFile(workoutId, title, creationDate, muscleGroups);
    }

    public String getWorkoutId() {
        return workoutId;
    }

    public String getTitle() {
        return title;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public List<MuscleGroupFile> getMuscleGroups() {
        return muscleGroups;
    }

    @Override
    public String fileName() {
        return workoutId.replace(" ", "_") + ".json";
    }
}
