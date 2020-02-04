import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {WorkoutService} from "../shared/workout.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-create-set',
  template: `
    <div>
      <div *ngIf="!showButton">
        <form [formGroup]="set"
              (ngSubmit)="create()">
          <div class="uk-margin">
            <input class="uk-input" formControlName="weight" type="text"> kg
          </div>
          <div class="uk-margin">
            <input class="uk-input" formControlName="repetitions" type="text"> #
          </div>
          <div class="uk-margin">
            <input class="uk-input" formControlName="waitingTime" type="text"> s
          </div>
          <button class="uk-button uk-width-1-1 uk-text-truncate" type="submit">Ok</button>
        </form>
      </div>
    </div>
    <hr/>
    <div *ngIf="showButton">
      <button class="uk-button uk-width-1-1 uk-text-truncate" [disabled]="set.valid" (click)="toggleButton()">
        Create {{typename}} </button>
    </div>
  `
})
export class CreateSetComponent implements OnInit {

  @Output() createSet = new EventEmitter<string>();
  @Input() typename: string;

  showButton: boolean = true;
  set: FormGroup;

  constructor(private workoutService: WorkoutService,
              private location: Location,
              private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    this.initForm();
  }

  create() {
    if (this.set.valid) {
      this.emitNewSetEvent(this.concatenateValues());
    }
  }

  toggleButton() {
    this.showButton = false;
  }

  private initForm() {
    if (this.set) {
      return;
    }
    this.set = this.formBuilder.group({
      weight: ["", Validators.required],
      repetitions: ["", Validators.required],
      waitingTime: [""]
    });
  }

  private concatenateValues() {
    return `${this.set.get('weight').value}_${this.set.get('repetitions').value}_${this.set.get('waitingTime').value}`;
  }

  private emitNewSetEvent(createdElements: string) {
    this.showButton = true;
    this.set.reset();
    this.createSet.emit(createdElements);
  }

}
