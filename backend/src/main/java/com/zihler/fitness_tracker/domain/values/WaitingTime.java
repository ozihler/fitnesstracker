package com.zihler.fitness_tracker.domain.values;

public class WaitingTime {
    private int waitingTime;
    private UnitOfTime unitOfTime;

    private WaitingTime(int waitingTime, UnitOfTime unitOfTime) {
        this.waitingTime = waitingTime;
        this.unitOfTime = unitOfTime;
    }

    public static WaitingTime of(int waitingTime, UnitOfTime unitOfTime) {
        return new WaitingTime(waitingTime, unitOfTime);
    }

    public static WaitingTime empty() {
        return of(-1, UnitOfTime.SECONDS);
    }

    public int value() {
        return waitingTime;
    }

    public UnitOfTime unitOfTime() {
        return unitOfTime;
    }
}
