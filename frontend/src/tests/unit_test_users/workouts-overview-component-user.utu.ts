import {WorkoutsOverviewComponentPageObject} from '../unit_test_page_objects/workouts-overview-component.utpo';
import {WorkoutEntry} from '../../app/workout/shared/workout-entry';
import {WorkoutId} from '../../app/workout/shared/workout-id';

export class WorkoutsOverviewUser {
  constructor(private workoutOverviewComponent: WorkoutsOverviewComponentPageObject) {

  }

  get testWorkouts(): WorkoutEntry[] {
    return [
      new WorkoutEntry(WorkoutId.from('1'), 'Workout 1', new Date(2020, 5, 24)),
      new WorkoutEntry(WorkoutId.from('2'), 'Workout 2', new Date(2020, 5, 22)),
      new WorkoutEntry(WorkoutId.from('3'), 'Workout 3', new Date(2020, 5, 25))
    ];
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
    this.workoutOverviewComponent.assertPathIs('/create-workout');
  }

  seesWorkoutsSortedByCreationDate() {
    this.workoutOverviewComponent.assertWorkoutsAreListedInOrder(this.testWorkouts, [2, 0, 1]);
  }
}
