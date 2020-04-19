import {MuscleGroupRaw} from "./muscle-group-raw";
import {MuscleGroup} from "./muscle-group";
import {ExerciseFactory} from "./exercise.factory";

export class MuscleGroupFactory {

  static from(muscleGroupRaw: MuscleGroupRaw): MuscleGroup {
    let exercises = ExerciseFactory.fromMultiple(muscleGroupRaw.exercises);

    return new MuscleGroup(undefined, muscleGroupRaw.name, exercises);
  }

  static fromMultiple(muscleGroupsRaw: MuscleGroupRaw[]): MuscleGroup[] {
    if (!muscleGroupsRaw) {
      return [];
    }
    return muscleGroupsRaw.map(muscleGroupRaw => MuscleGroupFactory.from(muscleGroupRaw));
  }
}
