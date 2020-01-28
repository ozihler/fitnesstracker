import {MuscleGroupRaw} from "./muscle-group-raw";

export interface WorkoutRaw {
  gid: number;
  title: string;
  creationDate: number;
  muscleGroups: MuscleGroupRaw[];
}
