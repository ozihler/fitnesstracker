package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WaitingTime;
import com.zihler.fitness_tracker.domain.values.Repetitions;
import com.zihler.fitness_tracker.domain.values.Weight;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class Set {
    private WorkoutId workoutId;
    private Weight weight;
    private Repetitions repetitions;
    private WaitingTime waitingTime;

    private Set(WorkoutId workoutId, Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        this.workoutId = workoutId;
        this.weight = weight;
        this.repetitions = repetitions;
        this.waitingTime = waitingTime;
    }

    public static Set newSet(Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        return new Set(WorkoutId.random(), weight, repetitions, waitingTime);
    }

    public static Set updateSet(WorkoutId workoutId, Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        return new Set(workoutId, weight, repetitions, waitingTime);
    }

    public WorkoutId getWorkoutId() {
        return workoutId;
    }

    public Weight getWeight() {
        return weight;
    }

    public Repetitions getRepetitions() {
        return repetitions;
    }

    public WaitingTime getWaitingTime() {
        return waitingTime;
    }
}
