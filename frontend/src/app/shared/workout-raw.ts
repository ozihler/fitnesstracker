import {MuscleGroupRaw} from "./muscle-group-raw";

export interface WorkoutRaw {
  gid: string;
  title: string;
  creationDate: number;
  muscleGroups: MuscleGroupRaw[];
}
