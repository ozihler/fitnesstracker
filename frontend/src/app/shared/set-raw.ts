import {ExerciseRaw} from "./exercise-raw";

export interface SetRaw {
  exercise: ExerciseRaw;
  repetitions: number;
  weight: number;
  waitingTime: number;
}
