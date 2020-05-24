import {CreateWorkoutComponent} from '../../app/workout/create-edit-workout/create-workout.component';
import {ComponentFixture} from '@angular/core/testing';

export class FindElement {
  constructor(private fixture: ComponentFixture<CreateWorkoutComponent>) {
  }

  by(selector: string) {
    return this.fixture.nativeElement.querySelector(selector);
  }

}
