import {ComponentFixture} from '@angular/core/testing';
import {WorkoutsOverviewComponent} from '../../app/workout/workouts-overview/workouts-overview.component';
import {Location} from '@angular/common';
import {Link} from '../unit_test_page_elements/link.utpe';
import {FindElement} from './find-element';
import {Button} from '../unit_test_page_elements/button.utpe';

export class WorkoutsOverviewComponentPageObject {
  constructor(private fixture: ComponentFixture<WorkoutsOverviewComponent>,
              private location: Location) {
  }

  clickBackButton() {
    new Link(new FindElement(this.fixture).by('#back-to-fitness-type-selection-link')).follow();
  }

  clickNewWorkoutButton() {
    new Button(new FindElement(this.fixture).by('#create-new-workout-button')).click();
  }

  assertPathIs(path: string) {
    expect(this.location.path()).toEqual(path);
  }
}
