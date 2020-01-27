import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ButtonTreeComponent } from './button-tree.component';

describe('ButtonGroupComponent', () => {
  let component: ButtonTreeComponent;
  let fixture: ComponentFixture<ButtonTreeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ButtonTreeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ButtonTreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
