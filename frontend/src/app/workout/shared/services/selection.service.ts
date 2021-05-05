import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {MuscleGroup} from '../muscle-group';
import {MuscleGroupFactory} from '../muscle-group.factory';
import {HttpClient} from '@angular/common/http';
import {map, take} from 'rxjs/operators';
import {environment} from '../../../../environments/environment';
import {MuscleGroupsRaw} from '../muscle-groups-raw';
import {ExerciseFactory} from '../exercise.factory';
import {ExercisesRaw} from '../exercises-raw';
import {Exercise} from '../exercise';
import {Set} from '../set';
import {SetRaw} from '../set-raw';
import {SetFactory} from '../set.factory';
import {WorkoutId} from '../workout-id';
import {MuscleGroupRaw} from '../muscle-group-raw';
import {ExerciseRaw} from '../exercise-raw';


@Injectable({
  providedIn: 'root'
})
export class SelectionService {
  baseUrl: string;

  constructor(private httpClient: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  newMuscleGroup(muscleGroupNames: string): Observable<MuscleGroup[]> {
    return this.httpClient.post<MuscleGroupsRaw>(
      `${this.baseUrl}/muscle-groups`,
      {
        muscleGroupNames
      }
    ).pipe(
      take(1),
      map(
        (response: MuscleGroupsRaw) =>
          MuscleGroupFactory.fromMultiple(response.muscleGroups)
      )
    );
  }

  getMuscleGroups(): Observable<MuscleGroup[]> {
    return this.httpClient.get<MuscleGroupsRaw>(`${this.baseUrl}/muscle-groups`)
      .pipe(
        take(1),
        map(
          (response: MuscleGroupsRaw) =>
            MuscleGroupFactory.fromMultiple(
              response.muscleGroups
            )
        )
      );
  }

  fetchExercisesFor(muscleGroupName: string) {
    return this.httpClient.get<ExercisesRaw>(this.baseUrl + '/muscle-groups/' + muscleGroupName + '/exercises')
      .pipe(take(1),
        map((exercises: ExercisesRaw) => ExerciseFactory.fromMultiple(exercises.exercises)));
  }

  createExercises(muscleGroup: MuscleGroup, exercisesString: string): Observable<Exercise[]> {
    return this.httpClient.post<ExercisesRaw>(`${this.baseUrl}/muscle-groups/${muscleGroup.name}/exercises`, {input: exercisesString})
      .pipe(take(1), map((e: ExercisesRaw) => ExerciseFactory.fromMultiple(e.exercises)));
  }

  addSetToExerciseExercise(workoutId: WorkoutId, exercise: Exercise, setToAdd: Set): Observable<Set> {
    return this.httpClient.post<SetRaw>(`${this.baseUrl}/workouts/${workoutId.value}/exercises/${exercise.name}/sets`, {
      weight: setToAdd.weight,
      weightUnit: setToAdd.weightUnit,
      numberOfRepetitions: setToAdd.numberOfRepetitions,
      waitingTime: setToAdd.waitingTime,
      waitingTimeUnit: setToAdd.waitingTimeUnit
    }).pipe(
      take(1),
      map((e: SetRaw) => SetFactory.from(e, exercise.children.length, exercise.multiplier)));
  }

  deleteMuscleGroup(muscleGroupName: string): Observable<MuscleGroup> {
    return this.httpClient.delete<MuscleGroupRaw>(this.baseUrl + '/muscle-groups/' + muscleGroupName)
      .pipe(
        take(1),
        map(
          (muscleGroup: MuscleGroupRaw) => MuscleGroupFactory.from(muscleGroup)
        )
      );
  }

  deleteExercise(name: string): Observable<Exercise> {
    return this.httpClient.delete<ExerciseRaw>(this.baseUrl + '/exercises/' + name)
      .pipe(
        take(1),
        map((exercise: ExerciseRaw) => ExerciseFactory.from(exercise)));
  }
}
