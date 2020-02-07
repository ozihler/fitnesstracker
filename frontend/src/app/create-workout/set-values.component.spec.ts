import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SetValuesComponent } from './set-values.component';

describe('SetValuesComponent', () => {
  let component: SetValuesComponent;
  let fixture: ComponentFixture<SetValuesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SetValuesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetValuesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
