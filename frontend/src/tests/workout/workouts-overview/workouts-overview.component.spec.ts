import {async, fakeAsync, TestBed, tick} from '@angular/core/testing';
import {WorkoutsOverviewComponent} from '../../../app/workout/workouts-overview/workouts-overview.component';
import {NO_ERRORS_SCHEMA} from '@angular/core';
import {RouterTestingModule} from '@angular/router/testing';
import {CreateWorkoutComponent} from '../../../app/workout/create-edit-workout/create-workout.component';
import {Location} from '@angular/common';
import {WorkoutService} from '../../../app/workout/shared/services/workout.service';
import {of} from 'rxjs';
import {FitnessTypeSelectionComponent} from '../../../app/fitness-type-selection.component';
import {WorkoutsOverviewUser} from '../../unit_test_users/workouts-overview-component-user.utu';
import {WorkoutsOverviewComponentPageObject} from '../../unit_test_page_objects/workouts-overview-component.utpo';


let workoutServiceMock = {fetchAllWorkouts: () => of([])};
let user: WorkoutsOverviewUser;

fdescribe('A WorkoutsOverview', () => {
  let component;
  let fixture;
  let location;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        WorkoutsOverviewComponent,
        CreateWorkoutComponent
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

  it('shows all workouts listed from top to bottom, sorted by creation date', fakeAsync(() => {
    user.clicksNewWorkoutButton();
    tick();
    user.seesCreateNewWorkoutPage();
  }));
});
