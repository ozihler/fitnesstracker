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


@Injectable({
  providedIn: 'root'
})
export class WorkoutService {
  private baseUrl: string;

  constructor(private httpClient: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  newWorkout(): Observable<Workout> {
    let body = {title: "New Workout"};
    let url = `${this.baseUrl}/workouts`;

    return this.httpClient.post<WorkoutRaw>(url, body)
      .pipe(map(data => WorkoutFactory.fromRaw(data)));
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

  fetchWorkoutWithId(workoutGid: string): Observable<Workout> {
    let url = `${this.baseUrl}/workouts?workoutGid=${workoutGid}`;

    return this.httpClient.get<WorkoutRaw>(url)
      .pipe(map(data => WorkoutFactory.fromRaw(data)));
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
