import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutTitleComponent } from './workout-title.component';

describe('WorkoutTitleComponent', () => {
  let component: WorkoutTitleComponent;
  let fixture: ComponentFixture<WorkoutTitleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkoutTitleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutTitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
