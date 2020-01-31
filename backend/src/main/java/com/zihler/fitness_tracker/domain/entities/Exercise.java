package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.Name;
import com.zihler.fitness_tracker.domain.values.Sets;

public class Exercise {
    private MuscleGroup muscleGroup;
    private Name name;
    private Sets sets;

    public Exercise(MuscleGroup muscleGroup, Name name) {
        this(muscleGroup, name, Sets.empty());
    }

    public Exercise(MuscleGroup muscleGroup, Name name, Sets sets) {
        this.muscleGroup = muscleGroup;
        this.name = name;
        this.sets = sets;
    }

    public void add(Set set) {
        this.sets.add(set);
    }

    public Name getName() {
        return name;
    }

    public Sets getSets() {
        return sets;
    }

    public MuscleGroup getMuscleGroup() {
        return muscleGroup;
    }
}
