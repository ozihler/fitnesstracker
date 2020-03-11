package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.musclegroups.entities;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
public class ExerciseRow {
    @Id
    private String name;
    private boolean isSelectable;

    @ManyToOne()
    @JoinColumn(name="exercise", nullable = false)
    private MuscleGroupRow muscleGroup;

    public ExerciseRow() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMuscleGroup(MuscleGroupRow muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getName() {
        return name;
    }

    public MuscleGroupRow getMuscleGroup() {
        return muscleGroup;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }
}
