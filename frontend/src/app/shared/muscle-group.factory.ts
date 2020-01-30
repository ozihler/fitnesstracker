import {MuscleGroupRaw} from "./muscle-group-raw";
import {MuscleGroup} from "./muscle-group";
import {ExerciseFactory} from "./exercise.factory";

export class MuscleGroupFactory {

  static from(muscleGroupRaw: MuscleGroupRaw): MuscleGroup {
    return new MuscleGroup(undefined, muscleGroupRaw.name, muscleGroupRaw.exercises ? muscleGroupRaw.exercises.map(e => ExerciseFactory.from(e)) : []);
  }

  static fromMultiple(muscleGroupsRaw: MuscleGroupRaw[]): MuscleGroup[] {
    if (!muscleGroupsRaw) {
      return [];
    }
    return muscleGroupsRaw.map(muscleGroupRaw => MuscleGroupFactory.from(muscleGroupRaw));
  }

  static fromName(name: string) {
    return {
      name: name,
      exercises: []
    }
  }
}
