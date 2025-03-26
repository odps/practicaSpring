import {Routes} from '@angular/router';
import {LoginComponent} from './core/auth/modules/login/login.component';
import {UnauthorizedComponent} from './shared/components/unauthorized/unauthorized.component';
import {loggedinGuard} from './core/auth/guards/loggedin.guard';
import {Main2Component} from './features/modules/main2/main2.component';

export const routes: Routes = [
  {path: '', component: LoginComponent, canActivate: [loggedinGuard]},
  // Primera tabla creada. {path: 'main', component: MainComponent, canActivate: [hasRoleGuard]},
  {path: 'main', component: Main2Component},
  {path: 'unauthorized', component: UnauthorizedComponent},
  {path: '**', component: UnauthorizedComponent}, // Placeholder, por defecto seria pagina de error.
];
