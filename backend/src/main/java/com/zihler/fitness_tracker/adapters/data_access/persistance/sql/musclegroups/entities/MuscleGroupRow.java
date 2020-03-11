package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="muscle_group")
public class MuscleGroupRow {
    @Id
    private String name;
    private boolean isSelectable;
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
