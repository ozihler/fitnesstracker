package com.zihler.fitness_tracker.adapters.data_access.persistance.sql.exceptions;

public class ExerciseNotFoundInJpaRepositoryException extends RuntimeException {
    public ExerciseNotFoundInJpaRepositoryException(String message) {
        super(message);
    }
}
