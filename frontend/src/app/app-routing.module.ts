import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {WorkoutSelectionComponent} from "./workout-selection/workout-selection.component";
import {WorkoutsOverview} from "./workouts-overview/workouts-overview.component";
import {WorkoutOverview} from "./workout-overview/workout-overview.component";
import {CreateMuscleGroupComponent} from "./create-muscle-group/create-muscle-group.component";
import {CreateExerciseComponent} from "./create-exercise/create-exercise.component";
import {CreateSetComponent} from "./create-set/create-set.component";


const routes: Routes = [

  /*
  * TODO Angepasstes Konzept umsetzen: Es gibt folgende Anfangs Use Cases:
  *   Fitness Typ auswählen (WorkoutSelection)
  *   [x] Workout Übersicht ansehen (WorkoutsOverview)
  *   Workout ansehen (ViewWorkout)
  *   Workout bearbeiten (EditWorkout)
  *   [x] Workout erstellen (WorkoutOverview)
  *     [x] > Muskelgruppe erstellen (CreateMuscleGroup) --> Klick auf "New Muscle Group"
  *     > Muskelgruppe hinzufügen (AddMuscleGroup) --> Klick unten auf Button
  *     > Muskelgruppe bearbeiten (EditMuscleGroup) --> Klick oben auf Button, Übungen hinzufügen, Name ändern
  *     > Muskelgruppe löschen (RemoveMuscleGroup) --> tbd
  *     > Muskelgruppe Reihenfolge ändern (OrderMuscleGroup) --> Drag and Drop des Buttons
  *         [x] > Übung erstellen (CreateExercise) --> Klick auf "New Exercise"
  *         [x] > Übung zu Muskelgruppe hinzufügen (AddExercise) --> Klick unten auf Button
  *         > Übung bearbeiten (EditExercise) --> Klick oben auf Exercise Button, Set hinzufügen, Name ändern
  *         > Übung löschen (RemoveExercise) --> tbd
  *         > Übung Reihenfolge ändern (OrderExercise) --> Drag and Drop des Buttons
  *             > Set hinzufügen (AddSet) --> Klick auf "Add Set" in EditExercise: Reps (#), Weight (kgs), Time Waiting After Exercise (secs)
  *             > Set bearbeiten (EditSet) --> Klick auf Set oben in Übersicht
  *             > Set löschen (DeleteSet) --> tbd
  *
  *
  * */
  {path: "workout-selection", component: WorkoutSelectionComponent},
  {path: "workouts-overview", component: WorkoutsOverview},
  {path: "workouts/:id", component: WorkoutOverview},
  {path: "create-muscle-group", component: CreateMuscleGroupComponent},
  {path: "create-exercise/:muscleGroupName", component: CreateExerciseComponent},
  {path: "create-set", component: CreateSetComponent},


  {path: "", redirectTo: "workout-selection", pathMatch: "full"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
