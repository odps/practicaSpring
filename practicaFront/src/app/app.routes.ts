import {Routes} from '@angular/router';
import {LoginComponent} from './core/auth/modules/login/login.component';
import {MainComponent} from './features/modules/main/main.component';

export const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'main', component: MainComponent},
  {path: '**', component: LoginComponent}, // Placeholder, por defecto seria pagina de error.
];
