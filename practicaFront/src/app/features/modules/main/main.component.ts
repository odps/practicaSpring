import {Component} from '@angular/core';
import {HeaderComponent} from '../../../shared/components/header/header.component';
import {UsertableComponent} from '../usertable/usertable.component';

@Component({
  selector: 'app-main',
  imports: [
    HeaderComponent,
    UsertableComponent
  ],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

}
