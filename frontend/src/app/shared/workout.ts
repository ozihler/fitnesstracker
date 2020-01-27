import {MuscleGroup} from "./muscle-group";
import { Id } from './id';

export interface Workout {
  id: Id;
  name: string;
  muscleGroups: MuscleGroup[];
}
