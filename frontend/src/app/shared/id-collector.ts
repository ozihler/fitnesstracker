import {WorkoutTreeVisitor} from "./workout-tree-visitor";
import {Exercise} from "./exercise";
import {MuscleGroup} from "./muscle-group";
import {Workout} from "./workout";
import {Set} from "./set";

export class IdCollector implements WorkoutTreeVisitor {
  private _ids: string[] = [];


  get ids(): string[] {
    return this._ids;
  }

  visitExercise(exercise: Exercise) {
    this.ids.push(exercise.name);
  }

  visitMuscleGroup(muscleGroup: MuscleGroup) {
    this.ids.push(muscleGroup.name);
  }

  visitSet(set: Set) {
    this.ids.push(set.name.toString());
  }

  visitWorkout(workout: Workout) {
    this.ids.push(workout.title.toString());
  }

}
