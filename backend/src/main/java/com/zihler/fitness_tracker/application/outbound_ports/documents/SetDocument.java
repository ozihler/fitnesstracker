package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.adapters.presentation.rest.controllers.sets.exceptions.IllegalSetDetailsException;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.Repetitions;
import com.zihler.fitness_tracker.domain.values.WaitingTime;
import com.zihler.fitness_tracker.domain.values.Weight;

public class SetDocument {
    private final Weight weight;
    private final Repetitions repetitions;
    private final WaitingTime waitingTime;

    public SetDocument(Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        if (weight == null || repetitions == null || weight.value() <= 0 || repetitions.number() <= 0) {
            throw new IllegalSetDetailsException("weight and number of repetitions must be larger 0!");
        }
        this.weight = weight;
        this.repetitions = repetitions;
        this.waitingTime = waitingTime;
    }

    public static SetDocument of(Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        return new SetDocument(weight, repetitions, waitingTime);
    }
    
    public static SetDocument of(Set set) {
        return of(set.getWeight(), set.getRepetitions(), set.getWaitingTime());
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
