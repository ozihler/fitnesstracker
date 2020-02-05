package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.Repetitions;
import com.zihler.fitness_tracker.domain.values.Weight;
import com.zihler.fitness_tracker.domain.values.WorkoutId;

public class SetDocument {
    private WorkoutId workoutId;
    private Weight weight;
    private Repetitions repetitions;
    private WaitingTime waitingTime;

    public SetDocument(WorkoutId workoutId, Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        this.workoutId = workoutId;
        this.weight = weight;
        this.repetitions = repetitions;
        this.waitingTime = waitingTime;
    }

    public static SetDocument of(WorkoutId workoutId, Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        return new SetDocument(workoutId, weight, repetitions, waitingTime);
    }

    public static SetDocument of(Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        return of(null, weight, repetitions, waitingTime);
    }

    public static SetDocument of(Set set) {
        return of(set.getWorkoutId(), set.getWeight(), set.getRepetitions(), set.getWaitingTime());
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
