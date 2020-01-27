import {ExerciseRaw} from "./exercise-raw";

export interface MuscleGroupRaw {
  name: string;
  exercises?: ExerciseRaw[];
}
