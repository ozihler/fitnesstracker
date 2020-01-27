import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {WorkoutService} from "../shared/workout.service";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-create-exercise',
  template: `
    <form (ngSubmit)="create()"
          [formGroup]="exerciseFormGroup">
      <input type="text"/>
      <button>Ok</button>
    </form>
    <div>{{currentExerciseValue()}}</div>
  `,
  styles: []
})
export class CreateExerciseComponent implements OnInit {
  exerciseFormGroup: FormGroup;
  private muscleGroupName: string;

  constructor(private workoutService: WorkoutService,
              private route: ActivatedRoute,
              private location: Location,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.initForm();

    this.route.paramMap.subscribe(params => this.muscleGroupName = params.get('muscleGroupName'));
  }

  private initForm() {
    if (this.exerciseFormGroup) {
      return;
    }
    this.exerciseFormGroup = this.formBuilder.group({exercise: ""});
  }

  create() {
    this.workoutService.newExercise(this.muscleGroupName, this.currentExerciseValue())
      .subscribe(() => {
        this.location.back();
      });
  }

  currentExerciseValue() {
    return this.exerciseFormGroup.get('exercise').value;
  }
}
