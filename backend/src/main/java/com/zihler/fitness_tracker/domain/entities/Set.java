package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.Repetitions;
import com.zihler.fitness_tracker.domain.values.WaitingTime;
import com.zihler.fitness_tracker.domain.values.Weight;

import java.util.Objects;

public class Set {
    private final Weight weight;
    private final Repetitions repetitions;
    private final WaitingTime waitingTime;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set set = (Set) o;
        return Objects.equals(weight, set.weight) &&
                Objects.equals(repetitions, set.repetitions) &&
                Objects.equals(waitingTime, set.waitingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, repetitions, waitingTime);
    }
}
