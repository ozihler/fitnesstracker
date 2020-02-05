package com.zihler.fitness_tracker.domain.values;

import java.time.Clock;
import java.util.Objects;

public class InsertionOrder implements Comparable<InsertionOrder> {

    private long insertionOrder;

    private InsertionOrder(long insertionOrder) {
        this.insertionOrder = insertionOrder;
    }

    public static InsertionOrder create() {
        return new InsertionOrder(Clock.systemDefaultZone().millis());
    }

    @Override
    public int compareTo(InsertionOrder insertionOrder) {
        return Long.compare(this.insertionOrder, insertionOrder.insertionOrder);
    }

    @Override
    public String toString() {
        return String.valueOf(insertionOrder);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InsertionOrder that = (InsertionOrder) o;
        return insertionOrder == that.insertionOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(insertionOrder);
    }
}
