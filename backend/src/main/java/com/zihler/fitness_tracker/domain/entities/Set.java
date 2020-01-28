package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.application.outbound_ports.documents.WaitingTime;
import com.zihler.fitness_tracker.domain.values.GID;
import com.zihler.fitness_tracker.domain.values.Repetitions;
import com.zihler.fitness_tracker.domain.values.Weight;

public class Set {
    private GID gid;
    private Weight weight;
    private Repetitions repetitions;
    private WaitingTime waitingTime;

    private Set(GID gid, Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        this.gid = gid;
        this.weight = weight;
        this.repetitions = repetitions;
        this.waitingTime = waitingTime;
    }

    public static Set newSet(Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        return new Set(GID.random(), weight, repetitions, waitingTime);
    }

    public static Set updateSet(GID gid, Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        return new Set(gid, weight, repetitions, waitingTime);
    }

    public GID getGid() {
        return gid;
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
