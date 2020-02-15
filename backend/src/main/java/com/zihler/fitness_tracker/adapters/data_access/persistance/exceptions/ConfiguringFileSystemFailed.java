package com.zihler.fitness_tracker.adapters.data_access.persistance.exceptions;

public class ConfiguringFileSystemFailed extends RuntimeException {
    private static final long serialVersionUID = -5244069092587260368L;

    public ConfiguringFileSystemFailed(Throwable cause) {
        super(cause);
    }
}
