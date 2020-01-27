import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkoutsOverview } from './workouts-overview.component';

describe('WorkoutsOverviewComponent', () => {
  let component: WorkoutsOverview;
  let fixture: ComponentFixture<WorkoutsOverview>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkoutsOverview ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkoutsOverview);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
