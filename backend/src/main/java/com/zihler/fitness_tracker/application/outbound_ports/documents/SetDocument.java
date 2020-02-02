package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.values.GID;
import com.zihler.fitness_tracker.domain.values.Repetitions;
import com.zihler.fitness_tracker.domain.values.Weight;

public class SetDocument {
    private GID gid;
    private Weight weight;
    private Repetitions repetitions;
    private WaitingTime waitingTime;


    private SetDocument(GID gid, Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        this.gid = gid;
        this.weight = weight;
        this.repetitions = repetitions;
        this.waitingTime = waitingTime;
    }

    public static SetDocument of(GID gid, Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        return new SetDocument(gid, weight, repetitions, waitingTime);
    }

    public static SetDocument of(Weight weight, Repetitions repetitions, WaitingTime waitingTime) {
        return new SetDocument(null, weight, repetitions, waitingTime);
    }

    public static SetDocument of(Set set) {
        return of(set.getGid(), set.getWeight(), set.getRepetitions(), set.getWaitingTime());
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
