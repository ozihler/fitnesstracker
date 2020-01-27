import {Exercise} from "./exercise";

export interface MuscleGroup {
  name: string;
  exercises?: Exercise[];
}
