package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.domain.values.UnitOfMeasurement;
import com.zihler.fitness_tracker.domain.values.UnitOfTime;

public class SetViewModel {
    @JsonProperty("gid")
    private long gid;
    @JsonProperty("weight")
    private float weight;
    @JsonProperty("weightUnit")
    private String weightUnit;
    @JsonProperty("numberOfRepetitions")
    private int numberOfRepetitions;
    @JsonProperty("waitingTime")
    private int waitingTime;
    @JsonProperty("waitingTimeUnit")
    private String waitingTimeUnit;

    public SetViewModel(long gid, float weight, String weightUnit, int numberOfRepetitions, int waitingTime, String waitingTimeUnit) {
        this.gid = gid;
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.numberOfRepetitions = numberOfRepetitions;
        this.waitingTime = waitingTime;
        this.waitingTimeUnit = waitingTimeUnit;
    }

    public static SetViewModel of(long gid, float weight, int numberOfRepetitions, int waitingTime) {
        return new SetViewModel(
                gid, weight, UnitOfMeasurement.KILOGRAMM.shortname(), numberOfRepetitions, waitingTime, UnitOfTime.SECONDS.shortname()
        );
    }

    public SetViewModel() {
    }

    public long getGid() {
        return gid;
    }

    public float getWeight() {
        return weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public String getWaitingTimeUnit() {
        return waitingTimeUnit;
    }

    @Override
    public String toString() {
        return "SetViewModel{" +
                "gid=" + gid +
                ", weight=" + weight +
                ", weightUnit='" + weightUnit + '\'' +
                ", numberOfRepetitions=" + numberOfRepetitions +
                ", waitingTime=" + waitingTime +
                ", waitingTimeUnit='" + waitingTimeUnit + '\'' +
                '}';
    }
}
