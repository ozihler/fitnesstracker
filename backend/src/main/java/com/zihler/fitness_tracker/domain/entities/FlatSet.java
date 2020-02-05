package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WaitingTime;
import com.zihler.fitness_tracker.domain.values.*;

class FlatSet implements Comparable<FlatSet> {
    private InsertionOrder insertionOrder;
    private WorkoutId workoutId;
    private MuscleGroupName muscleGroupName;
    private ExerciseName exerciseName;
    private Weight weight;
    private Repetitions repetitions;
    private WaitingTime waitingTime;

    private FlatSet(InsertionOrder insertionOrder, WorkoutId workoutId, MuscleGroupName muscleGroupName, ExerciseName exerciseName, Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        this.insertionOrder = insertionOrder;
        this.workoutId = workoutId;
        this.muscleGroupName = muscleGroupName;
        this.exerciseName = exerciseName;
        this.weight = weight;
        this.repetitions = repetitions;
        this.waitingTime = waitingTime;
    }

    public static FlatSet of(WorkoutId workoutId, MuscleGroupName muscleGroupName, ExerciseName exerciseName, Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        return new FlatSet(InsertionOrder.create(), workoutId, muscleGroupName, exerciseName, weight, repetitions, waitingTime);
    }

    @Override
    public int compareTo(FlatSet flatSet) {
        return insertionOrder.compareTo(flatSet.insertionOrder);
    }

    public WorkoutId getWorkoutId() {
        return workoutId;
    }

    public MuscleGroupName getMuscleGroupName() {
        return muscleGroupName;
    }

    public ExerciseName getExerciseName() {
        return exerciseName;
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
