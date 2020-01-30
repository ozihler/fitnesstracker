import {MuscleGroup} from "./muscle-group";
import {ButtonNode} from "../workout-details-view/button-group/button-node";
import {Exercise} from "./exercise";
import {Set} from "./set"
import {Workout} from "./workout";
import {Type} from "./type";

export class SubtreeFactory {

  static fromWorkout(workout: Workout) {
    return new ButtonNode(workout.title, undefined, SubtreeFactory.from(workout.muscleGroups, workout.title), Type.Workout);
  }

  static from(muscleGroups: MuscleGroup[], workout: string): ButtonNode[] {
    if (!muscleGroups) {
      return [];
    }
    return muscleGroups.map(muscleGroup => {
      return new ButtonNode(muscleGroup.name, workout, SubtreeFactory.formatExercises(muscleGroup.exercises, muscleGroup.name), Type.Muscle_Group);
    });
  }

  private static formatExercises(exercises: Exercise[], muscleGroup: string) {
    if (!exercises) {
      return [];
    }
    return exercises.map(e => new ButtonNode(e.name, muscleGroup, SubtreeFactory.formatSets(e.name, e.sets), Type.Exercise));
  }

  private static formatSets(exerciseName: string, sets: Set[]) {
    if (!exerciseName || !sets) {
      return [];
    }
    return sets
      .map((s, index) => {
        return new ButtonNode(`${s.repetitions}[reps]|${s.weight}[kg]|${s.waitingTime}[s]`, exerciseName, undefined, Type.Set);
      });
  }
}
