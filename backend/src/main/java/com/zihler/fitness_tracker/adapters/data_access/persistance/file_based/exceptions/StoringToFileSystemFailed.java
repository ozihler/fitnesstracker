package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions;

public class StoringToFileSystemFailed extends RuntimeException {
    public StoringToFileSystemFailed(Throwable cause) {
        super(cause);
    }
}
