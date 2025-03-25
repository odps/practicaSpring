import {Routes} from '@angular/router';
import {LoginComponent} from './core/auth/modules/login/login.component';
import {MainComponent} from './features/modules/main/main.component';
import {UnauthorizedComponent} from './shared/components/unauthorized/unauthorized.component';
import {hasRoleGuard} from './core/auth/guards/has-role.guard';
import {loggedinGuard} from './core/auth/guards/loggedin.guard';

export const routes: Routes = [
  {path: '', component: LoginComponent, canActivate: [loggedinGuard]},
  {path: 'main', component: MainComponent, canActivate: [hasRoleGuard]},
  {path: 'unauthorized', component: UnauthorizedComponent},
  {path: '**', component: UnauthorizedComponent}, // Placeholder, por defecto seria pagina de error.
];
