import {WorkoutsOverviewComponentPageObject} from '../unit_test_page_objects/workouts-overview-component.utpo';

export class WorkoutsOverviewUser {
  constructor(private workoutOverviewComponent: WorkoutsOverviewComponentPageObject) {

  }

  clicksBackLink() {
    this.workoutOverviewComponent.clickBackButton();
  }

  clicksNewWorkoutButton() {
    this.workoutOverviewComponent.clickNewWorkoutButton();
  }

  seesFitnessTypeSelectionPage() {
    this.workoutOverviewComponent.assertPathIs('/fitness-type-selection');
  }

  seesCreateNewWorkoutPage() {
    this.workoutOverviewComponent.assertPathIs('/workout/create-workout');
  }
}
