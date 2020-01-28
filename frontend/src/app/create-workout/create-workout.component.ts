import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {ButtonNode, Type} from "../workout-details-view/button-group/button-node";
import {Workout} from '../shared/workout';
import {SelectableElement} from "../shared/selectable-element";
import {SelectableElementFactory} from "../shared/selectable-element-factory";
import {SubtreeFactory} from "../shared/subtree.factory";
import {MuscleGroupFactory} from "../shared/muscle-group.factory";

@Component({
  selector: 'app-create-workout',
  template: `
    <div>{{workout?.creationDate}} {{workout?.title}}</div>
    =============================
    <app-button-tree [node]="workoutTree"
                     (changeSelectionEvent)="changeSelection($event)">
    </app-button-tree>
    =============================
    <app-element-selection
      [elements]="selectedElements"
      (selectedElement)="selectElement($event)">
    </app-element-selection>
    =============================
  `,
  styles: []
})
export class CreateWorkoutComponent implements OnInit {
  workoutTree: ButtonNode;
  selectedElements: SelectableElement[] = [];
  private workout: Workout;


  constructor(private workoutService: WorkoutService) {
  }

  ngOnInit() {
    this.workoutService.newWorkout()
      .subscribe(workout => {
        this.workout = workout;
        console.log(workout);
        this.workoutTree = SubtreeFactory.fromWorkout(workout);
       });

    this.fetchMuscleGroups();

  }

  private fetchMuscleGroups() {
    this.workoutService.fetchMuscleGroups()
      .subscribe(muscleGroups => {
        this.selectedElements = muscleGroups.map(mG => SelectableElementFactory.from(mG.name, Type.Muscle_Group));
      });
  }

  selectElement(selection: SelectableElement) {
    if (selection.type === Type.Muscle_Group) {
      this.workout.muscleGroups.push(MuscleGroupFactory.fromName(selection.name));
      this.workoutTree = SubtreeFactory.fromWorkout(this.workout);
      this.selectedElements = this.selectedElements.filter(m => m.name !== selection.name);
      this.workoutService.update(this.workout);
    }

  }


  changeSelection(node: ButtonNode) {
    console.log("Changed selection to: ", node);
  }
}

