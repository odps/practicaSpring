import {CanActivateFn, Router} from '@angular/router';
import {inject} from '@angular/core';

export const loggedinGuard: CanActivateFn = (route, state) => {
  const router: Router = inject(Router);

  if (localStorage.getItem('currentUser') && localStorage.getItem("token")) {
    return router.navigate(['main']);
  }

  return true;
};
