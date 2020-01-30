import {MuscleGroupRaw} from "./muscle-group-raw";
import {MuscleGroup} from "./muscle-group";
import {ExerciseFactory} from "./exercise.factory";

export class MuscleGroupFactory {

  static from(muscleGroupRaw: MuscleGroupRaw): MuscleGroup {
    return new MuscleGroup(undefined, muscleGroupRaw.name, ExerciseFactory.fromMultiple(muscleGroupRaw.exercises));
  }

  static fromMultiple(muscleGroupsRaw: MuscleGroupRaw[]): MuscleGroup[] {
    if (!muscleGroupsRaw) {
      return [];
    }
    return muscleGroupsRaw.map(muscleGroupRaw => MuscleGroupFactory.from(muscleGroupRaw));
  }
}
