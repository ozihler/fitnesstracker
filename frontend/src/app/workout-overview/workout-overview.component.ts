import {Component, OnInit} from '@angular/core';
import {WorkoutService} from "../shared/workout.service";
import {Id} from "../shared/id";
import {ButtonNode, NodeType} from "../workout-details-view/button-group/button-node";
import {Workout} from '../shared/workout';

@Component({
  selector: 'app-workout',
  template: `
    <div>{{workoutTree.name}}</div>
    =============================
    <app-button-group [node]="workoutTree"
                      (changeSelectionEvent)="changeSelection($event)">
    </app-button-group>
    =============================
    <app-element-selection
      [elements]="selectedElements"
      [type]="selectedType"
      (selectedElement)="selectElement($event)">
    </app-element-selection>
    =============================
  `,
  styles: []
})
export class WorkoutOverview implements OnInit {
  workoutTree: ButtonNode;
  selectedElements = [];
  selectedType: NodeType;


  constructor(private workoutService: WorkoutService) {
  }

  ngOnInit() {
    let workout: Workout = {id: Id.from(1 + ""), name: new Date().toDateString(), muscleGroups: []};

    this.workoutTree = new ButtonNode(workout.id, workout.name, [], 0, NodeType.WORKOUT);

    this.workoutService.fetchMuscleGroups()
      .subscribe(muscleGroups => {
        this.selectedElements = muscleGroups.map(mG => mG.name);
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

