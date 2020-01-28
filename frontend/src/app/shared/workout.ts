import {GID} from './gid';
import {MuscleGroup} from "./muscle-group";

export interface Workout {
  id: GID;
  title: string;
  creationDate: Date;
  muscleGroups: MuscleGroup[];
}
