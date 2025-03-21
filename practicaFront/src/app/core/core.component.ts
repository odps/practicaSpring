import {Component} from '@angular/core';
import {AuthComponent} from './auth/auth.component';

@Component({
  selector: 'app-core',
  imports: [
    AuthComponent
  ],
  templateUrl: './core.component.html',
  styleUrl: './core.component.css'
})
export class CoreComponent {

}
