import {async, fakeAsync, TestBed, tick} from '@angular/core/testing';
import {WorkoutsOverviewComponent} from '../../../app/workout/workouts-overview/workouts-overview.component';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {RouterTestingModule} from '@angular/router/testing';
import {CreateWorkoutComponent} from '../../../app/workout/create-edit-workout/create-workout.component';
import {DatePipe, Location, registerLocaleData} from '@angular/common';
import {WorkoutService} from '../../../app/workout/shared/services/workout.service';
import {FitnessTypeSelectionComponent} from '../../../app/fitness-type-selection.component';
import {WorkoutsOverviewUser} from '../../unit_test_users/workouts-overview-component-user.utu';
import {WorkoutsOverviewComponentPageObject} from '../../unit_test_page_objects/workouts-overview-component.utpo';
import {WorkoutEntryComponent} from '../../../app/workout/workouts-overview/workout-entry.component';
import localeDe from '@angular/common/locales/de';
import {of} from 'rxjs';

registerLocaleData(localeDe);

const workoutServiceMock = {
  fetchAllWorkouts: () => of(user.testWorkouts)
};
let user: WorkoutsOverviewUser;

describe('A workouts overview user', () => {
  let component;
  let fixture;
  let location;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        WorkoutsOverviewComponent,
        CreateWorkoutComponent,
        WorkoutEntryComponent,
        DatePipe
      ],
      schemas: [NO_ERRORS_SCHEMA],
      providers: [
        {provide: WorkoutService, useValue: workoutServiceMock}
      ],
      imports: [RouterTestingModule.withRoutes(
        [
          {path: 'fitness-type-selection', component: FitnessTypeSelectionComponent},
          {path: 'workout/create-workout', component: CreateWorkoutComponent}
        ]
      )]
    }).compileComponents();
  }));

  beforeEach(async(() => {
    location = TestBed.inject(Location);
    fixture = TestBed.createComponent(WorkoutsOverviewComponent);
    user = new WorkoutsOverviewUser(new WorkoutsOverviewComponentPageObject(fixture, location));
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('can navigate back to fitness type selection', fakeAsync(() => {
    user.clicksBackLink();
    tick();
    user.seesFitnessTypeSelectionPage();
  }));
  it('can navigate back to create new workout page', fakeAsync(() => {
    user.clicksNewWorkoutButton();
    tick();
    user.seesCreateNewWorkoutPage();
  }));

  it('sees all workouts listed from top to bottom, sorted by creation date', () => {
    user.seesWorkoutsSortedByCreationDate();
  });
});
