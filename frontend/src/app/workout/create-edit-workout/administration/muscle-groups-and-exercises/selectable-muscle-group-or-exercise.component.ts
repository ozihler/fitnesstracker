import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TreeNode} from "../../workout-tree/tree-node";

@Component({
  selector: 'app-selectable-muscle-group-or-exercise',
  template: `
    <div class="uk-grid uk-grid-collapse">
      <button
        id="ft-select-muscle-group-or-exercise-button"
        class="uk-button uk-button-default uk-width-2-3 uk-text-truncate"
        (click)="select(element)">{{element?.name}}
      </button>

      <!-- todo delete-muscle-group-or-exercise.component-->
      <button
        id="ft-delete-muscle-group-or-exercise-button"
        class="uk-button uk-button-danger uk-width-1-3"
        (click)="delete(element)">
        <i class="fa fa-trash"></i>
      </button>
    </div>`
})
export class SelectableMuscleGroupOrExerciseComponent implements OnInit {

  @Input() element: TreeNode;
  @Output() selectElementEvent = new EventEmitter<TreeNode>()
  @Output() deleteElementEvent = new EventEmitter<TreeNode>()

  constructor() {
  }

  ngOnInit() {
  }

  select(element: TreeNode) {
    if (element) {
      this.selectElementEvent.emit(element);
    }
  }

  delete(element: TreeNode) {
    this.deleteElementEvent.emit(element);
  }
}
