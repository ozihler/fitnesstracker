import {MuscleGroupRaw} from "./muscle-group-raw";

export interface WorkoutRaw {
  id: number;
  title: string;
  creationDate: number;
  muscleGroups: MuscleGroupRaw[];
}
