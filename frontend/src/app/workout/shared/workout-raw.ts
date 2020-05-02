import {MuscleGroupRaw} from "./muscle-group-raw";

export interface WorkoutRaw {
  workoutId: string;// todo rename to workoutId
  title: string;
  creationDate: number;
  muscleGroups: MuscleGroupRaw[];
}
