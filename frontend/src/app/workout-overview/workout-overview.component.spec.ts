import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutOverview } from './workout-overview.component';

describe('NewWorkoutComponent', () => {
  let component: WorkoutOverview;
  let fixture: ComponentFixture<WorkoutOverview>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkoutOverview ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutOverview);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
