package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private WorkoutRow workout;

    @OneToMany(mappedBy = "muscleGroup")
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
        return new ArrayList<>();
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }

    public WorkoutRow getWorkout() {
        return workout;
    }

    public void setWorkout(WorkoutRow workout) {
        this.workout = workout;
    }

    public void setExercises(List<ExerciseRow> exercises) {
        this.exercises = exercises;
        exercises.forEach(e -> e.setMuscleGroup(this));
    }
}
