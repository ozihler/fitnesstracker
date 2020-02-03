import {Workout} from "./workout";
import {MuscleGroup} from "./muscle-group";
import {Exercise} from "./exercise";
import {Set} from "./set";

export interface WorkoutTreeVisitor {
  visitWorkout(workout: Workout);

  visitMuscleGroup(muscleGroup: MuscleGroup);

  visitExercise(exercise: Exercise);

  visitSet(set: Set);
}

