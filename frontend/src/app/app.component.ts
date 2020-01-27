import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
    <div>
      <h1>{{title}}</h1>
      <router-outlet></router-outlet>
    </div>`
})
export class AppComponent {
  title: string = 'Fitness Tracker';
}

