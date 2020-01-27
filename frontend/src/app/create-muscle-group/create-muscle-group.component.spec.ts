import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMuscleGroupComponent } from './create-muscle-group.component';

describe('CreateMuscleGroupComponent', () => {
  let component: CreateMuscleGroupComponent;
  let fixture: ComponentFixture<CreateMuscleGroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateMuscleGroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMuscleGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
