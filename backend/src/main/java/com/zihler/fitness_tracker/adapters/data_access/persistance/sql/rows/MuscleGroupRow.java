package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "muscle_group")
public class MuscleGroupRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "is_selectable")
    private boolean isSelectable;

    @ManyToMany(mappedBy = "muscleGroups")
    private List<WorkoutRow> workouts;

    @OneToMany(mappedBy = "muscleGroup",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ExerciseRow> exercises;

    public MuscleGroupRow() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseRow> getExercises() {
        return exercises;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }

    public void setExercises(List<ExerciseRow> exercises) {
        this.exercises = exercises;
        exercises.forEach(e -> e.setMuscleGroup(this));
    }

    public List<WorkoutRow> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<WorkoutRow> workouts) {
        this.workouts = workouts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MuscleGroupRow that = (MuscleGroupRow) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
