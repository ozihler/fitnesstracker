import {MuscleGroupRaw} from "./muscle-group-raw";

export interface WorkoutRaw {
  workoutId: string;
  creationDate: number;
  muscleGroups: MuscleGroupRaw[];
}
