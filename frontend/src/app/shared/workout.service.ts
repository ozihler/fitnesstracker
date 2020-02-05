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
import {Exercise} from "./exercise";
import {SetFactory} from "./set.factory";
import {SetRaw} from "./set-raw";
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
      .pipe(map(exercises => ExerciseFactory.fromMultiple(exercises.exercises)));
  }

  newWorkout(): Observable<Workout> {
    let body = {title: "New Workout"};
    let url = `${this.baseUrl}/workouts`;

    return this.httpClient.post<WorkoutRaw>(url, body)
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

  update(workout: Workout): Observable<Workout> {
    let body = {
      workout: {
        gid: workout.gid.value,
        creationDate: workout.creationDate.getMilliseconds(),
        title: workout.title,
        muscleGroups: workout.children.map(value => this.toJson(value))
      }
    };
    return this.httpClient.put<WorkoutRaw>(`${this.baseUrl}/workouts`, body)
      .pipe(map(data => WorkoutFactory.fromRaw(data)));
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
      sets: exercise.children.map(set => this.toSetRequest(<Set>set))
    }
  }

  private toSetRequest(set: Set) {
    return {
      gid: set.gid.value,
      weight: set.weight,
      weightUnit: set.weightUnit,
      numberOfRepetitions: set.numberOfRepetitions,
      waitingTime: set.waitingTime,
      waitingTimeUnit: set.waitingTimeUnit
    }
  }

  newExercises(muscleGroup: MuscleGroup, exercisesString: string): Observable<Exercise[]> {
    return this.httpClient.post<ExercisesRaw>(`${this.baseUrl}/muscle-groups/${muscleGroup.name}/exercises`, {input: exercisesString})
      .pipe(map(e => ExerciseFactory.fromMultiple(e.exercises)));
  }

  newSetInExercise(exercise: Exercise, setDetails: string): Observable<Set> {

    return this.httpClient.post<SetRaw>(`${this.baseUrl}/exercises/${exercise.name}/sets`, {setDetails: setDetails})
      .pipe(map(e => SetFactory.from(e, exercise)));

  }
}
