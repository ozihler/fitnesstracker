import {Component, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environments/environment";
import {pipe} from "rxjs";
import {map} from "rxjs/operators";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-root',
  template: `
    <div class="uk-text-center">
      <router-outlet></router-outlet>

      <span>Instantiation time: {{time}}</span>
    </div>`
})
export class AppComponent {
  private time: string;

  constructor(private instantiationTimeService: InstantiationTimeService) {
    this.instantiationTimeService.getInstantiationTime()
      .subscribe(time => this.time = time);
  }
}

@Injectable({providedIn: 'root'})
class InstantiationTimeService {

  constructor(private http: HttpClient,
              private datePipe: DatePipe) {

  }

  getInstantiationTime() {
    return this.http.get<number>(environment.baseUrl + "/admin/instantiationTime")
      .pipe(
        map(timeAsUnixTimeStamp => {
          return this.datePipe.transform(
           new Date(timeAsUnixTimeStamp),
             'long',
           '',
           'de'
          )
        }));
  }

}
