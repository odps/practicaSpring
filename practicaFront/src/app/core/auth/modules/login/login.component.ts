import {Component} from '@angular/core';
import {AuthService} from '../../../services/auth.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Router} from '@angular/router';

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

  constructor(private authService: AuthService, private router: Router) {
  }

  onLogin() {
    this.authService.login(this.username, this.password).subscribe(
      next => {
        if (next.token) {
          console.log('Login successful');
          localStorage.setItem('token', next.token);
          localStorage.setItem('currentUser', JSON.stringify(next.user));
          this.router.navigate(['/main']);
        }
      }, error => {
        console.error(error);
      }
    );
  }//fin de onLogin
}
