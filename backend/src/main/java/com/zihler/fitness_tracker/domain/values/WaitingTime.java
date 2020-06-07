package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.exceptions.IllegalWaitingTimeException;

import java.util.Objects;

public class WaitingTime {
    private final int waitingTime;
    private final UnitOfTime unitOfTime;

    private WaitingTime(int waitingTime, UnitOfTime unitOfTime) {
        if (waitingTime < 0) {
            throw new IllegalWaitingTimeException("Waiting time cannot be below 0. Got " + waitingTime);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaitingTime that = (WaitingTime) o;
        return waitingTime == that.waitingTime &&
                unitOfTime == that.unitOfTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(waitingTime, unitOfTime);
    }

    @Override
    public String toString() {
        return "WaitingTime{" +
                "waitingTime=" + waitingTime +
                ", unitOfTime=" + unitOfTime +
                '}';
    }
}
