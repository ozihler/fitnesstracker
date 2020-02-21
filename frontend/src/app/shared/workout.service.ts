import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {MuscleGroup} from "./muscle-group";
import {Workout} from "./workout";
import {WorkoutFactory} from "./workout.factory";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {WorkoutRaw} from "./workout-raw";
import {environment} from "../../environments/environment";
import {Exercise} from "./exercise";
import {Set} from "./set";
import {WorkoutsSimpleRaw} from "./workouts-simple-raw";
import {WorkoutSimpleFactory} from "./workout-simple.factory";
import {WorkoutId} from "./workoutId";
import {WorkoutIdRaw} from "./workout-id-raw";


@Injectable({
  providedIn: 'root'
})
export class WorkoutService {
  private baseUrl: string;

  constructor(private httpClient: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  createNewOrFetchWorkoutWithId(workoutGid: string): Observable<Workout> {
    return this.createWorkoutRequest(workoutGid)
      .pipe(map(data => WorkoutFactory.fromRaw(data)));
  }

  private createWorkoutRequest(workoutGid: string) {
    if (!workoutGid) {
      return this.createNewWorkoutRequest();
    } else {
      return this.fetchWorkoutByIdRequest(workoutGid);
    }
  }

  private fetchWorkoutByIdRequest(workoutGid: string) {
    let url = `${this.baseUrl}/workouts?workoutGid=${workoutGid}`;
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
        gid: workout.gid.value,
        creationDate: new Date(workout.creationDate).getTime(),
        title: workout.title,
        muscleGroups: workout.children.map(value => this.toJson(value))
      }
    };
    return this.httpClient.put<WorkoutRaw>(`${this.baseUrl}/workouts`, body)
      .pipe(map(data => WorkoutFactory.fromRaw(data)));
  }

  copy(workoutId: WorkoutId): Observable<WorkoutId> {
    return this.httpClient.post<WorkoutIdRaw>(`${this.baseUrl}/workouts/copy`, {workoutId: workoutId.value})
      .pipe(map(copiedWorkoutId => WorkoutId.from(copiedWorkoutId.workoutId)));
  }

  fetchAllWorkouts() {
    return this.httpClient.get<WorkoutsSimpleRaw>(`${this.baseUrl}/workouts/overview`)
      .pipe(map(workoutsSimpleRaw => WorkoutSimpleFactory.fromMultiple(workoutsSimpleRaw)));
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
