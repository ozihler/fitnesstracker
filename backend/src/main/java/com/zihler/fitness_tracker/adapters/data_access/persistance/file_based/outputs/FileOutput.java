package com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.outputs;

import com.zihler.fitness_tracker.adapters.data_access.persistance.file_based.JsonReadWritableFile;

public interface FileOutput {
    JsonReadWritableFile data();
}
