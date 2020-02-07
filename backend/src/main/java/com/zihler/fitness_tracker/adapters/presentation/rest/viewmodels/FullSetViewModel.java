package com.zihler.fitness_tracker.adapters.presentation.rest.viewmodels;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zihler.fitness_tracker.domain.values.UnitOfMeasurement;
import com.zihler.fitness_tracker.domain.values.UnitOfTime;

public class FullSetViewModel {
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

    public FullSetViewModel(float weight, String weightUnit, int numberOfRepetitions, int waitingTime, String waitingTimeUnit) {
        this.weight = weight;
        this.weightUnit = weightUnit;
        this.numberOfRepetitions = numberOfRepetitions;
        this.waitingTime = waitingTime;
        this.waitingTimeUnit = waitingTimeUnit;
    }

    public static FullSetViewModel of(float weight, int numberOfRepetitions, int waitingTime) {
        return new FullSetViewModel(
                weight, UnitOfMeasurement.KILOGRAMM.shortname(), numberOfRepetitions, waitingTime, UnitOfTime.SECONDS.shortname()
        );
    }

    public FullSetViewModel() {
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
                ", weight=" + weight +
                ", weightUnit='" + weightUnit + '\'' +
                ", numberOfRepetitions=" + numberOfRepetitions +
                ", waitingTime=" + waitingTime +
                ", waitingTimeUnit='" + waitingTimeUnit + '\'' +
                '}';
    }
}
