package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.rows;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "set")
public class SetRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "weight", nullable = false)
    private float weight;

    @Column(name = "weight_unit", nullable = false)
    private String weightUnit;

    @Column(name = "repetitions", nullable = false)
    private int repetitions;

    @Column(name = "waiting_time")
    private int waitingTime;

    @Column(name = "waiting_time_unit")
    private String waitingTimeUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseRow exercise;

    public SetRow() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public String getWaitingTimeUnit() {
        return waitingTimeUnit;
    }

    public void setWaitingTimeUnit(String waitingTimeUnit) {
        this.waitingTimeUnit = waitingTimeUnit;
    }

    public ExerciseRow getExercise() {
        return exercise;
    }

    public void setExercise(ExerciseRow exercise) {
        this.exercise = exercise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetRow setRow = (SetRow) o;
        return id == setRow.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
