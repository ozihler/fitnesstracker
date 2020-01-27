import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutSelectionComponent } from './workout-selection.component';

describe('WorkoutSelectionComponent', () => {
  let component: WorkoutSelectionComponent;
  let fixture: ComponentFixture<WorkoutSelectionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkoutSelectionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutSelectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
