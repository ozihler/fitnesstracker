import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {TreeNode} from '../../workout-tree/tree-node';

@Component({
  selector: 'app-selectable-muscle-group-or-exercise',
  template: `
    <div class="uk-grid uk-grid-collapse">
      <button
        id="ft-select-{{item?.name | replace : ' ': '-' | lowercase}}-button"
        class="uk-button uk-button-default uk-width-2-3 uk-text-truncate"
        (click)="select(item)">{{item?.name}}</button>

      <!-- todo delete-muscle-group-or-exercise.component-->
      <button
        id="ft-delete-{{item?.name | replace : ' ': '-' | lowercase}}-button"
        class="uk-button uk-button-danger uk-width-1-3"
        (click)="delete(item)">
        <i class="fa fa-trash"></i>
      </button>
    </div>`
})
export class SelectableMuscleGroupOrExerciseComponent implements OnInit {

  @Input() item: TreeNode;
  @Output() selectItemEvent = new EventEmitter<TreeNode>();
  @Output() deleteItemEvent = new EventEmitter<TreeNode>();

  constructor() {
  }

  ngOnInit() {
  }

  select(element: TreeNode) {
    if (element) {
      this.selectItemEvent.emit(element);
    }
  }

  delete(element: TreeNode) {
    this.deleteItemEvent.emit(element);
  }
}
