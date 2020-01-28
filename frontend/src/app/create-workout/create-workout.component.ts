import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {ButtonNode, Type} from "../workout-details-view/button-group/button-node";
import {Workout} from '../shared/workout';
import {SelectableElement} from "../shared/selectable-element";
import {SelectableElementFactory} from "../shared/selectable-element-factory";

@Component({
  selector: 'app-create-workout',
  template: `
    <div>{{workout.creationDate}} {{workout.title}}</div>
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
        this.workoutTree = new ButtonNode(this.workout.id, this.workout.title, [], 0, Type.Workout);
      });

    this.workoutService.fetchMuscleGroups()
      .subscribe(muscleGroups => {
        this.selectedElements = muscleGroups.map(mG => SelectableElementFactory.from(mG.name, Type.Muscle_Group));

      });

  }

  selectElement(selection: any) {
    console.log("Element selected: ", selection);
    this.selectedElements[selection.nodeType].push(selection.element)

    // this.selectedMuscleGroups.push(muscleGroup);
    //
    // this.muscleGroups = this.muscleGroups.filter(e => e.name !== muscleGroup.name);
  }


  changeSelection(node: ButtonNode) {
    console.log("Changed selection to: ", node);
  }
}

