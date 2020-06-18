package com.zihler.fitness_tracker.adapters.data_access.persistance.data_loading;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetJson {
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

    public SetJson() {
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    public int getNumberOfRepetitions() {
        return numberOfRepetitions;
    }

    public void setNumberOfRepetitions(int numberOfRepetitions) {
        this.numberOfRepetitions = numberOfRepetitions;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public String getWaitingTimeUnit() {
        return waitingTimeUnit;
    }

    public void setWaitingTimeUnit(String waitingTimeUnit) {
        this.waitingTimeUnit = waitingTimeUnit;
    }

    @Override
    public String toString() {
        return "SetJson{" +
                "weight=" + weight +
                ", weightUnit='" + weightUnit + '\'' +
                ", numberOfRepetitions=" + numberOfRepetitions +
                ", waitingTime=" + waitingTime +
                ", waitingTimeUnit='" + waitingTimeUnit + '\'' +
                '}';
    }
}
