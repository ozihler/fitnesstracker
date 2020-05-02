package com.zihler.fitness_tracker.domain.values;

import com.zihler.fitness_tracker.domain.entities.Set;

import java.util.Objects;
import java.util.regex.Pattern;

public class Exercise {
    private Name name;
    private Sets sets;
    private Multiplier multiplier;
    private boolean isSelectable = true;

    private Exercise(Name name, Sets sets, Multiplier multiplier) {
        this.name = name;
        this.sets = sets;
        this.multiplier = multiplier;
    }

    public static Exercise of(String name) {
        name = name.trim();
        // note: a single wielding exercise can be either dumbbell or barbell,
        // whereas it gets pretty adventurous to wield two barbells at the same time ;)
        // So double wielding are >>always<< dumbbell for now.
        if (isSingleWieldingExercise(name)) {
            return of(Name.of(name), Sets.empty());
        } else {
            return dualWieldingDumbbellExercise(name);
        }
    }

    private static boolean isSingleWieldingExercise(String inputExerciseName) {
        return !Pattern.compile("^2#.+")
                .matcher(inputExerciseName)
                .find();
    }

    private static Exercise dualWieldingDumbbellExercise(String inputExerciseName) {
        String[] nameParts = inputExerciseName.split("#");
        Multiplier multiplier = Multiplier.of(nameParts[0]);
        Name exerciseName = Name.of(nameParts[1]);
        return new Exercise(exerciseName, Sets.empty(), multiplier);
    }

    public static Exercise of(Name name) {
        return of(name.toString());
    }

    public static Exercise of(Name name, Sets sets) {
        return new Exercise(name, sets, Multiplier.defaultValue());
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

    public Multiplier getMultiplier() {
        return multiplier;
    }

    public void addAll(Sets sets) {
        this.sets = sets;
    }

    public void setSelectable(boolean isSelectable) {
        this.isSelectable = isSelectable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Exercise exercise = (Exercise) o;
        return Objects.equals(name, exercise.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public boolean isSelectable() {
        return isSelectable;
    }


}
