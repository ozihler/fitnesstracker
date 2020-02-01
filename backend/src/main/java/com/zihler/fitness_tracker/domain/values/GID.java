package com.zihler.fitness_tracker.domain.values;

import java.util.Random;

public class GID {
    private long gid;

    public GID(long gid) {
        this.gid = gid;
    }

    public static GID random() {
        return new GID(new Random().nextLong());
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
}
