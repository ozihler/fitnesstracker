package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions;

public class MuscleGroupNotFoundException extends RuntimeException {
    public MuscleGroupNotFoundException(String message){
        super(message);
    }
}
