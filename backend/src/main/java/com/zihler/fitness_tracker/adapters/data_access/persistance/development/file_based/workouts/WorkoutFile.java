package com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.workouts;

import com.zihler.fitness_tracker.adapters.data_access.persistance.development.file_based.JsonReadWritableFile;
import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.workouts.requests.FullMuscleGroupViewModel;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.join;

// todo Replace with FullWorkoutFIle (and use same mappers)
public class WorkoutFile implements JsonReadWritableFile {
    private String workoutId;
    private String title;
    private long creationDate;
    private List<FullMuscleGroupViewModel> muscleGroups;

    public WorkoutFile() {
    }

    private WorkoutFile(String workoutId, String title, long creationDate, List<FullMuscleGroupViewModel> muscleGroups) {
        this.workoutId = workoutId;
        this.title = title;
        this.creationDate = creationDate;
        this.muscleGroups = muscleGroups;
    }

    public static WorkoutFile of(String workoutId, String title, long creationDate, List<FullMuscleGroupViewModel> muscleGroups) {
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

    public List<FullMuscleGroupViewModel> getMuscleGroups() {
        return muscleGroups;
    }

    @Override
    public String fileName() {
        return workoutId.replace(" ", "_") + ".json";
    }

    @Override
    public String toString() {
        return "WorkoutFile{" +
                "workoutId='" + workoutId + '\'' +
                ", title='" + title + '\'' +
                ", creationDate=" + creationDate +
                ", muscleGroups=" + join(muscleGroups, ',') +
                '}';
    }
}
