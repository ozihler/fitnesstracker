package com.zihler.fitness_tracker.adapters.data_access.persistance.exceptions;

public class StoringToFileSystemFailed extends RuntimeException {
    public StoringToFileSystemFailed(Throwable cause) {
        super(cause);
    }
}
