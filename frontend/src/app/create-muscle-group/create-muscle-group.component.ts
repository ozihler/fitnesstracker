import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {WorkoutService} from "../shared/workout.service";
import {Location} from "@angular/common";
import {Type} from "../shared/type";

@Component({
  selector: 'app-create-muscle-group',
  template: `
    <form [formGroup]="createElement"
          (ngSubmit)="create()">
      <div class="uk-margin">
        <input class="uk-input"
               formControlName="element"
               type="text">
      </div>
      <button type="submit">Ok</button>
    </form>
    <div>{{currentElementValue()}}</div>
  `,
  styles: []
})
export class CreateMuscleGroupComponent implements OnInit {

  @Output() createElementEvent = new EventEmitter<string>();

  private createElement: FormGroup;

  constructor(private workoutService: WorkoutService,
              private location: Location,
              private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    if (this.createElement) {
      return;
    }
    this.createElement = this.formBuilder.group({
      element: ["", Validators.required]
    });
  }

  create() {
    if (this.hasEnteredAnything()) {
      this.createElementEvent.emit(this.currentElementValue())
      this.workoutService.newMuscleGroup(this.currentElementValue())
        .subscribe(this.goBackInHistory());
    }
  }

  private hasEnteredAnything() {
    return !!this.currentElementValue();
  }

  private currentElementValue() {
    return this.createElement.get('element').value.trim();
  }

  private goBackInHistory() {
    return () => {
      this.location.back();
    }
  }
}
