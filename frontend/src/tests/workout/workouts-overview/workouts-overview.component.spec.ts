import {TestBed} from '@angular/core/testing';
import {WorkoutsOverviewComponent} from '../../../app/workout/workouts-overview/workouts-overview.component';
import {NO_ERRORS_SCHEMA} from '@angular/core';

describe('A WorkoutsOverview', () => {
  let component;
  let fixture;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WorkoutsOverviewComponent],
      schemas: [NO_ERRORS_SCHEMA]
    }).compileComponents();
    fixture = TestBed.createComponent(WorkoutsOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


});
