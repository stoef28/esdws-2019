import {Component} from '@angular/core';
import {environment} from "../environments/environment";

@Component({
  selector: 'root',
  templateUrl: './app.component.html'
})
export class AppComponent {
  name: string = environment.name;
  isProd: boolean = environment.production;
}
