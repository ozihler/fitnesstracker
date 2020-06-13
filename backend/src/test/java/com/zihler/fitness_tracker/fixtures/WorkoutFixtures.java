package com.zihler.fitness_tracker.fixtures;

import com.zihler.fitness_tracker.domain.entities.Exercise;
import com.zihler.fitness_tracker.domain.entities.MuscleGroup;
import com.zihler.fitness_tracker.domain.entities.Set;
import com.zihler.fitness_tracker.domain.entities.Workout;
import com.zihler.fitness_tracker.domain.values.*;

import java.time.LocalDate;

public class WorkoutFixtures {
    public final static Workout FULL_CHEST_TRICEPS_WORKOUT = new Workout(WorkoutId.of("1-1"),
            CreationDate.of(LocalDate.of(2019, 1, 10)),
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
                                            Multiplier.ONE
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
                                            Multiplier.TWO
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
                                            Multiplier.TWO
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
                                            Multiplier.ONE
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
                                            Multiplier.ONE
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
                                            Multiplier.TWO
                                    )
                            )
                    )
            ));

    public final static Workout FULL_BACK_BICEPS_WORKOUT = new Workout(WorkoutId.of("1-3"),
            CreationDate.of(LocalDate.of(2019, 1, 14)),
            MuscleGroups.muscleGroups(
                    new MuscleGroup(
                            Name.of("Back"),
                            Exercises.of(
                                    new Exercise(
                                            Name.of("Dead Lifts"),
                                            Sets.of(
                                                    new Set(
                                                            Weight.of(65, UnitOfMeasurement.KILOGRAMM),
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
                                            Multiplier.ONE
                                    ),
                                    new Exercise(
                                            Name.of("Barbell Row Standing"),
                                            Sets.of(
                                                    new Set(
                                                            Weight.of(40, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(12),
                                                            WaitingTime.of(45, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(45, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(11),
                                                            WaitingTime.of(60, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(42.5f, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(10),
                                                            WaitingTime.of(75, UnitOfTime.SECONDS)
                                                    )
                                            ),
                                            Multiplier.ONE
                                    ),
                                    new Exercise(
                                            Name.of("Dumbbell Bench Rowing"),
                                            Sets.of(
                                                    new Set(
                                                            Weight.of(27.5f, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(12),
                                                            WaitingTime.of(45, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(32.5f, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(11),
                                                            WaitingTime.of(60, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(30, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(10),
                                                            WaitingTime.of(75, UnitOfTime.SECONDS)
                                                    )
                                            ),
                                            Multiplier.TWO
                                    )

                            )
                    ),
                    new MuscleGroup(
                            Name.of("Biceps"),
                            Exercises.of(
                                    new Exercise(
                                            Name.of("Dumbbell Curls"),
                                            Sets.of(
                                                    new Set(
                                                            Weight.of(17.5f, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(12),
                                                            WaitingTime.of(45, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(15, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(11),
                                                            WaitingTime.of(60, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(16, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(10),
                                                            WaitingTime.of(75, UnitOfTime.SECONDS)
                                                    )
                                            ),
                                            Multiplier.TWO
                                    ),
                                    new Exercise(
                                            Name.of("Barbell Curls"),
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
                                            Multiplier.ONE
                                    ),
                                    new Exercise(
                                            Name.of("Dumbbell Hammer Curls"),
                                            Sets.of(
                                                    new Set(
                                                            Weight.of(15, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(12),
                                                            WaitingTime.of(45, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(16, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(11),
                                                            WaitingTime.of(60, UnitOfTime.SECONDS)
                                                    ),
                                                    new Set(
                                                            Weight.of(14, UnitOfMeasurement.KILOGRAMM),
                                                            Repetitions.of(10),
                                                            WaitingTime.of(75, UnitOfTime.SECONDS)
                                                    )
                                            ),
                                            Multiplier.TWO
                                    )
                            )
                    )
            ));

}
