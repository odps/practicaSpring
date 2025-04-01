import {Component} from '@angular/core';
import {Button} from 'primeng/button';
import {ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-test',
  imports: [
    Button,
    ReactiveFormsModule
  ],
  templateUrl: './test.component.html',
  styleUrl: './test.component.css'
})
export class TestComponent {

}
