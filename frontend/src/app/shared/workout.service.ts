import {Injectable} from '@angular/core';
import {from, Observable} from "rxjs";
import {MuscleGroup} from "./muscle-group";
import {MuscleGroupFactory} from "./muscle-group.factory";
import {Workout} from "./workout";
import {WorkoutFactory} from "./workout.factory";
import {Exercise} from "./exercise";
import {ExerciseFactory} from "./exercise.factory";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {WorkoutRaw} from "./workout-raw";
import {environment} from "../../environments/environment";
import {MuscleGroupRaw} from "./muscle-group-raw";
import {MuscleGroupsRaw} from "./muscle-groups-raw";


@Injectable({
  providedIn: 'root'
})
export class WorkoutService {
  private muscleGroups: MuscleGroup[] = [];
  static idCounter = 1;
  private baseUrl: string;

  constructor(private httpClient: HttpClient) {
    this.baseUrl = environment.baseUrl;
  }

  newMuscleGroup(muscleGroupNames: string): Observable<MuscleGroup[]> {
    let createdMuscleGroups: MuscleGroup[] = [];
    muscleGroupNames
      .split(/[ ;,.]+/)
      .forEach(muscleGroupName => {
        if (muscleGroupName.trim().length > 0) {
          createdMuscleGroups.push(MuscleGroupFactory.from({name: muscleGroupName.trim()}));
        }
      });

    createdMuscleGroups.forEach(muscleGroup => {
      this.muscleGroups.push(muscleGroup);
    });

    return from([createdMuscleGroups]);
  }

  newExercise(muscleGroupName: string, exerciseNames: string): Observable<Exercise[]> {
    let createdExercises: Exercise[] = [];
    exerciseNames
      .split(/[ ;,.]+/)
      .forEach(exerciseName => {
        if (exerciseName.trim().length > 0) {
          createdExercises.push(ExerciseFactory.from({name: exerciseName.trim()}));
        }
      });

    createdExercises.forEach(exercise => {
      this.muscleGroups
        .filter(m => m.name === muscleGroupName)
        .push(exercise);
    });

    return from([createdExercises]);
  }

  fetchMuscleGroups(): Observable<MuscleGroup[]> {
    return this.httpClient.get<MuscleGroupsRaw>(this.baseUrl + "/muscleGroups")
      .pipe(
        map(response => MuscleGroupFactory.fromMultiple(response.muscleGroups))
      );
  }

  newWorkout(): Observable<Workout> {
    let body = {title: "new Workout"};

    return this.httpClient.post<WorkoutRaw>(this.baseUrl+"/workouts", body)
      .pipe(map(data => WorkoutFactory.fromRaw(data)));
  }
}
