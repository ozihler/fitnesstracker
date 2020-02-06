package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Set;

public class Exercise {
    private MuscleGroup muscleGroup;
    private Name name;
    private Sets sets;

    public Exercise(MuscleGroup muscleGroup, Name name) {
        this(muscleGroup, name, Sets.empty());
    }

    private Exercise(MuscleGroup muscleGroup, Name name, Sets sets) {
        this.muscleGroup = muscleGroup;
        this.name = name;
        this.sets = sets;
    }

    public void add(Set set) {
        sets.add(set);
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

    public void addAll(Sets sets) {
        this.sets = sets;
    }
}
