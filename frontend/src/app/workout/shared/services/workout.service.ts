import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {MuscleGroup} from '../muscle-group';
import {Workout} from '../workout';
import {WorkoutFactory} from '../workout.factory';
import {HttpClient} from '@angular/common/http';
import {map, take} from 'rxjs/operators';
import {WorkoutRaw} from '../workout-raw';
import {environment} from '../../../../environments/environment';
import {Exercise} from '../exercise';
import {Set} from '../set';
import {WorkoutEntriesRaw} from '../workout-entries-raw';
import {WorkoutEntryFactory} from '../workout-entry.factory';
import {WorkoutId} from '../workout-id';
import {WorkoutIdRaw} from '../workout-id-raw';


@Injectable({
  providedIn: 'root'
})
export class WorkoutService {

  constructor(private httpClient: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  private baseUrl: string;

  private static toSetRequest(set: Set) {
    return {
      weight: set.weight,
      weightUnit: set.weightUnit,
      numberOfRepetitions: set.numberOfRepetitions,
      waitingTime: set.waitingTime,
      waitingTimeUnit: set.waitingTimeUnit
    };
  }

  createNewOrFetchWorkoutWithId(workoutId: string): Observable<Workout> {
    return this.createOrFetchWorkout(workoutId)
      .pipe(map((data: WorkoutRaw) => WorkoutFactory.fromRaw(data)));
  }

  private createOrFetchWorkout(workoutId: string) {
    if (!workoutId) {
      return this.createNewWorkout();
    } else {
      return this.fetchWorkoutByIdRequest(workoutId);
    }
  }

  updateWorkout(workout: Workout): Observable<Workout> {
    const body = {
      workout: {
        workoutId: workout.workoutId.value,
        creationDate: new Date(workout.creationDate).getTime(),
        muscleGroups: workout.children.map(value => this.toJson(value))
      }
    };
    return this.httpClient.put<WorkoutRaw>(`${this.baseUrl}/workouts`, body)
      .pipe(map((data:WorkoutRaw) => WorkoutFactory.fromRaw(data)));
  }

  private fetchWorkoutByIdRequest(workoutId: string) {
    const url = `${this.baseUrl}/workouts?workoutId=${workoutId}`;
    return this.httpClient.get<WorkoutRaw>(url);
  }

  private createNewWorkout() {
    return this.httpClient.post<WorkoutRaw>(`${this.baseUrl}/workouts`, {});
  }

  copyWorkoutWithId(workoutId: WorkoutId): Observable<WorkoutId> {
    return this.httpClient.post<WorkoutIdRaw>(
      `${this.baseUrl}/workouts/copy`,
      {workoutId: workoutId.value})
      .pipe(take(1), map((copiedWorkoutId:WorkoutIdRaw) => WorkoutId.from(copiedWorkoutId.workoutId)));
  }

  deleteWorkoutWithId(workoutId: WorkoutId) {
    return this.httpClient.delete<WorkoutRaw>(
      `${this.baseUrl}/workouts/${workoutId.value}`)
      .pipe(
        map((deletedWorkoutRaw:WorkoutRaw) => WorkoutFactory.fromRaw(deletedWorkoutRaw))
      );
  }

  fetchAllWorkouts() {
    return this.httpClient.get<WorkoutEntriesRaw>(`${this.baseUrl}/workouts/overview`)
      .pipe(
        map(
          (workoutsSimpleRaw:WorkoutEntriesRaw) => WorkoutEntryFactory.fromMultiple(workoutsSimpleRaw)
        )
      );
  }

  private toJson(muscleGroup: MuscleGroup) {
    return {
      name: muscleGroup.name,
      exercises: muscleGroup.children.map(exercise => this.toExerciseRequest(exercise as Exercise))
    };
  }

  // todo extract interfaces for requests
  private toExerciseRequest(exercise: Exercise) {
    return {
      name: exercise.name,
      multiplier: exercise.multiplier,
      sets: exercise.children.map(set => WorkoutService.toSetRequest(set as Set))
    };
  }
}
