package com.zihler.fitness_tracker.application.outbound_ports.documents;

import com.zihler.fitness_tracker.domain.values.UnitOfTime;

public class WaitingTime {
    private int waitingTime;
    private UnitOfTime unitOfTime;

    private WaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
        this.unitOfTime = UnitOfTime.SECONDS;
    }

    public static WaitingTime of(int waitingTime) {
        return new WaitingTime(waitingTime);
    }

    public static WaitingTime empty() {
        return new WaitingTime(-1);
    }

    public int value() {
        return waitingTime;
    }

    public UnitOfTime unitOfTime() {
        return unitOfTime;
    }
}
