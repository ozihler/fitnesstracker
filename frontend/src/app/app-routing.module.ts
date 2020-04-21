import {NgModule} from '@angular/core';
import {PreloadAllModules, RouterModule, Routes} from '@angular/router';
import {FitnessTypeSelectionComponent} from "./fitness-type-selection.component";


const routes: Routes = [
  {path: '', component: FitnessTypeSelectionComponent},
  {path: 'fitness-type-selection', component: FitnessTypeSelectionComponent},
  {path: 'workout', loadChildren: () => import("./workout/workout.module").then(m => m.WorkoutModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {preloadingStrategy: PreloadAllModules})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
