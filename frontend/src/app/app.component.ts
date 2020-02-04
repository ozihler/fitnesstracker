import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <div class="uk-text-center">
      <h1 class="uk-heading-small">{{title}}</h1>
      <router-outlet></router-outlet>
    </div>`
})
export class AppComponent {
  title: string = 'Fitness Tracker';
}

