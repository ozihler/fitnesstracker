package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.rowtypes;

import com.zihler.fitness_tracker.adapters.data_access.persistance.sql.workouts.rowtypes.WorkoutRow;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="muscle_group")
public class MuscleGroupRow {
    @Id
    private String name;
    private boolean isSelectable;

    @ManyToMany(mappedBy = "muscleGroups")
    private List<WorkoutRow> workouts;

    @OneToMany(mappedBy="muscleGroup")
    private List<ExerciseRow> exercises;


    public MuscleGroupRow() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }

    public List<ExerciseRow> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseRow> exercises) {
        this.exercises = exercises;
    }
}
