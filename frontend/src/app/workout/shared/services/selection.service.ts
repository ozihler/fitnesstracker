import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {MuscleGroup} from "../muscle-group";
import {MuscleGroupFactory} from "../muscle-group.factory";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {environment} from "../../../../environments/environment";
import {MuscleGroupsRaw} from "../muscle-groups-raw";
import {ExerciseFactory} from "../exercise.factory";
import {ExercisesRaw} from "../exercises-raw";
import {Exercise} from "../exercise";
import {Set} from "../set";
import {SetRaw} from "../set-raw";
import {SetFactory} from "../set.factory";
import {WorkoutId} from "../workout-id";
import {MuscleGroupRaw} from "../muscle-group-raw";
import {ExerciseRaw} from "../exercise-raw";


@Injectable({
  providedIn: 'root'
})
export class SelectionService {
  baseUrl: string;

  constructor(private httpClient: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  newMuscleGroup(muscleGroupNames: string): Observable<MuscleGroup[]> {
    return this.httpClient.post<MuscleGroupsRaw>(`${this.baseUrl}/muscle-groups`, {muscleGroupNames: muscleGroupNames})
      .pipe(map(response => MuscleGroupFactory.fromMultiple(response.muscleGroups)));
  }

  getMuscleGroups(): Observable<MuscleGroup[]> {
    return this.httpClient.get<MuscleGroupsRaw>(`${this.baseUrl}/muscle-groups`)
      .pipe(
        map(
          response =>
            MuscleGroupFactory.fromMultiple(
              response.muscleGroups
            )
        )
      );
  }

  fetchExercisesFor(muscleGroupName: string) {
    return this.httpClient.get<ExercisesRaw>(this.baseUrl + "/muscle-groups/" + muscleGroupName + "/exercises")
      .pipe(map(exercises => ExerciseFactory.fromMultiple(exercises.exercises)));
  }

  createExercises(muscleGroup: MuscleGroup, exercisesString: string): Observable<Exercise[]> {
    return this.httpClient.post<ExercisesRaw>(`${this.baseUrl}/muscle-groups/${muscleGroup.name}/exercises`, {input: exercisesString})
      .pipe(map(e => ExerciseFactory.fromMultiple(e.exercises)));
  }

  addSetToExerciseExercise(workoutId: WorkoutId, exercise: Exercise, setDetails: string): Observable<Set> {
    return this.httpClient.post<SetRaw>(`${this.baseUrl}/workouts/${workoutId.value}/exercises/${exercise.name}/sets`, {setDetails: setDetails})
      .pipe(map(e => SetFactory.from(e)));
  }

  deleteMuscleGroup(muscleGroupName: string): Observable<MuscleGroup> {
    return this.httpClient.delete<MuscleGroupRaw>(this.baseUrl + "/muscle-groups/" + muscleGroupName)
      .pipe(map(muscleGroup => MuscleGroupFactory.from(muscleGroup)));
  }

  deleteExercise(name: string): Observable<Exercise> {
    return this.httpClient.delete<ExerciseRaw>(this.baseUrl + "/exercises/" + name)
      .pipe(map(exercise => ExerciseFactory.from(exercise)));
  }
}
