import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {MuscleGroup} from "./muscle-group";
import {Workout} from "./workout";
import {WorkoutFactory} from "./workout.factory";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {WorkoutRaw} from "./workout-raw";
import {environment} from "../../../environments/environment";
import {Exercise} from "./exercise";
import {Set} from "./set";
import {WorkoutEntriesRaw} from "./workout-entries-raw";
import {WorkoutEntryFactory} from "./workout-entry.factory";
import {WorkoutId} from "./workout-id";
import {WorkoutIdRaw} from "./workout-id-raw";


@Injectable({
  providedIn: 'root'
})
export class WorkoutService {
  private baseUrl: string;

  constructor(private httpClient: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  createNewOrFetchWorkoutWithId(workoutId: string): Observable<Workout> {
    return this.createWorkoutRequest(workoutId)
      .pipe(map(data => WorkoutFactory.fromRaw(data)));
  }

  private createWorkoutRequest(workoutId: string) {
    if (!workoutId) {
      return this.createNewWorkoutRequest();
    } else {
      return this.fetchWorkoutByIdRequest(workoutId);
    }
  }

  private fetchWorkoutByIdRequest(workoutId: string) {
    let url = `${this.baseUrl}/workouts?workoutId=${workoutId}`;
    return this.httpClient.get<WorkoutRaw>(url);
  }

  private createNewWorkoutRequest() {
    let url = `${this.baseUrl}/workouts`;
    let body = {title: "New Workout"};
    return this.httpClient.post<WorkoutRaw>(url, body);
  }

  updateWorkout(workout: Workout): Observable<Workout> {
    let body = {
      workout: {
        workoutId: workout.workoutId.value,
        creationDate: new Date(workout.creationDate).getTime(),
        title: workout.title,
        muscleGroups: workout.children.map(value => this.toJson(value))
      }
    };
    return this.httpClient.put<WorkoutRaw>(`${this.baseUrl}/workouts`, body)
      .pipe(map(data => WorkoutFactory.fromRaw(data)));
  }

  copy(workoutId: WorkoutId): Observable<WorkoutId> {
    console.log(workoutId.value)
    return this.httpClient.post<WorkoutIdRaw>(`${this.baseUrl}/workouts/copy`, {workoutId: workoutId.value})
      .pipe(map(copiedWorkoutId => WorkoutId.from(copiedWorkoutId.workoutId)));
  }

  fetchAllWorkouts() {
    return this.httpClient.get<WorkoutEntriesRaw>(`${this.baseUrl}/workouts/overview`)
      .pipe(map(workoutsSimpleRaw => WorkoutEntryFactory.fromMultiple(workoutsSimpleRaw)));
  }

  private toJson(muscleGroup: MuscleGroup) {
    return {
      name: muscleGroup.name,
      exercises: muscleGroup.children.map(exercise => this.toExerciseRequest(exercise))
    }
  }

  // todo extract interfaces for requests
  private toExerciseRequest(exercise: Exercise) {
    return {
      name: exercise.name,
      sets: exercise.children.map(set => WorkoutService.toSetRequest(<Set>set))
    }
  }

  private static toSetRequest(set: Set) {
    return {
      weight: set.weight,
      weightUnit: set.weightUnit,
      numberOfRepetitions: set.numberOfRepetitions,
      waitingTime: set.waitingTime,
      waitingTimeUnit: set.waitingTimeUnit
    }
  }
}
