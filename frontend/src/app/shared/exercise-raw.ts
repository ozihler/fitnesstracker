import {SetRaw} from "./set-raw";
import {MuscleGroupRaw} from "./muscle-group-raw";

export interface ExerciseRaw {
  name: string;
  sets: SetRaw[];
  muscleGroup: MuscleGroupRaw
}
