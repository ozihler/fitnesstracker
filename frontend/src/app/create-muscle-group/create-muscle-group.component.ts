import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {WorkoutService} from "../shared/workout.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-create-muscle-group',
  template: `
    <form [formGroup]="createMuscleGroup"
          (ngSubmit)="create()">
      <div class="uk-margin">
        <input class="uk-input"
               formControlName="muscleGroup"
               type="text">
      </div>
      <button type="submit">Ok</button>
    </form>
    <div>{{currentMuscleGroupName()}}</div>
  `,
  styles: []
})
export class CreateMuscleGroupComponent implements OnInit {

  private createMuscleGroup: FormGroup;

  constructor(private workoutService: WorkoutService,
              private location: Location,
              private formBuilder: FormBuilder) {

  }

  ngOnInit() {
    this.initForm();
  }

  private initForm() {
    if (this.createMuscleGroup) {
      return;
    }
    this.createMuscleGroup = this.formBuilder.group({
      muscleGroup: ["", Validators.required]
    });
  }

  create() {
    if (this.hasEnteredAnyMuscleGroupName()) {
      this.workoutService.newMuscleGroup(this.currentMuscleGroupName())
        .subscribe(this.goBackInHistory());
    }
  }

  private hasEnteredAnyMuscleGroupName() {
    return !!this.currentMuscleGroupName();
  }

  private currentMuscleGroupName() {
    return this.createMuscleGroup.get('muscleGroup').value.trim();
  }

  private goBackInHistory() {
    return () => {
      this.location.back();
    }
  }
}
