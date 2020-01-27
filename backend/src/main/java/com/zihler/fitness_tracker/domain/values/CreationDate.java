package com.zihler.fitness_tracker.domain.values;

import java.time.LocalDateTime;

public class CreationDate {
    private final LocalDateTime creationDate;

    public CreationDate() {
        this.creationDate = LocalDateTime.now();
    }

    public static CreationDate from() {
        return new CreationDate();
    }
}
