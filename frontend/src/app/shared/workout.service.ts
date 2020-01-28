import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {MuscleGroup} from "./muscle-group";
import {MuscleGroupFactory} from "./muscle-group.factory";
import {Workout} from "./workout";
import {WorkoutFactory} from "./workout.factory";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {WorkoutRaw} from "./workout-raw";
import {environment} from "../../environments/environment";
import {MuscleGroupsRaw} from "./muscle-groups-raw";
import {ExerciseFactory} from "./exercise.factory";
import {ExercisesRaw} from "./exercises-raw";


@Injectable({
  providedIn: 'root'
})
export class WorkoutService {
  private baseUrl: string;

  constructor(private httpClient: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  newMuscleGroup(muscleGroupNames: string): Observable<MuscleGroup[]> {
    return this.httpClient.post<MuscleGroupsRaw>(`${this.baseUrl}/muscle-groups`, {muscleGroupNames: muscleGroupNames})
      .pipe(map(response => MuscleGroupFactory.fromMultiple(response.muscleGroups)));

  }

  fetchMuscleGroups(): Observable<MuscleGroup[]> {
    return this.httpClient.get<MuscleGroupsRaw>(`${this.baseUrl}/muscle-groups`)
      .pipe(
        map(response => MuscleGroupFactory.fromMultiple(response.muscleGroups))
      );
  }

  fetchExercisesFor(muscleGroupName: string) {
    return this.httpClient.get<ExercisesRaw>(this.baseUrl + "/muscle-groups/" + muscleGroupName + "/exercises")
      .subscribe(exercises=>ExerciseFactory.fromMultiple(exercises.exercises));
  }

  newWorkout(): Observable<Workout> {
    let body = {title: "New Workout"};

    return this.httpClient.post<WorkoutRaw>(`${this.baseUrl}/workouts`, body)
      .pipe(map(data => WorkoutFactory.fromRaw(data)));
  }

  update(workout: Workout): Observable<Workout> {
    return this.httpClient.put<WorkoutRaw>(`${this.baseUrl}/workouts`, {workout: workout})
      .pipe(map(data => WorkoutFactory.fromRaw(data)));
  }
}
