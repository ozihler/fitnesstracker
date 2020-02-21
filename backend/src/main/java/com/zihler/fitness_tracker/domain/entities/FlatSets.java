package com.zihler.fitness_tracker.domain.entities;

import com.zihler.fitness_tracker.domain.values.ExerciseName;
import com.zihler.fitness_tracker.domain.values.MuscleGroupName;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

import static java.util.stream.Collectors.toList;

class FlatSets {
    private List<FlatSet> sets = new ArrayList<>();

    public void add(FlatSet set) {
        sets.add(set);
    }

    List<MuscleGroupName> getMuscleGroupNames() {
        return sets.stream().map(FlatSet::getMuscleGroupName).collect(toList());
    }

    List<ExerciseName> getExerciseNames() {
        return sets.stream().map(FlatSet::getExerciseName).collect(toList());
    }
}
