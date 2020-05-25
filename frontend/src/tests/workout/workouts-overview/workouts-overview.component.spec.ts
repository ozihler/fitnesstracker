import {async, fakeAsync, TestBed, tick} from '@angular/core/testing';
import {WorkoutsOverviewComponent} from '../../../app/workout/workouts-overview/workouts-overview.component';
import {Component, NO_ERRORS_SCHEMA} from '@angular/core';
import {RouterTestingModule} from '@angular/router/testing';
import {CreateWorkoutComponent} from '../../../app/workout/create-edit-workout/create-workout.component';
import {Link} from '../../unit_test_page_elements/link.utpe';
import {Location} from '@angular/common';
import {WorkoutService} from '../../../app/workout/shared/services/workout.service';
import {of} from 'rxjs';

// test mock for routing
@Component({
  selector: 'app-fitness-type-selection',
  template: '<span>Hello World</span>'
})
class TestFitnessTypeSelectionComponent {

}

const workoutServiceMock = {fetchAllWorkouts: () => of([])};

describe('A WorkoutsOverview', () => {
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
          {path: 'fitness-type-selection', component: TestFitnessTypeSelectionComponent}
        ]
      )]
    }).compileComponents();
  }));

  beforeEach(async(() => {
    location = TestBed.inject(Location);
    fixture = TestBed.createComponent(WorkoutsOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('can navigate back to fitness type selection', fakeAsync(() => {
    new Link(fixture.nativeElement.querySelector('#back-to-fitness-type-selection-link')).follow();
    tick();
    expect(location.path()).toEqual('/fitness-type-selection');
  }));
});
