import {Routes} from '@angular/router';
import {LoginComponent} from './core/auth/modules/login/login.component';

export const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: '**', component: LoginComponent}, // Placeholder, por defecto seria pagina de error.
];
