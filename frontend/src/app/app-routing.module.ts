import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FitnessTypeSelectionComponent} from "./fitness-type-selection.component";


const routes: Routes = [
  {path: '', redirectTo: 'workout-selection', pathMatch: 'full'},
  {path: 'workout-selection', component: FitnessTypeSelectionComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
