import {GID} from './gid';
import {MuscleGroup} from "./muscle-group";

export interface Workout {
  gid: GID;
  title: string;
  creationDate: Date;
  muscleGroups: MuscleGroup[];
}
