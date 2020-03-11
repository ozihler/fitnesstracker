package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.exceptions;

import java.io.IOException;

public class CouldNotDeleteFolderException extends RuntimeException {
    public CouldNotDeleteFolderException(IOException e) {
        super(e);
    }
}
