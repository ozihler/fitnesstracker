package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.exceptions.InvalidTitleException;
import org.apache.logging.log4j.util.Strings;

import java.util.Objects;

public class WorkoutTitle {
    private String title;

    private WorkoutTitle(String title) {
        if (Strings.isBlank(title)) {
            throw new InvalidTitleException("Title cannot be blank");
        }
        this.title = title;
    }

    public static WorkoutTitle of(String title) {
        return new WorkoutTitle(title);
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkoutTitle that = (WorkoutTitle) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
