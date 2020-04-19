import {MuscleGroupRaw} from "./muscle-group-raw";

export interface WorkoutRaw {
  gid: string;// todo rename to workoutId
  title: string;
  creationDate: number;
  muscleGroups: MuscleGroupRaw[];
}
