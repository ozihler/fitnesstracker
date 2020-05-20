import {Button} from '../page_elements/button.pe';

export class WorkoutsOverview {
  private createNewWorkoutButton: Button;

  constructor() {
    this.createNewWorkoutButton = new Button('create-new-workout-button');
  }

  clickNewWorkoutButton() {
    return this.createNewWorkoutButton.click();
  }
}
