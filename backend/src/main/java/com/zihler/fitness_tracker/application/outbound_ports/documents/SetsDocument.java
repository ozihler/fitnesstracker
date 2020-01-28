package com.zihler.fitness_tracker.application.outbound_ports.documents;

import java.util.List;

public class SetsDocument {

    private List<SetDocument> sets;

    private SetsDocument(List<SetDocument> sets) {
        this.sets = sets;
    }

    public static SetsDocument of(List<SetDocument> sets) {
        return new SetsDocument(sets);
    }

    public List<SetDocument> getSets() {
        return this.sets;
    }
}
