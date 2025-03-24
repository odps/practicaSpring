import {Component} from '@angular/core';
import {routes} from '../../../app.routes';
import {Router} from '@angular/router';
import {AuthService} from '../../../core/services/auth.service';

@Component({
  selector: 'app-header',
  imports: [],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  protected readonly routes = routes;

  constructor(private readonly router: Router, private authService: AuthService) {
  }

  logout(): void {
    this.authService.logout();
  }
}
