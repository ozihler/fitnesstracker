import {MuscleGroup} from "./muscle-group";
import {ButtonNode} from "../workout-details-view/button-group/button-node";
import {Exercise} from "./exercise";
import {Set} from "./set"
import {Workout} from "./workout";
import {Type} from "./type";

export class SubtreeFactory {

  static from(muscleGroups: MuscleGroup[]): ButtonNode[] {
    if (!muscleGroups) {
      return [];
    }
    return muscleGroups.map(muscleGroup => {
      return new ButtonNode(muscleGroup.name, SubtreeFactory.formatExercises(muscleGroup.exercises), Type.Muscle_Group);
    });
  }

  private static formatExercises(exercises: Exercise[]) {
    if (!exercises) {
      return [];
    }
    return exercises.map(e => new ButtonNode(e.name, SubtreeFactory.formatSets(e.name, e.sets), Type.Exercise));
  }

  private static formatSets(exerciseName: string, sets: Set[]) {
    if (!exerciseName || !sets) {
      return [];
    }
    return sets
      .map((s, index) => {
        return new ButtonNode(`${s.repetitions}[reps]|${s.weight}[kg]|${s.waitingTime}[s]`, [], Type.Set);
      });
  }

  static fromWorkout(workout: Workout) {
    return new ButtonNode(workout.title, SubtreeFactory.from(workout.muscleGroups), Type.Workout);
  }
}
