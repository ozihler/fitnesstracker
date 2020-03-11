package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions;

public class LoadingDataFromFileSystemFailed extends RuntimeException {
    private static final long serialVersionUID = -5244069092587260368L;

    public LoadingDataFromFileSystemFailed(Throwable cause) {
        super(cause);
    }
}
