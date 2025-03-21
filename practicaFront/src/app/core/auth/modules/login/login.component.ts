import {Component} from '@angular/core';
import {AuthService} from '../../../services/auth.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-login',
  imports: [
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) {
  }

  onLogin() {
    this.authService.login(this.username, this.password).subscribe(
      next => {
        if (next.token) {
          console.log(next);
          localStorage.setItem('token', next.token);
        }
      }, error => {
        console.error(error);
      }
    );
  }//fin de onLogin
}
