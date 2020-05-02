package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.WaitingTime;
import com.zihler.fitness_tracker.domain.values.Repetitions;
import com.zihler.fitness_tracker.domain.values.Weight;

public class Set {
    private Weight weight;
    private Repetitions repetitions;
    private WaitingTime waitingTime;

    public Set(Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        this.weight = weight;
        this.repetitions = repetitions;
        this.waitingTime = waitingTime;
    }

    public static Set withValues(Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        return new Set(weight, repetitions, waitingTime);
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
