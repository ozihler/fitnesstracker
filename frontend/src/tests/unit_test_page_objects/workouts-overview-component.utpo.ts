import {ComponentFixture} from '@angular/core/testing';
import {WorkoutsOverviewComponent} from '../../app/workout/workouts-overview/workouts-overview.component';
import {Location} from '@angular/common';
import {Link} from '../unit_test_page_elements/link.utpe';
import {FindElement} from './find-element';
import {Button} from '../unit_test_page_elements/button.utpe';
import {WorkoutEntry} from '../../app/workout/shared/workout-entry';

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

  assertWorkoutsAreListedInOrder(workoutEntries: WorkoutEntry[],
                                 orderIndexes: number[]) {
    const allWorkoutSelectionButtons = this.findButtonsWith('.workout-selection-button');
    for (let i = 0; i < orderIndexes.length; ++i) {
      const workoutEntry = workoutEntries[orderIndexes[i]];
      expect(allWorkoutSelectionButtons[i].label).toContain(workoutEntry.workoutId.value);
      expect(allWorkoutSelectionButtons[i].label).toContain(this.toGermanDate(workoutEntry.creationDate));
    }
  }

  private toGermanDate(creationDate: Date) {
    return creationDate.getDate()
      + '.0' + (creationDate.getMonth() + 1)
      + '.' + (('' + creationDate.getFullYear()).substr(2, 4));
  }

  private findButtonsWith(selector): Button[] {
    const buttons: Button[] = [];
    new FindElement(this.fixture)
      .allWith(selector)
      .forEach(buttonNode => buttons.push(new Button(buttonNode as HTMLButtonElement)));
    return buttons;
  }
}
