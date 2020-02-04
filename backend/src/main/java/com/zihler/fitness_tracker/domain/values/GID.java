package com.zihler.fitness_tracker.domain.values;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class GID {
    private long gid;

    public GID(long gid) {
        this.gid = gid;
    }

    public static GID random() {
        return new GID(ThreadLocalRandom.current().nextLong(0L, Long.MAX_VALUE));
    }

    public static GID of(long gid) {
        return new GID(gid);
    }

    public long toLong() {
        return gid;
    }

    @Override
    public String toString() {
        return String.valueOf(gid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GID gid1 = (GID) o;
        return gid == gid1.gid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gid);
    }
}
