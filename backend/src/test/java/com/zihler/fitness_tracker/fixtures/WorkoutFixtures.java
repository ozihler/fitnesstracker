package com.zihler.fitness_tracker.fixtures;

import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.*;

import java.time.LocalDate;

public class WorkoutFixtures {
    public final static Workout FULL_WORKOUT = Workout.of(
            WorkoutId.of("1-1"),
            CreationDate.of(LocalDate.of(2019, 12, 10)),
            WorkoutTitle.of("W Chest Triceps"),
            MuscleGroups.muscleGroups(
                    new MuscleGroup(
                            Name.of("Chest"),
                            Exercises.of(
                                    new Exercise(
                                            Name.of("Bench Press"),
                                            Sets.of(
                                                    new Set(
                                                            Weight.of(55, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(12),
                                                            WaitingTime.of(45, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(60, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(10),
                                                            WaitingTime.of(60, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(55, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(11),
                                                            WaitingTime.of(75, UnitOfTime.SECONDS)
                                                    )
                                            ),
                                            Multiplier.ofOne()
                                    ),
                                    new Exercise(
                                            Name.of("Dumbbell Bench Press"),
                                            Sets.of(
                                                    new Set(
                                                            Weight.of(27.5f, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(12),
                                                            WaitingTime.of(45, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(30, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(11),
                                                            WaitingTime.of(60, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(32.5f, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(10),
                                                            WaitingTime.of(75, UnitOfTime.SECONDS)
                                                    )
                                            ),
                                            Multiplier.ofTwo()
                                    ),
                                    new Exercise(
                                            Name.of("Dumbbell Flies"),
                                            Sets.of(
                                                    new Set(
                                                            Weight.of(17.5f, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(12),
                                                            WaitingTime.of(45, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(20, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(11),
                                                            WaitingTime.of(60, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(22.5f, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(10),
                                                            WaitingTime.of(75, UnitOfTime.SECONDS)
                                                    )
                                            ),
                                            Multiplier.ofTwo()
                                    )

                            )
                    ),
                    new MuscleGroup(
                            Name.of("Triceps"),
                            Exercises.of(
                                    new Exercise(
                                            Name.of("Dips"),
                                            Sets.of(
                                                    new Set(
                                                            Weight.of(45, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(12),
                                                            WaitingTime.of(45, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(40, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(11),
                                                            WaitingTime.of(60, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(35, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(10),
                                                            WaitingTime.of(75, UnitOfTime.SECONDS)
                                                    )
                                            ),
                                            Multiplier.ofOne()
                                    ),
                                    new Exercise(
                                            Name.of("Overhead Dumbbell Pull"),
                                            Sets.of(
                                                    new Set(
                                                            Weight.of(30, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(12),
                                                            WaitingTime.of(45, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(27.5f, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(11),
                                                            WaitingTime.of(60, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(25, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(10),
                                                            WaitingTime.of(75, UnitOfTime.SECONDS)
                                                    )
                                            ),
                                            Multiplier.ofOne()
                                    ),
                                    new Exercise(
                                            Name.of("Kickbacks"),
                                            Sets.of(
                                                    new Set(
                                                            Weight.of(12.5f, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(12),
                                                            WaitingTime.of(45, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(10, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(11),
                                                            WaitingTime.of(60, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(7.5f, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(10),
                                                            WaitingTime.of(75, UnitOfTime.SECONDS)
                                                    )
                                            ),
                                            Multiplier.ofTwo()
                                    )
                            )
                    )
            )
    );
}
